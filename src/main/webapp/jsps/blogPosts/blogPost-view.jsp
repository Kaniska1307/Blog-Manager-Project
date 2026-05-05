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
</style>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<div class="wlmsg">
				<h2>View blog Post</h2>
			</div>

			<c:if test="${(error eq 'no values')}">
				<div class="ermsg">
					<ul>
						<li>Please enter correct values in the filter..</li>
					</ul>
				</div>
			</c:if>


			<div class="fltr">
				<form action="blog-post-view.html">
					<strong>Filter by:</strong> <label><strong>Blog
							Name</strong></label> <input type="text" name="blogName" id="blogName"> <strong>
						Search Post</strong> <input type="text" name="headlines" id="headlines">
					<input type="submit" value="Submit" class="sbmt" />
				</form>

				<h3>
					<c:forEach items="${indexList}" var="index">
						<a href="blog-post-view.html?post-index=${index }">${index }</a> ... 
                    </c:forEach>
					<a href="blog-post-view.html">All</a>
				</h3>
			</div>
			<table class="tbl">
				<tr>
					<th align="left">Headline</th>
					<th align="left">Blog Name</th>
					<th align="left">User Name</th>
					<th width="10%" align="left">Action</th>
				</tr>
				<c:forEach var="blog" items="${blogPostList}">
					<tr>
						<td>${blog.getHeadlines()}</td>
						<td>${blog.getBlogName()}</td>
						<td>${blog.getAuthorName()}</td>
						<td class="actn"><a
							href="blog-post-edit-indiv.html?blogId=${blog.getBlogId()}&blogName=${blog.getBlogName()}&postId=${blog.getPostId()}&headlines=${blog.getHeadlines()}">Edit
								Post</a><span>|</span><a
							href="blog-post-view.html?&postId=${blog.getPostId()}&headlines=${blog.getHeadlines()}">View
								Blog Post</a><span>|</span> <a href="" class="toggler"
							id="${blog.getPostId()}">More Information</a></td>
					</tr>
					<tr id="post${blog.getPostId()}" style="display: none;">
						<td colspan="5"><b>Post Content</b>: "${blog.getPostText() }"<br>
							<b>Post URL</b>: <a href="${blog.getPostURL()}">Link</a><br>
							<b>Post Start Date</b>: "${blog.getStartDate()}"<br> <b>Post
								Tags</b>: "${blog.getTag()}"<br> <b>Post Status</b>:
							"${blog.getStatus()}"<br></td>
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
						<a href="blog-post-view.html?page-no=${pageNo -1}">Previous</a>
					</c:otherwise>
				</c:choose>



				<!-- Condition for Page numbers -->



				<c:forEach var="i" begin="1" end="${Total }">
					<c:choose>



						<c:when test="${pageNo == i}">
							<a class="act" href="blog-post-view.html?page-no=${i }"
								value=${i }>${i }</a>
						</c:when>
						<c:otherwise>
							<a href="blog-post-view.html?page-no=${i }" value=${i }>${i }</a>
						</c:otherwise>
					</c:choose>



				</c:forEach>



				<!-- Condition for Next button -->
				<c:choose>
					<c:when test="${pageNo == Total}">
						<a class="inact" href="">Next</a>
					</c:when>
					<c:otherwise>
						<a href="blog-post-view.html?page-no=${pageNo+1 }">Next</a>
					</c:otherwise>
				</c:choose>



			</div>
		</section>
	</div>
</div>
<script>
	$(document).ready(function() {
		var change = 1;
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

		$(".toggler").click(function(e) {
			var id = this.id;
			e.preventDefault();
			if (change == 1) {
				$("#post" + id).show();
				document.getElementById(id).textContent = "Less Information";
				change = 0;
			} else {
				$("#post" + id).hide();
				document.getElementById(id).textContent = "More Information";
				change = 1;
			}
		});
	});
</script>