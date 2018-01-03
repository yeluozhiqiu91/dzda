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
        <form name="filterForm" id="filterForm" action="${pageContext.request.contextPath}/hbda/auth/Image/smrytj" novalidate>
            <input class="hide" name="bs" value="1">
            <label class="for">统计时间</label>
            <input class="ipt date" name="startTime" id="queryStartTime" value="${startTime}" type="text">
            <i>-</i>
            <input class="ipt date" name="endTime" id="queryEndTime" value="${endTime}" type="text">
            <button type="button" class="ubtn ubtn-primary" id="countImage">统计</button>
        </form>
    </div>

    <div class="table hide" id="countTable">
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>管理部门</th>
                <th>扫描员账户</th>
                <th>扫描员姓名</th>
                <th>扫描流水数量</th>
                <th>扫描机动车数量</th>
                <th>扫描图片总数</th>
            </tr>
            </thead>
            <tbody id="tbody">
                <c:forEach items="${userImageCountList}" var="userImageCount" varStatus="i">
                    <tr>
                        <td>${i.index+1}</td>
                        <c:if test="${i.index==0}">
                        <td rowspan="${userImageCountList.size()}">${userImageCount.manageDept}</td>
                        </c:if>
                        <td>${userImageCount.imageUser}</td>
                        <td>${userImageCount.imageUserName}</td>
                        <td><a href="javascript:;" class="ubtn ubtn-link" onclick="businessNumDetail('${startTime}','${endTime}','${userImageCount.userId}','${userImageCount.imageUserName}');">${userImageCount.businessNum}</a></td>
                        <td><a href="javascript:;" class="ubtn ubtn-link" onclick="driverNumDetail('${startTime}','${endTime}','${userImageCount.userId}');">${userImageCount.driverNum}</a></td>
                        <c:if test="${i.index==0}">
                        <td rowspan="${userImageCountList.size()}">${imagePhotoNum}</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- 扫描流水明细 start -->
<div class="box box-mini hide"  id="businessNumBox">
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
            <tbody id="businessNumTbody">
            </tbody>
        </table>
    </div>
    <div class="operate">
        <button type="button" onclick="$('#businessNumBox').hide();$('#filterBox').show();" class="ubtn ubtn-cyan">关闭</button>
    </div>
</div>
<!-- 扫描流水明细 end -->

<!-- 扫描档案编号明细 start -->
<div class="box box-mini hide" id="driverNumDetailBox">
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
            <tbody id="driverNumDetailTbody">
            </tbody>
        </table>
    </div>
    <div class="operate">
        <button type="button" onclick="$('#driverNumDetailBox').hide();$('#filterBox').show();"  class="ubtn ubtn-cyan">关闭</button>
    </div>
</div>
<script>
    if("${bs}"=='1'){
        $("#countTable").show();
    }
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
            if (start != null && start != "" || end != null && end != "") {
                fx.loading();
                $("#filterForm").submit();
            } else {
                fx.alert("请选择统计时间!");
            }
        })
    })

    function businessNumDetail(startTime,endTime,userId,userName) {
        fx.loading();
        $("#businessNumTbody").html("");
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/hbda/auth/Image/ajaxBusinessNumDetail",//路径
            data: {"startTime": startTime,"endTime": endTime,"userId": userId},
            dataType: "json",
            success: function (result) {
                if(result.result=="OK"){
                    $("#filterBox").hide();
                    $("#businessNumBox").show();
                    var data = result.data;
                    for(var i=0;i<data.length;i++){
                        $("#businessNumTbody").append("<tr><td>"+(i+1)+"</td><td>"+data[i].code+"</td><td>"+data[i].fileCode+"</td>" +
                                "<td>"+data[i].businessTypeInfoName+"</td><td>"+(data[i].applicationDate=null?"":data[i].applicationDate)+"</td><td>"+userName+"</td></tr>")
                    }
                }else if(result.result=="NO"){
                    fx.alert("数据加载异常!");
                }
                fx.closeLoading();
            }, error: function () {
                fx.alert("数据加载失败");
                fx.closeLoading();
            }
        })
    }

    function driverNumDetail(startTime,endTime,userId) {
        fx.loading();
        $("#driverNumDetailTbody").html("");
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/hbda/auth/Image/ajaxDriverNumDetail",//路径
            data: {"startTime": startTime,"endTime": endTime,"userId": userId},
            dataType: "json",
            success: function (result) {
                if(result.result=="OK"){
                    $("#filterBox").hide();
                    $("#driverNumDetailBox").show();
                    var data = result.data;
                    for(var i=0;i<data.length;i++){
                        $("#driverNumDetailTbody").append("<tr><td>"+(i+1)+"</td><td>"+(data[i].fileCode==null?"":data[i].fileCode)+"</td><td>"+(data[i].box==null?"":data[i].box)+"</td></tr>")
                    }
                }else if(result.result=="NO"){
                    fx.alert("数据加载异常!");
                }
                fx.closeLoading();
            }, error: function () {
                fx.alert("数据加载失败");
                fx.closeLoading();
            }
        })
    }
</script>