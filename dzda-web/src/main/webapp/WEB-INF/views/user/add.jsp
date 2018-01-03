<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="crumbs">
    <span>新增用户</span>
</div>

<div class="box c-form">
    <form action="/addUser" id="myform">
        <div class="item">
            <div class="txt">用户名：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="" name="account" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">姓名：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="" name="name" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">部门名称：</div>
            <div class="cnt">
                <select name="departmentId" class="slt" data-msg="你还未填写部门">
                    <c:forEach var="d" items="${departmentList}">
                        <option value="${d.departmentId}">${d.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="item">
            <div class="txt">身份证：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="" name="idCard" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">排序号：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="" name="orderNum" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">是否启用：</div>
            <div class="cnt cbxs">
                <label><input type="radio" name="status" class="status"  value="1"/>是</label>
                <label><input type="radio" name="status" class="status" value="0"/>否</label>
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