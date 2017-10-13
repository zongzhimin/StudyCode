<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function submit(){
		var name = $("#name").val();
		var password = $("#password").val();
		$.post("login/login.do",{name:name,password:password},function(result){
			if("1"==result.code){
				location.href="in/welcome.jsp";
			}else{
				alert("登录失败");
			}
		},"json");
	}

</script>
</head>
<body>
	<span>姓名：</span><input type="text" id="name"><br/>
	<span>密码：</span><input type="password" id="password"><br/>
	<button onclick="submit()">确认</button><br/>
</body>
</html>