
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="viewPart">
	<div class="frm blbx">
		<form>

			<h3>#${categoryName} posts</h3>
			<div class="detls">
				<div id="errorMsg3"></div>

				<table class="tbl vscrl">
					<tr>
						<th width="200"><input type="button" onclick='selects()'
							value="Select All" /> | <input type="button"
							onclick='deSelect()' value="Deselect All" /></th>
						<th width="100" align="left">Post ID</th>
						<th width="232" align="left">Post Title</th>
						<th width="232" align="left">Action</th>
					</tr>
					<c:forEach var="post" items="${categoryPostList}">
						<tr>
							<td align="center"><input type="checkbox" name="check"
								id="check" value="${post.getPostId()}" /></td>
							<td>${post.getPostId()}</td>
							<td>${post.getHeadlines()}</td>
							<td class="actn"><a
								href="blog-post-view.html?&postId=${post.getPostId()}&headlines=${post.getHeadlines()}">View
									Post</a></td>
						</tr>

					</c:forEach>
				</table>
				<input type="hidden" id="flag" name="flag" value="DELETE"> <input
					type="hidden" id="categoryId2" name="categoryId"
					value="${categoryId}">
				<p class="cacn aut">
					<input type="button" value="Remove" id="remove-submit"
						name="remove-submit" class="s-btn" />
				</p>
			</div>
		</form>
	</div>
	<div class="frm blbx">
		<form>
			<h3>#2. Attach post to ${categoryName}</h3>
			<div class="detls">
				<div id="errorMsg2"></div>
				<p>
					<label>Blog Post name:</label> <select name="postId" id="postId">
						<option value="select">Please select</option>
						<c:forEach var="post" items="${postDropDownList}">
							<option value="${post.getPostId()}">${post.getHeadlines()}</option>
						</c:forEach>
					</select>
				<p class="cacn">
					<input type="reset" value="Cancel" id="cancel" class="can" /> <input
						type="button" id="attach-submit" value="Attach" class="s-btn" />
				</p>
				<p class="cacn">
					<a class="lnk" href="blog-post-add.html">Add new post</a>
				</p>
			</div>
		</form>
	</div>
</div>

<script>
	function selects() {
		var ele = document.getElementsByName('check');
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].type == 'checkbox')
				ele[i].checked = true;
		}
	}
	function deSelect() {
		var ele = document.getElementsByName('check');
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].type == 'checkbox')
				ele[i].checked = false;

		}
	}

	$("#attach-submit").click(function() {
		var categoryId = $('#categoryName').val();
		var categoryName=$('#categoryName').find(":selected").text(); 
		var flag = "INSERT";
		var postId = $("#postId").val();
		if (postId != "select") {
			$("#errorMsg2").html("");
			$("#errorMsg2").removeClass('ermsg');
			$.post("attach-category-action.html", {
				postId : postId,
				flag : flag,
				categoryId : categoryId,
				categoryName : categoryName
			}, function(result, status) {
				 $("#insertdiv").html(result);
			});
		} else {
			$("#errorMsg2").html("Please select value for Post name..");
			$("#errorMsg2").addClass('ermsg');
		}
	});

	$("#remove-submit").click(function() {
		var categoryId = $('#categoryName').val();
		var categoryName=$('#categoryName').find(":selected").text(); 
		var flag = "DELETE";
		var postId = "";
		var ele = document.getElementsByName('check');
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].type == 'checkbox' && ele[i].checked == true)
				postId += ele[i].value + ",";
		}
		if (postId != "") {
			$("#errorMsg3").html("");
			$("#errorMsg3").removeClass('ermsg');
			$.post("attach-category-action.html", {
				postId : postId,
				flag : flag,
				categoryId : categoryId,
				categoryName : categoryName
			}, function(result, status) {
				 $("#insertdiv").html(result);
			});
		} else {
			$("#errorMsg3").html("Please select checkbox to remove posts..");
			$("#errorMsg3").addClass('ermsg');

		}
	});
	$("#cancel").click(function() {
		location.reload();
	});
</script>