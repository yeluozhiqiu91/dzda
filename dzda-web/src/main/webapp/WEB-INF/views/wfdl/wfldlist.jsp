<%@ include file="../common/taglib.jsp"%>
<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>道路代码管理</div>

    <div class="filter">
        <form id="filterForm" name="search" action="#!" onsubmit="return false;">
            <label class="for">区域</label>
            <select name="region" id="region" class="slt select2" disabled="">
            </select>

            <label class="for">道路代码</label>
            <input type="text" class="ipt" name="code" id="code" value="${wfdlMessage.dldm}" disabled="" />
            <button type="button" class="ubtn ubtn-primary" id="jadd">新增</button>
            <a type="button" class="ubtn ubtn-primary" href="/wfdl/manager">返回列表</a>
        </form>
    </div>

    <div class="table">
        <script id="tableTemp" type="text/html">
            {{each list as item i}}
            <tr>
                <td><label class="cbx"><input type="checkbox" value="{{item.lddm}}" /></label></td>
                <td class="tl">{{item.lddm}}</td>
                <td>{{item.ldmc}}</td>
                <td>{{item.dldm}}</td>
                <td>{{item.dlmc}}</td>
                <td>{{item.xzqh}}</td>
                <td>
                    <button type="button" class="ubtn ubtn-ghost jedit" data-id="{{item.lddm}}">编辑</button>
                    <button type="button" class="ubtn ubtn-red jdel" data-id="{{item.lddm}}">删除</button>
                </td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th width="50"><label class="cbx"><input type="checkbox" /></label></th>
                <th class="tl">路段代码</th>
                <th>路段名称</th>
                <th>道路代码</th>
                <th>道路名称</th>
                <th>行政区划</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </div>

    <div class="operate">
        <div class="action">
            <button type="button" class="ubtn ubtn-red jdel">删除</button>
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
    <form modelAttribute="wfldMess" action="/wfld/addWfld" method="post">
        <input type="hidden" value="${wfdlMessage.dldm}" name="dldm" />
        <input type="hidden" value="${wfdlMessage.dlmc}" name="dlmc" />
        <input type="hidden" value="${wfdlMessage.xzqh}" name="xzqh" />
        <input type="hidden" name="oldLddm" />
        <div class="item">
            <div class="txt">道路代码</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${wfdlMessage.dldm}" disabled="" />
            </div>
        </div>
        <div class="item">
            <div class="txt">道路名称</div>
            <div class="cnt">
                <input type="text" class="ipt" value="${wfdlMessage.dlmc}" disabled="" />
            </div>
        </div>
        <div class="item">
            <div class="txt">路段代码</div>
            <div class="cnt">
                <input type="text" class="ipt" name="lddm"  />
            </div>
        </div>
        <div class="item">
            <div class="txt">路段名称</div>
            <div class="cnt">
                <input type="text" class="ipt" name="ldmc"  />
            </div>
        </div>
        <div class="item">
            <div class="txt">行政区划</div>
            <div class="cnt">
                <select class="slt select2" disabled="">
                    <option value="${wfdlMessage.xzqh}" selected>${wfdlMessage.xzqh}</option>
                </select>
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
            this.select2();
            this.filter();
            this.bindEvent();
        },
        select2: function() {
            <c:forEach var="item" items="${tRegionList}" varStatus="status">
                var s = '';
                if(${item.id} == ${wfdlMessage.region}){
                    s += 'selected';
                }
                $("select[name='region']").append("<option value='${item.id}' "+s+"> ${item.name}</option>");
                s = '';
            </c:forEach>
            $('.slt').select2();
        },
        filter: function() {
            var that = this;
            var $form = $('#filterForm');
            var page =  new Page({
                pageIndex: 1, // 当前页
                pageSize: 10, // 每页显示10条
                url: '/wfld/list?dlbm=${wfdlMessage.dldm}'
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
                $form.attr({'action':'/wfld/updWfld'});
                if (id) {
                    fx.request('/wfld/getWfld', {id: id, pid: ${wfdlMessage.dldm}}, function(res){
                        $form.find('.ipt[name="lddm"]').val(res.data.lddm);
                        $form.find('input[name="oldLddm"]').val(res.data.lddm);
                        $form.find('.ipt[name="ldmc"]').val(res.data.ldmc);
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
                    lddm: {
                        rule: 'required;digits;remote(/wfld/validWfld, oldLddm, dldm)',
                        msg: {
                            required: '违法代码不能为空',
                            remote: '违法代码重复'
                        }
                    },
                    ldmc: 'required'
                },
                valid: function(form) {
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
                    that.del({id: id, pid : ${wfdlMessage.dldm}});
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
                    that.del({id: ids.join(','), pid : ${wfdlMessage.dldm}});
                }
            });
        },
        del: function(data) {
            fx.loading();
            $.ajax({
                url: '/wfld/delWfld',
                dataType: 'json',
                type: 'GET',
                type: 'POST',
                data: data,
                success: function(res) {
                    fx.closeLoading();
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