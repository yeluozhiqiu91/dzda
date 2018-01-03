<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="assets/css/style.css"/>
<link rel="stylesheet" href="assets/plugins/jedate/jedate.css"/>
<%--<style type="text/css">
    .box-mini .tit{text-align:center;padding:15px 0;font-size:20px;}
    .box-mini .info{font-size:16px;line-height:30px;overflow:hidden;}
    .box-mini .info .date{float:right;text-align:right;}
    .box-mini .info .fridgecode .img{display:block;margin-top:4px;border:1px solid #ddd;}
</style>--%>
<div class="crumbs">
    <span>机动车纸质档案</span>
    <i>&gt;</i>
    <span>B库转A库交接</span>
    <i>&gt;</i>
    <span>B库转A库登记</span>
</div>
<div class="box box-mini">
    <div class="filter">
        <%--<button type="button" class="ico ico-close fr" onclick="closePrintBox()"></button>--%>
        <button type="button" class="ubtn ubtn-primary" onclick="printPage()">打印</button>
        <button type="button" class="ubtn ubtn-primary" onclick="closePrintBox()">关闭</button>
    </div>
    <div id="printDiv">
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

</div>
<script type="text/javascript">
    function printPage() {
        var oldhtml=window.document.body.innerHTML;
        var headStr="<div class='box box-mini'>";
        var footStr="</div>";
        window.document.body.innerHTML=headStr+$("#printDiv").html()+footStr;
        window.print();
        window.document.body.innerHTML=oldhtml;
    }
    function closePrintBox(){
        window.location.href="/transfer/showBzadjPage";
    }
</script>