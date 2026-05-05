<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${(blogEditAuto eq null and not empty postList) }">
	<option value="select">Please select</option>
	<c:forEach var="post" items="${postList}">
		<option value="${post.getPostId()}">${post.getHeadlines()}</option>
	</c:forEach>
</c:if>