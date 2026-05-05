<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="frm blbx" id="updateDiv">
	hwllo
	<form action="update-authour-into-db.html">
		<div class="wlmsg">
			<H3>#2 Update Author</H3>
		</div>

		<input type="hidden" name="authorId"
			value="${authorObj.getAuthorId()}">
		<p>
			<label for="authorFirstName">First Name</label> <input type="text"
				pattern="^[a-zA-Z]{0,30}$" name="authorFirstName"
				id="authorFirstName" value="${authorObj.getAuthorFirstName()}">
		</p>
		<p>
			<label for="authorLastName">Last Name</label> <input type="text"
				pattern="^[a-zA-Z]{0,30}$" name="authorLastName"
				value="${authorObj.getAuthorLastName()}">
		</p>
		<p>
			<label for="authorQualification">Qualification</label> 
			<select name="authorQualification" id="authorQualification">
				<c:forEach var="qual" items="${qualificationList}">
					<option value="${qual}"
						<c:if test="${(qual eq authorObj.authorQualification)}"> selected="selected" </c:if>>${qual}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<label for="authorEmail">Email</label> <input type="text"
				pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$" name="authorEmail"
				value="${authorObj.getAuthorEmail()}">
		</p>
		<p>
			<label for="authorHomeTown">Home Town</label> <input type="text"
				pattern="^[a-zA-Z]{0,30}$" name="authorHomeTown"
				value="${authorObj.getAuthorHomeTown()}">
		</p>
		<p>
			<label for="authorPhoneNo">Phone Number</label> <input type="text"
				pattern="^[0-9]{0,10}$" name="authorPhoneNo"
				value="${authorObj.getAuthorPhoneNo()}">
		</p>

		<p align="right" class="cacn">
			<button type="reset" class="can">Cancel</button>
			<button type="submit" class="s-btn">Save Author</button>
		</p>


	</form>
</div>
