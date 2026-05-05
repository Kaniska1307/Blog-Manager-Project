<aside class="lft-cont">
        <div class="topmenu">
			<ul>
				<li><a href="blog-author-view.html">Blog Authors</a></li>
				<li><a href="blog-category-view.html">Blog Categories</a></li>
				<li><a href="blog-view-table.html">Blogs</a></li>
				<li><a href="blog-post-view.html">Blog Posts</a></li>
				<li><a href="attach-category.html">Attach Category</a></li>
			</ul>
			</div>
		</aside>
		
		
<script src="JavaScript/jquery-1.10.2.js" type="text/javascript"></script> 

<script type="text/javascript">
    $(function() {
        // this will get the full URL at the address bar
        var url = window.location.href;

        // passes on every "a" tag
        $(".topmenu a").each(function() {
            // checks if its the same on the address bar
            if (url == (this.href)) {
                $(this).closest("li").addClass("active");
                //for making parent of submenu active
               $(this).closest("li").parent().parent().addClass("active");
            }
        });
    });        
</script>