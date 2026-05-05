/**
 * 
 */
	$(function() {
	hideUpdateBlog();
})
function edit_submit() {
	console.log("edit_submit")
	$("#blogName").prop('disabled',true);
	hideUpdateBlog()
	event.preventDefault();
}
function hideUpdateBlog() {
	var user_name = $("#blogName").val();
	if (user_name == "select") {
		console.log("if")
		document.getElementById("update_block").style.display = 'none'
		return false;
	} else {
		console.log("else")
		document.getElementById("update_block").style.display = 'block'
		return true;
	}
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
                    })
}
