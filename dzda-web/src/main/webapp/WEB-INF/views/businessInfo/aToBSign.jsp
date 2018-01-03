<link rel="stylesheet" href="assets/css/style.css"/>
<link rel="stylesheet" href="assets/plugins/jedate/jedate.css"/>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .center {
        text-align: center;
    }
</style>
<div class="crumbs">
    <a href="#">机动车纸质档案</a>
    <i>&gt;</i>
    <a href="#">A库转B库交接</a>
    <i>&gt;</i>
    <span>A库转B库签收</span>
</div>

<div class="box" id="findFile">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="#" onsubmit="return false;" novalidate>
            <input class="hide" name="bs" value="1">
            <label class="for">批次号</label>
            <input class="ipt" name="code" type="text" value="${code}">
            <label class="for">箱号</label>
            <input class="ipt" name="box" type="text" value="${box}">
            <label class="for">登记时间</label>
            <input class="ipt date" name="start" id="queryStartTime" type="text" value="${start}">
            <i>-</i>
            <input class="ipt date" name="end" id="queryEndTime" type="text" value="${end}">
            <button type="button" id="selRegister" class="ubtn ubtn-primary">查询</button>
        </form>
    </div>

    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/hbda/businessInfo/aToBSign" pagesize="10"
                       class="hide">
            <display:setProperty name="basic.show.header" value="true"/>
            <display:column title="序号" headerClass="center hidd" class="center">${row_rowNum}</display:column>
            <display:column property="code" title="批次号" headerClass="center hidd" class="center"/>
            <display:column property="registerPersonName" title="登记人" headerClass="center hidd" class="center"/>
            <display:column property="registerDate" title="登记时间" headerClass="center hidd" class="center"/>
            <display:column property="receiverName" title="签收人" headerClass="center hidd" class="center"/>
            <display:column title="交接状态" headerClass="center hidd" class="center">
                <c:if test="${row.jjlx eq 1}">已签收</c:if>
                <c:if test="${row.jjlx eq 2}">签收中</c:if>
                <c:if test="${row.jjlx eq 0}">已登记</c:if>
            </display:column>
            <display:column property="box" title="存放箱子" headerClass="center hidd" class="center"/>
            <display:column title="管理" headerClass="center hidd" class="center">
                <c:if test="${row.jjlx eq 1}"><a class="ubtn ubtn-link"
                                                 onclick="lookDetail('${row.code}');">查看详情</a></c:if>
                <c:if test="${row.jjlx eq 2 or row.jjlx eq 0  }"><a class="ubtn ubtn-link"
                                                                    onclick="signBussiness('${row.code}','${row.deliveryId}','${row.signingId}')">进行签收</a></c:if>
            </display:column>
            <display:footer>
                <td colspan="10" style="text-align: right;">当前显示<span id="rownum">${row_rowNum+0}</span>条，共<span
                        id="rowcount">${paginatedList.model.count}</span>条
                </td>
            </display:footer>
        </display:table>
    </div>
</div>
<div id="lookDetail" hidden class="box">
    <div class="filter">
        <button type="button" class="ubtn ubtn-cyan fr" id="lookDetailBack">返回</button>
    </div>
    <table class="table tc">
        <thead>
        <tr>
            <th>序号</th>
            <th>流水号</th>
            <th>档案编号</th>
            <th>号牌种类</th>
            <th>号牌号码</th>
            <th>车辆识别代号</th>
        </tr>
        </thead>
        <tbody id="lookDetailTbody">
        </tbody>
    </table>
</div>
<div id="signBussiness" hidden class="box">
    <form name="filterForm" id="signBussinessForm" action="#" onsubmit="return false;" novalidate>
        <div class="filter">
            <button type="button" class="ubtn ubtn-cyan fr" id="signBussinessBack">返回</button>
            <label class="for">流水号</label>
            <input class="ipt" id="code" type="text" placeholder="请输入流水号信息，或用扫描枪扫描">
            <button type="button" id="ajaxGetBusinessInfo" class="ubtn ubtn-primary">确定签收</button>
        </div>
    </form>

    <div class="caption" id="signCode" style='font-weight: bold;font-size: large;'></div>
    <table class="table tc">
        <thead>
        <tr>
            <th>序号</th>
            <th>流水号</th>
            <th>档案编号</th>
            <th>号牌种类</th>
            <th>号牌号码</th>
            <th>车辆识别代号</th>
        </tr>
        </thead>
        <tbody id="signBussinessTbody">
        </tbody>
    </table>
