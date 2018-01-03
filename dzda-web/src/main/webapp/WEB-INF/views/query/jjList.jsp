<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.igool.ssp.web.constants.MyWrapper" %>

<div class="crumbs">
    <span>交接统计</span>
</div>

<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm">
            <label class="for">查询时间</label>
            <input class="ipt date" name="startTime" id="queryStartTime" type="text" value="${startTime}" readonly="readonly" style="background-color: white">
            <i>-</i>
            <input class="ipt date" name="endTime" id="queryEndTime" type="text" value="${endTime}" readonly="readonly" style="background-color: white">
            <button type="button" onclick="queryJjtj()" class="ubtn ubtn-primary">查询</button>
        </form>
    </div>

 <%--   <div class="table">
        <table>
        <thead>
            <tr>
                <th>序号</th>
                <th>管理部门 </th>
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
                    <c:if test="${jjtj.jjlx==2}">
                        <td><a title="查看明细" href="query/jxrwqsmxList?startTime=${startTime}&endTime=${endTime}&jjlx=${jjtj.jjlx}">${jjtj.departmentName}</a></td>
                    </c:if>
                    <c:if test="${jjtj.jjlx!=2}">
                        <td>${jjtj.departmentName}</td>
                    </c:if>
                <td>${jjtj.jjlxStr}</td>
                <td><a title="查看流水明细" href="/query/lsmxList?startTime=${startTime}&endTime=${endTime}&jjlx=${jjtj.jjlx}" >${jjtj.lsCount}</a></td>
                <td><a title="查看档案明细" href="/query/damxList?startTime=${startTime}&endTime=${endTime}&jjlx=${jjtj.jjlx}" >${jjtj.daCount}</a></td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
    </div>--%>

    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/query/jjList" pagesize="10" decorator="com.igool.ssp.web.constants.MyWrapper">
        <display:setProperty name="basic.show.header" value="true"/>
        <display:column property="dateTime" title="时间"/>
        <display:column property="departmentName" title="管理部门"/>
        <display:column property="jjlxStr" title="交接类型" />
        <display:column property="lsCount" title="流水数量"/>
        <display:column property="daCount" title="档案数量"/>
        <display:footer>
        <tr>
            <td colspan="8" style="text-align: right;">当前显示${row_rowNum+0}条，共${paginatedList.model.count}条</td>
        <tr>
            </display:footer>
            </display:table>
    </div>
</div>
<script>
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
    var queryJjtj = function () {
        if(validTime()==1){
//            var t1 = $("#queryStartTime").val();
//            var d1 = t1.replace(/\-/g, "/");
//            var date1 = new Date(d1);
//            var t2 = $("#queryEndTime").val();
//            var d2 = t2.replace(/\-/g, "/");
//            var date2 = new Date(d2);
//            var timeNum=parseInt(date2-date1)/1000/1000;
//            if(timeNum<=1296){
                var param = $("#filterForm").serialize();
                window.location = "/query/jjList?" + param;
//            }else {
//                layer.alert("查询时间跨度过长，请缩短查询时间");
//            }
        }
    }
    function validTime(){
        if($("#queryStartTime").val()==''){
            layer.alert("开始时间不能为空!");
            return 0;
        }else if($("#queryEndTime").val()==''){
            layer.alert("结束时间不能为空!");
            return 0;
        }else{
            return 1;
        }
    }
</script>
