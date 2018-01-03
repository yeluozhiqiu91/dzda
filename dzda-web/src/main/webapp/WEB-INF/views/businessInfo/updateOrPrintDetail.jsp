<link rel="stylesheet" href="assets/css/style.css"/>
<link rel="stylesheet" href="assets/plugins/jedate/jedate.css"/>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="crumbs">
    <a href="#">机动车纸质档案</a>
    <i>&gt;</i>
    <a href="#">A库转B库交接</a>
    <i>&gt;</i>
    <span>修改/补打印</span>
</div>

<div class="box box-mini">
    <div class="filter">
        <button type="button" class="ubtn ubtn-primary fr" onclick="javascript:history.back(-1);">返回</button>
        <button type="button" class="ubtn ubtn-primary print" onclick="tablePrint()">打印</button>
        <button type="button" class="ubtn ubtn-cyan del" id="delBusiness">删除</button>
    </div>

    <div class="table" id="tablePrint">
        <div class="tit">
            <c:if test="${realJjlx==0||realJjlx==3||realJjlx==4}">B库转A库</c:if>
            <c:if test="${realJjlx==1||realJjlx==2}">A库转B库</c:if>
            登记表
        </div>
        <div class="info" >
            <div class="date" style="float: right;">
                登记时间：<span id="registerDate">${registerDate}</span> <br>
                登记人：<span id="registerPerson">${registerPersonName}</span>
            </div>
            <div class="fridgecode">
                批次号：<span id="deliveryCode">${deliveryCode}</span>&nbsp;&nbsp;
                箱号：<span id="box">${box}</span>
                <img src="data:image/png;base64,${barcode15}" class="img" id="imgSrc"/>
            </div>
        </div>
        <table style="text-align: center;margin-top:5px" class="table">
            <thead>
            <tr>
                <th style="text-align: center;"><label class="cbx"></label></th>
                <th style="text-align: center;">序号</th>
                <th style="text-align: center;">流水号</th>
                <th style="text-align: center;">档案编号</th>
                <th style="text-align: center;">号牌种类</th>
                <th style="text-align: center;">号牌号码</th>
                <th style="text-align: center;">车辆识别代号</th>
            </tr>
            </thead>
            <tbody id="tbody">
                <c:forEach items="${businessInfos}" var="businessInfo" varStatus="i">
                    <tr>
                        <td>
                        <c:if test="${businessInfo.lszt==2}">
                            <label class="cbx-disabeld" id="${businessInfo.businessId}"></label>
                        </c:if>
                        <c:if test="${businessInfo.lszt !=2}">
                            <label class="cbx" id="${businessInfo.businessId}"></label>
                        </c:if>
                        </td>
                        <td>${i.index+1}</td>
                        <td>${businessInfo.code}</td>
                        <td>${businessInfo.fileCode}</td>
                        <td>${businessInfo.plateType}</td>
                        <td>${businessInfo.plateCode}</td>
                        <td>${businessInfo.carCode}</td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
    </div>

    <div class="operate">
        <div class="pagination"></div>
    </div>
</div>
<script>
    $(function () {
      $("#delBusiness").click(function () {
          var ids = [];
          $("#tbody").find('.cbx-on').each(function() {
              ids.push($(this).attr("id"));
          })
          if (ids.length === 0) {
              $.notify({
                  type: 'danger',
                  content: '请至少选择一行'
              })
          } else {
              var okey = function() {
                  var box = "${box}";
                  var deliveryId = "${deliveryId}";
                  var deliveryCode = "${deliveryCode}";
                  $.ajax({
                      type: "POST",
                      url: "${pageContext.request.contextPath}/hbda/businessInfo/ajaxDelDeliveryRel",//路径
                      data: { 'ids': ids.toString(),'box': box,'deliveryId': deliveryId,'deliveryCode': deliveryCode},
                      dataType: "json",
                      success: function (result) {
                          if(result.result=="OK"){
                              fx.alert("删除成功!");
                              window.setTimeout(function(){
                                  location.reload(this);
                              },500);
                          }else  if(result.result=="error"){
                              fx.alert("删除失败!");
                          }
                      }, error: function () {
                          fx.alert("数据加载失败");
                      }
                  })
                  layer.closeAll();
              }
              fx.confirm('确定删除出库申请数据？', okey);
          }
      })
    })

    function tablePrint() {
//        var box = $("#box").html();
//        var deliveryCode = $("#deliveryCode").html();
//        var imgSrc = $("#imgSrc").attr("src");
//        var registerPerson = $("#registerPerson").html();
//        var registerDate = $("#registerDate").html();
//        window.location="/hbda/businessInfo/businessInfoPrint?" +
//                "box="+box+"&deliveryCode="+deliveryCode+"&imgSrc="+imgSrc+"&registerPerson="+registerPerson+"&registerDate="+registerDate;
        var bdhtml = window.document.body.innerHTML;
        var beforeHtml="<html><head><link rel='stylesheet' href='/assets/css/style.css' /></head><style type='text/css'>html,body,.wrapper{height:auto;}</style><div class='box box-mini'>"
        var afterHtml="</div></html>";
        var map = '${businessInfoMap}';
        $("#tablePrint table thead tr th")[0].remove();
        $("#tablePrint table thead tr th")[1].remove();
        $("#tbody tr td").remove();
        var json = eval('(' + map + ')');
        var i=0;
        for(var key in json){
            $("#tbody").append("<tr><td>"+(++i)+"</td><td>"+json[key].fileCode+"</td><td>"+json[key].plateType+"</td>"+
                                    "<td>"+json[key].plateCode+"</td>"+
                                    "<td>"+json[key].carCode+"</td></tr>");
        }
        window.document.body.innerHTML = beforeHtml+$("#tablePrint").html()+afterHtml;
        window.print();
        window.document.body.innerHTML = bdhtml;

    }

</script>