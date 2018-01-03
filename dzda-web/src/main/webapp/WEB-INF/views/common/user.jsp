<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="user">
    <ul>
        <li><i class="ico ico-user"></i><span>您好，${loginUser.name}</span></li>
        <li><a href="/index"><i class="ico ico-home"></i>首页</a><i>|</i></li>
        <li><a href="notice.html"><i class="ico ico-notice"></i>通知公告</a><i>|</i></li>
        <li><a href="/user/editSelf?uid=${loginUser.userId}"><i class="ico ico-edit"></i>修改资料</a><i>|</i></li>
        <li><a href="/user/editPassword?uid=${loginUser.userId}"><i class="ico ico-lock"></i>修改密码</a><i>|</i></li>
        <li><a href="/logout"><i class="ico ico-logout"></i>退出</a></li>
    </ul>
</div>