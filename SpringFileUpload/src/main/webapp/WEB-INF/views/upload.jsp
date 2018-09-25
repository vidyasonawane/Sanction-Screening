<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=file] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button
{
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}


.container1 {
    padding-top: 50px;
    padding-right: 300px;
    padding-left: 300px;
    padding-bottom : 220px;
    
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}
</style>
</head>

<body>

	<form method="POST" action="uploadFile" enctype="multipart/form-data">
	
	<h2 align="right"> ${movie}</h2>
	
	<div class="container1">
	
		
		 
		<br><label for="name"><b>File Name</b></label><br>
    	<input type="text" placeholder="Enter File Name" name="name" required>
		
		<br><label for="file"><b>File to upload</b></label>
    	<input type="file" placeholder="Enter Filename" id="fileuploadid" name="file" required><br>
    	
    	<button type="submit">Upload File</button>
		
	</div>
 	
 	</form>

</body>
</html>