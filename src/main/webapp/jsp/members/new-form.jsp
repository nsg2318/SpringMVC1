<%--
  Created by IntelliJ IDEA.
  User: sunba
  Date: 2021/09/20
  Time: 11:54 오전
  To change this template use File | Settings | File Templates.
--%>

<%--이게 JSP 파일입니다 라는 뜻의 1개 라인--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username" />
    age:      <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
