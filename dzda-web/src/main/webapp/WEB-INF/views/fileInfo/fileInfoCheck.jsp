<link rel="stylesheet" href="assets/css/style.css"/>
<link rel="stylesheet" href="assets/plugins/jedate/jedate.css"/>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="crumbs">
    <a href="#">机动车纸质档案</a>
    <i>&gt;</i>
    <a href="#">档案审核</a>
    <i>&gt;</i>
    <span>档案审核</span>
</div>

<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="${pageContext.request.contextPath}/hbda/fileInfo/fileInfoCheck"
              novalidate>
            <input class="hide" name="bs" value="1">
            <label class="for">档案编号</label>
            <input class="ipt" name="code" id="code" type="text" placeholder="请输入流水号信息，或用扫描枪扫描">
            <button type="button" class="ubtn ubtn-primary" onclick="selFileInfo();">确定</button>
        </form>
    </div>

    <div class="table hide">
        <div class="caption">
            <span>车辆信息</span>
        </div>
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>档案编号</th>
                <th>号牌种类</th>
                <th>号牌号码</th>
                <th>车辆识别代号</th>
                <th>档案状态</th>
            </tr>
            </thead>
            <tbody id="tbody2">
            <tr>
                <td>${fileInfo.carNumber}</td>
                <td>${fileInfo.fileCode}</td>
                <td>${fileInfo.hpzl}</td>
                <td>${fileInfo.hphm}</td>
                <td>${fileInfo.carCode}</td>
                <td>
                    <c:if test="${fileInfo.status==0}">问题档案</c:if>
                    <c:if test="${fileInfo.status==1}">正常档案</c:if>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="table hide" style="margin-top:40px;">
        <div class="caption">
            <span>业务信息</span>
        </div>
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>流水号</th>
                <th>业务类型</th>
                <th>申请时间</th>
                <th>流水状态</th>
                <th>流水缺失状态</th>
                <th>问题档案管理</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${businessInfos}" var="businessInfo" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${businessInfo.code}</td>
                    <td>${businessInfo.businessTypeStr}</td>
                    <td>${businessInfo.applicationDate}</td>
                    <td>
                        <c:if test="${businessInfo.lsStatus=='1'}">正常</c:if>
                        <c:if test="${businessInfo.lsStatus=='2'}">嫌疑</c:if>
                        <c:if test="${businessInfo.lsStatus=='3'}">流水修改</c:if>
                        <c:if test="${businessInfo.lsStatus=='5'}">合格证待核查</c:if>
                        <c:if test="${businessInfo.lsStatus=='6'}">嫌疑并合格证核查未通过</c:if>
                        <c:if test="${businessInfo.lsStatus=='E'}">完成</c:if>
                        <c:if test="${businessInfo.lsStatus=='Q'}">退办</c:if>
                        <c:if test="${businessInfo.lsStatus=='W'}">业务未办结</c:if>
                    </td>
                    <td>
                        <c:if test="${businessInfo.qsStatus==0}">流水缺失</c:if>
                        <c:if test="${businessInfo.qsStatus==1}">正常</c:if>
                        <c:if test="${businessInfo.qsStatus==2}">资料缺失</c:if>
                    </td>
                    <td>
                        <c:if test="${businessInfo.lsStatus=='Q'}"></c:if>
                        <c:if test="${businessInfo.lsStatus != 'Q'}">
                            <c:if test="${businessInfo.qsStatus==0}">
                                <a href="javascript:;" class="ubtn ubtn-link"
                                   onclick="record(1,${businessInfo.businessId},${businessInfo.fileId},'${businessInfo.lsStatus}');">修改为正常流水</a>
                            </c:if>
                            <c:if test="${businessInfo.qsStatus==1}">
                                <a href="javascript:;" class="ubtn ubtn-link"
                                   onclick="record(2,${businessInfo.businessId},${businessInfo.fileId},'${businessInfo.lsStatus}');">记录为缺失流水</a>
                                <a href="javascript:;" class="ubtn ubtn-link"
                                   onclick="record(3,${businessInfo.businessId},${businessInfo.fileId},'${businessInfo.businessType}');">记录缺失资料</a>
                            </c:if>
                            <c:if test="${businessInfo.qsStatus==2}">
                                <a href="javascript:;"class="ubtn ubtn-link"
                                   onclick="record(3,${businessInfo.businessId},${businessInfo.fileId},'${businessInfo.businessType}');">记录缺失资料</a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="operate">
        <div class="pagination"></div>
    </div>
</div>


