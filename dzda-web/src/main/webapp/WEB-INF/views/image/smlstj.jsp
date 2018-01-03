<link rel="stylesheet" href="assets/css/style.css"/>
<link rel="stylesheet" href="assets/plugins/jedate/jedate.css"/>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="crumbs">
    <a href="#">机动车影像</a>
    <i>&gt;</i>
    <a href="#">查询统计</a>
    <i>&gt;</i>
    <span>历史档案扫描流水统计</span>
</div>

<div class="box" id="filterBox">
    <div class="filter">
        <form name="filterForm" id="filterForm"
              novalidate>
            <input class="hide" name="bs" value="1">
            <label class="for">统计时间</label>
            <input class="ipt date" name="startTime" id="queryStartTime" type="text">
            <i>-</i>
            <input class="ipt date" name="endTime" id="queryEndTime" type="text">
            <button type="button" class="ubtn ubtn-primary" id="countImage">统计</button>
        </form>
    </div>

    <div class="table hide" id="countTable">
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>管理部门</th>
                <th>业务类型</th>
                <th>扫描流水数量</th>
                <th>扫描流水总数</th>
                <th>扫描机动车总量</th>
            </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
    </div>
</div>

<!-- 扫描流水明细 start -->
<div class="box box-mini hide"  id="streamNumBox">
    <div class="filter">
        <em>查看扫描流水明细</em>
    </div>
    <div class="table">
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>流水号</th>
                <th>档案编号</th>
                <th>业务类型</th>
                <th>扫描时间</th>
                <th>扫描用户</th>
            </tr>
            </thead>
            <tbody id="streamNumTbody">
            </tbody>
        </table>
    </div>
    <div class="operate">
        <button type="button" onclick="$('#streamNumBox').hide();$('#filterBox').show();" class="ubtn ubtn-cyan">关闭</button>
    </div>
</div>
<!-- 扫描流水明细 end -->

<!-- 扫描档案编号明细 start -->
<div class="box box-mini hide" id="fileCodeDetailBox">
    <div class="filter">
        <em>查看扫描档案编号明细</em>
    </div>
    <div class="table">
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>档案编号</th>
                <th>存储箱子</th>
            </tr>
            </thead>
            <tbody id="fileCodeDetailTbody">
            </tbody>
        </table>
    </div>
    <div class="operate">
        <button type="button" onclick="$('#fileCodeDetailBox').hide();$('#filterBox').show();"  class="ubtn ubtn-cyan">关闭</button>
    </div>
</div>
<script>
    $(function () {

        var start = {
            format: 'YYYY-MM-DD hh:mm',
            maxDate: $.nowDate({DD: 0}), //最大日期
            choosefun: function (elem, datas) {
                end.minDate = datas; //开始日选好后，重置结束日的最小日期
                end.trigger = false;
                $("#queryEndTime").jeDate(end);
            }
        };
        var end = {
            format: 'YYYY-MM-DD hh:mm',
            maxDate: $.nowDate({DD: 0}), //最大日期
            choosefun: function (elem, datas) {
                start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
            }
        };
        $("#queryStartTime").jeDate(start);
        $("#queryEndTime").jeDate(end);

        $("#countImage").click(function () {
            var start = $("#queryStartTime").val();
            var end = $("#queryEndTime").val();

            if (start != null && start != "" && end != null && end != "") {
                fx.loading();
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/hbda/auth/Image/ajaxSmlstj",//路径
                    data: {"startTime": start,"endTime": end},
                    dataType: "json",
                    success: function (result) {
                        $("#tbody").html("");
                        $("#countTable").show();
                        fx.closeLoading();
                        if(result.result=="OK"){
                            var data = result.data;
                            var manageDept = data.manageDept;
                            var businessType = data.businessType;
                            var businessNum = data.businessNum;
                            var businessCount = data.businessCount;
                            var driverCount = data.driverCount;
                            var countHtml="";
                            for(var i=0;i<businessType.length;i++){
                                countHtml += "<tr><td>"+(i+1)+"</td>";
                                if(i==0){
                                    countHtml += "<td rowspan='"+businessType.length+"'>"+manageDept+"</td>";
                                }
                                countHtml +="<td>"+businessType[i]+"</td><td>"+businessNum[i]+"</td>";
                                if(i==0){
                                    countHtml += "<td rowspan='"+businessType.length+"'><a href='javascript:;' onclick='streamNumBox(\""+start+"\",\""+end+"\");' class='ubtn ubtn-link'>"+businessCount+"</a></td>" +
                                            "<td rowspan='"+businessType.length+"'> <a href='javascript:;' onclick='fileCodeDetailBox(\""+start+"\",\""+end+"\");' class='ubtn ubtn-link'>"+driverCount+"</a></td>";
                                }
                                countHtml += "</tr>";
                            }
                            $("#tbody").append(countHtml);
                        }else if(result.result=="NO"){
                            fx.alert("统计异常!");
                        }
                    }, error: function () {
                        fx.closeLoading();
                        fx.alert("数据加载失败");
                    }
                })
            } else {
                if(start == null || start == "" ){
                    fx.alert("请选择开始时间!");
                }else if(end == null || end == ""){
                    fx.alert("请选择结束时间!");
                }
            }
        })
    })

    function streamNumBox(startTime,endTime) {
        fx.loading();
        $("#streamNumTbody").html("");
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/hbda/auth/Image/ajaxStreamNum",//路径
            data: {"startTime": startTime,"endTime": endTime},
            dataType: "json",
            success: function (result) {
                if(result.result=="OK"){
                    $("#filterBox").hide();
                    $("#streamNumBox").show();
                    var data = result.data;
                    fx.closeLoading();
                    for(var i=0;i<data.length;i++){
                        $("#streamNumTbody").append("<tr><td>"+(i+1)+"</td><td>"+data[i].code+"</td><td>"+data[i].fileCode+"</td>" +
                                "<td>"+data[i].businessTypeInfoName+"</td><td>"+data[i].imageDate+"</td><td>"+data[i].userName+"</td></tr>")
                    }
                }else if(result.result=="NO"){
                    fx.alert("数据加载异常!");
                }
                fx.closeLoading();
            }, error: function () {
                fx.closeLoading();
                fx.alert("数据加载失败");
            }
        })
    }

    function fileCodeDetailBox(startTime,endTime) {
        fx.loading();
        $("#fileCodeDetailTbody").html("");
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/hbda/auth/Image/ajaxFileCodeDetail",//路径
            data: {"startTime": startTime,"endTime": endTime},
            dataType: "json",
            success: function (result) {
                if(result.result=="OK"){
                    $("#filterBox").hide();
                    $("#fileCodeDetailBox").show();
                    var data = result.data;
                    fx.closeLoading();
                    for(var i=0;i<data.length;i++){
                        $("#fileCodeDetailTbody").append("<tr><td>"+(i+1)+"</td><td>"+data[i].fileCode+"</td><td>"+(data[i].box==null?"":data[i].box)+"</td></tr>")
                    }
                }else if(result.result=="NO"){
                    fx.alert("数据加载异常!");
                }
                fx.closeLoading();
            }, error: function () {
                fx.closeLoading();
                fx.alert("数据加载失败");
            }
        })
    }
</script>