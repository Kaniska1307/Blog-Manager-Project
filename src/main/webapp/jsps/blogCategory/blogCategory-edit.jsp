<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<div class="frm blbx" id="edit-categrory">

				<h3>#1 Edit Category</h3>
				
				<!-- Error / Success msg div -->
				<c:if test="${(error.errorCode eq 0)}">
					<div class="sumsg">
						<ul>
							<li>${error.getErrorMessage()}</li>
						</ul>
					</div>
				</c:if>
				<c:if
					test="${(error.errorCode ne 0) && (error.errorCode ne null)}">
					<div class="ermsg">
					<ul>
						<li>${error.getErrorMessage()}</li>
						</ul>
					</div>
				</c:if>
                <form action="updatecategory-db.html">
				
					<label for="category-name">Category Name</label> <select
						name="category-name" id="category-name">
						<option value="select" selected="selected">Select</option>
						<c:forEach var="category" items="${categoryDetails}">
							<option value="${category.getCategoryId()}"
								<c:if test="${(catId eq category.categoryId)}"> selected="selected" </c:if>>${category.getCategoryName()}</option>
						</c:forEach>
					</select>
			
				<input type="submit" value="Edit">
				</form>


			</div>
			
			<!-- div for update category to be inserted -->
			<div id="update-categrory"></div>
              <c:if test="${updateClick eq true }">
              <%@include file="/jsps/blogCategory/blogCategory-update.jsp" %>
              </c:if>
		</section>
	</div>
</div>

<script>
	
function edit_submit() {
		var text = $('#category-name').val();
		$.ajax({
			url : "updatecategory-db.html",
			type : 'POST',
			data : {
				text : text
			},
			success : function(result) {
				$("#update-categrory").html(result);
				$(result).insertAfter($("#edit-categrory"));

			}
		});
	};
</script>