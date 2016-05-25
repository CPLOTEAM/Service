<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script>
	    $(document).ready(function(){});
        function SaveBook() {

            var bookData = 
                    { 
            		"firstName": "Sam", 
            		"lastName": "Italy",
            		"emailId":"asssdaa@gmail.com",
            		"password":"asah"
                    };
            $.ajax({
                type: "POST",
                url: "/UserRegistrationService/data/addnewuser",
                data: JSON.stringify(bookData),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                processData: true,
                success: function (data, status, jqXHR) {
                    alert("success" + data);
                },
                error: function (xhr) {
                    alert(xhr.responseText);
                }
            });
        }
        </script>
</head>
<body>
	<h2>Hello World!</h2>
	<button type="button" onclick="SaveBook()">Click Me!</button>
</body>
</html>
