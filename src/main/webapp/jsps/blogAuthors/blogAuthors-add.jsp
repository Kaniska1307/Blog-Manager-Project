<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">

			<div class="frm blbx">
			
			<!-- Form to insert author into db -->
				<form action="add-authors-into-db.html">
					<h3>Add Blogs Author</h3>
					
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
						<label for="authorFirstName">First Name <sup class="star">*</sup> </label> <input type="text"
							pattern="^[a-zA-Z]{0,30}$" name="authorFirstName"
							id="authorFirstName" required>
					</p>
					<p>
						<label for="authorLastName">Last Name</label> <input type="text"
							pattern="^[a-zA-Z]{0,30}$" name="authorLastName">
					</p>
					<p>
						<select name="authorQualification" id="authorQualification">
							<c:forEach var="qual" items="${qualificationList}">
								<option value="${qual}">${qual}</option>
							</c:forEach>

						</select> <label for="authorQualification">Qualification</label>

					</p>
					<p>
						<label for="authorEmail">Email</label> <input type="text"
							pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$" name="authorEmail">
					</p>
					<p>
						<label for="authorHomeTown">Home Town</label> <input type="text"
							pattern="^[a-zA-Z]{0,30}$" name="authorHomeTown">
					</p>
					<p>
						<label for="authorPhoneNo">Phone Number</label> <input type="text"
							pattern="^[0-9]*$" name="authorPhoneNo">
					</p>

					<p align="right" class="cacn">
						<button type="reset" class="can">Cancel</button>
						<button type="submit" class="s-btn">Save Author</button>
					</p>
				</form>
			</div>
		</section>
	</div>
</div>

