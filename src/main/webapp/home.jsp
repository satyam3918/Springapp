<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="addEntry">
		<div>Add New Journal Entry :</div><br>
		<div>TaskId :</div> <input type="text" name="taskId"><br>
		<div>TaskName :</div> <input type="text" name="taskName"><br>
		<div>TaskDescription :</div> <input type="text" name="taskDescription"><br>
		<div>TaskDate :</div> <input type="text" name="taskDate"><br>
		<div>CreatedBy :</div> <input type="text" name="createdBy"><br>
		
		<input type="submit">	
	</form><br>
	
	<form action="getEntry">
		<div>Enter the Id to get Specific Entry:</div><br>
		<input type="text" name="taskId"><br>
		
		<input type="submit">
	</form><br>
	
	<form action="deleteEntry">
		<div>Enter the Id to Delete Entry:</div><br>
		<input type="text" name="taskId"><br>
		<input type="submit">
	</form><br>
	

	



	
</body>
</html>