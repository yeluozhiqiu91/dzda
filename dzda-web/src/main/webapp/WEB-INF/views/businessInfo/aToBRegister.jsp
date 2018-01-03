<link rel="stylesheet" href="assets/css/style.css"/>
<link rel="stylesheet" href="assets/plugins/jedate/jedate.css"/>

<div class="crumbs">
    <a href="#">机动车纸质档案</a>
    <i>&gt;</i>
    <a href="#">A库转B库交接</a>
    <i>&gt;</i>
    <span>A库转B库登记</span>
</div>

<div class="box" id="select">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="#" onsubmit="return false;" novalidate>
            <button type="button" class="ubtn ubtn-cyan fr" id="comfirm">确认提交</button>
            <label class="for">流水号</label>
            <input class="ipt" id="code" type="text" placeholder="请输入流水号信息，或用扫描枪扫描">
            <button type="button" id="ajaxGetBusinessInfo" class="ubtn ubtn-primary">确定</button>
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

<div id="printTemp" class="box box-mini hide">
    <div class="filter">
        <button type="button" class="ico ico-close fr" onclick="closePrintBox()"></button>
        <button type="button" class="ubtn ubtn-primary" onclick="tablePrint();">打印</button>
    </div>
    <div id="tablePrint">
        <div class="tit">A库转B库登记表</div>
        <div class="info" >
            <div class="date" style="float: right;">
                登记时间：<span id="registerDate"></span> <br>
                登记人：<span id="registerPerson"></span>
            </div>
            <div class="fridgecode">
                批次号：<span id="deliveryCode"></span>
                &nbsp;&nbsp;
                箱号：<span id="box">${box}</span>
                <img src="" class="img" id="barCode"/>
            </div>
        </div>
        <div class="table" id="tablePrintData">
            <table class="tc">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>档案编号</th>
                    <th>号牌种类</th>
                    <th>号牌号码</th>
                    <th>车辆识别代号</th>
                </tr>
                </thead>
                <tbody id="tbodyPrint">
                </tbody>
            </table>
        </div>
    </div>
    <div class="operate">
        <button type="button" class="ubtn ubtn-cyan box-mini-close">关闭</button>
    </div>
</div>

<div class="layer-msg" id="dialogInput">
    <form action="" name="form1" onsubmit="return false;">
        <div class="hd"><i class="ico ico-box"></i>请输入箱号！</div>
        <div class="bd">
            <label>箱号：</label>
            <input type="text" class="ipt" name="box"/>
        </div>
        <div class="ft">
            <button type="button" id="register" class="ubtn ubtn-primary">下一步</button>
            <p style="line-height: 48px;color: red;" hidden id="msgBox"></p>
        </div>
    </form>
</div>

<script>
    var dataArr = new Array();
//    var printArr = new Array();
    var countDzda = 0;
