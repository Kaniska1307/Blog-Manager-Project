<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<div class="wlmsg">
				<h2>Edit Blog Author</h2>
			</div>

			<div class="frm blbx" id="edit-author">
				<h3>#1 Edit Author</h3>
				
				<!-- Error / Success msg div -->
				<c:if test="${(error.errorCode eq 0)}">
						<div class="sumsg">
							<ul>
								<li>${error.getErrorMessage()}</li>
							</ul>
						</div>
					</c:if>
					<c:if test="${(error.errorCode ne 0) && (error.errorCode ne null)}">
						<div class="ermsg">
							<li>${error.getErrorMessage()}</li>
						</div>
					</c:if>

				<p>
				
					<!-- Input for edit form -->
					<label for="author-name">Author Name</label> <select
						name="author-name" id="author-name">
						<option value="select" selected="selected">Select</option>
						<c:forEach var="author" items="${authorDetails}">
							<option value="${author.getAuthorId()}" <c:if test="${(aid eq author.authorId)}"> selected="selected" </c:if> >
								${author.getAuthorName()}</option>
						</c:forEach>
					</select>

				</p>
				<p class="cacn">
					<button id="edit-author-button" value="Edit" class="s-btn">Edit</button> <!-- calls ajax function to autocomplete update author jsp -->
				</p>

			</div>
			
			<!-- Div for update author jsp to be inserted -->
			<div id="update-author"></div>


			<script>
				$("#edit-author-button").click(function() {
					var text = $('#author-name').val();
					$.ajax({
						url : "updateauthor-db.html",
						type : 'POST',
						data : {
							text : text
						},
						success : function(result) {
							$("#update-author").html(result);
							//$(result).insertAfter($("#edit-author"));
						}
					});
				});
				
				 
			</script>
		</section>
	</div>
</div>
