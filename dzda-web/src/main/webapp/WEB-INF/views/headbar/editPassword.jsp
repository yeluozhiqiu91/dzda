<%@ include file="../common/taglib.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="cp" value="<%=basePath%>" />

<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>修改密码</div>
    <div class="form">
        <form action="/user/updatePassword" id="modify" method="POST">
            <input type="hidden" value="${success}" id="result" />
            <input type="hidden" value="${pwd}" name="pwd" />
            <div class="item">
                <div class="txt">原密码：</div>
                <div class="cnt">
                    <input class="ipt" name="oldPwd" type="text" data-msg-remote="原密码输入有误">
                </div>
            </div>
            <div class="item">
                <div class="txt">新密码：</div>
                <div class="cnt">
                    <input class="ipt" name="newPwd" type="password">
                </div>
            </div>
            <div class="item">
                <div class="txt">确认密码：</div>
                <div class="cnt">
                    <input class="ipt" name="pwdAgain" type="password">
                </div>
            </div>
            <div class="ft">
                <button type="submit" class="ubtn ubtn-blue submit" style="width:120px;">保存</button>
            </div>
        </form>
    </div>
</div>

<script>
    var _global = {
        init: function() {
            this.showMsg();
            this.bindEvent();
        },
        showMsg: function() {
            var result = $('#result').val();
            if(result != null && result != '' && result == 'true') {
                $.notify({title: '修改密码成功', type: 'success'});
            }
        },
        bindEvent: function() {
            var that = this,$form = $('#modify');
            $form.validator({
                fields: {
                    oldPwd: {
                        rule: 'required;remote(${cp}/user/validPwd, pwd)',
                        msg: {
                            required: '原密码不能为空'
                        }
                    },
                    newPwd: {
                        rule: 'required;password',
                        msg: {
                            required: '请输入密码',
                            length: '密码长度最小为6位'
                        }
                    },
                    pwdAgain: {
                        rule: 'required; match(newPwd)',
                        msg: {
                            required: '请再次输入密码',
                            match: '密码不一致!'
                        }
                    }
                }
            });
        }
    }
</script>