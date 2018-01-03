<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<spring:message code="web.host" var="host"/>
<c:url value="${host}" var="home"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${home}">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>电子档案自助查询系统 </title>
    <meta name="renderer" content="webkit" />
    <link rel="stylesheet" href="vehicleassets/css/style.css" />
</head>
<body>

<!-- header begin -->
<div class="header">
    <div class="logo">
        电子档案自助查询
    </div>
</div>
<!-- header end -->

<div class="content">
    <div class="page1">
        <div class="filter">
            <form name="filterForm" id="filterForm">
                <div class="item">
                    <label class="for">身份证号：</label>
                    <input type="hidden" name="querySfz" id="querySfz">
                    <input type="text" name="cardid" id="cardId" class="ipt" autocomplete="off" placeholder="请输入身份证号码" readonly="" />
                    <div class="keyboard" id="keyboard">
                        <button type="button" data-i="1">1</button>
                        <button type="button" data-i="2">2</button>
                        <button type="button" data-i="3">3</button>
                        <br>
                        <button type="button" data-i="4">4</button>
                        <button type="button" data-i="5">5</button>
                        <button type="button" data-i="6">6</button>
                        <br>
                        <button type="button" data-i="7">7</button>
                        <button type="button" data-i="8">8</button>
                        <button type="button" data-i="9">9</button>
                        <br>
                        <button type="button" data-i="X">X</button>
                        <button type="button" data-i="0">0</button>
                        <button type="button" data-i="-">退格</button>
                    </div>
                </div>
                <div class="item">
                    <button type="submit" class="ubtn ubtn-primary">查询</button>
                    <button type="reset" class="ubtn ubtn-cyan">清屏</button>
                </div>
            </form>
        </div>

        <div class="tab"></div>
        <div class="tabcont"></div>
        <div class="tips">
            <ul>
                <li>1、个人查询，请持自己的身份证，到人工审核岗，获取授权查询自己名下的车辆档案；</li>
                <li>2、单位查询请携带单位介绍信、律师函等相关证明文件，持自己的身份证，向人工审核岗提交需查询车辆的信息，获取授权查询相关车辆档案；</li>
                <li>3、获取查询授权后，使用自助查询机，输入已获授权的身份证号，查询车辆信息。</li>
                <li>4、查询完毕后，点击清屏按钮退出查询界面；若需打印相关档案资料，请持身份证，到人工审核岗登记岗申请打印。</li>
            </ul>

            <div class="qrcode">
                <div class="cnt">
                    微信扫一扫<br> 武汉交警微信公众号
                    <img src="vehicleassets/images/qrcode1.png">
                </div>
                <div class="cnt">
                    支付宝扫一扫<br> 武汉交警城市服务窗
                    <img src="vehicleassets/images/qucode2.png">
                </div>
            </div>
        </div>
    </div>

    <div class="page2">
        <div class="back">
            <button type="button" class="ubtn ubtn-primary" onclick="goback()">返回</button>
        </div>

        <div class="detail"></div>
    </div>
</div>

<div class="content" style="display: none" id="forprint">
    <div class="print" id="print">
        <div class="tit">电子档案自助查询</div>
        <div class="hd">
            <span id="print_queryType">车辆所有权：${queryType}</span>
            <span id="print_sfz">身份证号：${sfz}</span>
            <span id="print_printTime">打印时间：${printTime}</span>
        </div>
        <div class="bd">
            <dl>
                <dt>车辆信息</dt>
                <dd>
                    <label class="for">号牌号码：</label>
                    <span id="print_plateNo">${plateNo}</span>
                </dd>
                <dd>
                    <label class="for">机动车所有人：</label>
                    <span id="print_syr"></span>
                </dd>
                <dd>
                    <label class="for">车辆识别代号：</label>
                    <span id="print_vin">${vin}</span>
                </dd>
                <dd>
                    <label class="for">发动机号：</label>
                    <span id="print_engineNo">${engineNo}</span>
                </dd>
                <dd>
                    <label class="for">号牌种类：</label>
                    <span id="print_plateType">${plateType}</span>
                </dd>
                <dd>
                    <label class="for">车辆类型：</label>
                    <span id="print_vehicleType">${vehicleType}</span>
                </dd>
                <%--<dd>
                    <label class="for">中文品牌：</label>
                    <span>五菱</span>
                </dd>--%>
                <dd>
                    <label class="for">车辆型号：</label>
                    <span id="print_model">${model}</span>
                </dd>
                <dd>
                    <label class="for">车身颜色：</label>
                    <span id="print_carColor">${carColor}</span>
                </dd>
                <dd>
                    <label class="for">使用性质：</label>
                    <span id="print_useType">${useType}</span>
                </dd>
                <dd>
                    <label class="for">机动车状态：</label>
                    <span id="print_carStatus">${carStatus}</span>
                </dd>
                <dd>
                    <label class="for">初次登记日期：</label>
                    <span id="print_registerDate">${registerDate}</span>
                </dd>
                <dd>
                    <label class="for">检验有效期止：</label>
                    <span id="print_period">${period}</span>
                </dd>
                <dd>
                    <label class="for">手机号码：</label>
                    <span id="print_mobile">${mobile}</span>
                </dd>
            </dl>
        </div>
    </div>
