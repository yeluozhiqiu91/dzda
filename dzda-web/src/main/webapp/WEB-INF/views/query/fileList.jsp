<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>档案查询</span>
</div>

<div class="box">
    <div class="filter filter-res">
        <form name="filterForm" id="filterForm" onsubmit="return false;">
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
            <button type="submit" onclick="queryFile()" class="ubtn ubtn-primary">查询</button>
        </form>
    </div>

    <div class="table">
        <display:table name="paginatedList" id="row" requestURI="/query/fileList" pagesize="10" >
        <display:setProperty name="basic.show.header" value="true"/>
            <display:column property="code" title="流水号"/>
            <display:column property="fileCode" title="档案编号"/>
            <display:column property="plateTypeStr" title="号牌种类" />
            <display:column property="plateCode" title="号牌号码"/>
            <display:column property="carCode" title="车辆识别代号"/>
            <display:column property="businessTypeStr" title="业务类型"/>
            <display:column property="box" title="箱号"/>
            <display:column title="流水状态" headerClass="center hidd" class="center">
                <c:if test="${row.lszt eq 1}">A转B登记</c:if>
                <c:if test="${row.lszt eq 2}">A转B签收</c:if>
                <c:if test="${row.lszt eq 3}">B转A登记</c:if>
                <c:if test="${row.lszt eq 4}">B转A签收</c:if>
            </display:column>
        <display:footer>
        <tr>
            <td colspan="8" style="text-align: right;">当前显示${row_rowNum+0}条，共${paginatedList.model.count}条</td>
        <tr>
            </display:footer>
            </display:table>
    </div>
</div>
<script>
    var queryFile = function () {
        var param = $("#filterForm").serialize();
        $.ajax({
            url: '/query/fileList1',
            dataType: 'json',
            type: 'POST',
            data: param,
            success: function (data) {
                if (data.result == "data") {
                    window.location = "/query/fileList?" + param;
                } else if ($("#plateType").val() == '' && $("#plateCode").val() == ''
                    && $("#code").val() == '' && $("#carCode").val() == ''
                    && $("#fileCode").val() == '') {
                    layer.alert("无效的查询，请输入查询条件!");
                } else {
                    layer.alert("查无出库记录!");
                }
            },
            complete: function (xhr, sessionStatus) {
                var sessionStatus = xhr.getResponseHeader('sessionstatus');
                if (sessionStatus == 'timeout') {
                    window.location.href = '/login.do';
                }
            }
        })
    }

    function addRole() {
        window.location = "/hbda/auth/role/add";
    }
</script>
