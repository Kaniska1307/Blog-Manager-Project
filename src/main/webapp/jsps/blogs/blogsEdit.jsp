<%@ page import="java.sql.*"%>
<%@ page import="com.beans.BlogList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<div class="frm blbx">
				<div class="wlmsg">

					<h3>#1. Edit Blogs</h3>
				</div>

				<c:if test="${result ne null}">
					<c:if test="${result eq true}">
						<div class="sumsg">
							<ul>
								<li>Record Updated</li>
							</ul>
						</div>
					</c:if>
				</c:if>

				<c:if test="${GreaterText ne null}">
					<c:if test="${GreaterText eq true}">
						<div class="ermsg">
							<ul>
								<li>Please enter correct Blog name.</li>

							</ul>
						</div>
					</c:if>
				</c:if>


				<h3>Edit takes</h3>
				<form action="">
					Blog name:<select name="blogId" id="blogName">
						<c:if test="${(blogSetted eq true)}">
							<option value="${blogNameIndiv}" selected>"${blogIdIndiv }-
								${blogNameIndiv }"</option>
						</c:if> 
						<option value="select">Please select the blog</option>
						<c:forEach items="${blogList}" var="blog">
							<option value=${blog.blogName }>"${blog.blogId }-
								${blog.blogName }"</option>
						</c:forEach>
					</select><br> <br> <input type="submit" value="Edit"
						onClick="edit_submit()">
				</form>
			</div>


			<div id="update_block" class="frm blbx">
				<div class="wlmsg">
					<form action="blog-update.html">
						<table class="tbl">
							<h3>#2. Update Blogs</h3>
							</div>
							<div class="ermsg">
								<ul>
									<li>Please enter correct Blog name.</li>

								</ul>
							</div>
							<tr>
								<th>Blog Id</th>
								<td><input type="text" name="blogId" id="blogId"> <br></td>
							<tr>
								<th>Blog name</th>
								<td><input type="text" name="blogNameOne" id="blogNameOne"></td>
							</tr>
							<tr>
								<th>Status</th>
								<td><input type="text" name="status" id="status"></td>
							</tr>
							<tr>
								<td><button onclick="location.href='blog-edit.html'" class="can" type="button">
         Cancel</button></td>
								<td><input type="submit" value="Update Blogs"></td>
							</tr>
						</table>
					</form>
				</div>
		</section>
	</div>
</div>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>

	$(function() {
	hideUpdateBlog();
})
function edit_submit() {
	console.log("edit_submit")
	console.log("edit_submit")
    if(hideUpdateBlog()){
        var blog_name_select = $("#blogName").val();
        console.log(blog_name_select);
        $("#blogName").prop('disabled',true);
        $.getJSON("blog-edit-update.html", {blogName: blog_name_select}, function(data, status){
            var blogId = data[0];
            var blogName = data[1];
            var status = data[2];
            
            console.log(data);
            document.getElementById("blogId").value = blogId;
            document.getElementById("blogId").readOnly = true;
            document.getElementById("blogNameOne").value = blogName;
            //document.getElementById("forename").readOnly = true;
            document.getElementById("status").value = status;
            //document.getElementById("surname").readOnly = true;

        })
    }
	event.preventDefault();
}
function hideUpdateBlog() {
	var user_name = $("#blogName").val();
	if (user_name == "select") {
		console.log("if")
		document.getElementById("update_block").style.display = 'none'
		return false;
	} else {
		console.log("else")
		document.getElementById("update_block").style.display = 'block'
		return true;
	}
}
</script>