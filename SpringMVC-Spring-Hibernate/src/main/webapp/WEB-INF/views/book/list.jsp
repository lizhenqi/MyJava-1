<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title >Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 style="text-align: center">图书列表</h1>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-warning alert-dismissible fade in" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
            <strong>${message}</strong>
        </div>
    </c:if>

    <a href="/book/new" class="btn btn-info">添加新书</a>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>名称</th>
            <th>作者</th>
            <th>价格</th>
            <th>数量</th>
            <th>出版社</th>
            <th>类型</th>
            <th>#</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${bookList}" var="books">
            <tr>
                <td>${books.id}</td>
                <td>${books.bookname}</td>
                <td>${books.bookauthor}</td>
                <td>${books.bookprice}</td>
                <td>${books.booknum}</td>
                <td>${books.publisher.pubname}</td>
                <td>${books.bookType.booktype}</td>
                <td>
                    <a href="javascript:;" data="${books.id}" class="del">删除</a>
                    <a href="/book/update/${books.id}">修改</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>

</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script>
    $(function(){
        $(".del").click(function(){
            var id=$(this).attr("data");
            if(confirm("确认删除？")){
                window.location.href="/book/del/"+id;
            }
        })

    });



</script>

</body>
</html>












