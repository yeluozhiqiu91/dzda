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
    <a href="#">车辆信息授权</a>
    <i>&gt;</i>
    <span>车辆信息授权</span>
</div>

<div class="box" id="app">
    <div class="btn-box " style="padding-top: 10px">
        <span style="font-size: large;">车辆所有权:</span>
        <select class="ipt" style="width: 10%;" onchange="typeChange(this);" id="registertType">
            <option value="1">个人</option>
            <option value="2">单位</option>
        </select>
        <span id="single">
            <span style="font-size: large;margin-left: 15px;">身份证号码:</span>
            <input type="text" class="ipt" style="height: 34px;width: 13%;border-radius: 4px;" id="singleSfz"/>
            <%--<input class="ipt" id="code" type="text" placeholder="请输入流水号信息，或用扫描枪扫描">--%>
            <span style="font-size: large;margin-left: 15px;">
            <%--<a href="javascript:;" class="btn btn-primary User_ser"--%>
               <%--style="float: none;display: inline-block  !important;padding: 0 40px 0 44px;border-radius: 11px;"--%>
               <%--id="cardIdSingle">查询</a>--%>
            <button type="button" id="cardIdSingle" class="ubtn ubtn-primary">确定</button>
            </span>

        </span>
        <span id="unit" hidden>
            <span style="font-size: large;margin-left: 15px;">单位名称:</span>
            <input type="text" class="ipt" style="height: 34px;width: 13%;border-radius: 4px;" id="unitName"/>
            <%--<a href="javascript:;" class="btn btn-primary User_ser"--%>
               <%--style="float: none;display: inline-block  !important;padding: 0 40px 0 44px;border-radius: 11px;"--%>
               <%--id="cardIdUnit">查询</a>--%>
         <span style="font-size: large;margin-left: 15px;">
            <button type="button" id="cardIdUnit" class="ubtn ubtn-primary">确定</button>
         </span>
        </span>
        </span>
    </div>
    <div class="x_content x-content-top" id="carData" hidden>
        <div class="input-box g-clr">
            <div class="item">
                <div style="padding-top: 60px;background-color: white;" class="btn-box">
                    <span style="font-size: x-large;">|&nbsp;&nbsp;车辆信息</span>
                    <span style="float: right;width: 500px;">
                        <span style="font-size: large;">被授权人 :</span>
                        <input class="ipt" type="text" style="width: 35%;height: 34px;border-radius: 4px;" id="accreditSfz"
                               placeholder="请输入身份证号码"/>
                        <%--<a href="javascript:;" class="btn btn-primary User_ser"--%>
                           <%--style="float: none;display: inline-block  !important;padding: 0 40px 0 44px;border-radius: 11px;"--%>
                           <%--onclick="accredit()">授权</a>--%>
                        <button type="button" style="float: none;display: inline-block  !important;padding: 0 40px 0 44px;border-radius: 7px;"
                               onclick="accredit()" class="ubtn ubtn-cyan fr">授权</button>
                    </span>
                </div>
                <div class="table">
                    <table class="border">
                        <thead>
                        <tr>
                            <th><input type="checkbox" class="cbx" id="allSelect"></th>
                            <th>序号</th>
                            <th>号牌种类</th>
                            <th>号牌号码</th>
                            <th>车辆识别代号</th>
                            <th>中文品牌</th>
                            <th>机动车所有人</th>
                            <th>初次登记日期</th>
                        </tr>
                        </thead>
                        <tbody id="carDataTbody">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/plugins/layer/layer.js"></script>
