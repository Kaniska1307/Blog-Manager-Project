<%@ page import="java.sql.*"%>
<%@ page import="com.beans.Blog"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
table, td, th {
	border: 1px solid Gold;
	border-collapse: collapse;
	padding-top: 10px;
	padding-bottom: 10px;
	padding-left: 5px;
	padding-right: 10px;
}

th {
	background-color: skyblue;
}

h3 {
	text-align: right;
}

body {
	color: black;
}
</style>
<%@ page import="java.util.ArrayList"%>

<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<div class="wlmsg">
				<h2>View Blogs</h2>
			</div>

			<c:if test="${statusDelete ne null}">
				<c:if test="${statusDelete eq true}">
					<div class="ermsg">
						<ul>

							<li>Blogs with Live status can only be displayed</li>
						</ul>
					</div>
				</c:if>
			</c:if>

			<c:if test="${noBlog ne null}">
				<c:if test="${noBlog eq true}">
					<div class="ermsg">
						<ul>
							<li>Blogs name not found</li>
						</ul>
					</div>
				</c:if>
			</c:if>

			<div class="fltr">

				<form action="blog-view-filter.html">

					<strong>Filter by:</strong> <label><strong>Blog
							Name</strong></label> <input name=blogName id=blogName type="text"> <strong>
						Status</strong> <select name=status>
						<option value=live>Live</option>
						<option value=deleted>Deleted</option>
					</select> <input type="submit" value="Submit" class="sbmt" />
				</form>
				<h3>
					<c:forEach items="${indexList}" var="index">
						<a href="blog-view-table.html?blog-index=${index }">${index }</a> ... 
					</c:forEach>
					<a href="blog-view-table.html">All</a>
				</h3>
				<br>




				<c:choose>
					<c:when test="${postDetailsAvailable ne null}">

						<c:if test="${postDetailsAvailable eq true}">
							<table>
								<tr>
									<th><b>POST_ID</b></th>
									<th><b>HEADLINE</b></th>
									<th><b>BLOG_NAME</b></th>
									<th><b>POST_NAME</b></th>
									<th><b>AUTHOR_NAME</b></th>
									<th><b>CREATED_DATE</b></th>
								</tr>
								<c:forEach items="${blogPostList}" var="blogPost">

									<tr>
										<td>${blogPost.postId}</td>
										<td>${blogPost.headlines}</td>
										<td>${blogPost.blogName}</td>
										<td>${blogPost.postText}</td>
										<td>${blogPost.authorName}</td>
										<td>${blogPost.startDate}</td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${blogListFiltered ne null}">

								<c:if test="${blogListFiltered eq true}">
									<table>
										<tr>
											<th><b>BLOG_NAME</b></th>
											<th><b>STATUS</b></th>
											<th><b>CREATED_DATE</b></th>
											<th><b>ACTION</b></th>
										</tr>

										<c:forEach items="${blogList}" var="blog">

											<tr>
												<td>${blog.blogName}</td>
												<td>${blog.status}</td>
												<td>${blog.createdDate}</td>
												<td><a
													href="blog-edit-indiv.html?blogId=${blog.blogId}&blogName=${blog.blogName}">Edit</a>
													| <a
													href="blog-view-filter.html?blogName=${blog.blogName }&status=${blog.status }">View</a>
													| <a
													href="blog-viewposts-filter.html?blogId=${blog.blogId}&blogName=${blog.blogName}&pageNo=${pageNo}">View
														post </a>
											</tr>
										</c:forEach>
									</table>
									<div class="pgn" name="pageNo">

										<!-- Condition for Previous button -->
										<c:choose>
											<c:when test="${pageNo == 1}">
												<a class="inact" href="">Previous</a>
											</c:when>
											<c:otherwise>
												<a
													href="blog-view-table.html?page-no=${pageNo -1}&blog-index=${blogIndex}">Previous</a>
											</c:otherwise>
										</c:choose>

										<!-- Condition for Page numbers -->

										<c:forEach var="i" begin="1" end="${Total }">
											<c:choose>

												<c:when test="${pageNo == i}">
													<a class="act"
														href="blog-view-table.html?page-no=${i }&blog-index=${blogIndex}"
														value=${i }>${i }</a>
												</c:when>
												<c:otherwise>
													<a
														href="blog-view-table.html?page-no=${i }&blog-index=${blogIndex}"
														value=${i }>${i }</a>
												</c:otherwise>
											</c:choose>

										</c:forEach>

										<!-- Condition for Next button -->
										<c:choose>
											<c:when test="${pageNo == Total}">
												<a class="inact" href="">Next</a>
											</c:when>
											<c:otherwise>
												<a
													href="blog-view-table.html?page-no=${pageNo+1 }&blog-index=${blogIndex}">Next</a>
											</c:otherwise>
										</c:choose>

									</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<table>
									<tr>
										<th><b>BLOG_NAME</b></th>
										<th><b>STATUS</b></th>
										<th><b>CREATED_DATE</b></th>
										<th><b>ACTION</b></th>
									</tr>

									<c:forEach items="${blogList}" var="blog">

										<tr>
											<td>${blog.blogName}</td>
											<td>${blog.status}</td>
											<td>${blog.createdDate}</td>
											<td><a
												href="blog-edit-indiv.html?blogId=${blog.blogId}&blogName=${blog.blogName}">Edit</a>
												| <a
												href="blog-view-filter.html?blogName=${blog.blogName }&status=${blog.status }">View</a>
												| <a
												href="blog-viewposts-filter.html?blogId=${blog.blogId}&blogName=${blog.blogName}&pageNo=${pageNo}">View
													post </a>
										</tr>
									</c:forEach>
								</table>
								<div class="pgn" name="pageNo">

									<!-- Condition for Previous button -->
									<c:choose>
										<c:when test="${pageNo == 1}">
											<a class="inact" href="">Previous</a>
										</c:when>
										<c:otherwise>
											<a href="blog-view-table.html?page-no=${pageNo -1}">Previous</a>
										</c:otherwise>
									</c:choose>

									<!-- Condition for Page numbers -->

									<c:forEach var="i" begin="1" end="${Total }">
										<c:choose>

											<c:when test="${pageNo == i}">
												<a class="act" href="blog-view-table.html?page-no=${i }"
													value=${i }>${i }</a>
											</c:when>
											<c:otherwise>
												<a href="blog-view-table.html?page-no=${i }" value=${i }>${i }</a>
											</c:otherwise>
										</c:choose>

									</c:forEach>

									<!-- Condition for Next button -->
									<c:choose>
										<c:when test="${pageNo == Total}">
											<a class="inact" href="">Next</a>
										</c:when>
										<c:otherwise>
											<a href="blog-view-table.html?page-no=${pageNo+1 }">Next</a>
										</c:otherwise>
									</c:choose>

								</div>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
		</section>
	</div>
</div>
<script>
	$(document).ready(function() {
		var blogNames = [];
		$.getJSON("blogname-autocomplete.html", function(data, status) {
			for (var i = 0, len = data.length; i < len; i++) {
				blogNames.push((data[i]).toString());
			}
			console.log("blog name=" + blogNames);
			loadSuggestions(blogNames);
		});

		function loadSuggestions(blogNames) {
			console.log(blogNames);
			$("#blogName").autocomplete({
				lookup : blogNames,
				source : blogNames
			});
		}

	});
</script>