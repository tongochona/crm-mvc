<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/stylesheet/styles.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="login-container mx-auto" style="max-width: 400px;">
            <h2 class="text-center mb-4">Login</h2>

            <% 
                String errorMessage = (String) request.getAttribute("loginError");
                if (errorMessage != null) { 
            %>
                <p class="alert alert-danger"><%= errorMessage %></p>
            <% 
                } 
            %>

            <form action="admin/login" method="post">
                <div class="form-group mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" id="username" name="username" class="form-control" required>
                </div>

                <div class="form-group mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>

                <div class="form-group mb-3">
                    <input type="submit" value="Login" class="btn btn-primary w-100">
                </div>
            </form>

            <div class="text-center mt-3">
                <p>Don't have an account? 
                    <a href="admin/sign-up" class="link-primary">Sign Up</a>
                </p>
            </div>
        </div>
    </div>
</body>
</html>
