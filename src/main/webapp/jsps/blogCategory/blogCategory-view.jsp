<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
		<div class="wlmsg">
				<h2>View Blogs Category</h2>
			</div>
			<div class="frm blbx">
			<h3>Filter By</h3>
			
				<!-- form to filter data -->
				<form action="blog-category-view.html">
				
				<!-- Error / Success msg div -->
					<c:if test="${(error eq 'success')}">
						<div class="sumsg">
							<ul>
								<li>Thank you for your registration.</li>
							</ul>
						</div>
					</c:if>
					<c:if test="${(error ne 'success') && (error ne null)}">
						<div class="ermsg">
							<ul>
								<c:if test="${(error eq 'incorrect name')}">
									<li>Please enter correct name.</li>
								</c:if>
								<c:if test="${(error eq 'incorrect email id')}">
									<li>Please enter valid email id.</li>
								</c:if>
								<c:if test="${(error eq 'incorrect number')}">
									<li>Please enter correct number.</li>
								</c:if>
							</ul>
						</div>
					</c:if>
					<label for="categoryName">Search Category</label> <input
						type="text" name="categoryName" id="categoryName"> <label
						for="categoryIndex">Category Index</label>
						
						<!-- select field for index -->
						<select name="categoryIndex" id="categoryIndex">
						<option value=""%><---Select The Index---></option>
						<c:forEach var="index" items="${indexList}">
							<option
								value="${index}">${index}</option>
						</c:forEach>
					</select>
					<p class="cacn">
						<input type="submit" class="s-btn">
					</p>
				</form>
			</div>
			
			<!-- retrieved data is shown below -->
			<table class="tbl" >
				<tr>

					<th>Blog category name</th>
					<th>Status</th>
					<th>Created date</th>
					<th>Action</th>
				</tr>
				<tbody>
					<c:forEach var="categoryObj" items="${categoryBean}">
						<tr>
							<td>${categoryObj.getCategoryName()}</td>
							<td>${categoryObj.getCategoryStatus()}</td>
							<td>${categoryObj.getCategoryCreatedDate()}</td>
							<td><center><a href="blog-category-edit.html?category-id=${categoryObj.getCategoryId()}">Edit  </a> | <a href="blog-category-view.html?category-id=${categoryObj.getCategoryId()}">View </center> </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pgn" name="pageNo">



				<!-- Condition for Previous button -->
				<c:choose>
					<c:when test="${pageNo == 1}">
						<a class="inact" href="">Previous</a>
					</c:when>
					<c:otherwise>
						<a href="blog-category-view.html?page-no=${pageNo -1}">Previous</a>
					</c:otherwise>
				</c:choose>

				<!-- Condition for Page numbers -->

				<c:forEach var="i" begin="1" end="${Total}">
					<c:choose>
						<c:when test="${pageNo == i}">
							<a class="act" href="blog-category-view.html?page-no=${i}">${i }</a>
						</c:when>
						<c:otherwise>
							<a href="blog-category-view.html?page-no=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- Condition for Next button -->
				<c:choose>
					<c:when test="${pageNo == Total}">
						<a class="inact" href="">Next</a>
					</c:when>
					<c:otherwise>
						<a href="blog-category-view.html?page-no=${pageNo+1}">Next</a>
					</c:otherwise>
				</c:choose>



			</div>

		</section>
	</div>
</div>
