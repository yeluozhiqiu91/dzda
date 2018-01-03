<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    var setting;
    !(function($) {
        setting = {
            check: {
                enable: true,
                chkboxType: { "Y": "p", "N": "s" }
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                }
            },
            view: {
                showLine: false,
                fontCss: setHighlight
            }
        };
        treeinit();

    })(jQuery);



    function setData(zNodes) {
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    }

    function expandAll() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        treeObj.expandAll(true);
    }
    function unexpandAll() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        treeObj.expandAll(false);
    }



    //-->
    function treeinit() {
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/getSourceTree",
            data:{"rid":${rid}},
            success: function (data) {
                setData(data);
            }, error: function () {
                layer.alert("数据加载失败!");
            }
        })
    }

    function queryUser() {
        var name = $("#name").val();
        searchNode(name);
    }

    // 模糊搜索name满足条件的节点
    function searchNode(value) {
        if (value == "") return;
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");

        // 先关闭所有节点
        treeObj.expandAll(false);
        // 获取与value匹配的节点，使用的ztreeAPI getNodesByParamFuzzy
        var nodes = treeObj.getNodesByParamFuzzy("name", value);

        // 关闭所有节点的高亮
        // 使用API transformToArray获取所有的节点，getNodes()只能获得根节点，属性children也只能获取下一级节点
        var allNodes = treeObj.transformToArray(treeObj.getNodes());

        for (var i = 0; i < allNodes.length; i++) {

            allNodes[i].highlight = false;

            // 更新节点，因为hightlight改变了，使用updateNode可以更新节点
            treeObj.updateNode(allNodes[i]);
        }


        // 展开根节点（如果不展开根节点，下面的节点无法展开，不知道是不是bug）
        treeObj.expandNode(treeObj.getNodes()[0], true);

        // 高亮并展开搜索到的节点
        for (var i = 0; i < nodes.length; i++) {

            nodes[i].highlight = true;
            treeObj.updateNode(nodes[i]); // 更新节点，让高亮生效

            // 展开搜索到的节点的父节点
            treeObj.expandNode(nodes[i].getParentNode(), true);
        }
    }

    // 设置高亮字体
    function setHighlight(treeId, treeNode) {
        return (treeNode.highlight) ? {
            color: "green",
            "font-weight": "bold",
            "background-color": "#ddd"
        } : {
            color: "#333",
            "font-weight":"normal",
            "background-color": "transparent"

        };
    }

    function submit(){
        var sids="";
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
        var   nodes=treeObj.getCheckedNodes(true);
        for(var i=0;i<nodes.length;i++){
            sids+=nodes[i].id + ",";
        }
        $.ajax({
            type:"post",
            dataType:"json",
            url:"/saveRoleSource",
            data: {"sids":sids.substr(0,sids.length-1),"rid":$("#rid").val()},
            success:function(data) {
                if(data.success){
                    layer.alert("保存成功");
                }

            },error:function () {
                layer.alert("修改失败!");
            }
        })
    }
    function cancel(){
        window.location="/hbda/auth/role/jsgl";
    }




</script>
<div class="crumbs">
    <span>菜单和按钮授权</span>
</div>

<div class="box">
    <div class="filter">
        <form action="javascript:;">
            <input type="text" id="name" name="name" value="" class="ipt" autocomplete="off" placeholder="模糊查询" />
            <button type="submit" onclick="queryUser()" class="ubtn ubtn-primary" id="jsearchNodes">查询</button>
        </form>
    </div>
    <div class="operate">
        <button type="button"   onclick="submit();return false"  class="ubtn ubtn-primary">保存</button>
        <button type="button" onclick="cancel();return false" class="ubtn ubtn-gray" id="jedit">取消</button>
        <button type="button" class="ubtn ubtn-primary" onclick="expandAll()" id="expandAll">全部展开</button>
        <button type="button" class="ubtn ubtn-primary" onclick="unexpandAll()" id="collapseAll">全部折叠</button>
    </div>
    <input type="hidden"  value="${rid}" id="rid">
    <div class="c-red" style="margin-top:12px;">提示:首页必须勾选</div>
    <ul id="treeDemo" class="ztree"></ul>
</div>
<%--
<input type="hidden"  value="${rid}" id="rid">
<div class="c-red">提示:首页必须勾选</div>
<ul id="treeDemo" class="ztree"></ul>--%>
