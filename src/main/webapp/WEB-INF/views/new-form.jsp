<%--
  Created by IntelliJ IDEA.
  User: sunba
  Date: 2021/09/20
  Time: 7:21 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>

<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<%--여기에서는 ...8080/servelt-mvc/members/save  로 됨 만약 /save 였으면 바로 8080/save 임 (절대경로)--%>
<form action="save" method="post">
  username: <input type="text" name="username" />
  age: <input type="text" name="age" />
  <button type="submit">전송</button>
</form>
</body>
</html>
