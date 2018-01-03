<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>奖金发放统计</div>

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
                <button type="button" class="ubtn ubtn-primary" onclick="rewardExport()">导出</button>
            </div>
        </form>
    </div>

    <div class="table">
        <script id="tableTemp" type="text/html">
            {{each list as item i}}
            <tr>
                <td>{{item.id}}</td>
                <td>{{item.userName}}</td>
                <td>{{item.phone}}</td>
                <td>{{item.typeName}}</td>
                <td>{{item.categoryName}}</td>
                <td>{{item.gatherTime}}</td>
                <td>{{item.checkTime}}</td>
                <td>{{item.sspUser}}</td>
                <td>{{item.rewardNum}}</td>
                <td>{{item.rewardOrigion}}</td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th>编号</th>
                <th>举报人姓名</th>
                <th>联系方式</th>
                <th>举报类型</th>
                <th>渠道来源</th>
                <th>采集时间</th>
                <th>审核时间</th>
                <th>审核人</th>
                <th>应发奖金金额</th>
                <th>奖金发放渠道</th>
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
                    url: '/recordCount/rewardCount'
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
                    url: '/recordCount/rewardCountExport',
                    dataType: 'json',
                    type: 'POST',
                    data: data,
                    success: function(res) {
                        fx.closeLoading();
                        if (res.result == 'SUCCESS') {
                            $.notify({
                                type: 'success',
                                title: '操作成功'
                            })
                        } else {
                            $.notify({
                                type: 'danger',
                                title: '操作失败'
                            })
                        }
                    }
                })
            }
        }
        function rewardExport() {
            fx.loading();
            var $form = $('#filterForm');
            var regionId = $('#regionId').val();
            var type = $('#type').val();
            var category = $('#category').val();
            var gstart = $('#queryStartTime').val();
            var gend = $('#queryEndTime').val();
            var cstart = $('#queryStartCheckTime').val();
            var cend = $("#queryEndCheckTime").val();

            if(gstart && gend && gstart > gend){
                fx.alert("采集开始时间不得大于结束时间");
                return false;
            }

            if(cstart && cend && cstart > cend){
                fx.alert("审核开始时间不得大于结束时间");
                return false;
            }

           window.location.href="/recordCount/rewardCountExport?regionId="+regionId+"&type="+type+"&category="+category+
                    "&queryStartTime="+gstart+"&queryEndTime="+gend+"&queryStartCheckTime="+cstart+"&queryEndCheckTime="+cend;
            fx.closeLoading();
        }
</script>
</body>
</html>