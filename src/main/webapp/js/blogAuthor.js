/**
 * 
 */

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