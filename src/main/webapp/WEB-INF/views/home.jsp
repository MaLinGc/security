<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/commons/global.jsp" %>
home page
<p><security:authentication property="principal.name"></security:authentication></p>
<security:authorize url="/admin">
    <a href="${path }/admin">Admin</a>
</security:authorize>
<security:authorize url="/user">
    <a href="${path }/user">User</a>
</security:authorize>
<a href="/logout">登出</a>
