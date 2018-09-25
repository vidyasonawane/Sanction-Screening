<html>
<head>
<title>Upload File Request Page</title>
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
button {
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


.container2 {
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
	
	<form  action="validation" >
		<h2 align="center"> ${movie}</h2>
		
		<div class="container2">
			<label for="uname"><b>Bank Name</b></label>
	    	<input type="text" placeholder="Enter Bank name" name="uname" required>
	
	    	<button type="submit">Proceed Validation and Screening</button>
		</div>
	</form>
	</body>
	
</html>
	
	
	
	