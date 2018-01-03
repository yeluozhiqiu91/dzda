
<div class="breadcrumb">
    <em>添加角色</em>
</div>

<div class="box c-form">
    <form action="addRole" id="myform">
        <div class="item">
            <div class="txt">角色名：</div>
            <div class="cnt">
                <input type="text" data-msg-remote="角色已存在" class="ipt" id="name" value="" name="name" autocomplete="off">
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

</script>