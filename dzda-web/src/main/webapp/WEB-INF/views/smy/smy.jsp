<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta name="renderer" content="ie-stand">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta content="initial-scale=1.0,minimum-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <meta http-equiv="X-UA-Compatible" content="IE=9;IE=8;IE=7;ie=edge">
    <title>历史档案影像</title>
    <%--<meta name="renderer" content="ie-comp">--%>
    <%--<meta name="renderer" content="ie-stand">--%>
    <%--<jsp:include page="../common/header.jsp"></jsp:include>--%>

    <style>
        INPUT{float: left;margin:10px}
        .dy {
            float: right;
            margin-top: 18px;
            margin-right: 50px;
        }
        .button.is-warning{background-color:#f39c12;border-width:0;color:#fff;height:25px;}
    </style>
    <SCRIPT LANGUAGE=JavaScript>
        /*window.onload=function(){
            var flag = ${flag};
            if(!flag){
                    $(".cls").hide;
                var cls= document.getElementById("cls");
                console.log(cls)
                cls.style.display="none"
            }
        }*/
        function buttonSubmit_onclick() //http提交
        {
            var result = PowerScanOcx.Submit('${path}','${lsh}_${id}');
            if(0 !=result.length) {
                alert(result);  //第二个参数为批次的ID号,如果每次扫一个人的,应该是人员的编号
                if(result.indexOf("已成功上传扫描")>-1){
                    window.close();
                }
                //返回值为提交页面返回内容,可用于判断是否上传成功及错误原因.
            }

        }
        function buttonPrint_onclick() {
            PowerScanOcx.Print();
        }

        function buttonSave_onclick() //本地保存
        {
            //只演示了保存第一页图象
            if(PowerScanOcx.GetPageScanned(0)) //GetPageScanned函数为判断是否为待扫描图片
            {
                PowerScanOcx.SavePageAs(0,'C:\\测试\\Img001.jpg',2,0,75);
                alert('第一页保存为C:\\测试\\Img001.jpg');
            }
        }

        function buttonScan_onclick() //批量扫描
        {
            PowerScanOcx.AppendScan();
        }

        function buttonScanSingle_onclick() //单个扫描
        {
            PowerScanOcx.ReplaceScan(); //单个扫描的设计和重扫一样的，先选个页面，扫描到此页面
        }

        function buttonInsertScan_onclick() //插扫
        {
            PowerScanOcx.InsertScan();
        }

        function buttonReplaceScan_onclick() //重扫
        {
            PowerScanOcx.ReplaceScan();
        }

        function buttonCombineScan_onclick() //双面合并扫描
        {
            PowerScanOcx.CombineScan();
        }

        function buttonScanSetup_onclick() //显示扫描设置
        {
            PowerScanOcx.ShowScanDialog();
        }

        function buttonProcSetup_onclick() //显示图像处理设置
        {
            PowerScanOcx.ShowProcDialog();
        }

        function buttonDeletePage_onclick() //删除选中页面
        {
            PowerScanOcx.DeleteSelPages();
        }

        function buttonClearPage_onclick() //删除全部页面
        {
            PowerScanOcx.ClearPages();
        }

        function buttonBarcode_onclick() //识别条码
        {
            if(PowerScanOcx.SelectedPage>=0)
            {
                alert(PowerScanOcx.GetPageBarcode(PowerScanOcx.SelectedPage));
            }
        }

        function buttonBarcode2D_onclick() //识别二维条码
        {
            alert(PowerScanOcx.GetPageName(PowerScanOcx.SelectedPage));
            //if(PowerScanOcx.SelectedPage>=0)
            {
                //alert(PowerScanOcx.GetPageBarcode2D(PowerScanOcx.SelectedPage));
            }
        }

        function buttonDeskew_onclick()
        {
            PowerScanOcx.ProcSelPages(8,0);
        }

        function buttonBrightContrast_onclick()
        {
            PowerScanOcx.ProcSelPages(12,0);
        }

        function buttonSelectScanner_onclick() {
            PowerScanOcx.SelectScanner();
        }

    </SCRIPT>
</head>
<body>
<div id="cls" style="width:100%">
    <INPUT type="button" value="设置扫描仪" class="button is-warning" id=buttonScanSetup name=buttonScanSetup  language=JavaScript onclick="buttonScanSetup_onclick()">
    <INPUT type="button" value="选择扫描仪界面" class="button is-warning" id=buttonSelectScanner name=buttonSelectScanner  language=JavaScript onclick="buttonSelectScanner_onclick()">
    <INPUT type="button" value="扫描" class="button is-warning" id=buttonScanSingle name=buttonScanSingle language=JavaScript onclick="buttonScanSingle_onclick()">
    <INPUT type="button" value="替换扫描" class="button is-warning" id=buttonReplaceScan name=buttonReplaceScan language=JavaScript onclick="buttonReplaceScan_onclick()">
    <INPUT type="button" value="插入扫描" class="button is-warning" id=buttonInsertScan name=buttonInsertScan language=JavaScript onclick="buttonInsertScan_onclick()">
    <INPUT type="button" value="上传" class="button is-warning" id=buttonSubmit name=buttonSubmit language=JavaScript onclick="buttonSubmit_onclick()">
    <%--<INPUT type="button" value="打印" class="button is-warning" id=buttonPrint name=buttonSubmit language=JavaScript onclick="buttonPrint_onclick()">--%>
    <%--<a class="dy" href="assets/dy/PowerScanActiveX_WH_5.7.exe" target="_blank">下载控件</a>--%>
    <br>
</div>
<OBJECT id=PowerScanOcx style="MARGIN-LEFT:5px; WIDTH: 90%; HEIGHT:90%" classid="clsid:691D0C84-8E90-4401-AA96-DE255EE68EC9">
    <PARAM NAME="ShowToolBar" VALUE="false">
    <PARAM NAME="ShowToolBar1" VALUE="true">
    <PARAM NAME="ShowListView" VALUE="true">
    <PARAM NAME="ControlMode" VALUE="1">
    <PARAM NAME="WaterMark" VALUE="Water Mark">
    <PARAM NAME="PicTypes" VALUE="${nameDetails}">
    <PARAM NAME="PicNames" VALUE="${msg}">
    <PARAM NAME="ComboFontsize" VALUE="12">
</OBJECT>


<SCRIPT LANGUAGE=JavaScript>
</SCRIPT>

</body>
</html>