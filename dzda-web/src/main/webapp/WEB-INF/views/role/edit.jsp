
<div class="breadcrumb">
    <em>添加用户</em>
</div>

<div class="box c-form">
    <form action="updateRole" id="myform">
        <input type="hidden" name="rolesId"  value="${role.rolesId}" />
        <div class="item">
            <div class="txt">角色名：</div>
            <div class="cnt">
                <input type="text" value="${role.name}" class="ipt" id="name"  name="name" autocomplete="off">
            </div>
        </div>
        <div class="ft">
            <button type="submit" class="ubtn ubtn-primary"  id="jsubmit">保存</button>
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
                        name: '角色名: required;remote(ajaxVerifyRoleName)'
                    },
                    valid : function(form) {
                        form.submit();
                    }
                });
            }
        }

        _global.init();
    })(jQuery);

    function checkroleName(value){
        var roleName = $("#name").val();
        if($.trim(value)==$.trim(roleName)){
            return;
        }
        if( !$.trim(roleName)==null||!$.trim(roleName)=="" )
        {
            /*验证账号是否重复*/
            $.ajax({
                type: "post",
                dataType: "text",
                url: "ajaxVerifyRoleName",
                data: {"name": roleName},
                success: function (msg) {
                    if (msg != null && $.trim(msg) != "") {
                        layer.alert(msg);
                        $("#name").attr("value",value);
                    }
                }, error: function () {
                    layer.alert("数据加载失败!");
                }
            })
        }
    }


</script>