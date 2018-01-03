<%@ include file="../common/taglib.jsp"%>
<!-- BEGIN SIDEBAR -->
<%--
<div class="sidebar">

<c:forEach items="${menusList}" var="menus" varStatus="status">
    <c:if test="${menus.display==0}">
    <dl>
        <dt>
            <a href="javascript:;">
                <i class="ico ico-${status.index}"></i>
                <span>${menus.key}</span>
                <i class="ico ico-arrow-down"></i>
            </a>
        </dt>
        <dd>
        <c:forEach items="${menus.menuItems}" var="menusSon">
            <c:if test="${menusSon.display==0}">
            <a href="${menusSon.url}"
                    <c:if test="${menusSon.selected==true}">
                        class="current"
                    </c:if>
            >${menusSon.key}</a>
            </c:if>
        </c:forEach>
        </dd>
    </dl>
    </c:if>
</c:forEach>
</div>
--%>


<%--<div class="nav box">--%>
<%--<c:forEach items="${menusList}" var="menus" varStatus="status">
    <c:if test="${menus.display==0}">
    <dl>
        <dt>
            <i class="ico ico-${status.index}"></i>
            <span>${menus.key}</span>
            <i class="arrow"></i>
        </dt>
        <dd>
        <c:forEach items="${menus.menuItems}" var="menusSon">
            <c:if test="${menusSon.display==0}">
            <a href="${menusSon.url}"
                <c:if test="${menusSon.selected==true}">
                    class="current"
                </c:if>
                >${menusSon.key}</a>
            </c:if>
        </c:forEach>
        </dd>
    </dl>
    </c:if>
</c:forEach>--%>

<%--</div>--%>
<!-- END SIDEBAR -->