<div class="wrapper box">
    <div class="breadcrumb"><i class="ico ico-site"></i>违法审核</div>

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

            <div class="group">
                <label class="for">用户类型</label>
                <select name="grade" id="grade" class="slt select2">
                    <option value="">请选择</option>
                    <option value=0>普通用户</option>
                    <option value=1>vip用户</option>
                    <option value=2>城管</option>
                    <option value=3>警员</option>
                </select>
            </div>

            <div class="group">
                <label class="for">路段类型</label>
                <select name="roadType" id="roadType" class="slt select2">
                    <option value="">请选择</option>
                    <option value=1>非严管路段</option>
                    <option value=3>严管路段</option>
                </select>
            </div>

            <div class="group">
                <label class="for">违法类型</label>
                <select type="lawType" name="lawType" class="slt select2">
                    <option value="">请选择</option>
                    <option value="5">报废机动车上路行驶</option>
                    <option value="7">机动车闯红灯</option>
                    <option value="6">机动车逆向行驶</option>
                    <option value="188">机动车违反规定使用公交专用车道</option>
                    <option value="4">驾驶拼装机动车</option>
                    <option value="1">交通事故肇事逃逸</option>
                    <option value="9">进入导向车道，不按指示方向行驶</option>
                    <option value="186">客车严重超员</option>
                    <option value="8">违反禁止标线指示</option>
                    <option value="166">违停</option>
                    <option value="2">伪造、变造机动车号牌</option>
                    <option value="3">伪造、变造行驶证</option>
                </select>
            </div>

            <div class="group">
                <label class="for">车牌号码</label>
                <input type="text" class="ipt" name="carNum" id="carNum" placeholder="车牌号码" />
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
                <td><label class="cbx"><input type="checkbox" value="{{item.id}}" /></label></td>
                <td class="tl">{{i+1}}</td>
                <td>{{item.araName}}</td>
                <td>{{item.carNum}}</td>
                <td>{{item.categoryName}}</td>
                <td>{{item.gatherTime}}</td>
                <td>{{item.typeName}}</td>
                <td>{{item.gradeName}}</td>
                <td>{{item.roadTypeName}}</td>
                <td>{{item.checkStatus}}</td>
                <td>
                    <a  onclick="checkRecordView('{{item.id}}') " class="ubtn ubtn-ghost">审核</a>
                </td>
            </tr>
            {{/each}}
        </script>

        <table class="tc">
            <thead>
            <tr>
                <th width="50"><label class="cbx"><input type="checkbox" /></label></th>
                <th class="tl">编号</th>
                <th>区域</th>
                <th>车牌号码</th>
                <th>渠道来源</th>
                <th>采集时间</th>
                <th>举报类型</th>
                <th>用户类型</th>
                <th>路段类型</th>
                <th>状态</th>
                <th width="60">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
    </div>

    <div class="operate">
        <div class="action">
            <button type="button" class="ubtn ubtn-red jdel">批量不通过</button>
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
        function checkRecordView(id) {
            var ids="";
            $("td>label>input[type='checkbox']").each(function(){
                if(ids==""){
                    ids+=$(this).val();
                }else{
                    ids+= ","+$(this).val();
                }




            });
            window.location.href="/sspoffer/checkRecordView?offerId="+id+"&ids="+ids;
        }
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
                    url: '/sspoffer/wfsh'
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
                        fx.alert("开始时间不得大于结束时间");
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
                    url: '/sspoffer/deleteSsp',
                    dataType: 'json',
                    type: 'GET',
                    type: 'POST',
                    data: data,
                    success: function(res) {
                        fx.closeLoading();
                        if (res.success) {
                            $.notify({
                                type: 'danger',
                                title: '操作成功'
                            })
                            window.location.href="/sspoffer/managerWF";
                        } else {
                            $.notify({
                                type: 'success',
                                title: '操作失败'
                            })
                        }
                    }
                })
            }
        }

</script>
</body>
</html>