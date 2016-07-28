<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/28
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="col-xs-6" style="margin-left: 20%">

        <div class="page-header">
            <h4 style="text-align: center">添加书籍</h4>
        </div>
        <form action="" method="post">
            <div class="form-group">
                <lable>书籍名称</lable>
                <input type="text" class="form-control" name="bookname">
            </div>
            <div class="form-group">
                <lable>作者</lable>
                <input type="text" class="form-control" name="bookauthor">
            </div>
            <div class="form-group">
                <lable>价格</lable>
                <input type="text" class="form-control" name="bookprice">
            </div>
            <div class="form-group">
                <lable>数量</lable>
                <input type="text" class="form-control" name="booknum">
            </div>
            <div class="form-group">
                <lable>出版社</lable>
                <%--<input type="text" class="form-control" name="pubname">--%>
                <select name="publisher.id" class="form-control">
                    <option value=""></option>
                    <c:forEach items="${publisherList}" var="publisher">
                        <option value="${publisher.id}">${publisher.pubname}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <lable>类型</lable>
                <%--<input type="text" class="form-control" name="booktype">--%>
                <select name="bookType.id" class="form-control">
                    <option value=""></option>
                    <c:forEach items="${bookTypeList}" var="booktype">
                        <option value="${booktype.id}">${booktype.booktype}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <button class="btn btn-danger pull-right">确认新增</button>
            </div>
        </form>
    </div>

</div>

</body>
</html>
