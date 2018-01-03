<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="crumbs">
    <span>用户权限</span>
</div>

<div class="table">
    <input  type="hidden" value="${uid}" id="uid">
    <table>
        <thead>
        <tr>
            <th width="40">&nbsp;</th>
            <th>角色名</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roleList}" var="role">
            <tr>
                <td><input type="checkbox"  <c:if test="${role.isSelected==0}">
                    checked
                </c:if> value="${role.rolesId}" id="c1" /></td>
                <td><label for="c1">${role.name}</label></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="operate">
    <button type="button" class="ubtn ubtn-primary" onclick="submit();return false" id="jsubmit">保存</button>
    <button type="button" class="ubtn ubtn-gray" onclick="cancel()" id="jback">取消</button>
</div>

<script type="text/javascript">
    function submit(){
        var roles = $(":checked");
        var rids="";
        for(i=0;i<roles.length;i++){
            rids+= roles[i].value+",";
        }
        $.ajax({
            type:"post",
            dataType:"json",
            url:"/saveUserRole",
            data: {"rids":rids.substr(0,rids.length-1),"uid":$("#uid").val()},
            success:function(data) {
                if(data.success){
                    cancel();
                }

            },error:function () {
                layer.alert("修改失败!");
            }
        })
    }
    function cancel(){
        window.history.back(-1);
    }
</script>