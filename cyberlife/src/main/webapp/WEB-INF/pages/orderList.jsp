<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div class="col" id="admin-main">
<table class="table table-striped table-hover">
	<thead>
		<tr>
			
			<th>Order id</th>
			<th>Order status</th>
			<th>Payment status</th>
			<th>Total price</th>
			<th>Date</th> 
			 <sec:authorize access="hasRole('ROLE_ADMIN')">
			<th>Update order</th>
			</sec:authorize>
		
		</tr>
	</thead>
	
	<c:forEach items="${orders}" var="order">
	<tr>
			<td>${order.orderId}</td>
			<td>${order.orderStatus}</td>
			<td>${order.paymentStatus}</td>
			<td>${order.orderPrice}</td>
			<td>${order.orderDate}</td>
		     <sec:authorize access="hasRole('ROLE_ADMIN')">
			<td>
			<form:form modelAttribute="updatedOrder">
			<form:input type="hidden" path="orderId" id="orderId" value="${order.orderId}"/>
		        <form:select id ="orderStatus" path="orderStatus">
				<form:option items="${order.orderStatus}" value="orderStatus"
					itemLabel="orderStatus" />
				<form:option value="awaits delivery"/>
				<form:option value="delivered, awaits pickup"/>
				<form:option value="delivered and recieved"/>
			</form:select>
			<form:select id = "paymentStatus" path="paymentStatus">
				<form:option items="${order.paymentStatus}" value="paymentStatus"
					itemLabel="paymentStatus" />
				<form:option value="unpaid"/>
				<form:option value="paid"/>
			</form:select>
				<button type="submit" id="update" class="btn btn-success">Update order</button>
			</form:form>
		
			</td>
			</sec:authorize>
			
	
			
	</tr>
	</c:forEach>
</table>
</div>
<div>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
			 <a href="<c:url value = "/admin/admin-home" />" class="btn btn-secondary">Back to admin home</a>
	       </sec:authorize>
	        <a href="<c:url value = "/myAccount" />" class="btn btn-secondary">Back to my account</a>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 

</body>
</html>