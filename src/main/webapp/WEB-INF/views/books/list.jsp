<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>图书清单</h3>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <div class="well well-sm">
        <form method="get" class="form-inline">
            <div class="form-group">
                <input type="text" placeholder="书籍名称" name="bookname" class="form-control" value="${bookname}">
            </div>
            <div class="form-group">
                <select name="type" class="form-control">
                    <option value="">请选择类型</option>
                    <c:forEach items="${types}" var="type">
                        <option value="${type.id}" ${typeid==type.id?'selected':''}>${type.booktype}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select name="pub" class="form-control">
                    <option value="">请选择类型</option>
                    <c:forEach items="${pubs}" var="pub">
                        <option value="${pub.id}" ${pubid==pub.id?'selected':''}>${pub.pubname}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-default">搜索</button>
        </form>

    </div>
    <a href="/books/new" class="btn btn-success">添加新书籍</a>

    <table class="table">
        <thead>
        <tr>
            <td>书名</td>
            <td>作者</td>
            <td>出版社</td>
            <td>价格</td>
            <td>数量</td>
            <td>分类</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.items}">
            <tr>
                <td colspan="7">没有任何数据</td>
            </tr>
        </c:if>
        <c:forEach var="book" items="${page.items}">
            <tr>
                <td>${book.bookname}</td>
                <td>${book.bookauthor}</td>
                <td>${book.publisher.pubname}</td>
                <td>${book.bookprice}</td>
                <td>${book.booknum}</td>
                <td>${book.bookType.booktype}</td>
                <td>
                    <a href="/books/${book.id}">修改</a>
                    <a href="#" rel="${book.id}" class="delLink">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination pull-right" id="page"></ul>
</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>

<script>
    $(function () {
        $("#page").twbsPagination({
            totalPages:${page.totalPages},
            visiblePages: 5,
            first: '首页',
            prev: '上一页',
            next: '下一页',
            last: '末页',
            href: '?p={{number}}'
        });
    });
    $(".delLink").click(function () {
        var id = $(this).attr("rel");
        if (confirm("确定要删除吗？")) {
            window.location.href = "/books/" + id + "/del";
        }
    });
</script>
</body>
</html>
