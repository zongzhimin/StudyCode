<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript">
</script>
</head>
<body>
	<form action="http://localhost:8080/AllStudy/socketPushController/login.do" method="post">
		<span>姓名：</span><input type="text" name="name"><br/>
		<input type="submit" value="登录">
	</form>
</body>
</html>