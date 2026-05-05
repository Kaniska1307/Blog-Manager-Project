<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main cntr">
	<div class="cntr1">
		<section class="rgt-cont centr">
			<div class="wlmsg">
				<h2>Add blog posts</h2>
			</div>
			<div id="add" class="frm blbx">
				<c:if test="${(error eq 'success')}">
					<div class="sumsg">
						<ul>
							<li>Post succesfully saved...!</li>
						</ul>
					</div>
				</c:if>


				<div id="errorMsg"></div>
				<c:if test="${(error ne 'success') && (error ne null)}">
					<div class="ermsg">
						<ul>
							<c:if test="${(error eq 'incorrect url')}">
								<li>Please enter valid url..</li>
							</c:if>
							<c:if test="${(error eq 'incorrect date')}">
								<li>Please enter valid date for Start Date (Date should be
									in DD/MM/YYYY format).</li>
							</c:if>
							<c:if test="${(error eq 'incorrect length')}">
								<li>Please Enter PostText less than 4000 characters..</li>
							</c:if>
						</ul>
					</div>
				</c:if>


				<form id="add" action="add-post-form.html" method="post">
					<label for="blogName">Blog Name*:</label> <select name="blogId"
						id="blogName" required>
						<option value="">Please Select</option>
						<c:forEach var="blog" items="${blogList}">
							<option value="${blog.getBlogId()}">${blog.getBlogName()}</option>
						</c:forEach>
					</select><br> <label for="authorName">User name* :</label> <select
						name="authorId" id="authorName" required>
						<option value="">Please Select</option>
						<c:forEach var="author" items="${authorList}">
							<option value="${author.getAuthorId()}">${author.getAuthorName()}</option>
						</c:forEach>

					</select><br> <label for="headlines">Headlines* :</label> <input
						type="text" name="headlines" id="headlines" required><br>
					<label for="postText">Post text* :</label> <input type="text"
						name="postText" id="postText" required><br> <label
						for="postURL">Post URL* :</label> <input type="text"
						name="postURL" id="postURL" required><br> <label
						for="tags">Tag :</label> <input type="text" name="tag" id="tag"><br>
					<label for="startDate">Start date* :</label> <input type="text"
						name="startDate" id="startDate" required><br> <br>
					<p class="cacn">
						<input type="submit" value="Update" onclick="add_submit()"
							class="s-btn" />
						<button onclick="location.href='blog-post-view.html'" class="can" type="button">
         Cancel</button>
					</p>

				</form>
			</div>
		</section>
	</div>
</div>
<script type="text/javascript">
    $(function() {

       $("#blogName").blur(function() {
            validate_blogName();
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
    });



   function checkempty(id, valid, fieldName) {
        var val = $("#" + id).val();
        if (val == "") {
            $("#" + valid).html("Please enter a value for "+fieldName);
            $("#" + valid).addClass('ermsg');
            return true;
        } else {
            $("#" + valid).html("")
            $("#" + valid).removeClass('ermsg');
            return false;
        }



   }



   function add_submit() {

        if (validate_blogName()
                && validate_authorName()
                && validate_headlines()
                && validate_postURL()
                && validate_postText()
                && validate_startDate())
        {
            console.log("validation is done!")
        } else {
            event.preventDefault();
            $("#errorMsg").html("Please enter required details*!")
        }
    }




    function validate_blogName(){
        if(checkempty("blogName", "errorMsg","Blog Name")){return false};
        return true;
    }
    function validate_authorName(){
        if(checkempty("authorName", "errorMsg","User name")){return false;}
        return true;
    }
    
    function validate_headlines(){
        if(checkempty("headlines", "errorMsg","Headlines")) {return false};
        return true;
    }
    
    function validate_postText(){
    	 if(checkempty("postText", "errorMsg","Post Text") || countTextValidation()) {return false};
         return true;
    }
    
    function validate_startDate(){
   	 if(dateValidation()) {return false};
        return true;
   }
    
    function validate_postURL(){
        if(checkempty("postURL", "errorMsg","Post URL") || urlValidation()) {return false};
        return true;
    }
    
    
   
    
    function dateValidation() {
        var date = $("#startDate").val();
        $.post("date-validation.html",
                        {
                            date : date
                        },
                        function(data, status) {
                            if (data == "incorrect date") {
                                $("#errorMsg").html("Please enter valid date for Start Date (Date should be in DD/MM/YYYY format).");
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
        $.post("url-validation.html",
                        {
                            url : url
                        },
                        function(data, status) {
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
        $.post("count-validation.html",
                        {
                            text : text
                        },
                        function(data, status) {
                            if (data == "incorrect length") {
                                $("#errorMsg").html("Please Enter PostText less than 4000 characters.");
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