<script>
    $(function () {

        $('#cardIdSingle').click(function () {
            var singleSfz = $("#singleSfz").val();
//            var singleName = $("#singleName").val();
            $("#allSelect").prop("checked", "");
            if (singleSfz != null && singleSfz != ''/* && singleName != null && singleName != ''*/) {
                $("#carDataTbody").html("");
                var registertType = $("#registertType").val();
                $("#accreditSfz").val(singleSfz);
                $("#accreditSfz").attr("disabled", true);
                if (checkSfz(singleSfz) == "OK") {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/hbda/carInfo/ajaxCarInfo",//路径
                        data: {"singleSfz": singleSfz, /*"name": singleName,*/ "registertType": registertType},
                        dataType: "json",
                        success: function (result) {
                            $("#carDataTbody").html("");
                            if (result.result != null && result.result != '') {
                                fx.alert(result.result);
                            } else {
                                var data = result.data;
                                var carDataHtml = "";
                                for (var i = 0; i < data.length; i++) {
                                    carDataHtml += " <tr><td>";
                                    if (data[i].isLet != 1) {
                                        carDataHtml += "<input type=\"checkbox\" class=\"cbx\"   carSfz=\"" + data[i].sfzmhm + "\"" +
                                                "   hpzl=\"" + data[i].hpzl + "\"  value=\"" + data[i].hphm + "\">";
                                    } else {
                                        carDataHtml += "已授权";
                                    }
                                    carDataHtml += "</td>" +
                                            "<td>" + (i + 1) + "</td>" +
                                            "<td>" + (data[i].hpzl==null?"":data[i].hpzl) + "</td>" +
                                            "<td>" + (data[i].hphm==null?"":data[i].hphm) + "</td>" +
                                            "<td>" + (data[i].clsbdh==null?"":data[i].clsbdh) + "</td>" +
                                            "<td>" + (data[i].clpp1==null?"":data[i].clpp1) + "</td>" +
                                            "<td>" + (data[i].syr==null?"":data[i].syr) + "</td>" +
                                            "<td>" + (data[i].ccdjrq==null?"":data[i].ccdjrq) + "</td></tr>";
                                }
                                $("#carDataTbody").append(carDataHtml);
                                $("#carData").show();
                            }
                        }, error: function () {
                            fx.alert("数据加载失败");
                        }
                    })
                }
            } else {
                fx.alert("输入框不能为空!");
            }
        })

        $('#cardIdUnit').click(function () {
            var unitName = $("#unitName").val();
            $("#allSelect").prop("checked", "");
            if (unitName != null && unitName != '') {
                $("#carDataTbody").html("");
                var registertType = $("#registertType").val();
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/hbda/carInfo/ajaxCarInfo",//路径
                    data: {"name": unitName, "registertType": registertType},
                    dataType: "json",
                    success: function (result) {
                        $("#carDataTbody").html("");
                        if (result.result != null && result.result != '') {
                            fx.alert(result.result);
                        } else {
                            var data = result.data;
                            var carDataHtml = "";
                            for (var i = 0; i < data.length; i++) {
                                carDataHtml += " <tr><td><input type=\"checkbox\" class=\"cbx\"   carSfz=\"" + data[i].sfzmhm + "\"" +
                                        "   hpzl=\"" + data[i].hpzl + "\"  value=\"" + data[i].hphm + "\"></td>" +
                                        "<td>" + (i + 1) + "</td>" +
                                        "<td>" + data[i].hpzl + "</td>" +
                                        "<td>" + data[i].hphm + "</td>" +
                                        "<td>" + data[i].clsbdh + "</td>" +
                                        "<td>" + data[i].clpp1 + "</td>" +
                                        "<td>" + data[i].syr + "</td>" +
                                        "<td>" + data[i].ccdjrq + "</td></tr>";
                            }
                            $("#carDataTbody").append(carDataHtml);
                            $("#carData").show();
                        }
                    }, error: function () {
                        fx.alert("数据加载失败");
                    }
                })
            } else {
                fx.alert("输入框不能为空!");
            }
        })

        $('body').on('click', '.close', function () {
            $('.model, .model-mask').remove();
        })
        $('body').on('click', 'th .cbx', function () {
            var icheck = this.checked;
            $('.cbx').each(function () {
                this.checked = icheck;
            })
        })
        $('body').on('click', 'td .cbx', function () {
            var icheck = this.checked;
            if (icheck) {
                $('td .cbx').each(function () {
                    if (this.checked == false) {
                        icheck = false;
                        return false; // break;
                    }
                })
            } else {
                icheck = false;
            }
            $('th .cbx')[0].checked = icheck;
        })
    })

    function accredit() {
        var accreditSfz = $("#accreditSfz").val();
        var unitName = $("#unitName").val();
        var cbx = [];
        var carSfzs = [];
        var hpzls = [];
        var msg = '';

        $('td .cbx:checked').each(function () {
            cbx.push(this.value);
            carSfzs.push($(this).attr("carSfz"));
            hpzls.push($(this).attr("hpzl"));
        })
        if (accreditSfz == '') {
            msg = '请输入被授权人身份证号码';
        } else if (checkSfz(accreditSfz) == "OK") {
            if (cbx.length === 0) {
                msg = '请勾选车辆';
            } else {
                layer.open({
                    skin: 'layui-custom',
                    title: false,
                    content: '<div class="tit"><i class="i"></i>授权确认</div><div class="text">请确认是否将车辆 ' + cbx.join('、') + ' 授权  ' + accreditSfz + ' 可查询？</div>',
                    btn: ['确定', '取消'],
                    area: '540px',
                    yes: function () {
                        var registertType = $("#registertType").val();
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/hbda/carInfo/ajaxAddAccredit",//路径
                            data: {
                                "registertType": registertType,
                                "accreditSfz": accreditSfz,
                                "hphms": cbx.toString(),
                                "unitName": unitName,
                                "carSfzs": carSfzs.toString(),
                                "hpzls": hpzls.toString()
                            },
                            dataType: "json",
                            success: function (result) {
                                layer.closeAll();
                                if (result.result == "OK") {
                                    fx.alert('授权成功!');
                                    typeChange(registertType);
                                } else if (result.result == "ERROR") {
                                    fx.alert('授权失败!');
                                } else if (result.result == "NO") {
                                    var resData = result.data.split(",");
                                    var str = "";
                                    for (var q = 0; q < resData.length; q++) {
                                        for (var i = 0; i < cbx.length; i++) {
                                            if (resData[q] == cbx[i]) {
                                                str += "【" + resData[q] + "】";
                                            }
                                        }
                                    }
                                    layer.open({
                                        title: false,
                                        btn: false,
                                        area: '540px',
                                        content: '<div class="layui-warning"> 授权失败!  原因 : ' + str + ' 重复授权!</div>'
                                    })
                                }
                            }, error: function () {
                                layer.closeAll();
                                fx.alert("数据加载失败");
                            }
                        })
                    }
                })
            }
        }
        if (msg) {
            fx.alert(msg);
            return "NO";
        } else {
            return "OK";
        }
    }
    function typeChange(type) {
        $("#accreditSfz").attr("disabled", false);
        if (type.value == 1) {
            $("#unit").hide();
            $("#single").show();

        } else if (type.value == 2) {
            $("#single").hide();
            $("#unit").show();
        }
        $("#carDataTbody").html("");
        $("#allSelect").prop("checked", "");
        $("input").val("");
        $("#carData").hide();
    }

    function checkSfz(i) {
        var reg = /^\d{6}(19|2\d)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)?$/;
        var cardId = i;
        var msg = '';
        if (!reg.test(cardId)) {
            msg = '请输入正确的身份证号码';
        }
        if (msg) {
            fx.alert(msg);
            return "NO";
        } else {
            return "OK";
        }
    }

</script>