<%@ page import="java.sql.*"%>
<%@ page import="com.beans.BlogAuthors"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<div class="wlmsg">
				<h2>Add Blogs</h2>
			</div>

			<!-- Displaying the status of Add blog -->
			<c:if test="${Added ne null}">
				<c:if test="${Added eq true}">
					<div class="sumsg">
						<ul>

							<li>Thank you for your registration.</li>
						</ul>
					</div>
				</c:if>
			</c:if>



			<!-- Displays the error message of the process-->
			<c:if test="${Added ne null}">
				<c:if test="${Added eq false}">
					<div class="ermsg">
						<ul>

							<li>Blog name already exists</li>
						</ul>
					</div>

				</c:if>
			</c:if>

			<c:if test="${GreaterText ne null}">
				<c:if test="${GreaterText eq true}">
					<div class="ermsg">
						<ul>
							<li>Blog Name length should not be greater than 15
								characters</li>
						</ul>
					</div>
				</c:if>
			</c:if>


			<!-- Form for getting Blog input details -->
			<form action="blog-add.html">
				Blog name:<input type="text" name="blogName"><br> <br>
                <button onclick="location.href='blog-view-table.html'" class="can" type="button">
         Cancel</button>
				<input type="submit" value="Submit">
			</form>
		</section>
	</div>
</div>
<script>
	function countTextValidation() {
		var text = $("#blogName").val();
		$
				.post(
						"count-validation.html",
						{
							text : text
						},
						function(data, status) {
							if (data == "incorrect length") {
								$("#errorMsg")
										.html(
												"Please Enter BlogName less than or equal to 15 characters.");
								$("#errorMsg").addClass('ermsg');
								return true;
							} else {
								$("#errorMsg").html("");
								$("#errorMsg").removeClass('ermsg');
								return false;
							}
						});
	}
</script>