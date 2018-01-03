<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>档案查询</span>
</div>

<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm">
            <label class="for">查询时间</label>
            <input class="ipt date" name="startTime" id="queryStartTime" type="text" value="${startTime}" readonly="readonly" style="background-color: white">
            <i>-</i>
            <input class="ipt date" name="endTime" id="queryEndTime" type="text" value="${endTime}" readonly="readonly" style="background-color: white">
            <button type="submit" onclick="queryWtda()" class="ubtn ubtn-primary">查询</button>
            <button type="button" onclick="printWtda()" class="ubtn ubtn-primary">打印</button>
        </form>
    </div>
    <c:if test="${startTime!=null && startTime!='' &&endTime!=null&&endTime!=''}">
    <div id="forPrint">
    <div class="tit">${startTime}——${endTime} 问题档案列表</div>
    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/query/wtdaList" pagesize="10" >
        <display:setProperty name="basic.show.header" value="true"/>
        <display:column title="序号">${row_rowNum}</display:column>
        <display:column property="fileCode" title="档案编号"/>
        <display:column property="businessCode" title="流水号"/>
        <display:column property="qsStatusStr" title="流水缺失状态" />
        <display:column property="qsDetail" title="资料种类缺失明细"/>
        <display:footer>
        <tr>
            <td colspan="7" style="text-align: right;">当前显示${row_rowNum+0}条，共${paginatedList.model.count}条</td>
        <tr>
            </display:footer>
            </display:table>
    </div>
        </c:if>
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
    function queryWtda() {
        if(validTime()==1){
            var param=$("#filterForm").serialize();
            window.location = "/query/wtdaList?"+param;
        }
    }
    function addRole() {
        window.location = "/hbda/auth/role/add";
    }
    function printWtda(){
        var count='${paginatedList.model.count}';
        if(count==0){
            layer.alert("暂无数据，无法打印");
        }else{
            var oldhtml=window.document.body.innerHTML;
            var beforeHtml="<html><head><link rel='stylesheet' href='/assets/css/style.css' /></head><div class='box'>"
            var afterHtml="</div></html>";
            window.document.body.innerHTML=beforeHtml+$("#forPrint").html()+afterHtml;
            window.print();
            window.document.body.innerHTML=oldhtml;
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
            });
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
