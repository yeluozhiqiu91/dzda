<link rel="stylesheet" href="assets/css/style.css"/>
<link rel="stylesheet" href="assets/plugins/jedate/jedate.css"/>

<div class="crumbs">
    <a href="#">机动车纸质档案</a>
    <i>&gt;</i>
    <a href="#">导出未签收流水</a>
    <i>&gt;</i>
    <span>导出未签收流水</span>
</div>

<div class="box" id="select">
    <div class="filter">
        <form name="filterForm" id="filterForm" action="/hbda/businessInfo/exportBoxNotSignToExcel" >
            <label class="for">箱号</label>
            <input class="ipt" id="box" name="box" type="text" placeholder="请输入箱号信息">
            <button type="button"  id="ajaxGetBusinessInfo" class="ubtn ubtn-primary">确定</button>
        </form>
    </div>
</div>

<script>
    $(function () {
        $("#ajaxGetBusinessInfo").click(function () {
            var box = $("#box").val();
            if(box==""||box==null){
                fx.alert("箱号不能为空!");
            }else
            {
                $("#filterForm").submit();
                $("#box").val("");
            }
        })
    })

</script>
