<!-- sidebar menu -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="menu">
<c:forEach items="${menusList}" var="menus" varStatus="status">
    <c:if test="${menus.key!='首页'}">
    <div class="item <c:if test='${isIndex=="isIndex"&&status.index==1}'>active</c:if><c:if test='${menus.selected==true}'>active</c:if>" id="menu1">
        <div class="hd"><a href="javascript:;">${menus.key}</a></div>
        <div class="bd">
            <c:forEach items="${menus.menuItems}" var="sonMenus">
            <dl <c:if test="${sonMenus.selected==true}">class="expand"</c:if>>
                <dt>
                    <a href="javascript:;">
                        <span>${sonMenus.key}</span>
                        <i class="ico ico-right"></i>
                    </a>
                </dt>
                <dd>
                    <c:forEach items="${sonMenus.menuItems}" var="child">
                    <a href="${child.url}" id="menu1_1_1" <c:if test="${child.selected==true}">
                        class="current"
                    </c:if>><i class="ico ico1"></i>${child.key}</a>
                    </c:forEach>
                </dd>
            </dl>
            </c:forEach>
            <%--<dl>
                <dt>
                    <a href="javascript:;">
                        <span>B库转A库交接</span>
                        <i class="ico ico-right"></i>
                    </a>
                </dt>
                <dd>
                    <a href="ba1.html" id="menu1_2_1"><i class="ico ico1"></i>B库转A库登记</a>
                    <a href="ba2.html" id="menu1_2_2"><i class="ico ico2"></i>B库转A库签收</a>
                </dd>
            </dl>
            <dl>
                <dt>
                    <a href="javascript:;">
                        <span>档案审核</span>
                        <i class="ico ico-right"></i>
                    </a>
                </dt>
                <dd>
                    <a href="c.html" id="menu1_3_1"><i class="ico ico8"></i>档案审核</a>
                </dd>
            </dl>
            <dl>
                <dt>
                    <a href="javascript:;">
                        <span>查询统计</span>
                        <i class="ico ico-right"></i>
                    </a>
                </dt>
                <dd>
                    <a href="d1.html" id="menu1_4_1"><i class="ico ico5"></i>档案查询</a>
                    <a href="d2.html" id="menu1_4_2"><i class="ico ico6"></i>交接统计</a>
                    <a href="d3.html" id="menu1_4_3"><i class="ico ico7"></i>问题档案查询</a>
                </dd>
            </dl>--%>
        </div>
    </div>
    </c:if>
</c:forEach>

    <%--<div class="item" id="menu2">
        <div class="hd"><a href="#">机动车影像</a></div>
        <div class="bd">
            <dl>
                <dt>
                    <a href="javascript:;">
                        <span>档案影像</span>
                        <i class="ico ico-right"></i>
                    </a>
                </dt>
                <dd>
                    <a href="e1.html" id="menu2_1_1"><i class="ico ico4"></i>历史档案影像</a>
                </dd>
            </dl>
            <dl>
                <dt>
                    <a href="javascript:;">
                        <span>查询统计</span>
                        <i class="ico ico-right"></i>
                    </a>
                </dt>
                <dd>
                    <a href="f1.html" id="menu2_2_1"><i class="ico ico5"></i>影像查询</a>
                    <a href="f2.html" id="menu2_2_2"><i class="ico ico6"></i>历史档案扫描流水统计</a>
                    <a href="f3.html" id="menu2_2_3"><i class="ico ico7"></i>按扫描人员统计</a>
                </dd>
            </dl>
        </div>
    </div>

    <div class="item" id="menu3">
        <div class="hd"><a href="#">档案抽检</a></div>
    </div>
    <div class="item" id="menu4">
        <div class="hd"><a href="#">库房管理</a></div>
    </div>
    <div class="item" id="menu5">
        <div class="hd"><a href="#">综合查询</a></div>
    </div>
    <div class="item" id="menu6">
        <div class="hd"><a href="#">统计分析</a></div>
    </div>
    <div class="item" id="menu7">
        <div class="hd"><a href="#">系统管理</a></div>
        <div class="bd">
            <dl>
                <dt>
                    <a href="javascript:;">
                        <span>系统管理</span>
                        <i class="ico ico-right"></i>
                    </a>
                </dt>
                <dd>
                    <a href="department.html" id="menu7_1_1"><i class="ico ico9"></i>部门管理</a>
                    <a href="role.html" id="menu7_1_2"><i class="ico ico10"></i>角色管理</a>
                    <a href="user.html" id="menu7_1_3"><i class="ico ico11"></i>用户管理</a>
                </dd>
            </dl>
        </div>
    </div>--%>
</div>

