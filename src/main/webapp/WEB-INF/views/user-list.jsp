<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Management</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
</head>
<body>
	<div class="bg-secondary bg-gradient">
		<div class="d-flex justify-content-between align-items-center p-3">
			<h1 class="pt-3">User Management</h1>
			<div>
				<a class="btn btn-success btn-lg me-2" href="customer/list">Customer List</a> 
				<a class="btn btn-danger btn-lg" href="admin/logout">Logout</a>
			</div>
		</div>
	</div>
	<div class="container mt-5">

		<form class="form-container d-flex mb-5" action="admin/user/search"
			method="GET">
			<input type="text" class="form-control" name="search"
				placeholder="Search (Firstname, Surname, Mail, Username)"
				value="${search}">
			<button type="submit" class="btn btn-primary ms-3">Search</button>
		</form>

		<c:choose>
			<c:when test="${users.size() > 0}">
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col"><a href="admin/user/list?sortBy=1">Firstname</a></th>
							<th scope="col"><a href="admin/user/list?sortBy=2">Lastname</a></th>
							<th scope="col"><a href="admin/user/list?sortBy=3">Email</a></th>
							<th scope="col"><a href="admin/user/list?sortBy=4">Username</a></th>
							<th scope="col"><a href="admin/user/list?sortBy=5">Role</a></th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td>${user.email}</td>
								<td>${user.username}</td>
								<td>${user.role}</td>
								<td><a class="btn btn-danger"
									href="admin/user/delete?id=${user.id}"
									onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<h3 class="text-center">No users found!</h3>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
