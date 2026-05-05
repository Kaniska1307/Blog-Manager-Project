<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<c:if test="${(error eq 'success')}">
				<div class="sumsg">
					<ul>
						<li>Post succesfully updated..</li>
					</ul>
				</div>
			</c:if>
			<div class="frm blbx">
				<div class="wlmsg">
					<h3>#1. Edit Blogs</h3>
				</div>
				<div id="errorMsg1"></div>

				<c:if test="${(error ne 'success') && (error ne null)}">
					<div class="ermsg">
						<ul>
							<c:if test="${(error eq 'incorrect url')}">
								<li>Please enter valid url.</li>
							</c:if>
							<c:if test="${(error eq 'incorrect date')}">
								<li>Please enter valid date in DD/MM/YYYY format.</li>
							</c:if>
						</ul>
					</div>
				</c:if>
				<form>
					<label for="blogName1">Blog Name :</label> <select name="blogName1"
						id="blogName1">
						<c:if test="${(blogEditAuto ne null)}">
							<option value="${blogEditAuto.getBlogId()}" selected>${blogEditAuto.getBlogName()}</option>
						</c:if>
						<c:if test="${(blogEditAuto eq null)}">
							<option value="select">Please select</option>
							<c:forEach var="blog" items="${blogList}">
								<option value="${blog.getBlogId()}">${blog.getBlogName()}</option>
							</c:forEach>
						</c:if>
					</select><br> <br> <label for="postName">Post Name :</label> <select
						name="postName" id="postName">
						<option value="select">Please select</option>
						<c:if test="${(blogEditAuto ne null)}">
							<option value="${blogEditAuto.getPostId()}" selected>${blogEditAuto.getHeadlines()}</option>
						</c:if>

					</select><br> <br>
					<p class="cacn">
						<input type="submit" value="Edit" onclick="edit_submit()"
							class="s-btn" />
					</p>
				</form>
			</div>
			<div id="updateBlog" class="frm blbx">
				<div class="wlmsg">
					<h3 id="updatemsg"></h3>
				</div>

				<c:if test="${(error eq 'success')}">
					<div class="sumsg">
						<ul>
							<li>Post succesfully updated</li>
						</ul>
					</div>
				</c:if>
				<div id="errorMsg"></div>



				<form id="update" action="update-post-form.html">
					<label for="blogName2">Blog Name :</label> <select name="blogId"
						id="blogName2" required>
					</select><br> <label for="authorName">User name :</label> <select
						name="authorId" id="authorName" required>
					</select><br> <label for="headlines">Headlines :</label> <input
						type="text" name="headlines" id="headlines" required><br>
					<label for="postId">Post Id :</label> <input type="text"
						name="postId" id="postId" required><br> <label
						for="postText">Post text :</label> <input type="text"
						name="postText" id="postText"><br> <label
						for="postURL">Post URL :</label> <input type="text" name="postURL"
						id="postURL" required><br> <label for="tags">Tag
						:</label> <input type="text" name="tag" id="tag"><br> <label
						for="startDate">Start date :</label> <input type="text"
						name="startDate" id="startDate" required><br> <br>
					<p class="cacn">
						<input type="submit" onclick="update_submit()" value="Update"
							class="s-btn" /> <input type="submit" value="Cancel"
							onclick="location.href='blog-post-edit.html'" class="can" />
					</p>

				</form>
			</div>
		</section>
	</div>
</div>

<script>
	$(function() {
		hideUpdateUser();
	})
	function edit_submit() {
		console.log("edit_submit")
		if (hideUpdateUser()) {
			var blogId = $("#blogName1").val();
			var postId = $("#postName").val();
			$.getJSON("edit-update-autocomplete.html", {
				postId : postId,
				blogId : blogId
			}, function(data, status) {
				console.log(data);
				$("#blogName1").prop('disabled', true);
				$("#postName").prop('disabled', true);
				$('#blogName2').append($('<option>', {
					value : data[1]
				}).text(data[3]));
				$('#authorName').append($('<option>', {
					value : data[0]
				}).text(data[4]));
				$("#updatemsg").html(" #2. Update " + data[5]);
				document.getElementById("headlines").value = data[5];
				document.getElementById("postId").value = data[2];

				document.getElementById("headlines").readOnly = true;
				document.getElementById("postId").readOnly = true;

			})

			event.preventDefault();
		}
		event.preventDefault();
	}
	
	function hideUpdateUser() {
		var blog_name = $("#blogName1").val();
		var post_name = $("#postName").val();
		if (blog_name == "select" || post_name == "select") {
			document.getElementById("updateBlog").style.display = 'none'
			return false;
		} else {
			console.log("else")
			document.getElementById("updateBlog").style.display = 'block'
			return true;
		}
	}
