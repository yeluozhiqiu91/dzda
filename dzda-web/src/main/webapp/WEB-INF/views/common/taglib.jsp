<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<spring:message code="web.host" var="host"/>
<spring:message code="static.web.host" var="statichost"/>
<c:url value="${host}" var="home" />
<c:url value="${statichost}" var="statichome" />