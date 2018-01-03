<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="crumbs">
    <span>机动车纸质档案</span>
    <i>&gt;</i>
    <span>B库转A库交接</span>
    <i>&gt;</i>
    <span>B库转A库签收,查看详情</span>
</div>
<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="#" onsubmit="return false;" novalidate>
            <a href="javascript:history.back(-1);" class="ubtn ubtn-cyan fr">返回</a>
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