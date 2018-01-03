<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>违法审核</div>
    <div class="operate">
        <a href="javascript:history.back(-1);" class="ubtn ubtn-primary">返回</a>
    </div>

    <div class="floor">
        <h2>基本信息</h2>
        <ul class="list">
            <li>
                <span>采集时间：</span>
                <em>${sspOfferRecord.gatherTime} </em>
            </li>
            <li>
                <span>渠道来源：</span>
                <em>${sspOfferRecord.categoryName}</em>
            </li>
            <li>
                <span>违法地点：</span>
                <em>${sspOfferRecord.roadName}</em>
            </li>
        </ul>
    </div>
    <div class="floor">
        <h2>交通违法照片</h2>
        <ul class="illegal">
            <c:forEach items="${listImage}" var="image">
                <li><img src="${image.imageUrl}" alt=""></li>
            </c:forEach>

        </ul>
    </div>
    <div class="floor">
        <h2>交通违法视频</h2>
        <div class="video">
            <div class="inner">
                <video class="video-js" id="video1" controls preload="auto" poster="">


                    <%--       <c:forEach items="${listVideo}" var="video">
                           <source src="${video.videoUrl}">
                           ${video.videoUrl}
                       </c:forEach>--%>
                    <source src="/assets/images/1234.mp4">

                </video>
                、
            </div>
            <div class="img">
                <ul>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>

                </ul>
            </div>
        </div>

        <div class="buttons">
            <button type="button" class="ubtn ubtn-blue" id="catVideoBtn">截图</button>
            <button type="button" class="ubtn ubtn-ghost" id="trunLeft">左转</button>
            <button type="button" class="ubtn ubtn-ghost" id="trunRight">右转</button>
            <button type="button" class="ubtn ubtn-ghost" id="trun180">180度旋转</button>
        </div>

        <div class="pic">
            <ul>
                <c:forEach items="${listImageTwo}" var="imageTwo">
                    <li><img src="${imageTwo.imageUrl}" alt=""><i class="ico ico-cbx"></i></li>
                </c:forEach>
            </ul>
            <div class="buttons">
                <button type="button" class="ubtn ubtn-blue">确认</button>
                <button type="button" class="ubtn ubtn-ghost">取消</button>
            </div>
        </div>
    </div>

    <div class="floor">
        <h2>交通违法地点</h2>
        <div class="map">
            <div id="map"></div>
        </div>
    </div>

    <div class="floor">
        <div class="operate">
            <button type="button" class="ubtn ubtn-primary" id="jsubmit">完善信息</button>
            <button type="button" class="ubtn ubtn-red" id="jnopass">不通过</button>
            <button type="button" class="ubtn ubtn-ghost" id="jprev">上一页</button>
            <button type="button" class="ubtn ubtn-ghost" id="jnext">下一页</button>
        </div>
    </div>
</div>


