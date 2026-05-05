<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
h3 {
	text-align: right;
}
</style>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<div class="wlmsg">
				<h2>View Blogs Author</h2>
			</div>

			<div class="frm blbx">
				<h3>Filter By</h3>
				<!-- form to filter data -->
				<form action="blog-author-view.html" id="blogAuthor-view">
				
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
					<label for="author-name">Name</label><input type="text"
						name="authorFirstName" id="author-name"> <label
						for="author-email">Email</label><input type="text"
						id="author-email" name="authorEmail"> <label
						for="authorQualification">Qualification</label> <select
						name="authorQualification" id="authorQualification">
						<option value=""><---Select---></option>
						<c:forEach var="qual" items="${qualificationList}">
							<option value="${qual}">${qual}</option>
						</c:forEach>
					</select>
					<div class="cacn">
						<input type="submit" class="s-btn">
					</div>
				</form>
				
			</div>
	
			<!-- For index -->
			<h3>
			<c:forEach var="index" items="${indexList}">
				<a href="blog-author-view.html?author-index=${index }">${index }</a>...
			</c:forEach>
			<a href="blog-author-view.html?">All</a>
			</h3>
			
			<!-- retrieved data is shown below -->
			<table class="tbl">
				<tr>

					<th>First Name</th>
					<th>Middle Name</th>
					<th>Qualification</th>
					<th>Email</th>
					<th>Native</th>
					<th>Action</th>

				</tr>
				<tbody>
					<c:forEach var="authorObj" items="${authorBean}">
						<tr>
							<td>${authorObj.getAuthorFirstName()}</td>
							<td>${authorObj.getAuthorLastName()}</td>
							<td>${authorObj.getAuthorQualification()}</td>
							<td>${authorObj.getAuthorEmail()}</td>
							<td>${authorObj.getAuthorHomeTown()}</td>
							<td><a
								href="blog-authors-edit.html?author-id=${authorObj.getAuthorId()}">Edit
									author </a></td>
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
						<a href="blog-author-view.html?page-no=${pageNo -1}">Previous</a>
					</c:otherwise>
				</c:choose>



				<!-- Condition for Page numbers -->



				<c:forEach var="i" begin="1" end="${Total }">
					<c:choose>



						<c:when test="${pageNo == i}">
							<a class="act" href="blog-author-view.html?page-no=${i}">${i }</a>
						</c:when>
						<c:otherwise>
							<a href="blog-author-view.html?page-no=${i}">${i}</a>
						</c:otherwise>
					</c:choose>



				</c:forEach>



				<!-- Condition for Next button -->
				<c:choose>
					<c:when test="${pageNo == Total}">
						<a class="inact" href="">Next</a>
					</c:when>
					<c:otherwise>
						<a href="blog-author-view.html?page-no=${pageNo+1 }">Next</a>
					</c:otherwise>
				</c:choose>



			</div>
		</section>
	</div>
</div>
<script>
$(document).ready(function() {
    var blogAuthor = [];
    $.getJSON("blog-author-autocomplete.html", function(data, status) {
        for (var i = 0, len = data.length; i < len; i++) {
            blogAuthor.push((data[i]).toString());
        }
        console.log("blog author=" + blogAuthor);
        loadSuggestions(blogAuthor);
    });



    function loadSuggestions(blogAuthor) {
        console.log(blogAuthor);
        $("#author-name").autocomplete({
            lookup : blogAuthor,
            source : blogAuthor
        });
    }



});
</script>
