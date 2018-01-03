<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>按地区统计</div>

    <div class="filter filter-res">
        <form id="filterForm" action="#!">
            <div class="group">
                <label class="for">区域</label>
                <select name="regionId" id="regionId" class="slt select2">
                    <option value="">请选择</option>
                    <option value="15">江岸区</option>
                    <option value="16">江汉区</option>
                    <option value="17">硚口区</option>
                    <option value="18">汉阳区</option>
                    <option value="19">武昌区</option>
                    <option value="20">青山区</option>
                    <option value="21">洪山区</option>
                    <option value="22">东西湖区</option>
                    <option value="23">汉南区</option>
                    <option value="24">蔡甸区</option>
                    <option value="25">江夏区</option>
                    <option value="26">黄陂区</option>
                    <option value="27">新洲区</option>
                    <option value="28">经济技术开发区</option>
                    <option value="29">东湖新技术开发区</option>
                    <option value="30">化学工业区</option>
                    <option value="31">东湖生态旅游风景区</option>
                </select>
            </div>

            <div class="group">
                <label class="for">举报类型</label>
                <select name="type" id="type" class="slt select2">
                    <option value="">请选择</option>
                    <option value=1>图片</option>
                    <option value=2>视频</option>
                    <option value=3>图片和视频</option>
                    <option value=5>电话</option>
                </select>
            </div>

            <div class="group">
                <label class="for">渠道来源</label>
                <select name="category" id="category" class="slt select2">
                    <option value="">请选择</option>
                    <option value=2>交警APP</option>
                    <option value=3>微信</option>
                    <option value=4>支付宝</option>
                    <option value=5>行车记录仪</option>
                    <option value=6>交警官网</option>
                    <option value=7>高德</option>
                    <option value=8>行车记录仪二代</option>
                    <option value=20>其他2</option>
                </select>
            </div>

            <div class="group group2">
                <label class="for">采集时间</label>
                <input type="text" class="ipt date" name="queryStartTime" id="queryStartTime" placeholder="采集时间" />
                <i>-</i>
                <input type="text" class="ipt date" name ="queryEndTime" id="queryEndTime" placeholder="采集时间" />
            </div>
            <div class="group group2">
                <label class="for">审核时间</label>
                <input type="text" class="ipt date" name="queryStartCheckTime" id="queryStartCheckTime" placeholder="审核时间" />
                <i>-</i>
                <input type="text" class="ipt date" name ="queryEndCheckTime" id="queryEndCheckTime" placeholder="审核时间" />
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
                <td>{{item.areaName}}</td>
                <td>{{item.typeName}}</td>
                <td>{{item.categoryName}}</td>
                <td>{{item.gatherTime}}</td>
                <td>{{item.checkTime}}</td>
                <td>{{item.unCheck}}</td>
                <td>{{item.checkunPassed}}</td>
                <td>{{item.unTest}}</td>
                <td>{{item.testPassed}}</td>
                <td>{{item.testunPassed}}</td>
                <td>{{item.exported}}</td>
                <td>{{item.unExported}}</td>
                <td>{{item.totalCount}}</td>
                <td>
                    <button type="button" class="ubtn ubtn-ghost" onclick="regionCountExport('{{item.areaName}}','{{item.regionId}}','{{item.type}}','{{item.category}}','{{item.gatherTime}}','{{item.checkTime}}')">导出</button>
                </td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th>区域</th>
                <th>举报类型</th>
                <th>渠道来源</th>
                <th>采集时间段</th>
                <th>审核时间段</th>
                <th>待审核</th>
                <th>审核不通过</th>
                <th>待检测</th>
                <th>检测通过</th>
                <th>检测不通过</th>
                <th>已导出</th>
                <th>未导出</th>
                <th>合计</th>
                <th width="60">操作</th>
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
                var $gstart = $('#queryStartTime');
                var $gend = $('#queryEndTime');
                var $cstart = $('#queryStartCheckTime');
                var $cend = $("#queryEndCheckTime");
                var page =  new Page({
                    pageIndex: 1, // 当前页
                    pageSize: 10, // 每页显示10条
                    url: '/recordCount/regionCount'
                })

                $gstart.datetimepicker({
                    format: 'yyyy/mm/dd hh:ii:ss',
                    minView: 'hour'
                });
                $gend.datetimepicker({
                    format: 'yyyy/mm/dd hh:ii:ss',
                    minView: 'hour'
                })
                $cstart.datetimepicker({
                    format: 'yyyy/mm/dd hh:ii:ss',
                    minView: 'hour'
                });
                $cend.datetimepicker({
                    format: 'yyyy/mm/dd hh:ii:ss',
                    minView: 'hour'
                })

                // 查询
                $form.on('submit', function() {
                    var gstart = $gstart.val(),
                            gend = $gend.val();

                    if(gstart && gend && gstart > gend){
                        fx.alert("采集开始时间不得大于结束时间");
                        return false;
                    }
                    var cstart = $cstart.val(),
                            cend = $cend.val();

                    if(cstart && cend && cstart > cend){
                        fx.alert("审核开始时间不得大于结束时间");
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
    function regionCountExport(areaName,regionId,type,category,gatherTimeRange,checkTimeRange) {
        window.location.href="/recordCount/regionCountExport?areaName="+areaName+"&regionId="+regionId+"&type="+type+"&category="+category+"&gatherTimeRange="+gatherTimeRange+"&checkTimeRange="+checkTimeRange;
    }
</script>
</body>
</html>