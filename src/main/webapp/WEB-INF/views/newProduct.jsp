<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/Layout/TagLib.jsp"%>
<title>${title}</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body class="animsition">

	<div>
		<h2>${headerMSG}</h2>
	</div>

	<div>
		<c:choose>
			<c:when test='${title=="New Product"}'>
				<spring:url value="/admin/product/add" var="url" />
			</c:when>
			<c:otherwise>
				<spring:url value="/admin/product/edit" var="url" />
			</c:otherwise>
		</c:choose>
		<form:form commandName="product" cssClass="form-horizontal"
			action="${url}" method="POST">

			<c:if test='${title=="Edit Product"}'>
				<form:hidden path="id" class="form-control"></form:hidden>
			</c:if>


			<form:label path="name">Product Name</form:label>
			<form:input type="text" path="name" class="form-control"></form:input>
			<br />

			<form:label path="description">Product Description</form:label>
			<form:input type="text" path="description" class="form-control"></form:input>
			<br />

			<form:label path="price">Product Price</form:label>
			<form:input type="number" path="price" class="form-control"></form:input>
			<br />

			<form:label path="quantity">Product Quantity</form:label>
			<form:input type="number" path="quantity" class="form-control"></form:input>
			<br />

			<form:label path="inStock">Is Product inStock</form:label>
			<form:input type="text" path="inStock" class="form-control"></form:input>
			<br />

			<button type="submit">new Product</button>

		</form:form>
	</div>

</body>
</html>