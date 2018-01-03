<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="crumbs">
    <span>修改用户</span>
</div>

<div class="box c-form">
    <form action="/updateUser" id="myform">
        <input type="hidden" name="userId" value="${user.userId}">
        <div class="item">
            <div class="txt">用户名：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${user.account}" name="account" readonly="readonly">
            </div>
        </div>
        <div class="item">
            <div class="txt">姓名：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${user.name}" name="name" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">身份证：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${user.idCard}" name="idCard" autocomplete="off">
            </div>
        </div>
        <input type="hidden" class="ipt" value="${user.orderNum}" name="orderNum">
        <input type="hidden" class="ipt" value="${user.status}" name="status">
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