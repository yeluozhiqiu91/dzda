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
    <span>修改/补打印</span>
</div>

<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="${pageContext.request.contextPath}/hbda/businessInfo/updateOrPrint" novalidate>
            <input class="hide" name="bs" value="1">
            <label class="for">批次号</label>
            <input class="ipt" value="${code}" name="code" type="text">
            <label class="for">登记时间</label>
            <input class="ipt date" value="${queryStartTime}" name="queryStartTime" id="queryStartTime" type="text">
            <i>-</i>
            <input class="ipt date" value="${queryEndTime}" name="queryEndTime" id="queryEndTime" type="text">
            <button type="button"  class="ubtn ubtn-primary" id="findBusiness" >查询</button>
        </form>
    </div>

    <div class="table" hidden id="dataTable">
        <%--<table class="tc">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th>序号</th>--%>
                <%--<th>登记批次号</th>--%>
                <%--<th>登记人</th>--%>
                <%--<th>登记时间</th>--%>
                <%--<th>签收人</th>--%>
                <%--<th>交接状态</th>--%>
                <%--<th>存放箱子</th>--%>
                <%--<th>管理</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody id="tbody"></tbody>--%>
        <%--</table>--%>
        <display:table name="paginatedList" id="row" requestURI="/hbda/businessInfo/updateOrPrint" pagesize="10">
            <display:setProperty name="basic.show.header" value="true"/>
            <display:column title="序号" headerClass="center " class="center">${row_rowNum}</display:column>
            <display:column property="code" title="登记批次号" headerClass="center " class="center"/>
            <display:column property="registerPersonName" title="登记人" headerClass="center " class="center"/>
            <display:column property="registerDate" title="登记时间" headerClass="center " class="center"/>
            <display:column property="receiverName" title="签收人" headerClass="center " class="center"/>
            <display:column title="交接状态" headerClass="center " class="center">
                <c:if test="${row.realJjlx eq 1}">A转B</c:if>
                <c:if test="${row.realJjlx eq 2}">A转B</c:if>
                <c:if test="${row.realJjlx eq 3 or row.realJjlx eq 0}">B转A</c:if>
                <c:if test="${row.realJjlx eq 4}">B转A</c:if>
                <c:if test="${row.jjlx eq 1}">已签收</c:if>
                <c:if test="${row.jjlx eq 2}">签收中</c:if>
                <c:if test="${row.jjlx eq 0}">已登记</c:if>
            </display:column>
            <display:column title="存放箱子" headerClass="center " class="center">
                <a class="ubtn ubtn-link" id="box_${row.box}"  onclick="updateBox('${row.box}');">${row.box}</a>
            </display:column>
            <display:column title="管理" headerClass="center " class="center">
                <a class="ubtn ubtn-link" href="${pageContext.request.contextPath}/hbda/businessInfo/updateOrPrintDetail?box=${row.box}&deliveryCode=${row.code}&deliveryId=${row.deliveryId}&registerPersonName=${row.registerPersonName}&registerDate=${row.registerDate}&realJjlx=${row.realJjlx}">查看详情</a>
            </display:column>
            <display:footer>
                <td colspan="10" style="text-align: right;">当前显示<span id="rownum">${row_rowNum+0}</span>条，共<span
                        id="rowcount">${paginatedList.model.count}</span>条
                </td>
            </display:footer>
        </display:table>
    </div>

    <div class="operate">
        <div class="pagination"></div>
    </div>
</div>


<div class="layer-msg" id="dialogInput">
    <form action="" name="form1" onsubmit="return false;">
        <input type="hidden" id="beforeBox" value="">
        <div class="hd"><i class="ico ico-box"></i>请输入箱号！</div>
        <div class="bd">
            <label>箱号：</label>
            <input type="text" class="ipt" name="box" />
        </div>
        <div class="ft">
            <button type="button" id="updateBox" class="ubtn ubtn-primary">确定</button>
            <p style="line-height: 48px;color: red;" hidden id="msgBox"></p>
        </div>
    </form>
</div>

<script>
    if("${bs}"==1){
        $("#dataTable").show();
    }
    $(function () {
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

        $("#findBusiness").click(function () {
            $("#filterForm").submit();
        })

        $("#updateBox").click(function () {
            var box = $("#dialogInput").find('.ipt').val();
            if (box != "" && box != null) {
                $("#msgBox").html("").hide();
                var beforeBox = $("#beforeBox").val();
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/hbda/businessInfo/updateBox",//路径
                    data: { 'box': box,'beforeBox':beforeBox},
                    dataType: "json",
                    success: function (result) {

                        if(result.result=="OK"){
                            layer.closeAll();
                            fx.alert("箱子修改成功!");
                            window.setTimeout(function(){
                                location.reload(this);
                            },500);
                        }else if(result.result == "NO"){
                            $("#msgBox").show().html("箱子已存在!");
                        }else if(result.result == "error"){
                            fx.alert("箱子修改失败!");
                        }
                    }, error: function () {
                        fx.alert("数据加载失败");
                    }
                })
            }else {
                $("#msgBox").show().html("请先输入箱号");
                $("#dialogInput").find('.ipt').focus();
            }
        })
    })

   function updateBox(box) {
       $("#beforeBox").val(box);
       $("#dialogInput").find('.ipt').val('');
       layer.open({
           area: '540px',
           type: 1,
           title: false,
           content: $("#dialogInput")
       })
   }

</script>