<div class="form layer-form" id="tempForm">
    <div class="title">完善信息</div>
    <div class="cnt1">
        <div class="group" style="height:170px;">
            <h3>举报内容</h3>
            <p>时间：${sspOfferRecord.gatherTime}</p>
            <p>路口路段:<span entercode="address">${sspOfferRecord.roadName}</span></p>
            <p>GPS坐标:<span entercode="gpsLongItude">${sspOfferRecord.gpsLongItude}</span> <span
                    entercode="gpsLatItude">${sspOfferRecord.gpsLatItude}</span></p>
        </div>

        <div class="group">
            <h3>车辆信息</h3>
            <div class="thumb">
                <c:forEach items="${listImage}" var="image">
                    <img src="${image.imageUrl}">
                </c:forEach>
            </div>
            <div class="ctrl">
                <button type="button" class="ubtn ubtn-ghost fr" id="jn">下一张</button>
                <button type="button" class="ubtn ubtn-ghost" id="jp">上一张</button>
            </div>
        </div>
    </div>
    <div class="cnt2">
        <div class="group">
            <h3>违法信息</h3>
            <div class="item">
                <div class="txt">违法地点：</div>
                <div class="cnt">
                    ${sspOfferRecord.roadName}
                </div>
            </div>
            <div class="item">
                <div class="txt">区域：</div>
                <div class="cnt">
                    <select class="slt select2" name="xzqh" id="xzqh">
                        <option parentid="9" code="420102" tid="15">江岸区</option>
                        <option parentid="9" code="420103" tid="16">江汉区</option>
                        <option parentid="9" code="420104" tid="17">硚口区</option>
                        <option parentid="9" code="420105" tid="18">汉阳区</option>
                        <option parentid="9" code="420107" tid="20">青山区</option>
                        <option parentid="9" code="420111" tid="21">洪山区</option>
                        <option parentid="9" code="420112" tid="22">东西湖区</option>
                        <option parentid="9" code="420113" tid="23">汉南区</option>
                        <option parentid="9" code="420114" tid="24">蔡甸区</option>
                        <option parentid="9" code="420115" tid="25">江夏区</option>
                        <option parentid="9" code="420117" tid="27">新洲区</option>
                        <option parentid="9" code="4201A1" tid="28">经济技术开发区</option>
                        <option parentid="9" code="4201A2" tid="29">东湖新技术开发区</option>
                        <option parentid="9" code="4201A3" tid="30">化学工业区</option>
                        <option parentid="9" code="420106" tid="19" selected="selected">武昌区</option>
                        <option parentid="9" code="420116" tid="26">黄陂区</option>
                        <option parentid="9" code="4201A5" tid="31">东湖生态旅游风景区</option>
                    </select>
                </div>
            </div>
            <div class="item">
                <div class="txt">地点核实：</div>
                <div class="cnt">
                    <input type="text" name="dldm" id="dldm" class="ipt" style="margin-bottom:9px;" name="carNumber"
                           autocomplete="off">
                    <select name="lddm" id="lddm" class="slt select2">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="group">
            <h3>号牌信息</h3>
            <div class="item">
                <div class="txt">号牌种类：</div>
                <div class="cnt">
                    <select class="slt select2" name="carType" id="carType">
                        <option value="02">小型汽车</option>
                        <option value="01">大型汽车</option>
                        <option value="08">轻便摩托车</option>
                        <option value="07">两、三轮摩托车</option>
                        <option value="13">农用运输车</option>
                        <option value="06">外籍汽车</option>
                        <option value="04">领馆汽车</option>
                        <option value="03">使馆汽车</option>
                        <option value="09">使馆摩托车</option>
                        <option value="10">领馆摩托车</option>
                        <option value="11">境外摩托车</option>
                        <option value="12">外籍摩托车</option>
                        <option value="05">港澳入出境车</option>
                        <option value="14">拖拉机</option>
                        <option value="15">挂车</option>
                        <option value="16">教练汽车</option>
                        <option value="23">警用汽车</option>
                    </select>
                </div>
            </div>
            <div class="item">
                <div class="txt">号牌号码：</div>
                <div class="cnt">
                    <select class="slt select2" style="width:80px;" name="carNamber_1" id="carNamber_1">
                        <option value="鄂">鄂</option>
                        <option value="沪">沪</option>
                        <option value="苏">苏</option>
                        <option value="浙">浙</option>
                        <option value="皖">皖</option>
                        <option value="鲁">鲁</option>
                        <option value="粤">粤</option>
                        <option value="赣">赣</option>
                        <option value="京">京</option>
                        <option value="藏">藏</option>
                        <option value="川">川</option>
                        <option value="甘">甘</option>
                        <option value="桂">桂</option>
                        <option value="贵">贵</option>
                        <option value="黑">黑</option>
                        <option value="吉">吉</option>
                        <option value="冀">冀</option>
                        <option value="津">津</option>
                        <option value="晋">晋</option>
                        <option value="辽">辽</option>
                        <option value="蒙">蒙</option>
                        <option value="闽">闽</option>
                        <option value="宁">宁</option>
                        <option value="青">青</option>
                        <option value="琼">琼</option>
                        <option value="陕">陕</option>
                        <option value="湘">湘</option>
                        <option value="新">新</option>
                        <option value="渝">渝</option>
                        <option value="豫">豫</option>
                        <option value="云">云</option>
                        <option value="使">使</option>
                    </select>
                    <select class="slt select2" style="width:80px;" name="carNamber_3" id="carNamber_3">
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                        <option value="E">E</option>
                        <option value="F">F</option>
                        <option value="G">G</option>
                        <option value="H">H</option>
                        <option value="I">I</option>
                        <option value="J">J</option>
                        <option value="K">K</option>
                        <option value="L">L</option>
                        <option value="M">M</option>
                        <option value="N">N</option>
                        <option value="O">O</option>
                        <option value="P">P</option>
                        <option value="Q">Q</option>
                        <option value="R">R</option>
                        <option value="S">S</option>
                        <option value="T">T</option>
                        <option value="U">U</option>
                        <option value="V">V</option>
                        <option value="W">W</option>
                        <option value="X">X</option>
                        <option value="Y">Y</option>
                        <option value="Z">Z</option>
                    </select>
                    <input class="ipt" name="carNamber_2" id="carNamber_2" maxlength="5"
                           style="width:172px;text-transform: uppercase">
                </div>
            </div>
        </div>

        <div class="group">
            <h3>违法行为</h3>
            <div class="item">
                <div class="txt">违法代码：</div>
                <div class="cnt">
                    <select class="slt select2" onchange="gradeChange()" name="lawCode" id="lawCode">
                        <c:forEach items="${listLawCode}" var="lawCode">

                            <option value="${lawCode.lawName}" tid="${lawCode.id}">${lawCode.lawCode}</option>
                        </c:forEach>

                        <%--                        <option  value="机动车违反规定使用公交专用车道">10191</option>
                                                <option  value="违停">10391</option>
                                                <option  value="违停">10392</option>
                                                <option  value="违停">10393</option>
                                                <option  value="交通事故肇事逃逸">123</option>
                                                <option  value="机动车逆向行驶">13010</option>
                                                <option  value="违反禁止标线指示">13451</option>
                                                <option  value="机动车闯红灯">16251</option>
                                                <option  value="客车严重超员">17162</option>--%>
                    </select>
                </div>
            </div>
            <div class="item">
                <div class="txt">违法类型：</div>
                <div class="cnt"><input type="text" disabled="disabled" class="ipt" id="lawName"
                                        style="margin-bottom:9px;"></div>
            </div>

        </div>
    </div>

    <div class="buttons">
        <button type="button" class="ubtn ubtn-blue layer-close">关闭</button>
        <button type="button" onclick="sspsuccess()" class="ubtn ubtn-blue submit" style="width:120px;">保存信息</button>
    </div>
