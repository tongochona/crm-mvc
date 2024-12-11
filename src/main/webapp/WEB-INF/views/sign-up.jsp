<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Sign Up</h2>

        
        <c:if test="${not empty error}">
            <p class="alert alert-danger">${error}</p>
        </c:if>

        <form:form action="admin/sign-up" method="post" modelAttribute="user">
            <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <form:input path="firstName" id="firstName" class="form-control"/>
                <form:errors path="firstName" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <form:input path="lastName" id="lastName" class="form-control"/>
                <form:errors path="lastName" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <form:input path="email" id="email" class="form-control"/>
                <form:errors path="email" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <form:input path="username" id="username" class="form-control"/>
                <form:errors path="username" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <form:password path="password" id="password" class="form-control"/>
                <form:errors path="password" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                <label for="verificationCode" class="form-label">Verification Code</label>
                <input type="text" name="verificationCode" id="verificationCode" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary">Register</button>
        </form:form>
    </div>
</body>
</html>
