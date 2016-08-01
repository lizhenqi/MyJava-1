<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>测试 UserSave！</h1>

<%--向Action发送值--%>
<form action="/user/save" method="post">

    <%--Action获取jsp传值如果属性少不封装就直接这样--%>
    <%--<input type="text" name="username">--%>
    <%--<input type="text" name="address">--%>

    <%--如果属性多把属性封装在对象里，就得如下（user为Action里面封装的对象）--%>
    <input type="text" name="user.username">
    <input type="text" name="user.address">

    <button>保存</button>
</form>
</body>
</html>