</div>
<div class="footer">
    <p>版权所有 &copyright; 2017 武汉市公安局交通管理局车辆管理所</p>
    <p>地址：武汉市武昌区友谊大道特8号</p>
</div>

<script id="tableTemp" type="text/html">
    {{each data as item}}
    <div class="item">
        <div class="tit"><button button='button' class="ubtn ubtn-primary pr" onclick="printVehicle('{{item.name}}','{{item.plateNo}}','{{item.vin}}','{{item.engineNo}}','{{item.plateType}}','{{item.vehicleType}}','{{item.model}}','{{item.carColor}}','{{item.useType}}','{{item.carStatus}}','{{item.registerDate}}','{{item.period}}','{{item.mobileHide}}','{{item.syq}}')">打印</button>车辆信息</div>
        <div class="grid">
            <div class="row">
                <div class="col3">
                    <label class="for">号牌号码：</label>
                    <span>{{item.plateNo}}</span>
                </div>
                <div class="col3">
                    <label class="for">机动车所有人：</label>
                    <span>{{item.name}}</span>
                </div>
                <div class="col3">
                    <label class="for">车辆识别代号：</label>
                    <span>{{item.vinHide}}</span>
                </div>
            </div>
            <div class="row">
                <div class="col3">
                    <label class="for">发动机号：</label>
                    <span>{{item.engineNoHide}}</span>
                </div>
                <div class="col3">
                    <label class="for">号牌种类：</label>
                    <span>{{item.plateType}}</span>
                </div>
                <div class="col3">
                    <label class="for">车辆类型：</label>
                    <span>{{item.vehicleType}}</span>
                </div>
            </div>
            <div class="row">
                <%--<div class="col3">
                    <label class="for">中文品牌：</label>
                    <span>{{item.brands}}</span>
                </div>--%>
                <div class="col3">
                    <label class="for">车辆型号：</label>
                    <span>{{item.model}}</span>
                </div>
                <div class="col3">
                    <label class="for">车身颜色：</label>
                    <span>{{item.carColor}}</span>
                </div>
            </div>
            <div class="row">
                <div class="col3">
                    <label class="for">使用性质：</label>
                    <span>{{item.useType}}</span>
                </div>
                <div class="col3">
                    <label class="for">机动车状态：</label>
                    <span>{{item.carStatus}}</span>
                </div>
                <div class="col3">
                    <label class="for">初次登记日期：</label>
                    <span>{{item.registerDate}}</span>
                </div>
            </div>
            <div class="row">
                <div class="col3">
                    <label class="for">检验有效期止：</label>
                    <span>{{item.period}}</span>
                </div>
                <div class="col3">
                    <label class="for">手机号码：</label>
                    <span>{{item.mobileHide}}</span>
                </div>
            </div>
        </div>

        <div class="tit">业务信息</div>
        <div class="table">
            <table class="border">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>流水号</th>
                    <th>业务类型</th>
                    <th>申请时间</th>
                    <th>流水操作</th>
                </tr>
                </thead>
                <tbody>
                {{each item.business as list}}
                <tr>
                    <td>{{list.increment}}</td>
                    <td>{{list.number}}</td>
                    <td>{{list.name}}</td>
                    <td>{{list.time}}</td>
                    <td>
                        {{if list.isImage == 1}}
                        <button type="button" class="ubtn ubtn-primary" onclick="showDetail('{{list.number}}','{{list.name}}','{{list.time}}','{{list.plateNo}}')">影像查看</button>
                        {{/if}}
                    </td>
                </tr>
                {{/each}}
                </tbody>
            </table>
        </div>
    </div>
    {{/each}}
