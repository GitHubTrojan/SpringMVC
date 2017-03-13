<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/4 0004
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JMS-Producer</title>
</head>
<body>
<h3>JMS-Producer!!!</h3>
<form action="onsend" method="post">

    MessageText:<textarea name="message">${time }</textarea>

    <input type="submit" value="提交" />
</form>
<h4><a href="welcome">返回主页</a></h4>
</body>
</html>
