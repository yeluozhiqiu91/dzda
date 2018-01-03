<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="web.host" var="host"/>
<c:url value="${host}" var="home"/>
<!DOCTYPE html>
<html lang="en">
<tiles:insertAttribute name="head"/>
<body id="body">
<div class="wrapper">
    <%--<tiles:insertAttribute name="headbar"/>--%>
    <div class="header">
        <tiles:insertAttribute name="logo"/>
        <tiles:insertAttribute name="user"/>
        <tiles:insertAttribute name="menu"/>
    </div>
    <!-- BEGIN CONTAINER -->
    <%--<tiles:insertAttribute name="left"/>--%>
    <!-- BEGIN PAGE -->
    <div class="content">
        <tiles:insertAttribute name="content"/>
    </div>
</div>
<tiles:insertAttribute name="footjs"/>
</body>
</html>