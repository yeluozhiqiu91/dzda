<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="crumbs">
    <span>修改密码</span>
</div>

<div class="box c-form">
    <form action="/updatePassword" id="myform">
        <input type="hidden" name="userId" value="${user.userId}">
        <div class="item">
            <div class="txt">原密码：</div>
            <div class="cnt">
                <input type="password" class="ipt" name="oldPassword" id="oldPassword" autocomplete="off">
                <span id="oldhit"></span>
            </div>
        </div>
        <div class="item">
            <div class="txt">新密码：</div>
            <div class="cnt">
                <input type="password" class="ipt"  name="newPassword" id="newPassword" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">再次输入新密码：</div>
            <div class="cnt">
                <input type="password" class="ipt" name="confirmPassword" id="confirmPassword" autocomplete="off">
                <span id="hit"></span>
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
        var showMsg = function(message) {
            $("#oldhit").html(message || '');
        }
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
                        oldPassword: '原密码: required',
                        newPassword: '新密码: required',
                        confirmPassword: '再次输入新密码: required'
                    },
                    valid : function(form) {
                        //form.submit();
                        if($("#newPassword").val()!=$("#confirmPassword").val()){
                            layer.alert("两次输入新密码不一致!");
                            return;
                        }
                        $.ajax({
                            type:"post",
                            dataType:"json",
                            url:"/savePassword",
                            data: {"oldPassword":$("#oldPassword").val(),"newPassword":$("#newPassword").val()},
                            success:function(data) {
                                if(data.code == '0') {
                                    layer.alert("修改成功，即将跳转到登录页面");
                                    window.setTimeout(function(){window.location='/logout'},3000);
                                }else{
                                    // 错误提示信息
                                    showMsg(data.message);
                                }

                            }/*,error:function () {
                                layer.alert("修改失败!");
                            }*/
                        })
                    }
                });
            }
        }


        _global.init();
    })(jQuery);


</script>