</div>

<div class="form layer-form" id="notPass">
    <div class="title">原因</div>
    <form action="" name="reason" onsubmit="return false;">
        <div class="item">
            <div class="txt">原因：</div>
            <div class="cnt">
                <select id="checkAdvice" class="slt select2" name="type" data-rule="required">
                    <option value="">请选择</option>
                    <option value="1">交通事故肇事逃逸</option>
                    <option value="2">伪造、变造机动车号牌</option>
                    <option value="3">伪造、变造行驶证</option>
                    <option value="4">驾驶拼装机动车</option>
                    <option value="5">报废机动车上路行驶</option>
                    <option value="6">机动车逆向行驶</option>
                    <option value="7">机动车闯红灯</option>
                    <option value="8">违反禁止标线指示</option>
                    <option value="9">进入导向车道，不按指示方向行驶</option>
                    <option value="166">违停</option>
                    <option value="186">客车严重超员</option>
                    <option value="188">机动车违反规定使用公交专用车道</option>
                </select>
            </div>
        </div>
        <div class="item">
            <div class="txt">说明：</div>
            <div class="cnt">
                <textarea id="checkMemo" name="desc" class="ipt ipt-mul" data-rule="required"></textarea>
            </div>
        </div>
        <div class="buttons" style="padding:20px 0;">
            <button type="button" class="ubtn ubtn-blue layer-close">关闭</button>
            <button type="button" onclick="unsuccess()" class="ubtn ubtn-blue submit" style="width:120px;">保存信息</button>
        </div>
    </form>
