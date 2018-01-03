<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>字典管理</div>

    <div class="filter">
        <form id="filterForm" name="search" action="#!" onsubmit="return false;">

            <label class="for">字典类型</label>
            <input type="text" class="ipt" name="type" id="type" />
            <label class="for">字典名称</label>
            <input type="text" class="ipt" name="name" id="name" />

            <button type="submit" class="ubtn ubtn-primary">查询</button>
            <button type="button" class="ubtn ubtn-primary" id="jadd">新增</button>
        </form>
    </div>

    <div class="table">
        <script id="tableTemp" type="text/html">
            {{each list as item i}}
            <tr>
                <td><label class="cbx"><input type="checkbox" value="{{item.id}}" /></label></td>
                <td class="tl">{{item.dname}}</td>
                <td>{{item.dvalue}}</td>
                <td>{{item.dtype}}</td>
                <td>{{item.ddiscribe}}</td>
                <td>
                    <button type="button" class="ubtn ubtn-ghost jedit" data-id="{{item.id}}">编辑</button>
                </td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th width="50"><label class="cbx"><input type="checkbox" /></label></th>
                <th class="tl">字典名称</th>
                <th>字典值</th>
                <th>字典类型</th>
                <th>字典说明</th>
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
    <form modelAttribute="dictionary" action="/dic/addDic" method="post">
        <input type="hidden" class="ipt" name="id" value="0"/>
        <div class="item">
            <div class="txt">字典名称：</div>
            <div class="cnt">
                <input type="text" class="ipt" name="dname" data-rule="required" placeholder="字典名称" />
            </div>
        </div>
        <div class="item">
            <div class="txt">字典值：</div>
            <div class="cnt">
                <input type="text" class="ipt" name="dvalue"  data-rule="required" placeholder="字典值" />
            </div>
        </div>
        <div class="item">
            <div class="txt">字典类型：</div>
            <div class="cnt">
                <input type="text" class="ipt" name="dtype"  data-rule="required" placeholder="字典类型" />
            </div>
        </div>
        <div class="item">
            <div class="txt">字典说明：</div>
            <div class="cnt">
                <input type="text" class="ipt" name="ddiscribe"  data-rule="required" placeholder="字典说明" />
            </div>
        </div>
        <div class="tips">
            <p>注意：</p>
            <p>1、添加字典值为地区时，字典类型为“Region”，且必须按“省”-“市”-“区”的先后顺序添加，同一级别无先后顺序。</p>
            <p>2、添加字典值为举报类型时，字典类型为“UploadType”。添加字典值为用户渠道时，字典类型为“Category”。</p>
            <p>3、添加字典值为审核意见时，字典类型为“CheckAdvice”。</p>
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
        },
        filter: function() {
            var that = this;
            var $form = $('#filterForm');
            var page =  new Page({
                pageIndex: 1, // 当前页
                pageSize: 10, // 每页显示10条
                url: '/dic/list'
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
                $form.attr({'action':'/dic/updateDic'});
                if (id) {
                    fx.request('/dic/getTDicById', {id: id}, function(res){
                        $form.find('.ipt[name="id"]').val(id);
                        $form.find('.ipt[name="dname"]').val(res.data.dname);
                        $form.find('.ipt[name="dvalue"]').val(res.data.dvalue);
                        $form.find('.ipt[name="dtype"]').val(res.data.dtype);
                        $form.find('.ipt[name="ddiscribe"]').val(res.data.ddiscribe);
                        layer.open({
                            area: '540px',
                            type: 1,
                            title: false,
                            content: $temp
                        });
                    });
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
                    fx.request('/dic/delTDic', {id: id}, function(res){
                        if(res.result = 'SUCCESS'){
                            window.location.reload(true);
                        }
                    });
                }
            });
        }
    }
</script>