import eventlet
from flask import Flask,render_template
from rq import Queue
from flask_socketio import SocketIO
import redis
#from worker import r

app=Flask(__name__,template_folder='C:\Users\vanda\OneDrive\Desktop\New folder\templates')
app.config["SECRET_KEY"]="abcd123"

r=redis.Redis()
q=Queue(connection=r)
eventlet.monkey_patch()

socketio=SocketIO(
    app,
    async_mode="eventlet",
    logger=True,
    engineio_logger=True,
    allow_upgrades=True,
    cors_allowed_origins="*",
    ping_timeout=10,
    ping_interval=10,
    message_queue="redis://"
)

@socketio.on("connect")
def connect():
    print('@socketio.on("connect")')

def push_notification_job(data):
    socketio.emit("notification_js",data)

@app.route("/",methods=["GET"])
def index():
    return "<h1> Index Page </h1>"

@app.route("/int:<message>",methods=["GET"])
def push_notification(message):
    data={"new_notification":1}
    push=q.enqueue(message)
    return f"<h1> push notification page: {message} </h1>"

@app.route("/notification",methods=["GET"])
def notification():
    return render_template("notification.html")

if __name__=="__main__":
    socketio.run(app,debug=True)
