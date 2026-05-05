<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="frm blbx" id="update-author">
	<form action="update-cat-into-db.html">
		<h3>#2 Update Category</h3>
		<input type="hidden" name="categoryId" id="categoryId"
			value="${categoryObj.getCategoryId()}"> <label
			for="categroryName">Catergory Name</label> <input type="text"
			id="categoryName" name="categoryName"
			value="${categoryObj.getCategoryName()}"> <label for="status">Status</label>
		<select id="categoryStatus" name="categoryStatus">
			<option>--Please Select--</option>
			<option value="LIVE"
				<c:if test="${(categoryObj.categoryStatus eq 'Live')}"> selected="selected" </c:if>>Live</option>
			<option value="DELETED"
				<c:if test="${(categoryObj.categoryStatus eq 'Deleted')}"> selected="selected" </c:if>>Deleted</option>

		</select>


		<p class="cacn">
			<button type="reset" class="can">Cancel</button>
			<button type="submit" class="s-btn">Update</button>
		</p>


	</form>
</div>