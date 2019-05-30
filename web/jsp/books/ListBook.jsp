<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="main.css" type="text/css" rel="stylesheet" />
    <title>图书管理</title>
</head>
<body>
<div class="main">
    <h2 class="title"><span>图书管理</span></h2>
    <form action="ListBook.do" method="post">
        <table border="1" width="100%" class="tab">
            <tr>
                <th><input type="checkbox" id="chbAll"></th>
                <th>编号</th>
                <th>书名</th>
                <th>价格</th>
                <th>出版日期</th>
                <th>操作</th>
            </tr>
            <c:forEach var="book" items="${books}">
                <tr>
                    <th><input type="checkbox" name="ids" value="${book.id}"></th>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.price}</td>
                    <td><fmt:formatDate value="${book.publishDate}" pattern="yyyy年MM月dd日"/></td>
                    <td>
                        <a href="Delete.do?id=${book.id}" class="abtn">删除</a>
                        <a href="EditBook.do?id=${book.id}" class="abtn">编辑</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p style="color: red">${message}</p>
        <p>
            <a href="AddBook.do" class="abtn" >添加</a>
            <input type="submit"  value="刷新" class="btn"/>
        </p>
    </form>
</div>
</body>
</html>