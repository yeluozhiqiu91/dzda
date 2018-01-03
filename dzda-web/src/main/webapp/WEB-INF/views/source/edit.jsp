<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="crumbs">
    <span>修改资源</span>
</div>

<div class="box c-form">
    <form action="/updateSource" id="myform">
        <input type="hidden" value="${source.moduleId}" name="moduleId">
        <div class="item">
            <div class="txt">名称：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${source.name}" name="name" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">请求地址：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${source.path}" name="path" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">排序：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${source.orderNumber}" name="orderNumber" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">备注：</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${source.remarks}" name="remarks" autocomplete="off">
            </div>
        </div>
        <div class="item">
            <div class="txt">是否生效：</div>
            <div class="cnt cbxs">
                <label><input type="radio" name="status" class="status"  value="1" <c:if test="${source.status==1}">
                    checked
                </c:if>/>生效</label>
                <label><input type="radio" name="status" class="status" value="0" <c:if test="${source.status==0}">
                    checked
                </c:if>/>失效</label>
            </div>
        </div>
        <div class="ft">
            <button type="submit" class="ubtn ubtn-primary" id="jsubmit">保存</button>
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
                        name: '名称: required',
                        orderNumber: '排序: required;digits',
                        remarks:'备注：required',
                        status: '是否生效: checked'
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