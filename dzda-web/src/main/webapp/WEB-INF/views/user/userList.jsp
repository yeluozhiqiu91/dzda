<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2017/9/7
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>用户管理</span>
</div>
<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm">
            <label class="for">用户姓名</label>
            <input class="ipt" name="userName" id="userName" type="text">
            <button type="submit" onclick="queryUser()" class="ubtn ubtn-primary">查询</button>
        </form>
    </div>

    <div class="operate">
        <%--<button type="button" class="ubtn ubtn-cyan" id="modify">修改</button>
        <button type="button" class="ubtn ubtn-cyan" id="del">删除</button>--%>
        <button type="button" onclick="addUser()" class="ubtn ubtn-cyan" id="add">新增</button>
    </div>
    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/user/userList" pagesize="10" >
        <display:setProperty name="basic.show.header" value="true"/>
        <display:column property="account" title="用户名"/>
            <display:column property="name" title="姓名"/>
            <display:column property="departmentName" title="部门名称"/>
        <display:column title="操作" >
            <a href="/userRole?uid=${row.userId}" class="ubtn ubtn-primary">权限配置</a>
            <a href="/user/edit?uid=${row.userId}" class="ubtn ubtn-primary">修改</a>
            <button type="button" onclick="resetPassword(${row.userId})" class="ubtn ubtn-primary">重置密码</button>
        </display:column>
        <display:footer>
        <tr>
            <td colspan="4" style="text-align: right;">当前显示${row_rowNum+0}条，共${paginatedList.model.count}条</td>
        <tr>
            </display:footer>
            </display:table>
    </div>
</div>
<script>
    function resetPassword(uid){
        layer.confirm('确认重置密码', {
            yes: function () {
                window.location = "/resetPassword?uid=" + uid;
            }
        });
    }
    var queryUser = function () {
        window.location = "/user/userList?userName=" + $("#userName").val();
    }
    function addUser() {
        window.location = "/user/add";
    }
</script>