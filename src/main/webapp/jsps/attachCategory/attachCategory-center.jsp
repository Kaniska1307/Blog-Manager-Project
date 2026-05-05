<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="rgt-cont centr">

	<c:if test="${(status ne null)}">
		<div class="sumsg">
			<ul>
				<li>${status}</li>
			</ul>
		</div>
	</c:if>

	<div class="frm blbx">
		<form>
			<div class="detls">
				<div id="errorMsg1"></div>
				<p>
					<label>Blog category name</label> <select name="categoryName"
						id="categoryName">
						<option value="select">Please select</option>
						<c:forEach var="category" items="${categoryList}">
							<option value="${category.getCategoryId()}">${category.getCategoryName()}</option>
						</c:forEach>
					</select>
				<p class="cacn">
					<input type="button" id="view-submit" value="View" class="s-btn" />
				</p>
			</div>
		</form>
	</div>
	<div id="insertdiv"></div>

</section>
<script type="text/javascript">

$("#view-submit").click(function(){
    var categoryId=$('#categoryName').val();
    var categoryName=$('#categoryName').find(":selected").text(); 
    if (categoryId == "select") {
        $("#errorMsg1").html("Please select value for category name.." );
        $("#errorMsg1").addClass('ermsg');
    }
    else{
    	 $("#errorMsg1").html("" );
         $("#errorMsg1").removeClass('ermsg');
         $("#categoryName").prop('disabled',true);
    	$.ajax({
            url: "show-attachcategory-details.html",
            type: 'POST',
            data: { categoryId:categoryId , categoryName:categoryName },
            success :function(result)
            {            
               $("#insertdiv").html(result);
            }
        });    	
    }  
});

</script>
