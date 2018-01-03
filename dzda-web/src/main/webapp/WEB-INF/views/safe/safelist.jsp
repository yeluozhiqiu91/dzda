<%@ include file="../common/taglib.jsp"%>
<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>安全管理</div>
    <div class="filter">
        <form id="filterForm" name="search" action="#!" onsubmit="return false;">

            <label class="for">安全码</label>
            <input type="text" class="ipt" name="code" id="code"  />

            <label class="for">渠道来源</label>
            <select name="source" id="source" class="slt select2">
                <option value="">请选择</option>
            </select>
            <button type="submit" class="ubtn ubtn-primary">查询</button>
            <button type="button" class="ubtn ubtn-primary" id="jadd">新增</button>
        </form>
    </div>

    <div class="table">
        <script id="tableTemp" type="text/html">
            {{each list as item i}}
            <tr>
                <td><label class="cbx"><input type="checkbox" value="{{item.safecode}}" /></label></td>
                <td>{{item.safecode}}</td>
                <td>{{item.categoryid}}</td>
                <td>{{item.isvalidate == 1 ? '生效' : '失效'}}</td>
                <td>
                    <button type="button" class="ubtn ubtn-ghost jedit" data-id="{{item.safecode}}">{{item.isvalidate == 0 ? '生效' : '失效'}}</button>
                </td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th width="50"><label class="cbx"><input type="checkbox" /></label></th>
                <th>安全码</th>
                <th>渠道来源</th>
                <th>是否生效</th>
                <th width="180">操作</th>
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
    <form modelAttribute="validate" action="/safe/addValidate" method="post">
        <div class="item">
            <div class="txt">安全码：</div>
            <div class="cnt">
                <input type="text" class="ipt" name="safecode" placeholder="安全码" />
            </div>
        </div>
        <div class="item">
            <div class="txt">渠道来源：</div>
            <div class="cnt">
                <select name="categoryid" class="slt select2">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="item">
            <div class="txt">开始时间：</div>
            <div class="cnt">
                <input type="text" class="ipt date" name="starttime" placeholder="开始时间" />
            </div>
        </div>
        <div class="item">
            <div class="txt">结束时间：</div>
            <div class="cnt">
                <input type="text" class="ipt date" name="endtime" placeholder="结束时间" />
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
            <c:forEach var="item" items="${CategoryList}" varStatus="status">
                $("select[name='source']").append("<option value='${item.dvalue}'> ${item.dname}</option>");
                $("select[name='categoryid']").append("<option value='${item.dvalue}'> ${item.dname}</option>");
            </c:forEach>
            $('.slt').select2();
        },
        filter: function() {
            var that = this;
            var $form = $('#filterForm');
            var page =  new Page({
                pageIndex: 1, // 当前页
                pageSize: 10, // 每页显示10条
                url: '/safe/list'
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
                $operate = $('.operate'),
                opType = 'add';

            // 新增
            $('#jadd').on('click', function() {
                opType = 'add';
                $form[0].reset();
                $form.find('.select2').trigger('change.select2');
                $form.prev('.title').html('新增');
                layer.open({
                    area: '540px',
                    type: 1,
                    title: false,
                    content: $temp
                });
            });

            // 失效
            $tbody.on('click', '.jedit', function() {
                var id = $(this).data('id');
                if (id) {
                    fx.request('/safe/changeState', {id: id}, function(res){
                        $.notify({
                            'type': 'success',
                            'content': '操作成功'
                        });
                        window.location.reload(true);
                    });
                }
            });

            // 验证
            $form.validator({
                fields: {
                    safecode: {
                        rule: 'safecode: required;remote(/safe/validSafe);',
                        msg: {
                            required: '安全码不能为空',
                            remote: '安全码重复'
                        }
                    },
                    categoryid: 'required',
                    starttime: 'required',
                    endtime: 'required'
                },
                valid: function(form) {
                    form.submit();
                    // that.post($(form).serialize());
                    layer.closeAll();
                    return false;
                }
            });

            // 批量删除
            $operate.on('click', '.jdel', function() {
                var ids = '';
                $tbody.find('td .cbx-on').each(function(key, val) {
                    ids += $(val).find("input[type='checkbox']").val() + ',';
                });
                var id = ids.substring(0, ids.length - 1);
                if(id){
                    fx.request('/safe/delSafeByIds', {id: id}, function(res){
                        if(res.result = 'SUCCESS'){
                            window.location.reload(true);
                        }
                    });
                }
            });

            $form.find('.date').datetimepicker({
                format: 'yyyy-mm-dd'
            });
        }
    }
</script>