
document
	.getElementById("cityInput")
	.addEventListener("keyup", 	function () {
        var prefix = this.value;
      /*  console.log(prefix);*/


        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/input?prefix=" + prefix, true);
        xhr.send(null);

        xhr.addEventListener("load", function () {
            var cities = JSON.parse(xhr.responseText);
            var html = '';
            for (var index = 0; index < cities.length; index++) {
                html += "<p> " + cities[index] + " </p>";
            }
            document.getElementById('searchResult').innerHTML = html;
        })
    });
