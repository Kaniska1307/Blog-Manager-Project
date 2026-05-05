<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<style>
.topmenu ul li.active a, .topmenu ul li a:hover {
	font-weight: bold;
	font-color: red;
}
</style>


<aside class="lft-cont rgt">
	<ul>
		<li><a href="blog-add-page.html">Add blogs</a></li>
		<li><a href="blog-edit.html">Edit blogs</a></li>
		<li><a href="blog-view-table.html">View blogs</a></li>
	</ul>
</aside>

<script>
	$(document).ready(function() {
		// it will get the full URL at the address bar
		console.log("get location");
		var url = location.href;
		console.log(url);
		// passes on every "a" tag
		$(".topmenu a").each(function() {
			// checks if its the same on the address bar
			console.log(url);
			if (url == (this.href)) {
				console.log("matched");
				$(this).closest("li").addClass("active");
				//for making parent of submenu active
				$(this).closest("li").parent().parent().addClass("active");
			}
		});
	});
</script>