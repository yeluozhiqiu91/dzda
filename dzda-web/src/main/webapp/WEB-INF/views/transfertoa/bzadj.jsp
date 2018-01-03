<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="assets/css/style.css"/>
<div class="crumbs">
    <span>机动车纸质档案</span>
    <i>&gt;</i>
    <span>B库转A库交接</span>
    <i>&gt;</i>
    <span>B库转A库登记</span>
</div>
<div class="box" id="select">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="#" onsubmit="return false;" novalidate>
            <button type="button" class="ubtn ubtn-cyan fr" id="confirm" onclick="confirmSubmit()">确认提交</button>
            <label class="for">流水号</label>
            <input class="ipt" id="code" name="code" type="text" placeholder="请输入流水号信息，或用扫描枪扫描">
            <button type="button" class="ubtn ubtn-primary" onclick="findLs()" id="OKButton">确定</button>
            <c:if test="${isTest=='isTest'}"><button type="button" class="ubtn ubtn-primary" onclick="queryAll()" >查询</button></c:if>
        </form>
    </div>
    <div class="table hide">
        <div class="caption">
            <span id="lsh"></span> <em>已登记</em>，共 <i id="countLsh"></i> 条流水，<i id="countDabh"></i>个档案编号登记。
        </div>
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>流水号</th>
                <th>档案编号</th>
                <th>号牌种类</th>
                <th>号牌号码</th>
                <th>车辆识别代号</th>
                <th>管理</th>
            </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </div>

    <div class="operate">
        <div class="pagination"></div>
    </div>
</div>
<%--<div id="printTemp" class="box box-mini hide">
    <div class="filter">
        &lt;%&ndash;<button type="button" class="ico ico-close fr" onclick="closePrintBox()"></button>&ndash;%&gt;
        <button type="button" class="ubtn ubtn-primary" onclick="printPage()">打印</button>
    </div>
    <div id="tablePrint">
        <div class="tit">B库转A库登记表</div>
        <div class="info">
            <div class="date">
                登记时间：${registerDate}<br>
                登记人：${registerPersonName}
            </div>
            <div class="fridgecode">
                批次号：${pcCode}
                <img src="data:image/png;base64,${txm}" class="img" />
            </div>
            <div class="table">
                <table class="tc">
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
                    <tbody id="tbody">
                    <c:if test="${businessInfoList!=null}">
                        <c:forEach var="b" items="${businessInfoList}" varStatus="status">
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${b.code}</td>
                                <td>${b.fileCode}</td>
                                <td>${b.plateType}</td>
                                <td>${b.plateCode}</td>
                                <td>${b.carCode}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>--%>
<script type="text/javascript">
    $(function () {
        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                $("#OKButton").click();
            }
        });
    })
    var dataArr = new Array();
    var countDzda = 0;
    var i = 1;
    function findLs(){
        var code = $("#code").val();
        if (code.length == 11) {
            code = code.substring(1, 11);
            code = "1A" + code + 0;
        }
        $("#code").val("");
        if (code != "" && code != null) {
            for (var k = 0; k < dataArr.length; k++) {
                if (dataArr[k].code == code) {
                    fx.alert("流水【" + code + "】在列表中已存在");
                    return;
                }
            }

        $.ajax({
            type:"post",
            dataType:"json",
            url:"/transfer/findBusinessForDj",
            data: {"lsh":code},
            success:function(data) {
                if(data.code == '0') {
                    $(".table").removeClass("hide");
                    dataArr.push(data.businessInfo);
                    $("#lsh").html(data.businessInfo.code);
                    $("#countLsh").html(dataArr.length);
                    $("#tbody").after("<tr id=\"tr_" + data.businessInfo.code + "\"><td>" + i++ + "</td><td>" + data.businessInfo.code + "</td>" +
                            "<td>" + data.businessInfo.fileCode + "</td><td>" + data.businessInfo.plateTypeStr + "</td>" +
                            "<td>" + data.businessInfo.plateCode + "</td><td>" + data.businessInfo.carCode + "</td>" +
                            "<td><a href=\"javascript:;\" onclick=\"delTr('" + data.businessInfo.code + "');\"  class=\"ubtn ubtn-del\">删除</a></td></tr>");
                    for (var k = 0; k < dataArr.length - 1; k++) {
                        if (dataArr[k].fileCode == data.businessInfo.fileCode) {
                            return;
                        }
                    }

                    $("#countDabh").html(++countDzda);
                }else{
                    // 错误提示信息
                    fx.alert(data.msg);
                }

            }
        });
        }
    }
    function delTr(tr) {
        $("#tr_" + tr).remove();
        var a = new Array();
        for (var k = 0; k < dataArr.length; k++) {
            var j = 0;
            if (dataArr[k].code == tr) {
                dataArr.splice(k, 1);
            }
            for (var i = 0; i < a.length; i++) {
                if (dataArr[k] != null && dataArr[k].fileCode == a[i]) {
                    j++;
                }
            }
            if (j == 0 && dataArr[k] != null) {
                a.push(dataArr[k].fileCode);
            }

        }
        countDzda = a.length;
        $("#countLsh").html(dataArr.length);
        $("#countDabh").html(countDzda);
    }
    function confirmSubmit(){
        if(dataArr.length==0){
            fx.alert("未录入任何流水，无法提交");
            return;
        }
        var arr = JSON.stringify(dataArr);
        $.ajax({
            type: "POST",
            url: "/transfer/bzadj",//路径
            data: {"data": arr},
            dataType: "json",
            success: function (result) {
                if (result.result == "OK") {
                    dataArr = new Array();
                    countDzda = 0;
                    $("#lsh").html("");
                    $("#countLsh").html(dataArr.length);
                    $("#countDabh").html(countDzda);
                    $("[id^='tr_']").remove();
                    $(".table").hide();
                    fx.succeed({
                        title: '提交成功',
                        content: '您提交的批次号：'+result.code,
                        okeyValue: '打印批次号',
                        okey: function() {
                            printpcCode(result.code);
                        }
                    })
                } else if (result.result == "NO") {
                    fx.alert("提交失败");
                }
            }, error: function () {
                fx.alert("数据加载失败");
            }
        })
    }
    function printpcCode(id){
        layer.closeAll();
        /*$("#select").hide();
        $("#printTemp").show();*/
        window.location.href="/transfer/printPcCode?pcCode="+id;
        //window.open("/transfer/printPcCode?pcCode="+id);
    }
    function printPage() {

    }
    function queryAll(){
        window.location.href="/transfer/queryAll";
    }
</script>