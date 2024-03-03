
        function togglePasswordVisibility() {
            var passwordInput = document.getElementById("passwordInput");
            passwordInput.type = passwordInput.type === "password" ? "text" : "password";
        }

        function validateForm() {
            var passwordInput = document.getElementById("passwordInput");
            var password = passwordInput.value;
            var passwordRegex = /^(?=.*[!@#$%^&*(),.?":{}|<>])(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

            if (!passwordRegex.test(password)) {
                alert("Password must have at least one special character, one uppercase letter, one lowercase letter, and one number");
                return false;
            }

            return true;
        }
