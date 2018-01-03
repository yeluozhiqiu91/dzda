<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>按审核人员统计</div>

    <div class="filter filter-res">
        <form id="filterForm" action="#!">

            <div class="group">
                <label class="for">操作人</label>
                <input type="text" class="ipt" id="userName" name="userName"/>
            </div>

            <div class="group group2">
                <label class="for">采集时间</label>
                <input type="text" class="ipt date" name="queryStartTime" id="queryStartTime" placeholder="采集时间" />
                <i>-</i>
                <input type="text" class="ipt date" name ="queryEndTime" id="queryEndTime" placeholder="采集时间" />
            </div>
            <div class="group group9">
                <button type="submit" class="ubtn ubtn-primary">查询</button>
            </div>
        </form>
    </div>

    <div class="table">
        <script id="tableTemp" type="text/html">
            {{each list as item i}}
            <tr>
                <td>{{item.userName}}</td>
                <td>{{item.gatherTime}}</td>
                <td>{{item.pass}}</td>
                <td>{{item.unPass}}</td>
                <td>{{item.checkPass}}</td>
                <td>{{item.unCheckPass}}</td>
                <td>{{item.totalCount}}</td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th>操作人</th>
                <th>时间段</th>
                <th>审核通过</th>
                <th>审核不通过</th>
                <th>检测通过</th>
                <th>检测不通过</th>
                <th>合计</th>
            </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
    </div>

    <div class="operate">
        <div class="action">
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
                var $start = $('#queryStartTime');
                var $end = $('#queryEndTime');

                var page =  new Page({
                    pageIndex: 1, // 当前页
                    pageSize: 10, // 每页显示10条
                    url: '/recordCount/userCount'
                })

                $start.datetimepicker({
                    format: 'yyyy/mm/dd hh:ii:ss',
                    minView: 'hour'
                });
                $end.datetimepicker({
                    format: 'yyyy/mm/dd hh:ii:ss',
                    minView: 'hour'
                })

                // 查询
                $form.on('submit', function() {
                    var start = $start.val(),
                            end = $end.val();

                    if(start && end && start > end){
                        fx.alert("采集开始时间不得大于结束时间");
                        return false;
                    }
                    var filterData = $form.serializeArray();
                    page.request(true, filterData);
                    return false;
                })
            },
            bindEvent: function() {
                var that = this,
                        $temp = $('#tempForm'),
                        $form = $temp.find('form'),
                        $tbody = $('#tbody');

                // 批量不通过
                $('.action').find('.jdel').on('click', function() {
                    var ids = [];
                    $tbody.find('.cbx input:checked').each(function() {
                        ids.push(this.value);
                    })
                    if (ids.length === 0) {
                        $.notify({
                            type: 'warning',
                            content: '请至少选择一行'
                        })
                    } else {
                        that.del({ids: ids.join(',')});
                    }
                })
            },
            del: function(data) {
                fx.loading();
                $.ajax({
                    url: 'http://del.cn',
                    dataType: 'json',
                    type: 'GET',
                    type: 'POST',
                    data: data,
                    success: function(res) {
                        fx.closeLoading();
                        if (res.data.result == 0) {
                            $.notify({
                                type: 'danger',
                                title: '操作失败'
                            })
                        } else {
                            $.notify({
                                type: 'success',
                                title: '操作成功'
                            })
                        }
                    }
                })
            }
        }

</script>
</body>
</html>