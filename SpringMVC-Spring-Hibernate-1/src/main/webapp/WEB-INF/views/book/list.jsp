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


    <div class="well well-sm">
        <form method="get" class="form-inline">
            <div class="form-group">
                <input type="text" placeholder="书籍或作者" class="form-control" value="${q_s_like_bookname_or_author}" name="q_s_like_bookname_or_author">
            </div>
            <div class="form-group">

                <input type="text" placeholder="最低价格" class="form-control" name="q_f_ge_bookprice" value="${q_f_ge_bookprice}">
                --<input type="text" placeholder="最高价格 " class="form-control" name="q_f_le_bookprice" value="${q_f_le_bookprice}">
            </div>
            <%--<div class="form-group">--%>
                <%--<select id="" class="form-control" name="type">--%>
                    <%--<option value="">请选择类型</option>--%>
                    <%--<c:forEach items="${pageList.items}" var="type">--%>
                        <%--<option value="${type.b}" ${typeid==type.id? 'selected':''} >${type.booktype}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>

            <%--<div class="form-group" >--%>
                <%--<select class="form-control" name="pub">--%>
                    <%--<option value="">请选择出版社</option>--%>
                    <%--<c:forEach items="${pageList.items}" var="pub">--%>
                        <%--<option value="${pub.id}" ${pubid==pub.id?'selected':''}>${pub.pubname}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
            <button class="btn btn-default">搜索</button>
        </form>
    </div>




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
        <c:forEach items="${pageList.items}" var="books">
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
    <ul class="pagination pull-right" id="page"></ul>
</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.js"></script>
<script>
    $(function(){
        $(".del").click(function(){
            var id=$(this).attr("data");
            if(confirm("确认删除？")){
                window.location.href="/book/del/"+id;
            }
        })
    });

    //jQuery分页插件
    $(function(){
        $("#page").twbsPagination({
            totalPages:${pageList.totalPage},
            visiblePages:5,
            first:"首页",
            prev:"上一页",
            next:"下一页",
            last:"末页",
            href:"?p={{number}}&q_s_like_bookname=${q_s_like_bookname}&q_f_ge_bookprice=${q_f_ge_bookprice}&q_f_le_bookprice=${q_f_le_bookprice}"
        })
    });


</script>

</body>
</html>












