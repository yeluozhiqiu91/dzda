<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    <!--


    function zTreeBeforeClick(treeId, treeNode, clickFlag) {
    }

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
            type: "get",
            dataType: "json",
            url: "/department/getTree",
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

    function addSource(){
        var id;
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var selectedNodes = treeObj.getSelectedNodes();
        if (selectedNodes.length > 0) {
            id= selectedNodes[0].id;

        } else {
            id=0;
        }
        window.location = "/department/add?id="+id;
    }

    function editSource(){
        var id;
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var selectedNodes = treeObj.getSelectedNodes();
        if (selectedNodes.length > 0) {
            id= selectedNodes[0].id;

        } else {
            layer.alert("请选择一个节点");
            return;
        }
        window.location = "/department/edit?id="+id;
    }
    function delSource(){
        var id;
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var selectedNodes = treeObj.getSelectedNodes();
        if (selectedNodes.length > 0) {
            id= selectedNodes[0].id;

        } else {
            layer.alert("请选择一个节点");
            return;
        }
        $.ajax({
            type:"post",
            dataType:"json",
            url:"/deleteDepartment",
            data: {"departmentId":id},
            success:function(data) {
                if(data.result == 'success') {
                    window.location="/department/manager";
                }else{
                    showMsg(data.msg);
                }

            }
        })
    }
    function showMsg(msg){
        $.notify({title: msg, type: 'warning'});
    }
    var setting;
    !(function($) {
        setting = {
            check: {
                enable: false
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
            },
            callback: {
                beforeClick: zTreeBeforeClick
            }
        };
        treeinit();

    })(jQuery);



</script>



<div class="crumbs">
    <span>部门管理</span>
</div>

<div class="box">
    <div class="filter">
        <form action="javascript:;">
            <input type="text" id="name" name="name" value="" class="ipt" autocomplete="off" placeholder="模糊查询" />
            <button type="submit" onclick="queryUser();" class="ubtn ubtn-primary" id="jsearchNodes">查询</button>
        </form>
    </div>
    <div class="operate">
        <button type="button"  onclick="addSource()" class="ubtn ubtn-cyan">添加</button>
        <button type="button" onclick="editSource()" class="ubtn ubtn-primary" id="jedit">修改</button>
        <button type="button" onclick="delSource()" class="ubtn ubtn-primary" id="jdel">删除</button>
        <button type="button" class="ubtn ubtn-primary" onclick="expandAll()" id="expandAll">全部展开</button>
        <button type="button" class="ubtn ubtn-primary" onclick="unexpandAll()" id="collapseAll">全部折叠</button>
    </div>
    <ul id="treeDemo" class="ztree"></ul>
</div>

<%--<ul id="treeDemo" class="ztree"></ul>--%>
