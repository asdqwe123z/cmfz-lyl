<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <script type="text/javascript">
        $(function () {
            $('#dg').edatagrid({
                fit: true,
                pagination:true,
                fitColumns:true,
                pageSize: 5,
                pageList: [3, 5, 7],
                url:"${pageContext.request.contextPath}/getBannerAll",
                updateUrl: "${pageContext.request.contextPath}/updateBanner",
                columns:[[
                    {field:"id",hidden:true},
                    {field:'name',title:'标题',width:80},
                    { field: 'status', title: '状态', width: 100, editor: {type: "text",options: {required: true}
                    }
                    },
                    {field:'createDate',title:'时间',width:80,align:'right',sortable:true},
                    {field:'description',title:'描述',width:150,sortable:true},
                ]],
                toolbar: [{
                    iconCls: 'icon-add',
                    text: "添加",
                    handler: function () {
                        $("#myadd").dialog("open");
                    }
                }, '-', {
                    iconCls: 'icon-edit',
                    text: "修改",
                    handler: function () {
                        var row = $("#dg").edatagrid("getSelected");
                        alert(row);
                        if (row == null) {
                            alert("请先选中行")
                        } else {
                            var index = $("#dg").edatagrid("getRowIndex", row);
                            alert(index);
                            $("#dg").edatagrid("editRow", index);
                        }
                    }
                }, '-', {
                    iconCls: 'icon-remove',
                    text: "删除",
                    handler: function () {
                        var row= $("#dg").edatagrid("getSelected");
                        alert(row.id);
                        $.ajax({
                            url:"${pageContext.request.contextPath}/deleteId",
                            data:"id="+row.id,
                            success:function(data){
                                if(data){
                                    $.messager.alert("消息提醒","成功","info");
                                }else{
                                    $.messager.alert("消息提醒","失败","error");
                                }
                                $("#dg").edatagrid("load");

                            },
                        });

                    }

                }, '-', {
                    iconCls: 'icon-save',
                    text: "保存",
                    handler: function () {
                        $("#dg").edatagrid("saveRow");
                    }
                }],
                view: detailview,
                detailFormatter: function(rowIndex, rowData){
                    return '<table><tr>' +
                        '<img src="${pageContext.request.contextPath}/' + rowData.url + '" style="height:50px;"></td>' +
                        '<td style="border:0">' +
                        '<p>Attribute: ' + rowData.description + '</p>' +
                        '<p>Status: ' + rowData.createDate + '</p>' +
                        '</td>' +
                        '</tr></table>';
                }
            });
            $("#myadd").dialog({
                title:"添加",
                closed:true,
                width:300,
                height:150,
                collapsible:true,
            });
        })
        function doadd(){
            $("#addForm").form("submit",{
                url:"${pageContext.request.contextPath}/addBanner",
                success:function(data){
                    if(data=="true"){
                        alert("cg");
                    }else{
                        alert("sb");
                    }
                    $("#myadd").dialog("close");
                    $("#dg").edatagrid("load");
                },
            });
        }
    </script>



<table id="dg"></table>
<div id="myadd">
    <form id="addForm" enctype="multipart/form-data" method="post">
        标题：<input  name="name"><br>
        时间：<input  name="createDate"><br>
        描述：<input  name="description" >
        图片：<input type="file" name="tupian">
        <a class="easyui-linkbutton" onclick="doadd()"><input type="button" value="立即添加"></a>
    </form>
</div>
