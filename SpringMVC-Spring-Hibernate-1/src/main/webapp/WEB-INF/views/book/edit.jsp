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
            <h4 style="text-align: center">修改书籍</h4>
        </div>
        <form action="" method="post">
            <input type="hidden" class="form-control" name="id" value="${book.id}">
            <div class="form-group">
                <lable>书籍名称</lable>
                <input type="text" class="form-control" name="bookname" value="${book.bookname}">
            </div>
            <div class="form-group">
                <lable>作者</lable>
                <input type="text" class="form-control" name="bookauthor" value="${book.bookauthor}">
            </div>
            <div class="form-group">
                <lable>价格</lable>
                <input type="text" class="form-control" name="bookprice" value="${book.bookprice}">
            </div>
            <div class="form-group">
                <lable>数量</lable>
                <input type="text" class="form-control" name="booknum" value="${book.booknum}">
            </div>
            <div class="form-group">
                <lable>出版社</lable>
                <%--<input type="text" class="form-control" name="pubname">--%>
                <select name="publisher.id" class="form-control">
                    <c:forEach items="${publisherList}" var="publishers">
                        <option ${book.publisher.id==publishers.id ? 'selected':''} value="${publishers.id}">${publishers.pubname}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <lable>类型</lable>
                <%--<input type="text" class="form-control" name="booktype">--%>
                <select name="bookType.id" class="form-control">
                    <c:forEach items="${bookTypeList}" var="booktypes">
                        <option ${book.bookType.id==booktypes.id?'selected':''} value="${booktypes.id}">${booktypes.booktype}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <button class="btn btn-danger pull-right">确认修改</button>
            </div>
        </form>
    </div>

</div>

</body>
</html>
