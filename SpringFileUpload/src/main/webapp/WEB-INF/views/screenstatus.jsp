<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}

.w3-container {
    padding-top: 50px;
    padding-right: 300px;
    padding-left: 300px;
    padding-bottom : 220px;
    
}
</style>
</head>

<body>
<h2 align="center"> Transaction Status</h2>
<div class="w3-container">
 
  <table class="w3-table-all">
    
      <tr class="w3-hover-red">
        <th>Transaction ID </th>
        <th>${id}</th>
      </tr>
    
    <tr class="w3-hover-blue">
      <td>Payer Name </td>
      <td>${payername}</td>
    </tr>
    
    <tr class="w3-hover-green">
      <td>Payee Name</td>
      <td>${payeename}</td>
    </tr>
    
    <tr class="w3-hover-black">
      <td>Screening Status </td>
      <td><b>${status}</b></td>
    </tr>
    
    <tr class="w3-hover-yellow">
            <td><b>${Reason}</b></td>
            <td><b></b></td>
    </tr>
    
  </table>
</div>

</body>
</html> 
