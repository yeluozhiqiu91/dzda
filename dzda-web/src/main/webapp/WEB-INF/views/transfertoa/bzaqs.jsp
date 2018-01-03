<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>机动车纸质档案</span>
    <i>&gt;</i>
    <span>B库转A库交接</span>
    <i>&gt;</i>
    <span>B库转A库签收</span>
</div>
<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="#" onsubmit="return false;" novalidate>
            <label class="for">批次号</label>
            <input class="ipt" name="pcCode" type="text" value="${pcCode}">
            <label class="for">登记时间</label>
            <input class="ipt date" name="startTime" id="queryStartTime" type="text" value="${startTime}" readonly="readonly" style="background-color: white">
            <i>-</i>
            <input class="ipt date" name="endTime" id="queryEndTime" type="text" value="${endTime}" readonly="readonly" style="background-color: white">
            <button type="submit" onclick="query()" class="ubtn ubtn-primary">查询</button>
        </form>
    </div>

    <div class="table hide">
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>批次号</th>
                <th>登记人</th>
                <th>登记时间</th>
                <th>签收人</th>
                <th>交接状态</th>
                <th>存放箱子</th>
                <th>管理</th>
            </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </div>

    <%--<div class="operate">
        <div class="pagination"></div>
    </div>--%>
    <c:if test="${paginatedList.model.count!=0}">
    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/transfer/showBzaqsPage" pagesize="10" >
        <display:setProperty name="basic.show.header" value="true"/>
        <display:column title="序号">${row_rowNum}</display:column>
        <display:column property="code" title="批次号"/>
        <display:column property="registerPersonName" title="登记人"/>
        <display:column property="registerDate" title="登记时间"/>
        <display:column property="signPersonName" title="签收人"/>
        <display:column property="statusStr" title="交接状态"/>
        <display:column title="操作" >
            <c:if test="${row.status eq 1}"><a class="ubtn ubtn-link" href="/transfer/showDeliveryDetail?pcCode=${row.code}">查看详情</a></c:if>
            <c:if test="${row.status eq 0 ||row.status eq 2}"><a class="ubtn ubtn-link"  href="/transfer/bzaqsDetail?pcCode=${row.code}&startTime=${startTime}&endTime=${endTime}">进行签收</a></c:if>
        </display:column>
        <display:footer>
        <tr>
            <td colspan="7" style="text-align: right;">当前显示${row_rowNum+0}条，共${paginatedList.model.count}条</td>
        <tr>
            </display:footer>
            </display:table>
    </div>
    </c:if>
</div>
<script type="text/javascript">
    $(function () {
        $("#queryStartTime").jeDate({
            format:"YYYY-MM-DD hh:mm:ss",
            isTime:true,
            minDate:"2014-09-19 00:00:00",
            maxDate: $.nowDate({DD:0})
        });
        $("#queryEndTime").jeDate({
            format:"YYYY-MM-DD hh:mm:ss",
            isTime:true,
            minDate:"2014-09-19 00:00:00",
            maxDate: $.nowDate({DD:0})
        })
    });
    var query = function () {
        var startTime=$("#queryStartTime").val();
        var endTime=$("#queryEndTime").val();
        if(startTime==''&&endTime==''){
            layer.alert("请至少选择一个时间");
        }else{
            var param=$("#filterForm").serialize();
            window.location = "/transfer/showBzaqsPage?"+param;
        }
    }
</script>