<%@ include file="../common/taglib.jsp"%>
<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>违法代码管理</div>

    <div class="filter">
        <form id="filterForm" name="search" action="#!" onsubmit="return false;">
            <label class="for">违法类型</label>
            <select class="slt select2" name="pid" id="pid">
                <option value="-2" >请选择</option>
            </select>

            <label class="for">违法代码</label>
            <input type="text" class="ipt" name="code" id="code"  />
            <button type="submit" class="ubtn ubtn-primary">查询</button>
            <button type="button" class="ubtn ubtn-primary" id="jadd">新增</button>
            <a type="button" class="ubtn ubtn-primary" href="/law/manager">返回列表</a>
        </form>
    </div>

    <div class="table">
        <script id="tableTemp" type="text/html">
            {{each list as item i}}
            <tr>
                <td><label class="cbx"><input type="checkbox" value="{{item.id}}" /></label></td>
                <td class="tl">{{item.lawName}}</td>
                <td>{{item.lawCode}}</td>
                <td>
                    <button type="button" class="ubtn ubtn-ghost jedit" data-id="{{item.id}}">编辑</button>
                    <button type="button" class="ubtn ubtn-red jdel" data-id="{{item.id}}">删除</button>
                </td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th width="50"><label class="cbx"><input type="checkbox" /></label></th>
                <th class="tl">违法类型</th>
                <th>违法代码</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </div>

    <div class="operate">
        <div class="action">
            <button type="button" class="ubtn ubtn-red jdel">批量删除</button>
            <select name="" class="slt select2">
                <option value="10">每页10行</option>
                <option value="20">每页20行</option>
                <option value="50">每页50行</option>
                <option value="100">每页100行</option>
            </select>
        </div>
        <div class="pagination"></div>
    </div>
</div>


<div class="form layer-form" id="tempForm">
    <div class="title">新增</div>
    <form modelAttribute="lawcode" action="/law/addLaw" method="post">
        <input type="hidden" class="ipt" name="id" value="0" />
        <input type="hidden" name="lawName" />
        <input type="hidden" name="oldLawCode" />
        <div class="item">
            <div class="txt">违法类型：</div>
            <div class="cnt">
                <select class="slt select2" name="parentId">
                </select>
            </div>
        </div>
        <div class="item">
            <div class="txt">违法代码：</div>
            <div class="cnt">
                <input type="text" class="ipt" name="lawCode" />
            </div>
        </div>
        <div class="buttons" style="padding:20px 0;">
            <button type="button" class="ubtn ubtn-blue layer-close">关闭</button>
            <button type="submit" class="ubtn ubtn-blue submit" style="width:120px;">保存信息</button>
        </div>
    </form>
</div>

<script>
    var _global = {
        init: function() {
            this.filter();
            this.bindEvent();
            this.select2();
        },
        select2: function() {
            $('.slt').select2();
            <c:forEach var="item" items="${lawCodeList}" varStatus="status">
                var s = '';
                if(${item.id} == ${parentId}){
                    s += 'selected';
                }
                $("select[name='pid']").append("<option value='${item.id}' "+s+"> ${item.lawName}</option>");
                $("select[name='parentId']").append("<option value='${item.id}' "+s+"> ${item.lawName}</option>");
                s = '';
            </c:forEach>
        },
        filter: function() {
            var that = this;
            var $form = $('#filterForm');
            var page =  new Page({
                pageIndex: 1, // 当前页
                pageSize: 10, // 每页显示10条
                url: '/law/list?fid=${parentId}'
            })

            // 查询
            $form.on('submit', function() {
                var filterData = $form.serializeArray();
                page.request(true, filterData);
                return false;
            })
        },
        bindEvent: function() {
            var that = this,
                $temp = $('#tempForm'),
                $form = $temp.find('form'),
                $tbody = $('#tbody'),
                opType = 'add';

            // 新增
            $('#jadd').on('click', function() {
                opType = 'add';
                $form[0].reset();
                $form.prev('.title').html('新增');
                $form.find('.select2').prop('disabled', false).trigger('change.select2');
                layer.open({
                    area: '540px',
                    type: 1,
                    title: false,
                    content: $temp
                });
            });

            // 编辑
            $tbody.on('click', '.jedit', function() {
                opType = 'edit';
                var id = $(this).data('id');
                $form[0].reset();
                $form.prev('.title').html('编辑');
                $form.removeAttr('action');
                $form.attr({'action':'/law/updLaw'});
                if (id) {
                    fx.request('/law/getLaw', {id: id}, function(res){
                        $form.find('.ipt[name="id"]').val(id);
                        $form.find('.ipt[name="lawCode"]').val(res.data.lawCode);
                        $form.find('input[name="oldLawCode"]').val(res.data.lawCode);
                        layer.open({
                            area: '540px',
                            type: 1,
                            title: false,
                            content: $temp
                        })
                    });
                }
            });

            // 验证
            $form.validator({
                fields: {
                    lawCode: {
                        rule: 'required;remote(/law/validLawCode, oldLawCode)',
                        msg: {
                            required: '违法代码不能为空',
                            remote: '违法代码重复'
                        }
                    },
                    parentId: 'required'
                },
                valid: function(form) {
                    var text = ''
                    $('.select2-selection__rendered').each(function(){
                        var id = $(this).attr('id');
                        if(id.substring(0, 16) == 'select2-parentId')
                            text = id;
                    });
                    var name = $('#'+text).text();
                    $('input[name="lawName"]').val(name);
                    form.submit();
                    layer.closeAll();
                    return false;
                }
            });

            // 删除
            $tbody.on('click', '.ubtn-red', function() {
                var id = $(this).data('id');
                layer.confirm('确认删除吗？', function(key) {
                    layer.close(key);
                    that.del({id: id});
                });
            });

            // 批量删除
            $('.action').find('.jdel').on('click', function() {
                var ids = [];
                $tbody.find('.cbx input:checked').each(function() {
                    ids.push(this.value);
                });
                if (ids.length === 0) {
                    $.notify({
                        type: 'warning',
                        content: '请至少选择一行'
                    });
                } else {
                    that.del({id: ids.join(',')});
                }
            });
        },
        del: function(data) {
            $.ajax({
                url: '/law/delTDic',
                dataType: 'json',
                type: 'GET',
                type: 'POST',
                data: data,
                success: function(res) {
                    if(res.result = 'SUCCESS'){
                        $.notify({
                            type: 'success',
                            title: '删除成功'
                        });
                        window.location.reload(true);
                    }
                }
            });
        }
    }
</script>