</script>

<script id="detailTemp" type="text/html">
    <div class="tit">业务流水信息</div>
    <div class="info">
        <p>号牌号码：{{plateNo}}</p>
        <p>业务类型：{{name}}</p>
        <p>业务流水号：{{number}}</p>
        <p>申请时间： {{time}}</p>
    </div>

    <div class="tit">影像信息</div>
    <div class="thumb">
        {{each pic as list}}
        <div class="item">
            <div class="res">
                <img src="{{list.src}}" data-src="{{list.primarySrc}}" alt="图片丢失" />
            </div>
            <div class="name">{{list.name}}</div>
        </div>
        {{/each}}
    </div>
</script>
<script id="imgTemp" type="text/html">
    <div class="swiper-container">
        <div class="swiper-wrapper">
            {{each pic as list}}
            <div class="swiper-slide"><img src="{{pic}}"></div>
            {{/each}}
        </div>
    </div>
</script>
<!-- 公用 -->
<script src="vehicleassets/js/jquery191.js"></script>
<script src="vehicleassets/js/art.template.js"></script>
<script src="vehicleassets/js/swiper.js"></script>

<script>

    // 判断各种浏览器，找到正确的方法
    function launchFullscreen() {
        var docElm = document.documentElement;
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        } else if (docElm.msRequestFullscreen) {
            document.body.msRequestFullscreen();
        } else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
        } else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
        }
    }

    // 关闭详情
    function goback() {
        $('.page1').show();
        $('.page2').hide();
    }
    // 显示详情
    function showDetail(code,name,sqrq,plateNo) {
        if (code) {
            $.ajax({
                url: '/vehicle/findAllImage',
                data: {lsh: code,hphm:plateNo,ywlx:name,sqsj:sqrq},
                cache: false,
                timeout:10000,
                beforeSend: function() {
                    $('<div class="spinner">加载中...</div>').appendTo($('body'));
                },
                complete: function() {
                    $('.spinner').remove();
                },
                success: function(res) {
                    if (res.result == 'fail') {
                        _global.alert('查询失败');
                    } else {
                        // $('.thumb').viewer('destroy');
                        $('.detail').empty().html(template('detailTemp', res.data));
                        $('.page1').hide();
                        $('.page2').show();
                        // $('.thumb').viewer({zoomRatio: 0.2});
                        window.scrollTo(0,0);
                    }
                },
                error: function() {}
            })
        }
    }
    function showPic(el) {
        var imgs = [],
            index = 0,
            model = [];

        $('.thumb').find('img').each(function(i) {
            var src = $(this).data('src') || this.src;
            model.push('<li class="item"><img src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=" data-src="' + src + '"></li>');
            if (this.src === el.src) {
                index = i;
            }
        })
        var $model = $('<div class="model"><div class="pic"><ul>' + model.join('') + '</ul></div><div class="close"></div><div class="lb-op"><div class="lb-turn-left"></div><div class="lb-turn-right"></div></div></div>').appendTo($('body'));

        $model.before('<div class="model-mask"></div>');
        $model.css({
            width: '82%',
            height: '66%',
            left: '9%',
            top: '17%'
        }).fadeIn(300);

        $model.find('.pic').swipeSlide({
            index: index,
            continuousScroll: true,
            autoSwipe: false,
            lazyLoad: true,
            firstCallback: function(i,sum){
                $('#imgRotate').attr('id', '');
                $model.find('img').eq(i).attr('id', 'imgRotate');
            },
            callback: function(i,sum){
                $('#imgRotate').attr('id', '');
                $model.find('img').eq(i+1).attr('id', 'imgRotate');
            }
        });
    }
    var _global = {
        init: function() {
            this.bindEvent();
            this.tab();
            this.filter();
            //this.fix();
        },
        rotate: function(arrow) {
            var $img = $('#imgRotate');
            var rotation = parseInt($img.data('rotate'), 10) || 0;
            rotation += arrow;

            if (rotation === 4) {
                rotation = 0;
            } else if (rotation === -1) {
                rotation = 3;
            }
            $('#imgRotate').css({
                'transform': 'rotate(' + (90*rotation) + 'deg)',
                'WebkitTransform': 'rotate(' + (90*rotation) + 'deg)',
                'OTransform': 'rotate(' + (90*rotation) + 'deg)',
                'msTransform': 'rotate(' + (90*rotation) + 'deg)',
                'MozTransform': 'rotate(' + (90*rotation) + 'deg)'
            }).data('rotate', rotation);
        },
        bindEvent: function() {
            var that = this;
            // 关闭弹层
            $('body').on('click', '.close', function() {
                $('.model, .model-mask').remove();
            })

            $('.detail').on('click', 'img', function() {
                showPic(this);
            })

            $('body').on('click', '.lb-turn-left', function() {
                that.rotate(-1);
            });
            $('body').on('click', '.lb-turn-right', function() {
                that.rotate(1);
            });
        },
        alert: function(msg, opts) {
            var defaults = {
                width: 540,
                top: 0
            }
            opts = $.extend(defaults, opts);
            msg = msg || '提示';
            var $model = $('<div class="model"><div class="cont">' + msg + '</div><div class="close"></div></div>').appendTo($('body'));

            var
                    winHeight = $(window).height();
            winWidth  = $(window).width(),
                    width     = opts.width || $model.outerWidth(),
                    height    = opts.height || $model.outerHeight(),
                    _left      = (winWidth - width)/2,
                    _top       = (winHeight - height)/2;

            $model.before('<div class="model-mask"></div>');
            $model.css({
                width: width,
                left: _left,
                top: 320
            }).fadeIn(300);
        },
        fix: function() {
            var height = $(window).height(),
                    F = $('.footer').outerHeight();
            $('.content').css('min-height', height - F - 70 - 40);
        },
        tab: function() {
            $('.tab').on('click', 'span', function() {
                var k = $(this).index();
                $(this).addClass('active').siblings().removeClass('active');
                $('.tabcont').find('.item').eq(k).show().siblings().hide();
            })
        },
        filter: function() {
            var that = this;
            var $form = $('#filterForm');
            var reg = /^\d{6}(19|2\d)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)?$/;

            // 清空
            $form.on('reset', function() {
                $('.tab').empty().hide();
                $('.tabcont').empty().hide();
                $('.tips').show();
                $('#keyboard').show();
            })

            // 查询
            $form.on('submit', function() {
                var cardId = $('#cardId').val();
                var queryType=$('#queryType').val();
                var companyName=$('#companyName').val();
                if(cardId==''){
                    that.alert('请输入身份证号码');
                }else if(!reg.test(cardId)){
                    that.alert('请输入正确的身份证号码');
                }else if(queryType=='2'&&companyName==''){
                    that.alert('请输入单位名称');
                }
                else {
                    $.ajax({
                        url: '/vehicle/findAllVehicle',
                        data: {sfz: cardId,queryType:queryType,companyName:companyName},
                        cache: false,
                        timeout:10000,
                        beforeSend: function() {
                            $('<div class="spinner">加载中...</div>').appendTo($('body'));
                        },
                        complete: function() {
                            $('.spinner').remove();
                        },
                        success: function(res) {
                            if (res.result == 'fail') {
                                //that.alert('没有查询到车辆');
                                that.alert(res.msg);
                            } else {
                                $('#keyboard').hide();
                                $('#querySfz').val(res.querySfz);
                                $('#queryedType').val(res.queryType);
                                $('#hidCompany').val(res.companyName);
                                that.process(res);
                            }
                        }
                    })
                }
                return false;
            })

            // 键盘
            $('#cardId').on('click', function() {
                $('#keyboard').show();
                return false;
            })

            $('body').on('click', function() {
                //$('#keyboard').hide();
            })

            $('#keyboard').on('click keydown', 'button', function() {
                var val = $('#cardId').val();
                var i = $(this).data('i');
                if (val.length >= 18 && i !== '-') {
                    return false;
                } else if (val.length !== 17 && i === 'X') {
                    // 最后一位才可能为X
                    return false;
                } else if (i === '-') {
                    val = val.substring(0, val.length -1);
                } else {
                    val += i;
                }
                $('#cardId').val(val);
                return false;
            })
        },
        process: function(res) {
            var tab = [];
            $.each(res.data, function(i, item) {
                tab.push('<span>' + item.plateNo + '</span>');
            })
            if (tab.length === 1) {
                tab = [];
                $('.tab').empty().hide();
            } else {
                $('.tab').html(tab.join('')).show().find('span:eq(0)').addClass('active');
            }
            $('.tabcont').empty().html(template('tableTemp', res)).show();
            $('.tips').hide();
        }
    }
    $(function() {
        _global.init();
    })
    function changeInput(){
        var se=$("#queryType").val();
        if(se=='1'){
            $("#inputCompany").hide();
            $("#companyName").hide();
        }else{
            $("#inputCompany").show();
            $("#companyName").show();
        }
    }
    function printVehicle(name,plateNo,vin,engineNo,plateType,vehicleType,model,carColor,useType,carStatus,registerDate,period,mobile,syq){
        //var sfz=$('#cardId').val();
        var sfz=$('#querySfz').val();
        var queryType=$('#queryedType').val();
        var companyName=$('#hidCompany').val();
        if(syq=='1'){
            $("#print_queryType").html("车辆所有权：个人");
        }else{
            $("#print_queryType").html("车辆所有权：单位");
        }
        $("#print_sfz").html("身份证号："+sfz);
        var date=new Date();
        var dateStr=date.getFullYear()+"-";
        if((date.getMonth()+1)<=9){
            dateStr=dateStr+"0"+(date.getMonth()+1)+"-";
        }else{
            dateStr=dateStr+(date.getMonth()+1)+"-";
        }
        if(date.getDate()<=9){
            dateStr=dateStr+"0"+date.getDate()+" ";
        }else{
            dateStr=dateStr+date.getDate()+" ";
        }
        if(date.getHours().length<2){
            dateStr=dateStr+"0"+date.getHours()+":";
        }else{
            dateStr=dateStr+date.getHours()+":";
        }
        if(date.getMinutes().length<2){
            dateStr=dateStr+"0"+date.getMinutes()+":";
        }else{
            dateStr=dateStr+date.getMinutes()+":";
        }
        if(date.getSeconds()<10){
            dateStr=dateStr+"0"+date.getSeconds();
        }else{
            dateStr=dateStr+date.getSeconds();
        }

        $.ajax({
                url:'/vehicle/print',
                data: {sfz: sfz,printTime:dateStr,queryType:syq,name:name,plateNo:plateNo,vin:vin,engineNo:engineNo,plateType:plateType,vehicleType:vehicleType,model:model,carColor:carColor,useType:useType,carStatus:carStatus,registerDate:registerDate,period:period,mobile:mobile},
            cache: false,
            success: function(res) {
        }
    });
        /*dateStr=dateStr+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();*/
        $("#print_printTime").html("打印时间："+dateStr);
        $("#print_syr").html(name);
        $("#print_plateNo").html(plateNo);
        $("#print_vin").html(vin);
        $("#print_engineNo").html(engineNo);
        $("#print_plateType").html(plateType);
        $("#print_vehicleType").html(vehicleType);
        $("#print_model").html(model);
        $("#print_carColor").html(carColor);
        $("#print_useType").html(useType);
        $("#print_carStatus").html(carStatus);
        $("#print_registerDate").html(registerDate);
        $("#print_period").html(period);
        $("#print_mobile").html(mobile);
        addWaterMarker(sfz);
        var oldhtml=window.document.body.innerHTML;
        window.document.body.innerHTML=$("#forprint").html();
        window.print();
        window.document.body.innerHTML=oldhtml;
        $('#cardId').val(sfz);
        $('#queryType').val(queryType);
        $('#companyName').val(companyName);
        _global.init();
    }
    function addWaterMarker(str) {
        var can = document.createElement('canvas');
        can.width = 260;
        can.height = 140;
        can.style.display = 'none';
        var cans = can.getContext('2d');
        cans.rotate(-20 * Math.PI / 180);
        cans.font = "18px tahoma";
        cans.fillStyle = "rgba(204, 204, 204, 1)";
        cans.textAlign = 'left';
        cans.textBaseline = 'Middle';
        cans.fillText(str, 10, 110);
        document.getElementById('print').style.backgroundImage = "url(" + can.toDataURL("image/png") + ")";
    }
</script>
</body>
</html>