<%@ include file="/WEB-INF/pages/common/header.jsp"%>



<div class="container-fluid">
 <div class="row">
 
 	<sec:authorize
						access="hasRole('ROLE_ADMIN')">
		<div class="col-2 px-1 bg-dark" id="admin-sidebar">
			<div class="py-2 sticky-top">
				<div class="nav flex-column">
				<div class="sidebar-item">
				<a id="stats" href="<c:url value="/admin/stats" />" class="nav-link text-white" class="nav-link text-white"><spring:message code="label.adminStats" /></a>
					<a id="product-list" href="<c:url value="/admin/productList" />" class="nav-link text-white"><spring:message code="label.inventoryList" /></a>
					<a id="add-product" href="<c:url value="/admin/addProduct" />" class="nav-link text-white"><spring:message code="label.addProduct" /></a>
					<a id="add-category" href="<c:url value="/admin/addCategory"/>" class="nav-link text-white"><spring:message code="label.addCategory" /></a>
					<a id="add-cons" href="<c:url value="/admin/addCons"/>" class="nav-link text-white"><spring:message code="label.addAiConfig" /></a>
					<a id="order-status" href="<c:url value="/admin/orderStatus"/>" class="nav-link text-white"><spring:message code="label.orderManagement" /></a>
				</div>
				</div>
			</div>
		</div>
		</sec:authorize>
		
		
	
		
		
		