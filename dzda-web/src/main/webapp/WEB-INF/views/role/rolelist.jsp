<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>角色管理</span>
</div>

<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm">
            <label class="for">角色名称</label>
            <input class="ipt" name="roleName" id="roleName" type="text">
            <button type="submit" onclick="queryRole()" class="ubtn ubtn-primary">查询</button>
        </form>
    </div>

    <div class="operate">
        <%--<button type="button" class="ubtn ubtn-cyan" id="modify">修改</button>
        <button type="button" class="ubtn ubtn-cyan" id="del">删除</button>--%>
        <button type="button" onclick="addRole()" class="ubtn ubtn-cyan" id="add">新增</button>
    </div>
    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/hbda/auth/role/jsgl" pagesize="10" >
        <display:setProperty name="basic.show.header" value="true"/>
        <display:column property="name" title="角色名"/>
        <display:column title="操作" >
            <a href="/roleSource?rid=${row.rolesId}" class="ubtn ubtn-primary">权限配置</a>
            <a href="/role/edit?rid=${row.rolesId}" class="ubtn ubtn-primary">修改</a>
        </display:column>
        <display:footer>
        <tr>
            <td></td>
            <td style="text-align: right;">当前显示${row_rowNum+0}条，共${paginatedList.model.count}条</td>
        <tr>
            </display:footer>
            </display:table>
    </div>
<%--<div class="table">
    <table>
        <thead>
        <tr>
            <th>角色名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role">
        <tr>
            <td>${role.name}</td>
            <td>

            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>--%>
<%--<display:table name="paginatedList" requestURI="hbda/auth/role/jsgl" pagesize="10">
    <display:setProperty name="basic.show.header" value="false" />
</display:table>--%>
</div>
<script>
    var queryRole = function () {
        window.location = "/hbda/auth/role/jsgl?roleName=" + $("#roleName").val();
    }
    function addRole() {
        window.location = "/hbda/auth/role/add";
    }
</script>
