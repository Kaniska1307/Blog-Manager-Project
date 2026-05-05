<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">

			<div class="frm blbx">
			
			<!-- Form to insert category into db -->
			<form action="add-category-into-db.html">
				<h3>Add Blogs Category</h3>
				
				<!-- Error / Success msg div -->
				<c:if test="${(error.errorCode eq 0)}">
					<div class="sumsg">
							${error.getErrorMessage()}
					</div>
				</c:if>
				<c:if test="${(error.errorCode ne 0) && (error.errorCode ne null)}">
					<div class="ermsg">
						<li>${error.getErrorMessage()}</li>
					</div>
				</c:if>
					<p>
							<label for ="categoryName">Blog category name</label>
							<input type="text" name="categoryName" id="categoryName">
					</p>
						<p align="right" class="cacn">
							<button type="reset" class="can">Cancel</button>
							<button type="submit" class="s-btn">Save Category</button>
						</p>
				</form>
			</div>
		</section>
	</div>
</div>