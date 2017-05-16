<%@ page language="java" import="com.huhang.userlevel.entity.User" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="text-center text-success">
			<h1>Welcome, <%
				User user=(User)request.getSession().getAttribute("user");
				out.write(user.getName());
			%>!</h1>
			<h1><%
				String msg=(String)request.getAttribute("msg");
				if(msg!=null)
					out.write(msg);
			%></h1>
			<div class="success">
				<a href="./logout.scaction" class="btn btn-link btn-lg">Log out</a>
			</div>
		</div>
	</div>
</body>
</html>