//    var deliveryCode = '';
//    var registerDate = '';
//    var registerPerson = '';
//    var imgSrc = '';

    var i = 1;
    $(function () {
        $("#ajaxGetBusinessInfo").click(function () {
            var code = $("#code").val();
            if (code.length == 11) {
                code = code.substring(1, 11);
                code = "1A" + code + 0;
            }
            $("#code").val("");
            if (code != "" && code != null) {
                fx.loading();
                for (var k = 0; k < dataArr.length; k++) {
                    if (dataArr[k].code == code) {
                        fx.alert("流水【" + code + "】在列表中已存在");
                        fx.closeLoading();
                        return;
                    }
                }
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/hbda/businessInfo/getBusinessInfoByLsh",//路径
                    data: {"code": code},
                    dataType: "json",
                    success: function (result) {
                        if (result.result == "null") {
                            fx.alert("查无该流水");
                        } else if (result.result == "1") {
                            fx.alert("该流水已登记");
                        } else if (result.result == "2") {
                            fx.alert("该流水已在B库");
                        } else if (result.result == "error") {
                            fx.alert("查询异常");
                        } else if (result.result == "data") {
                            $(".table").show();
                            dataArr.push(result.data);

                            $("#lsh").html(result.data.code);
                            $("#countLsh").html(dataArr.length);
                            $("#tbody").after("<tr id=\"tr_" + result.data.code + "\"><td>" + i++ + "</td><td>" + result.data.code + "</td>" +
                                    "<td>" + result.data.fileCode + "</td><td>" + result.data.plateTypeStr + "</td>" +
                                    "<td>" + result.data.plateCode + "</td><td>" + result.data.carCode + "</td>" +
                                    "<td><a href=\"javascript:;\" onclick=\"delTr('" + result.data.code + "');\"  class=\"ubtn ubtn-del\">删除</a></td></tr>");
                            for (var k = 0; k < dataArr.length - 1; k++) {
                                if (dataArr[k].fileCode == result.data.fileCode) {
                                    return;
                                }
                            }

                            $("#countDabh").html(++countDzda);
                        }
                        fx.closeLoading();
                    }, error: function () {
                        fx.alert("数据加载失败");
                        fx.closeLoading();
                    }
                })
                fx.closeLoading();
            }
        })

        $("#comfirm").click(function () {

            if (dataArr.length > 0) {
                $("#msgBox").hide();
                // 确认提交
                $("#dialogInput").find('.ipt').val('');
                layer.open({
                    area: '540px',
                    type: 1,
                    title: false,
                    content: $("#dialogInput")
                })

            } else {
                fx.alert("流水列表为空");
            }
        })

        $("#register").click(function () {
            var box = $("#dialogInput").find('.ipt').val();
            if (box != "" && box != null) {
                fx.loading();
                layer.closeAll();
                $("#msgBox").html("").hide();
                var arr = JSON.stringify(dataArr);
                $("#box").html(box)
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/hbda/businessInfo/addAToBRegister",//路径
                    data: {"data": arr, 'box': box},
                    dataType: "json",
                    success: function (result) {
                        if (result.result == "OK") {
                            var printData=dataArr;
                            i = 1;
                            countDzda = 0;
                            dataArr = new Array();
                            $("#lsh").html("");
                            $("#countLsh").html(dataArr.length);
                            $("#countDabh").html(countDzda);
                            $("[id^='tr_']").remove();
                            layer.closeAll();
                            fx.succeed({
                                title: '提交成功',
                                content: '您提交的批次号：' + result.deliveryInfo.code + '，箱号： ' + box,
                                okeyValue: '打印批次号',
                                okey: function () {
                                    layer.closeAll();
                                    $("#tablePrintData").css('display'," ");
                                    $("#registerDate").html(result.deliveryInfo.registerDate);
                                    $("#registerPerson").html(result.userName);
                                    $("#deliveryCode").html(result.deliveryInfo.code);
                                    $("#barCode").attr("src", "data:image/png;base64," + result.barcode15);

                                    $("#select").hide();
                                    $("#printTemp").show();
                                    var html="";
                                    var arr = new Array();
                                    var xh=1;
                                    for (var k = 0; k < printData.length; k++) {
                                        var i=0;
                                        for(var q = 0; q < arr.length; q++){
                                            if(arr[q].fileCode==printData[k].fileCode){
                                                i=1;
                                            }
                                        }
                                        if(i==0){
                                            html +="<tr><td>" + (xh++) + "</td><td>" + printData[k].fileCode + "</td><td>" + printData[k].plateTypeStr + "</td>" +
                                                    "<td>" + printData[k].plateCode + "</td><td>" + printData[k].carCode + "</td></tr>";
                                            $("#tbodyPrint").html(html);
                                            arr.push(printData[k]);
                                        }
                                    }
                                    printArr=arr;
                                }
                            })

                        } else if (result.result == "boxIsExist") {
                            $("#msgBox").show().html("箱子已存在");
                            layer.open({
                                area: '540px',
                                type: 1,
                                title: false,
                                content: $("#dialogInput")
                            })
                        }else if (result.result == "NO") {
                            fx.alert("提交失败");
                        }
                        fx.closeLoading();
                    }, error: function () {
                        fx.alert("数据加载失败");
                        fx.closeLoading();
                    }
                })
            } else {
                $("#msgBox").show().html("请先输入箱号");
                $("#dialogInput").find('.ipt').focus();
            }
        })

        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                $("#ajaxGetBusinessInfo").click();
            }
        });
    });

    function tablePrint() {
//        var box = $("#box").html();
//        window.location="/hbda/businessInfo/businessInfoPrint?" +
//        "box="+box+"&deliveryCode="+deliveryCode+"&imgSrc="+("data:image/png;base64,"+imgSrc)+"&registerPerson="+registerPerson+"&registerDate="+registerDate;
        var bdhtml = window.document.body.innerHTML;
        var beforeHtml="<html><head><link rel='stylesheet' href='/assets/css/style.css' /></head><style type='text/css'>html,body,.wrapper{height:auto;}</style><div class='box box-mini'>"
        var afterHtml="</div></html>";
        window.document.body.innerHTML = beforeHtml+$("#tablePrint").html()+afterHtml;
        window.print();
        window.document.body.innerHTML = bdhtml;
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

</script>
