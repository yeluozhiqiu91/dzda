<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta name="renderer" content="webkit" />
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="assets/css/style.css" />
    <link rel="stylesheet" href="assets/css/login.css" />
</head>

<body>


<div class="login-box">
    <div class="title">电子档案管理系统</div>
    <div class="form">
        <form id="loginForm">
            <div class="group">
                <input type="text" placeholder="用户名" id="username" name="username" autocomplete="off" value="" class="input user">
            </div>

            <div class="group">
                <input type="password" placeholder="密码" id="password" name="password" autocomplete="off" value="" class="input lock">
            </div>

            <div class="group">
                <img src="/Kaptcha.jpg" class="verifycodeImg" id="verifycodeImg" />
                <input type="text" placeholder="验证码" id="verifycode" name="verifycode" autocomplete="off" value="" class="input shot">
            </div>

            <div class="button">
                <button type="submit" class="ubtn ubtn-blue" id="submit">登 录</button>
            </div>

            <div class="msg" id="msg"></div>
        </form>
    </div>
</div>

<div class="login-footer">
    <ul>
        <li>
            <img src="assets/images/logo.png" />
            <em>武汉市公安局车管所</em>
        </li>
        <li class="hr"></li>
        <li>
            <em>湖北易通达电子科技有限公司</em>
        </li>
    </ul>
</div>

<script src="assets/js/jquery191.js"></script>
<script>
    !(function($) {
        var
                $username = $('#username'),
                $password = $('#password'),
                $verifycode = $('#verifycode'),
                $submit   = $('#submit'),
                $msg      = $('#msg'),
                isSubmit    = false; // 锁定登录按钮

        var showMsg = function(message) {
            $msg.html(message || '');
        }

        var checkUsername = function() {
            var msg = $username.val() ? '' : '请输入用户名';
            msg && $username.focus();
            showMsg(msg);
            return msg;
        }
        var checkPassword = function() {
            var msg = $password.val() ? '' : '请输入密码';
            msg && $password.focus();
            showMsg(msg);
            return msg;
        }
        var checkVerifycode = function() {
            var msg = $verifycode.val() ? '' : '请输入验证码';
            msg && $verifycode.focus();
            showMsg(msg);
            return msg;
        }

        var checkForm = function() {
            return !checkUsername() && !checkPassword() && !checkVerifycode();
        }

        // 提交
        $submit.on('click', function() {
            if (!isSubmit && checkForm()) {
                $.ajax({
                    url: '/ajaxSbmi',
                    type: 'POST',
                    dataType: 'json',
                    data: $('#loginForm').serialize(),
                    success: function(res) {
                        if(res.code == '0') {
                            window.location = '/index';
                        }else{
                            // 错误提示信息
                            showMsg(res.message);
                            $verifycode.val('');
                            $('#verifycodeImg').trigger('click');
                        }
                    },
                    complete: function() {
                        isSubmit = false;
                    },
                    beforeSend: function() {
                        isSubmit = true;
                    }
                })
            }
            return false;
        });

        // 验证码
        $('#verifycodeImg').on('click', function() {
            this.src = '/Kaptcha.jpg?' + Math.random();
            $verifycode.val('').focus();
        })
    })(jQuery);
</script>
</body>
</html>