</script>

<script>
	$(function() {
		$("#blogName1").blur(function() {
			validate_blogName1();
		});
		$("#postName").blur(function() {
			validate_postName();
		});
		$("#blogName2").blur(function() {
			validate_blogName2();
		});
		$("#authorName").blur(function() {
			validate_authorName();
		});
		$("#headlines").blur(function() {
			validate_headlines();
		});
		$("#postURL").blur(function() {
			validate_postURL();
		});
		$("#postText").blur(function() {
			validate_postText();
		});
		$("#startDate").blur(function() {
			validate_startDate();
		});
	})

	function checkempty(id, valid, fieldName) {
		var val = $("#" + id).val();
		if (val == "" || val == "select") {
			$("#" + valid).html("Please enter a value for " + fieldName);
			$("#" + valid).addClass('ermsg');
			return true;
		} else {
			$("#" + valid).html("")
			$("#" + valid).removeClass('ermsg');
			return false;
		}

	}

	function update_submit() {

		if (validate_blogName() && validate_authorName()
				&& validate_headlines() && validate_postURL()
				&& validate_postText() && validate_startDate()) {
			console.log("validation is done!")
		} else {
			event.preventDefault();
			$("#errorMsg").html("Please enter required details*!")
		}
	}

	function validate_blogName1() {
		if (checkempty("blogName1", "errorMsg1", "Blog Name")) {
			return false;
		}
		return true;
	}
	function validate_postName() {
		if (checkempty("postName", "errorMsg1", "Post Name")) {
			return false;
		}
		return true;
	}
	function validate_blogName2() {
		if (checkempty("blogName2", "errorMsg", "Blog Name")) {
			return false;
		}
		
		return true;
	}
	function validate_authorName() {
		if (checkempty("authorName", "errorMsg", "User name")) {
			return false;
		}
		return true;
	}

	function validate_headlines() {
		if (checkempty("headlines", "errorMsg", "Headlines")) {
			return false;
		}
		return true;
	}

	function validate_postText() {
		if (checkempty("postText", "errorMsg", "Post Text")
				|| countTextValidation()) {
			return false;
		}
		return true;
	}

	function validate_startDate() {
		if (dateValidation()) {
			return false;
		}
		return true;
	}

	function validate_postURL() {
		if (checkempty("postURL", "errorMsg", "Post URL") || urlValidation()) {
			return false;
		}
		return true;
	}

	function dateValidation() {
		var date = $("#startDate").val();
		$
				.post(
						"date-validation.html",
						{
							date : date
						},
						function(data, status) {
							if (data == "incorrect date") {
								$("#errorMsg")
										.html(
												"Please enter valid date for Start Date (Date should be in DD/MM/YYYY format).");
								$("#errorMsg").addClass('ermsg');
								return true;
							} else {
								$("#errorMsg").html("");
								$("#errorMsg").removeClass('ermsg');
								return false;
							}
						});
	}

	function urlValidation() {
		var url = $("#postURL").val();
		$.post("url-validation.html", {
			url : url
		}, function(data, status) {
			if (data == "incorrect url") {
				$("#errorMsg").html("Please Enter valid URL");
				$("#errorMsg").addClass('ermsg');
				return true;
			} else {
				$("#errorMsg").html("");
				$("#errorMsg").removeClass('ermsg');
				return false;
			}
		});
	}

	function countTextValidation() {
		var text = $("#postText").val();
		$.post("count-validation.html", {
			text : text
		}, function(data, status) {
			if (data == "incorrect length") {
				$("#errorMsg").html(
						"Please Enter PostText less than 4000 characters.");
				$("#errorMsg").addClass('ermsg');
				return true;
			} else {
				$("#errorMsg").html("");
				$("#errorMsg").removeClass('ermsg');
				return false;
			}
		});
	}

	$("#blogName1").blur(function() {
		var blogId = $("#blogName1").val();
		if (blogId == "select") {
		} else {
			$.ajax({
				url : "load-post.html",
				type : 'POST',
				data : {
					blogId : blogId
				},
				success : function(result) {
					$("#postName").html(result);
				}
			});
		}

	});
</script>