<%@ include file="../common/taglib.jsp"%>
<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>道路路段管理</div>

    <div class="filter">
        <form id="filterForm" name="search" action="#!" onsubmit="return false;">
            <label class="for">区域</label>
            <select name="region" id="region" class="slt select2">
                <option value="">请选择</option>
            </select>

            <label class="for">道路代码</label>
            <input type="text" class="ipt" name="code" id="code" />

            <label class="for">道路名称</label>
            <input type="text" class="ipt" name="name" id="name" />

            <button type="submit" class="ubtn ubtn-primary">查询</button>
            <button type="button" class="ubtn ubtn-primary" id="jadd">新增</button>
            <button type="button" class="ubtn ubtn-primary" id="jimport">导入</button>
        </form>
    </div>

    <div class="table">
        <script id="tableTemp" type="text/html">
            {{each list as item i}}
            <tr>
                <td><label class="cbx"><input type="checkbox" value="{{item.dldm}}" /></label></td>
                <td>{{item.dldm}}</td>
                <td>{{item.dlmc}}</td>
                <td>{{item.xzqh}}</td>
                <td>{{item.region}}</td>
                <td>
                    <button type="button" class="ubtn ubtn-ghost jedit" data-id="{{item.dldm}}">编辑</button>
                    <button type="button" class="ubtn ubtn-ghost jcode" data-id="{{item.dldm}}">路段代码管理</button>
                    <button type="button" class="ubtn ubtn-red jdel" data-id="{{item.dldm}}">删除</button>
                </td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th width="50"><label class="cbx"><input type="checkbox" /></label></th>
                <th>道路代码</th>
                <th>道路名称</th>
                <th>行政区划</th>
                <th>区域</th>
                <th width="260">操作</th>
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
    <form modelAttribute="wfdlMess" action="/wfdl/addWfdl" method="post">
        <input type="hidden" class="ipt" name="oldDldm" />
        <div class="item">
            <div class="txt">道路代码</div>
            <div class="cnt">
                <input type="text" class="ipt" name="dldm" data-rule="required" />
            </div>
        </div>
        <div class="item">
            <div class="txt">道路名称</div>
            <div class="cnt">
                <input type="text" class="ipt" name="dlmc" data-rule="required" />
            </div>
        </div>
        <div class="item">
            <div class="txt">区域：</div>
            <div class="cnt">
                <select name="xzqh" class="slt select2">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="buttons" style="padding:20px 0;">
            <button type="button" class="ubtn ubtn-blue layer-close">关闭</button>
            <button type="submit" class="ubtn ubtn-blue submit" style="width:120px;">保存信息</button>
        </div>
    </form>
</div>

<div class="form layer-form" id="importForm">
    <div class="title">导入</div>
    <form action="/wfdl/uploadWfdl" method="post" enctype="multipart/form-data">
        <div class="item">
            <div class="txt">选择文件</div>
            <div class="cnt">
                <input type="file" class="ipt" name="filename" data-rule="required" />
            </div>
        </div>
        <div class="buttons" style="padding:20px 0;">
            <a href="javascript:void(0)" onclick="downloadTemplate()" class="ubtn ubtn-gray" target="_blank">下载模板</a>
            <button type="button" class="ubtn ubtn-blue layer-close" onclick="reloadPage()">关闭</button>
            <button type="submit" class="ubtn ubtn-blue submit" style="width:120px;">确定</button>
        </div>
    </form>
</div>

<script>

    function downloadTemplate(){
        window.open('/template/DlldTemplate.xls', '_self');
    }

    var _global = {
        init: function() {
            this.filter();
            this.bindEvent();
            this.select2();
        },
        select2: function() {
            <c:forEach var="item" items="${tRegionList}" varStatus="status">
                $("select[name='region']").append("<option value='${item.id}'> ${item.name}</option>");
                $("select[name='xzqh']").append("<option value='${item.id}'> ${item.name}</option>");
            </c:forEach>
            $('.slt').select2();
        },
        filter: function() {
            var that = this;
            var $form = $('#filterForm');
            var page =  new Page({
                pageIndex: 1,
                pageSize: 10,
                url: '/wfdl/list'
            });

            // 查询
            $form.on('submit', function() {
                var filterData = $form.serializeArray();
                page.request(true, filterData);
                return false;
            });
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
                $form.find('.select2').trigger('change.select2');
                $form.prev('.title').html('新增');
                $form.find('.ipt[name="oldDldm"]').val('');
                layer.open({
                    area: '540px',
                    type: 1,
                    title: false,
                    content: $temp
                })
            });

            // 编辑
            $tbody.on('click', '.jedit', function() {
                opType = 'edit';
                var id = $(this).data('id');
                $form[0].reset();
                $form.prev('.title').html('编辑');
                $form.removeAttr('action');
                $form.attr({'action':'/wfdl/updWfdl'});
                if (id) {
                    fx.request('/wfdl/getWfdl', {id: id}, function(res){
                        $form.find('.ipt[name="dldm"]').val(res.data.dldm);
                        $form.find('.ipt[name="oldDldm"]').val(res.data.dldm);
                        $form.find('.ipt[name="dlmc"]').val(res.data.dlmc);
                        $form.find('.slt[name="xzqh"]').val(res.data.region);
                        $form.find('.select2').trigger('change.select2');
                        layer.open({
                            area: '540px',
                            type: 1,
                            title: false,
                            content: $temp
                        });
                    });
                }
            });

            // 验证
            $form.validator({
                fields: {
                    dldm: {
                        rule: 'required;digits;remote(/wfdl/validWfdl, oldDldm)',
                        msg: {
                            required: '道路代码不能为空',
                            remote: '道路代码重复'
                        }
                    },
                    dlmc: 'required',
                    xzqh: 'required'
                },
                valid: function(form) {
                    form.submit();
                    layer.closeAll();
                    return false;
                }
            });

            // 道路代码管理
            $tbody.on('click', '.jcode', function() {
                var id = $(this).data('id');
                window.location.href = '/wfld/manager?id='+id;
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
                })
                if (ids.length === 0) {
                    $.notify({
                        type: 'warning',
                        content: '请至少选择一行'
                    });
                } else {
                    that.del({id: ids.join(',')});
                }
            });

            var $importForm = $('#importForm');
            $('#jimport').on('click', function() {
                $importForm.find('.cnt').html('<input type="file" class="ipt" name="filename"  data-rule="required" data-msg="请选择上传文件"  />');
                layer.open({
                    area: '540px',
                    type: 1,
                    title: false,
                    content: $importForm
                })
            });

            $importForm.validator({
                valid: function(form) {
                    $(form).ajaxSubmit({
                        success: function(data) {
                            layer.alert(data);
                        }
                    });
                }
            });
        },
        del: function(data) {
            fx.loading();
            $.ajax({
                url: '/wfdl/delMes',
                dataType: 'json',
                type: 'GET',
                type: 'POST',
                data: data,
                success: function(res) {
                    fx.closeLoading();
                    if(res.result = 'SUCCESS'){
                        window.location.reload(true);
                    }
                }
            });
        }
    }

    function reloadPage(){
        window.location.reload(true);
    }
</script>