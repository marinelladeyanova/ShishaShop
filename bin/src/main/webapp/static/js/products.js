
document.getElementById("productsView").addEventListener("keyup", function() {
    var sub = this.value;
    console.log(sub);

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/categories?sub=" + sub, true);
    xhr.send(null);



    xhr.addEventListener("load", function() {
        var products = JSON.parse(xhr.responseText);
   		var html = '';
    	for (var index=0; index < products.length; index++) {
        	html += "<p> " + products[index].name + " </p>";
    	}
    	document.getElementById('productsResult').innerHTML = html;
	});


});