</div>
<script>
    var dataArr = new Array();
    var deliveryId=0;
    var signingId=0;
    $(function () {

        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                $("#ajaxGetBusinessInfo").click();
            }
        });

        if ("${bs}" == 1) {
            $("#row").show();
        }
        var start = {
            format: 'YYYY-MM-DD hh:mm:ss',
            maxDate: $.nowDate({DD: 0}), //最大日期
            choosefun: function (elem, datas) {
                end.minDate = datas; //开始日选好后，重置结束日的最小日期
                end.trigger = false;
                $("#queryEndTime").jeDate(end);
            }
        };
        var end = {
            format: 'YYYY-MM-DD hh:mm:ss',
            maxDate: $.nowDate({DD: 0}), //最大日期
            choosefun: function (elem, datas) {
                start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
            }
        };
        $("#queryStartTime").jeDate(start);
        $("#queryEndTime").jeDate(end);

        $("#selRegister").click(function () {
            window.location = "${pageContext.request.contextPath}/hbda/businessInfo/aToBSign?" + $("#filterForm").serialize();
        })

        $("#lookDetailBack").click(function () {
            $("#findFile").show();
            $("#lookDetail").hide();
            $("#lookDetailTbody").html("");
        })
        $("#signBussinessBack").click(function () {
            $("#findFile").show();
            $("#signBussiness").hide();
            $(".showBussiness").html("");
            $("#signCode").html("");
            dataArr = new Array();
            deliveryId=0;
            signingId=0;
        })

        $("#ajaxGetBusinessInfo").click(function () {
            var code = $("#code").val();
            if (code != "" && code != null) {
                fx.loading();
                if (code.length == 11) {
                    code = code.substring(1, 11);
                    code = "1A" + code + 0;
                }
                var make=0;
                for (var k = 0; k < dataArr.length; k++) {
                    if (dataArr[k].code == code) {
                        make ++;
                        break;
                    }
                }
                if(make>0){
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/hbda/businessInfo/signBusinessInfoByLsh",//路径
                        data: {"code": code,"deliveryId":deliveryId,"signingId":signingId},
                        dataType: "json",
                        success: function (result) {
                            if (result.result == "null") {
                                fx.alert("该批次无此流水");
                            }  else if (result.result == "error") {
                                fx.alert("查询异常");
                            } else if (result.result == "data") {
                                var data = result.data;
                                $("#signBussinessTbody").html("");
                                for (var i = 0; i < dataArr.length; i++) {
                                    if(dataArr[i].code==data.code){
                                        dataArr.splice(i,1);
                                        i --;
                                    }else {
                                        $("#signBussinessTbody").append("<tr class='showBussiness'><td>" + (i + 1) + "</td><td>" + dataArr[i].code + "</td>" +
                                                "<td>" + dataArr[i].fileCode + "</td><td>" + dataArr[i].plateType + "</td>" +
                                                "<td>" + dataArr[i].plateCode + "</td><td>" + dataArr[i].carCode + "</td></tr>");
                                    }
                                }
                                $("#signCode").html(" <span>"+data.code+"</span> <em style='color: blue'>&nbsp;&nbsp;已签收</em>");
                            }else if (result.result == "OK") {
                                fx.alert("签收成功!");
                                $("#signCode").html("");
                                window.setTimeout(function(){
                                    location.reload(this);
                                },500);
                            }
                            $("#code").val("");
                            fx.closeLoading();
                        }, error: function () {
                            fx.closeLoading();
                            fx.alert("数据加载失败");
                        }
                    })
                }else {
                    fx.alert("请确认此流水是否签收或是否存在该批次!");
                }
            }
        })
    })

    function lookDetail(code) {
        $("#findFile").hide();
        $("#lookDetail").show();
        fx.loading();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/hbda/businessInfo/ajaxLookSignDetailByCode",//路径
            data: {"code": code,"bs":"lookDetail"},
            dataType: "json",
            success: function (result) {
                var data = result.data;
                for (var i = 0; i < data.length; i++) {
                    $("#lookDetailTbody").append("<tr><td>" + (i + 1) + "</td><td>" + data[i].code + "</td>" +
                            "<td>" + data[i].fileCode + "</td><td>" + data[i].plateType + "</td>" +
                            "<td>" + data[i].plateCode + "</td><td>" + data[i].carCode + "</td></tr>");
                }
                fx.closeLoading();
            }, error: function () {
                fx.alert("数据加载失败");
                fx.closeLoading();
            }
        })
    }

    function signBussiness(code,deliveryId,signingId) {
        this.deliveryId=deliveryId;
        this.signingId=signingId;
        $("#findFile").hide();
        $("#signBussiness").show();
        fx.loading();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/hbda/businessInfo/ajaxLookSignDetailByCode",//路径
            data: {"code": code,"bs":"signBussiness"},
            dataType: "json",
            success: function (result) {
                var data = result.data;
                for (var i = 0; i < data.length; i++) {
                    $("#signBussinessTbody").append("<tr class='showBussiness'><td>" + (i + 1) + "</td><td>" + data[i].code + "</td>" +
                            "<td>" + data[i].fileCode + "</td><td>" + data[i].plateType + "</td>" +
                            "<td>" + data[i].plateCode + "</td><td>" + data[i].carCode + "</td></tr>");

                    dataArr.push(data[i]);
                }
                fx.closeLoading();
            }, error: function () {
                fx.alert("数据加载失败");
                fx.closeLoading();
            }
        })
    }
</script>