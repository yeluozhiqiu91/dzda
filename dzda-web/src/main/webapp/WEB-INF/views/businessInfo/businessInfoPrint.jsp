<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit" />
    <link rel="stylesheet" href="/assets/css/style.css"/>
</head>
<style type="text/css">
    html,body,.wrapper{height:auto;}
</style>
<body>
<div id="tablePrint">
    <div class="tit" style="text-align: center;padding: 15px 0;font-size: 20px;">A库转B库登记表</div>
    <div class="info" >
        <div class="date" style="float: right;">
            登记时间：<span id="registerDate">${registerDate}</span> <br>
            登记人：<span id="registerPerson">${registerPerson}</span>
        </div>
        <div class="fridgecode">
            批次号：<span id="deliveryCode">${deliveryCode}</span>
            &nbsp;&nbsp;
            箱号：<span id="box">${box}</span>
            <br/>
            <img src="${imgSrc}" class="img" id="imgSrc"/>
        </div>
    </div>
    <div class="table" id="tablePrintData">
        <table class="tc">
            <thead>
            <tr>
                <th>序号</th>
                <th>档案编号</th>
                <th>号牌种类</th>
                <th>号牌号码</th>
                <th>车辆识别代号</th>
            </tr>
            </thead>
            <tbody id="tbodyPrint">
            <c:forEach items="${businessInfos}" var="businessInfo" varStatus="i" >
                <tr>
                    <td>${i.index+1}</td>
                    <td>${businessInfo.fileCode}</td>
                    <td>${businessInfo.plateType}</td>
                    <td>${businessInfo.plateCode}</td>
                    <td>${businessInfo.carCode}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="/assets/js/jquery191.js"></script>
<script>
    $(function () {
        window.print();
    })
</script>
</body>
</html>