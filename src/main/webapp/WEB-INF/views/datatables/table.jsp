<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tablelist</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/datatables/css/dataTables.bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="page-header">
        <h3>DataTables list</h3>
    </div>
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
            <button type="button" class="btn btn-default">搜索</button>
        </form>
    </div>

    <a href="/datatabless/new" class="btn btn-success" id="newBookBtn" style="margin-bottom: 20px">添加新书籍</a>

    <table id="datatable" class="table">
        <thead>
        <tr>
            <td>ID</td>
            <td>书名</td>
            <td>作者</td>
            <td>数量</td>
            <td>价格</td>
            <td>出版社</td>
            <td>分类</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>

<%--新增弹出框--%>
<div class="modal fade" id="newBookModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加新书籍</h4>
            </div>
            <div class="modal-body">
                <form id="saveForm">
                    <div class="form-group">
                        <label>书籍名称</label>
                        <input type="text" name="bookname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>书籍作者</label>
                        <input type="text" name="bookauthor" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>书籍价格</label>
                        <input type="text" name="bookprice" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>书籍数量</label>
                        <input type="text" name="booknum" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>分类</label>
                        <select class="form-control" name="typeid">
                            <c:forEach items="${types}" var="type">
                                <option value="${type.id}">${type.booktype}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>出版社</label>
                        <select class="form-control" name="pubid">
                            <c:forEach items="${pubs}" var="pub">
                                <option value="${pub.id}">${pub.pubname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/css/bootstrap.min.css"></script>
<script src="/static/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/js/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function () {
        var dataTable = $("#datatable").DataTable({
            "serverSide": true,
            "order": [0, 'desc'],
            "searching": false,
            "ajax": "/datatables/data.json",
            "lengthMenu": [5, 10, 25, 50, 75, 100],
            "columns": [
                {"data": "id"},
                {"data": "bookname"},
                {"data": "bookauthor"},
                {"data": "bookprice", "name": "bookprice"},
                {"data": "booknum", "name": "booknum"},
                {"data": "publisher.pubname"},
                {"data": "bookType.booktype", "name": "typeid"},
                {
                    "data": function (row) {
                        return "<a href='javascript:;' rel='" + row.id + "' class='editLink'>修改 </a>" +
                                "<a href='javascript:;' rel='" + row.id + "' class='delLink'>删除</a>"
                    }
                }
            ],
            "columnDefs": [
                {targets: [0], visible: false},
                {targets: [1, 2, 5, 7], orderable: false}
            ],
            "language": {
                "search": "请输入书籍名称:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示_START_ 到 _END_ 条数据,共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据筛选)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });

        //添加书籍
        $("#saveForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                bookname: {
                    required: true
                },
                bookauthor: {
                    required: true
                },
                bookprice: {
                    required: true,
                    number: true
                },
                booknum: {
                    required: true,
                    digits: true
                },
                messages: {
                    bookname: {
                        required: "请输入书籍名称"
                    },
                    bookauthor: {
                        required: "请输入作者"
                    },
                    bookprice: {
                        required: "请输入价格",
                        number: "请输入正确的价格"
                    },
                    booknum: {
                        required: "请输入数量",
                        digits: "请输入正确的数量"
                    }
                }
            },
            submitHandler: function (form) {
                $.post("/datatables/new", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                $("#newBookModel").modal("hide");
                                dataTable.ajax.reload();
                            }
                        })
                        .fail(function () {
                            alert("请求出现异常！");
                        });
            }

        });

        $("#newBookBtn").click(function () {
            $("#saveForm")[0].reset();
            $("#newBookModel").modal({
                show:true,
                backdrop:'static',
                keyboard:false
            });
        });

        $("#savaBtn").click(function () {
            $("#saveForm").submit();
        });
    });
</script>
</body>
</html>
