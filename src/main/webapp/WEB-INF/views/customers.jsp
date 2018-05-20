<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="/WEB-INF/views/Layout/TagLib.jsp"%>
		<title>Customers</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	<body class="animsition">
		
		<div>
		    <h2>Customer Management</h2>
		</div>
		
		<div>
		    <table>
		        <tr>
		            <td>ID</td>
		            <td>Name</td>
		            <td>Country</td>
		            <td>Email</td>
		            <td>Password</td>
		            <td>Enabled</td>
		        </tr>
		        <c:forEach var="customer" items="${customers}">
		            <tr>
		                <td>${customer.id}</td>
		                <td>${customer.name}</td>
		                <td>${customer.country}</td>
		                <td>${customer.email}</td>
		                <td>${customer.password}</td>
		                <td>${customer.enabled}</td>
		            </tr>
                </c:forEach>
		    </table>
		    
		</div>
		
		
	</body>
</html>