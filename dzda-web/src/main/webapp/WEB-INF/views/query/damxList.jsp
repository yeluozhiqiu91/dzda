<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>查看流水号明细</span>
    <a class="ubtn ubtn-cyan fr" href="/query/jjList?startTime=${startTime}&endTime=${endTime}" style="float:right">返回</a>
</div>
<div class="box">
    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/query/damxList" pagesize="10" >
        <display:setProperty name="basic.show.header" value="true"/>
        <display:column  title="序号">${row_rowNum}</display:column>
        <display:column property="fileCode" title="档案编号" />
        <display:column property="pcCode" title="批次号"/>
        <display:column property="box" title="箱号"/>
        <c:if test="${jjlx==1||jjlx==3}"><display:column property="signDate" title="登记时间"/></c:if><c:if test="${jjlx==2||jjlx==4}"><display:column property="signDate" title="签收时间"/></c:if>
        <display:column property="userName" title="操作人员 "/>
        <display:footer>
        <tr>
            <td colspan="8" style="text-align: right;">当前显示${row_rowNum+0}条，共${paginatedList.model.count}条</td>
        <tr>
            </display:footer>
            </display:table>
    </div>
</div>
