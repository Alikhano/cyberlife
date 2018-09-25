<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>
<link href="<c:url value="/static/css/product.css" />" rel="stylesheet">


<div class="product-container">

		<div class="left-column">
			<img src="${pageContext.request.contextPath}/static/images/${product.model}.jpg"
				alt="image" />
		</div>
		<div class="right-column">
			<h3 class="title mb-3">${product.model}</h3>
			<p>${product.description}</p>
			<p>
				<strong>Category: </strong>${product.category.catType}
			</p>
			<p>
				<strong>Consciousness: </strong>${product.cons.level}
				<br>
				${product.cons.description}
			</p>
			<p>
			<strong>Quantity: </strong>${product.unitsInStock}
			<span id="outWarning" class="alert alert-warning"></span>
			</p>
			<p>
			<strong>Price: </strong>${product.price} USD</p>

			<p>
			<form:form action="${pageContext.request.contextPath}/viewProduct" method="post" modelAttribute="newCartItem" id="newCartItem">
			 <form:input path="itemId" type="hidden" name="itemId" value=""/>
			 <input type='hidden' id='productId' name='productId' value='${product.productId}'/>
			 <input type='hidden' id='unitsInStock' name='unitsInStock' value='${product.unitsInStock}'/>
			 <form:input path="quantity" id="quantity" class="form-Control"/>
			 <input type="submit" id ="addToCart" value="Add to cart" class="btn btn-success">
			 <a href="<c:url value = "/catalogue" />" class="btn btn-secondary">Back</a>
	         </form:form>
			</p>

		</div>
	</div>



<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>	
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var quantity = document.getElementsByName('unitsInStock')[0].value;
	if (quantity == 0) {
		$("#outWarning").text("Oops! Sorry, we're out of stock");
		$("#addToCart").prop( "disabled", true );
	}
	else {
		$("#outWarning").css('display', 'none');
	}

	$("#newCartItem").validate({
		rules : {
			"unitsInStock" : {
				digits: true
			}
		},
		messages : {
			"unitsInStock" : {
				digits: "Entered value shpuld contain only digits"
			}
			}
		})
});
</script>
</body>
</html>