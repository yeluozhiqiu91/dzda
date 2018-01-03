<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>金信交接统计</span>
    <a class="ubtn ubtn-cyan fr" href="query/jjList?startTime=${startTime}&endTime=${endTime}" style="float:right">返回</a>
</div>

<div class="box">

    <div class="table">
        <table>
        <thead>
            <tr>
                <th>序号</th>
                <th>管理部门</th>
                <th>交接类型</th>
                <th>流水数量</th>
                <th>档案数量</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${jjtjList==null||jjtjList.size()==0}"><tr><td colspan="5" align="center">暂无数据</td></tr></c:if>
            <c:forEach items="${jjtjList}" var="jjtj" varStatus="status">
                <tr>
                <td>${status.index+1}</td>
                <td>${jjtj.departmentName}</td>
                <td>${jjtj.jjlxStr}</td>
                <td>${jjtj.lsCount}</td>
                <td>${jjtj.daCount}</td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
    </div>
</div>
<script>
    $(function () {
    });
</script>