<div class="form layer-form" id="tempForm">
    <div class="title">请选择缺失资料种类</div>
    <form action="" name="form1" onsubmit="return false;">
        <input type="hidden" id="businessId" value="">
        <input type="hidden" id="fileId" value="">
        <div class="item" style="padding-top:15px;overflow:hidden;" id="dataTypes">
        </div>
        <div class="ft">
            <button type="button" class="ubtn ubtn-gray layer-close">取消</button>
            <button type="button" class="ubtn ubtn-primary" id="material" style="width:120px;">确定</button>
        </div>
    </form>
</div>

<script>
    if("${bs}"=='1'){
        $(".table").show();
    }
    $(function () {
        $('#tempForm').on('click', '.cbx', function() {
            $(this).toggleClass('cbx-on');
        })
        
        $("#material").click(function () {
            var businessDetailIds = [];
            var businessId = $("#businessId").val();
            var fileId = $("#fileId").val();
            $('#tempForm').find('.cbx-on').each(function(i, item) {
                var businessDetailId = this.id;
                businessDetailIds.push(businessDetailId.substr(businessDetailId.indexOf('_')+1));
            })
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/hbda/fileInfo/updateQsZl",//路径
                data: {"businessDetailIds": businessDetailIds.toString(),"businessId": businessId,"fileId":fileId},
                dataType: "json",
                success: function (result) {
                    if(result.result=="OK"){
                        fx.alert("修改成功!");
                        window.setTimeout(function(){
                            location.reload(this);
                        },500);
                    }else if(result.result=="NO"){
                        fx.alert("修改失败!");
                    }
                }, error: function () {
                    fx.alert("数据加载失败");
                }
            })
            layer.closeAll();
        })
    })

    function selFileInfo() {
        var code = $("#code").val();
        if (code != null && code != '') {
            fx.loading("加载中");
            $("#filterForm").submit();
        }else {
            fx.alert("请输入档案编号!");
        }
    }

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            selFileInfo();
        }
    });

    function record(type,businessId,fileId,lsStatus) {
        if(type==1){
            fx.confirm('是否记录为正常流水？', function () {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/hbda/fileInfo/updateLshStatus",//路径
                    data: {"type": type,"businessId": businessId,"fileId": fileId},
                    dataType: "json",
                    success: function (result) {
                        if(result.result=="OK"){

                            fx.alert("修改成功!");
                            window.setTimeout(function(){
                                location.reload(this);
                            },500);
                        }else if(result.result=="NO"){
                            fx.alert("修改失败!");
                        }
                    }, error: function () {
                        fx.alert("数据加载失败");
                    }
                })
                layer.closeAll();
            })
        }else if(type==2){
            fx.confirm('是否记录为缺失流水？', function () {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/hbda/fileInfo/updateLshStatus",//路径
                    data: {"type": type,"businessId": businessId,"fileId": fileId},
                    dataType: "json",
                    success: function (result) {
                        if(result.result=="OK"){
                            layer.closeAll();
                            fx.alert("修改成功!");
                            window.setTimeout(function(){
                                location.reload(this);
                            },500);
                        }else if(result.result=="NO"){
                            fx.alert("修改失败!");
                        }
                    }, error: function () {
                        fx.alert("数据加载失败");
                    }
                })
            })
        }else if(type==3){
            $("#dataTypes").html("");
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/hbda/fileInfo/getBusinessTypeDetailByBusinessType",//路径
                data: {"businessId": businessId,"businessType": lsStatus},
                dataType: "json",
                success: function (result) {
                    if(result.result=="OK"){
                        var data=result.data;
                        $("#businessId").val(businessId);
                        $("#fileId").val(fileId);
                        for( var i=0;i<data.length;i++ ){
                            if(data[i].isQs != null && data[i].isQs != "" ){
                                $("#dataTypes").append('  <label class="cbx cbx-on" id="dataTypes_'+data[i].businessDetailId+'">'+data[i].detailName+'</label>');
                            }else {
                                $("#dataTypes").append('  <label class="cbx" id="dataTypes_'+data[i].businessDetailId+'">'+data[i].detailName+'</label>');
                            }
                        }
                    }else {
                        fx.alert("资料种类获取失败!");
                    }
                }, error: function () {
                    fx.alert("数据加载失败");
                }
            })
            // 记录缺失资料
//            $('#tempForm').find('.cbx-on').removeClass('cbx-on');
            layer.open({
                area: '621px',
                type: 1,
                title: false,
                content: $('#tempForm')
            })
        }
    }
</script>