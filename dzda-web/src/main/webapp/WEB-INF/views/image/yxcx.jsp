<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>机动车影像</span>
    <i>&gt;</i>
    <span>查询和统计</span>
    <i>&gt;</i>
    <span>影像查询</span>
</div>
<div class="box">
    <div class="filter filter-res">
        <form name="filterForm" id="filterForm">
            <div class="group">
                <label class="for">号牌种类</label>
                <select class="slt" name="plateType" id="plateType">
                    <option value="" <c:if test="${businessInfo.plateType==null||businessInfo.plateType==''}">selected</c:if>>不限</option>
                    <c:forEach items="${plateTypeList}" var="plateType">
                        <option value="${plateType.plateType}"
                                <c:if test="${plateType.plateType==businessInfo.plateType}">selected</c:if>
                        >${plateType.name}</option>
                    </c:forEach>
                </select>
                <%--<input class="ipt" name="plateType" id="plateType" type="text" value="${businessInfo.plateType}">--%>
            </div>
            <div class="group">
                <label class="for">号牌号码</label>
                <input class="ipt" name="plateCode" id="plateCode" type="text" value="${businessInfo.plateCode}">
            </div>
            <div class="group">
                <label class="for">档案编号</label>
                <input class="ipt" name="fileCode" id="fileCode" type="text" value="${businessInfo.fileCode}">
            </div>
            <div class="group">
                <label class="for">流水号</label>
                <input class="ipt" name="code" id="code" type="text" value="${businessInfo.code}">
            </div>
            <div class="group">
                <label class="for">车辆识别代号</label>
                <input class="ipt" name="carCode" id="carCode" type="text" value="${businessInfo.carCode}">
            </div>
            <button type="submit" class="ubtn ubtn-primary">查询</button>
        </form>
    </div>

    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/hbda/auth/Image/yxcx" pagesize="10" >
        <display:setProperty name="basic.show.header" value="true"/>
        <display:column property="code" title="流水号"/>
        <display:column property="fileCode" title="档案编号"/>
        <display:column property="plateType" title="号牌种类" />
        <display:column property="plateCode" title="号牌号码"/>
        <display:column property="carCode" title="车辆识别代号"/>
        <display:column property="businessType" title="业务类型"/>
        <display:column property="imageDate" title="扫描时间"/>
        <display:column property="imageUserName" title="扫描人"/>
        <display:column title="管理" ><button type="button" onclick="showImage('${row.code}')" class="ubtn ubtn-primary">查看流水影像</button></display:column>
        <display:footer>
        <tr>
            <td colspan="9" style="text-align: right;">当前显示${row_rowNum+0}条，共${paginatedList.model.count}条</td>
        <tr>
            </display:footer>
            </display:table>
    </div>

    <div class="operate">
        <div class="pagination"></div>
    </div>
</div>
<script type="text/javascript">
    function showImage(businessCode){
        var userId='${userId}';
        window.open("/showSmy?businessCode="+businessCode+"&userId="+userId);
    }
</script>