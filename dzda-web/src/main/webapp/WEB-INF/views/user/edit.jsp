<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="crumbs">
    <span>新增用户</span>
</div>

<div class="box c-form">
    <form action="/updateUser" id="myform">
        <input type="hidden" name="userId" value="${user.userId}">
        <div class="item">
            <div class="txt">用户名：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${user.account}" name="account" autocomplete="off" readonly="readonly">
            </div>
        </div>
        <div class="item">
            <div class="txt">姓名：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${user.name}" name="name" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">部门名称：</div>
            <div class="cnt">
                <select name="departmentId" class="slt" data-msg="你还未填写部门">
                    <c:forEach var="d" items="${departmentList}">
                        <option value="${d.departmentId}" <c:if test="${d.departmentId==user.departmentId}">selected</c:if>>${d.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="item">
            <div class="txt">身份证：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${user.idCard}" name="idCard" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">排序号：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${user.orderNum}" name="orderNum" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">是否启用：</div>
            <div class="cnt cbxs">
                <label><input type="radio" name="status" class="status"  value="1"
                        <c:if test="${user.status==1}">checked</c:if>
                />是</label>
                <label><input type="radio" name="status" class="status" value="0"
                              <c:if test="${user.status==0}">checked</c:if>
                />否</label>
            </div>
        </div>
        <div class="ft">
            <button type="submit" class="ubtn ubtn-primary" id="jsubmit">保存</button>
            <button type="button" class="ubtn ubtn-gray" id="jback">取消</button>
        </div>
    </form>
</div>
<script type="text/javascript">



    !(function($) {
        var _global = {
            init: function() {
                this.bindEvent();
                this.checkForm();
            },
            bindEvent: function() {
                // 取消
                $('#jback').on('click', function() {
                    window.history.back(-1);
                })
            },
            checkForm: function() {
                var that = this;
                $('#myform').validator({
                    fields: {
                        account: '用户名: required',
                        name: '姓名: required',
                        status: '是否启用: checked'
                    },
                    valid : function(form) {
                        form.submit();
                    }
                });
            }
        }


        _global.init();
    })(jQuery);


</script>