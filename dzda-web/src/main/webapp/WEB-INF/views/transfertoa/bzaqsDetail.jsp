<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="crumbs">
    <span>机动车纸质档案</span>
    <i>&gt;</i>
    <span>B库转A库交接</span>
    <i>&gt;</i>
    <span>B库转A库签收</span>
</div>
<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="#" onsubmit="return false;" novalidate>
            <a onclick="back() " class="ubtn ubtn-cyan fr">返回</a>
            <label class="for">流水号</label>
            <input class="ipt" name="businessCode" id="businessCode" type="text">
            <input type="hidden" name="pcCode" value="${pcCode}" id="pcCode">
            <input type="hidden" name="startTime" value="${startTime}">
            <input type="hidden" name="endTime" value="${endTime}">
            <button type="submit" id="OKButton" class="ubtn ubtn-primary" onclick="confirmQs()">确认签收</button>
        </form>
    </div>

    <div class="table">
        <div class="caption" id="msg"><c:if test="${msg!=null}">${msg}</c:if> </div>
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
                <c:if test="${businessInfoList==null|| businessInfoList.size()==0}"><tr><td colspan="6" align="center">暂无数据</td></tr></c:if>
                <c:forEach var="b" items="${businessInfoList}" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${b.code}</td>
                        <td>${b.fileCode}</td>
                        <td>${b.plateTypeStr}</td>
                        <td>${b.plateCode}</td>
                        <td>${b.carCode}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#OKButton").click();
        }
    });
    function confirmQs() {
        var businessCode=$("#businessCode").val();
        if(businessCode!='') {
            var param = $("#filterForm").serialize();
            window.location.href = "/transfer/bzaConfirmQs?" + param;
        }
    }

    function back(){
        $("#pcCode").val('');
        var param=$("#filterForm").serialize();
        window.location.href="/transfer/showBzaqsPage?"+param;
    }
</script>