<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/1
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>测试 List中...</h1>

       <%--获取从Action传来的值--%>
        <ul>

            <%--names可能不会提示直接输入就行--%>
            <c:forEach items="${names}" var="name">
                <li>${name} </li>
            </c:forEach>

        </ul>


</body>
</html>
