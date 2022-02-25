<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
    <%@include file="../common/head.jsp"%>

    <script type="text/javascript">
        $(function (){
            //返回首页
            $("#toindex").click(function (){
                location="index.jsp";
            });

            //修改表单提交点击事件
            $("#submit_update").click(function (){

                if($("#upload_update").val()==""){
                    $("#flag").val("false");
                }else{
                    $("#flag").val("true");
                }
                return confirm("您确定要更改图书的信息吗？");
            });
            //上架提交点击事件
            $("#submit_add").click(function (){
            });
            $("#submit_add").click(function (){
                if($("#upload_add").val()==""||$("#book_name").val()==""||$("#book_author").val()==""||$("#book_publisher").val()==""||$("#book_lenduser_id").val()==""||$("#book_type").val()==""){
                    alert("请将带星号项填写完整!!!")
                    return false;
                }
            });
            mes_borrow();
            mes_back();
        });
    </script>
    <style>
        * {
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
    <%--静态包含管理员菜单jsp--%>
    <%@include file="../common/manager_menubar.jsp"%>

<div id="table">
    <form action="bookServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="${empty param.id ? "addBook" :"updateBook"}">
        <input type="hidden" name="pageNo" value=${param.pageNo}>
        <input type="hidden" name="oldPath" value="${requestScope.oldPath}">
        <input type="hidden" name="book_id" value="${requestScope.book.book_id}">
        <input id="flag" type="hidden" name="selectFlag">
            <c:if test="${requestScope.book==null}">
                <div class="table_mark">添加图书
                    <input class="back" type="button" onclick="back()" value="返回上一步">
                </div>
        <table id="books_table" >
                <tr>
                    <td>封面*</td>
                    <td>书名*</td>
                    <td>作者*</td>
                    <td>出版社*</td>
                    <td>图书类别*</td>
                    <td>书主id*</td>
                    <td>操作</td>
                </tr>
                <tr>
                    <td><input id="upload_add" name="img_path" type="file"/></td>
                    <td><input id="book_name" name="book_name" type="text"/></td>
                    <td><input id="book_author" name="book_author" type="text"/></td>
                    <td><input id="book_publisher" name="book_publisher" type="text"/></td>
                    <td>
                        <select id="book_type" name="book_type">
                            <option value="计算机科学">计算机科学</option>
                            <option value="文学">文学</option>
                            <option value="自然科学">自然科学</option>
                            <option value="工程技术">工程技术</option>
                            <option value="艺术">艺术</option>
                            <option value="思想政治">思想政治</option>
                        </select>
                    </td>
                    <td><input id="book_lenduser_id" name="book_lenduser_id" type="text"/></td>
                    <td><input id="submit_add" type="submit" value="提交" /></td>
                </tr>
        </table>
            </c:if>
            <c:if test="${requestScope.book!=null}">
                <div class="table_mark">修改图书
                    <input class="back" type="button" onclick="back()" value="返回上一步">
                </div>
                <table id="books_table" >
                <tr>
                    <td>封面</td>
                    <td>书名</td>
                    <td>作者</td>
                    <td>出版社</td>
                    <td>书主</td>
                    <td>图书类型</td>
                    <td>操作</td>
                </tr>
                <tr>
                    <td><input id="upload_update" name="img_path" type="file" /></td>
                    <td><input type="text" name="book_name" value="${requestScope.book.book_name}"></td>
                    <td><input type="text" name="book_author" value="${requestScope.book.book_author}"></td>
                    <td><input type="text" name="book_publisher" value="${requestScope.book.book_publisher}"></td>
                    <td><input type="text" name="book_lenduser_id" value="${requestScope.book.book_lenduser_id}"></td>
                    <td>
                        <select  name="book_type" >
                            <option value="${requestScope.book.book_type}">${requestScope.book.book_type}</option>
                            <option value="计算机科学">计算机科学</option>
                            <option value="文学">文学</option>
                            <option value="自然科学">自然科学</option>
                            <option value="工程技术">工程技术</option>
                            <option value="艺术">艺术</option>
                            <option value="思想政治">思想政治</option>
                        </select>
                    </td>
                    <%--
                    <td>
                        <select name="book_state">
                            <c:choose>
                                <c:when test="${requestScope.book.book_state eq 'free'}">
                                    <option value="${requestScope.book.book_state}">空闲</option>
                                </c:when>
                                <c:when test="${requestScope.book.book_state eq 'booking'}">
                                    <option value="${requestScope.book.book_state}">已被预约</option>
                                </c:when>
                                <c:when test="${requestScope.book.book_state eq 'borrowing'}">
                                    <option value="${requestScope.book.book_state}">已被借阅</option>
                                </c:when>
                                <c:when test="${requestScope.book.book_state eq 'out'}">
                                    <option value="${requestScope.book.book_state}">已下架</option>
                                </c:when>
                            </c:choose>


                            <c:if test="${requestScope.book.book_state ne 'free'}">
                                <option value="free">空闲</option>
                            </c:if>
                            <c:if test="${requestScope.book.book_state ne 'booking'}">
                                <option value="booking">已被预约</option>
                            </c:if>
                            <c:if test="${requestScope.book.book_state ne 'borrowing'}">
                                <option value="borrowing">已被借阅</option>
                            </c:if>
                            <c:if test="${requestScope.book.book_state ne 'out'}">
                                <option value="out">已下架</option>
                            </c:if>
                        </select>
                    </td>
                    --%>
                    <td><input id="submit_update" type="submit" value="提交" /></td>
                </tr>
        </table>
            </c:if>
    </form>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
