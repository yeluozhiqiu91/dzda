<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="crumbs">
    <span>机动车影像</span>
    <i>&gt;</i>
    <span>历史档案影像</span>
    <i>&gt;</i>
    <span>历史档案影像</span>
</div>
<div class="box">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="#" onsubmit="return false;" novalidate>
            <label class="for">流水号</label>
            <input class="ipt" name="businessCode" id="businessCode" type="text" placeholder="请输入流水号信息，或用扫描枪扫描">
            <button type="button" id="OKButton" class="ubtn ubtn-primary" onclick="queryLs()">确定</button>
        </form>
    </div>

    <div class="table">
        <table class="tc">
            <thead>
            <tr>
                <th>流水号</th>
                <th>号牌种类</th>
                <th>号牌号码</th>
                <th>业务类型</th>
                <th>车辆识别代号</th>
                <th>办理时间</th>
                <th>管理</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:if test="${businessInfo!=null}">
                <tr>
                    <td>${businessInfo.code}</td>
                    <td>${businessInfo.plateType}</td>
                    <td>${businessInfo.plateCode}</td>
                    <td>${businessInfo.businessType}</td>
                    <td>${businessInfo.carCode}</td>
                    <td>${businessInfo.applicationDate}</td>
                    <c:if test="${lswz=='A'}">
                        <td><button onclick="showAlert()">影像采集</button> </td>
                    </c:if>
                    <c:if test="${lswz!='A'}">
                        <td><a href="/showSmy?businessCode=${businessInfo.code}&userId=${userId}" target="_blank">影像采集</a> </td>
                    </c:if>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

    <div class="operate">
        <div class="pagination"></div>
    </div>
</div>
<script type="text/javascript">
    function queryLs(){
        var businessCode=$("#businessCode").val();
        window.location.href="/hbda/auth/Image/showSmy?businessCode="+businessCode;
    }
    function showAlert(){
        $.notify({title: "流水不在B库，不能影像", type: 'warning'});
    }
    $(function(){
        $("#businessCode").focus();
        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                $("#OKButton").click();
            }
        });
        var msg='${msg}';
        if(msg!=''){
            //layer.alert(msg);
            $.notify({title: msg, type: 'warning'});
        }
    });
</script>