</div>

<script src="http://api.map.baidu.com/api?v=2.0&ak=yf0xLlteaVZiaOgqMILN9ZgN"></script>


<script>

    function gradeChange() {
        $("#lawName").val($("#lawCode option:selected").val());
    }
    function unsuccess() {
        if ($.trim($("#checkMemo").val()) != null) {
            if ($.trim($("#checkMemo").val()).length > 60) {
                $.notify({
                    type: 'warning',
                    content: '说明的长度不能超过60个字'
                })
                return
            }
        }
        $.ajax({
            type: 'GET',
            dataType: 'json',
            data: {
                id:${sspOfferRecord.id},
                checkStatus: "2",
                checkMemo: $("#checkMemo").val(),
                checkAdvice: $("#checkAdvice option:selected").val()
            },
            url: '/sspoffer/checkRecord',
            success: function (res) {
                $.notify({
                    type: 'succeed',
                    content: '数据加载成功'
                })
                window.location.href = "/sspoffer/managerWF";
            },
            error: function () {
                $.notify({
                    type: 'warning',
                    content: '数据加载失败'
                })
            }
        })
    }

    var _global = {
        init: function () {
            this.zoomPic();
            this.select2();
            this.bindEvent();
            this.video();
            this.map();
            this.slideImg();
            this.region();
        },
        zoomPic: function () {
            $('.illegal, .pic').viewer({zoomRatio: 0.2});
        },
        select2: function () {
            $('.slt').select2();
            gradeChange();
        },
        video: function () {
            var that = this,
                    videoId = 'video1',
                    $pic = $('.pic'),
                    $img = $('.img'),
                    $mark = $('.video-mark');


            videojs(videoId, {
                language: 'zh-CN'
            }).ready(function () {
                $('#video1').find('video').wrap('<div style="position:absolute;top:0;left:0;width:100%;height:100%;overflow:hidden;"></div>').parent().after($mark)
            });

            // 截图
            $('#catVideoBtn').on('click', function () {
                var result = false;
                $img.find('li').each(function () {
                    if ($(this).is(':empty')) {
                        that.screenshot($(this));
                        result = true;
                        return false;
                    }
                })
                if (!result) {
                    $.notify({
                        type: 'danger',
                        title: '截图失败',
                        content: '请先删除一张截图'
                    })
                }
            })
            // 删除截图
            $img.on('click', '.ico-del', function () {
                $(this).parent().empty();
            })

            // 左转
            $('#trunLeft').on('click', function () {
                fx.rotateVideo(videoId, -1);
            })

            // 右转
            $('#trunRight').on('click', function () {
                fx.rotateVideo(videoId, 1);
            })
            // 180度旋转
            $('#trun180').on('click', function () {
                fx.rotateVideo(videoId, 2);
            })

            // 选取截图
            $pic.on('click', '.ico-cbx', function () {
                if ($pic.find('.ico-cbx-on').length === 4 && !$(this).hasClass('ico-cbx-on')) {
                    $.notify({
                        type: 'warning',
                        content: '最多只能选中4张'
                    })
                } else {
                    $(this).toggleClass('ico-cbx-on');
                }
            })
            // 确认截图
            $pic.on('click', '.ubtn-blue', function () {
                var $cbx = $pic.find('.ico-cbx-on');

                if ($cbx.length === 4) {
                    $cbx.each(function (i) {
                        var $el = $(this),
                                imgSrc = $el.siblings('img').attr('src');
                        $img.find('li').eq(i).html('<img type="http" shotTime="' + i + '" src="' + imgSrc + '" /><i class="ico ico-del"></i>');
                    })
                    $pic.find('.ubtn-ghost').trigger('click');
                } else {
                    $.notify({
                        type: 'warning',
                        content: '必须选中4张截图'
                    })
                }
            })
            // 取消截图
            $pic.on('click', '.ubtn-ghost', function () {
                $pic.find('.ico-cbx-on').each(function () {
                    $(this).removeClass('ico-cbx-on');
                })
            })

            $pic.viewer({zoomRatio: 0.2});

        },
        screenshot: function ($el) {
            var _video = $('#video1').find('video').get(0);
            var currentTime = _video.currentTime;
            // 创建canvas
            var canvas = document.createElement("canvas");
            var width = Math.min(_video.videoWidth, 1280);
            var height = Math.min(_video.videoHeight, 720);
            canvas.width = width;
            canvas.height = height;
            canvas.crossOrigin = 'anonymous';
            canvas.getContext('2d').drawImage(_video, 0, 0, width, height);
            $el.html('<img type="base64" shotTime="' + currentTime + '" src="' + canvas.toDataURL('image/jpeg') + '" /><i class="ico ico-del"></i>');
        },
        map: function () {
            // 百度地图API功能
            var map = new BMap.Map("map");
            var point = new BMap.Point(${sspOfferRecord.gpsLongItude}, ${sspOfferRecord.gpsLatItude});
            var myIcon = new BMap.Icon('/assets/images/ico.png', new BMap.Size(23, 28), {imageOffset: new BMap.Size(-269, 0)});

            // 创建标注
            var marker = new BMap.Marker(point, {icon: myIcon});
            // 将标注添加到地图中
            map.addOverlay(marker);
            map.centerAndZoom(point, 18); // 定位到中心点
            // 启用滚轮放大缩小
            // map.enableScrollWheelZoom();
        },
        bindEvent: function () {
            var that = this,
                    $thumb = $('#thumb'),
                    $temp = $('#tempForm');


            // 保存信息
            $temp.on('click', '.submit', function () {
                if ($('.img').find('img').length != 4) {
                    $.notify({
                        type: 'warning',
                        content: '必须选中4张截图'
                    })
                    return
                }
                if ($("#dldm").attr("dlmc") == "") {
                    $.notify({
                        type: 'warning',
                        content: '请填写道路名称！'
                    })
                    return
                }
                if(!$("#lddm option:selected").attr("lddm")){

                    $.notify({
                        type: 'warning',
                        content: '请选择路段！'
                    })
                    return
                }
                if ($.trim($("#carNamber_2").val()).length != 5) {
                    $.notify({
                        type: 'warning',
                        content: '请输入正确的车牌号码！'
                    })
                    return
                }
                $.ajax({
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        id:${sspOfferRecord.id},
                        checkStatus: "1",
                        regionId: $("#xzqh option:selected").attr("tid"),
                        carType: $("#carType").val(),
                        checkSame: true,
                        lawType: $("#lawCode option:selected").attr('tid'),
                        lawCode: $("#lawCode option:selected").text(),
                        carNum: $("#carNamber_1").val() + ($("#carNamber_3").val() == undefined ? "" : $("#carNamber_3").val()) + ($("#carNamber_2").val()).toUpperCase(),
                        pic1: $('.img').find('img').eq(0).attr('src'),
                        pic2: $('.img').find('img').eq(1).attr('src'),
                        pic3: $('.img').find('img').eq(2).attr('src'),
                        pic4: $('.img').find('img').eq(3).attr('src'),
                        picType: $('.img').find('img').eq(0).attr('type'),
                        pic1Time: $('.img').find('img').eq(0).attr('shotTime'),
                        pic2Time: $('.img').find('img').eq(1).attr('shotTime'),
                        pic3Time: $('.img').find('img').eq(2).attr('shotTime'),
                        pic4Time: $('.img').find('img').eq(3).attr('shotTime'),
                        ldbm: $("#lddm option:selected").attr("lddm"),
                        dlbm: $("#lddm option:selected").attr("dldm"),
                        dlmc: $("#lddm option:selected").attr("ldmc"),
                        dlldStatus: "2",
                        checkSame: true,
                    },
                    url: '/sspoffer/checkRecord',
                    success: function (res) {
                        $.notify({
                            type: 'succeed',
                            content: '数据加载成功'
                        })
                        window.location.href = "/sspoffer/managerWF";
                    },
                    error: function () {
                        $.notify({
                            type: 'warning',
                            content: '数据加载失败'
                        })
                    }
                })
            })

            // 完善信息
            $('#jsubmit').on('click', function () {
                layer.open({
                    area: '1000px',
                    type: 1,
                    title: false,
                    content: $temp
                })
            })


            // 不通过
            $('#jnopass').on('click', function () {
                layer.open({
                    area: '540px',
                    type: 1,
                    title: false,
                    content: $('#notPass')
                })
            })

            // 上一页
            $('#jprev').on('click', function () {
                        $.ajax({
                            type: 'POST',
                            dataType: 'json',
                            data: {id: sspId, ids: ids, type: 0},
                            url:"/sspoffer/prevornext",
                            success: function (res) {
                                if(res.success){
                                    window.location.href="/sspoffer/checkRecordView?offerId="+res.obj+"&ids="+res.msg;
                                }else{
                                    $.notify({
                                        type: 'warning',
                                        content: '已经到顶了'
                                    })
                                }
                            },
                            error: function () {
                                $.notify({
                                    type: 'warning',
                                    content: '数据加载失败'
                                })
                            }
                        })


            })
            // 下一页
            $('#jnext').on('click', function () {
                $.ajax({
                    type: 'POST',
                    dataType: 'json',
                    data: {id: sspId, ids: ids, type: 1},
                    url:"/sspoffer/prevornext",
                    success: function (res) {
                        if(res.success){
                            window.location.href="/sspoffer/checkRecordView?offerId="+res.obj+"&ids="+res.msg;
                        }else{
                            $.notify({
                                type: 'warning',
                                content: '已经是最后一条了'
                            })
                        }
                    },
                    error: function () {
                        $.notify({
                            type: 'warning',
                            content: '数据加载失败'
                        })
                    }
                })

            })

        },
        slideImg: function () {
            $('.thumb').slideImg({
                next: '#jn',
                prev: '#jp'
            });
        },
        // 完善信息地区级联
        region: function () {
            var that = this,
                    data = {xzqh: $('#xzqh').find('option:selected').attr('code')};

            $('#xzqh').on('change', function () {
                data.xzqh = $(this).find('option:selected').attr('code');
                $('#dldm').val('');
                $('#lddm').empty().trigger('change.select2');
            })

            $("#dldm").autocomplete({
                serviceUrl: "/sspoffer/queryWfdlMessage",
                paramName: 'address',
                params: data,
                dataType: 'json',
                type: 'POST',
                deferRequestBy: 100,
                showNoSuggestionNotice: true,
                noSuggestionNotice: '地点无效',
                transformResult: function (res) {
                    var _data = [];
                    $.each(res.data, function (i, item) {
                        _data.push({
                            value: item.dlmc,
                            xzqh: item.xzqh,
                            dldm: item.dldm
                        })
                    })
                    return {suggestions: _data};
                },
                onSelect: function (data) {
                    that.queryWfldMessage(data.xzqh, data.dldm);
                }
            });
        },
        queryWfldMessage: function (xzqh, dldm) {
            fx.request("/sspoffer/queryWfldMessage", {xzqh: xzqh, dldm: dldm}, function (datas) {
                var _dldm = "";
                $.each(datas.data, function (i, n) {
                    _dldm += '<option ygld="' + n.ygld + '" dldm="' + n.dldm + '" lddm="' + n.lddm + '" ldmc="' + n.ldmc + '"  value="' + n.dldm + '">' + n.ldmc + '</option>';
                })
                $("#lddm").html(_dldm).trigger('change.select2');
            })
        }
    }

    var ids = "${ids}";
    var sspId =${sspOfferRecord.id};
</script>
