<%@page import="by.pvt.jdbc.model.Car"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<br />


	<c:if test="${requestScope.cars != null}">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Num</th>
					<th scope="col">Brand name</th>
					<th scope="col">Model</th>
					<th scope="col">Production time</th>
					<th scope="col">Color</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>

			<c:forEach var="car" items="${requestScope.cars}">
				<tr>
					<td scope="row">${car.id}</td>
					<td>${car.brand.name}</td>
					<td>${car.model}</td>
					<td>${car.dateOfManufacturing}</td>
					<td>${car.color}</td>
					<td><a class="btn btn-danger"
						href="http://localhost:8080/webappsample/car/delete?id=${car.id}">Delete car</a>
						</td>
				</tr>

			</c:forEach>
		</table>
	</c:if>

	<a class="btn btn-primary"
		href="http://localhost:8080/webappsample/car/add.jsp">Add car</a>

</body>
</html>
