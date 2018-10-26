<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <script type="text/javascript">
        $(function () {
            $('#albumall').treegrid({
                url:"${pageContext.request.contextPath}/selectAlbumAll",
                fit: true,
                fitColumns:true,
                pagination:true,
                idField:"id",
                treeField:"name",
                columns:[[
                    {field:'id',hidden:true},
                    {title:'文章',field:"name",width:180},
                    {field:'url',title:'文件',width:60,align:'right'},
                    {field:'size',title:'大小',width:80},
                    {field:'duration',title:'时常',width:80}

                ]],
                toolbar: [{

                        iconCls: 'icon-edit',
                        text:"专辑详情",
                        handler: function(){
                            var row=$("#albumall").treegrid("getSelected");
                            $.ajax({
                                url:"${pageContext.request.contextPath}/selectAlbumId",
                                data:"id="+row.id,
                                success:function(data){
                                    /*var jj=JSON.parse(data);*/
                                    $.messager.alert("专辑简介",data,"info");
                                }
                            });
                        }
                    },'-',{
                        iconCls: 'icon-help',
                        text:"添加专辑",
                        handler: function(){
                            $("#tijiaozj").dialog("open");
                        }
                    },'-',{
                        iconCls: 'icon-edit',
                        text:"添加章节",
                        handler: function(){
                            $("#tijiaozhang").dialog("open");
                        }
                    },'-',{
                        iconCls: 'icon-help',
                        text:"下载音频",
                        handler: function(){
                            var row= $("#albumall").treegrid("getSelected");
                            alert(row.url);
                            window.location.href="${pageContext.request.contextPath}/dowload?url="+row.url;
                        }

                    }]
            });
            $("#albumall").treegrid({
                onDblClickRow:function(row){
                    bf(row);
                }
            })
        });
        function addclaer(){
            $("#formtj").form("submit",{
                url:"${pageContext.request.contextPath}/addAlbumAll",
                success:function(data){
                    if(data=="true"){
                        alert("cg");
                    }else{
                        alert("sb");
                    }
                    $("#tijiaozj").dialog("close");
                    $("#albumall").treegrid("load");
                },
            });
        }
        //form表单提交
        function formzhang(){
            $("#formtzhang").form("submit",{
                url:"${pageContext.request.contextPath}/addChapter",
                success:function(data){
                    if(data=="true"){
                        alert("cg");
                    }else{
                        alert("sb");
                    }
                    $("#tijiaozhang").dialog("close");
                    $("#albumall").treegrid("load");
                },
            });
        }
        function bf(row) {
            $("#audio1").prop("src","${pageContext.request.contextPath}/img/"+row.url);
            $("#music").dialog({
                title:"播放"
            })
        }
    </script>

<table id="albumall" style="width:600px;height:400px"></table>
<div id="tijiaozj" class="easyui-dialog" title="添加专辑" style="width:400px;height:200px;"data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            <%--buttons:[{
				text:'保存',
				handler:function(){
                    $('#formtj').form('submit', {
                    url:'${pageContext.request.contextPath}/addAlbumAll',
                    success:function(data=='true'){
                        if(data){
                            alert('cgl')
                        }else{
                            alert('sbl')
                        }
                    }
                    });
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#tijiaozj').dialog('close');
				}
			}]--%>
">
    <form id="formtj" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">专辑名:</label>
            <input id="name" class="easyui-validatebox" type="text" name="name" data-options="required:true" />
        </div>
        <div>
            <label for="coverImg">专辑图片:</label>
            <input id="coverImg" class="easyui-filebox" style="width:300px" name="tupian">
        </div>
        <div>
            <label for="count">专辑数量:</label>
            <input id="count" class="easyui-validatebox" style="width:300px" name="count">
        </div>
        <div>
            <label for="score">评分:</label>
            <input id="score" class="easyui-validatebox" type="text" name="score" data-options="required:true" />
        </div>
        <div>
            <label for="author">作者:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-options="required:true" />
        </div>
        <div>
            <label for="broadCast">播音员:</label>
            <input id="broadCast"class="easyui-validatebox" type="text" name="broadCast" data-options="required:true" />
        </div>
        <div>
            <label for="brief">简介:</label>
            <input id="brief" class="easyui-validatebox" type="text" name="brief" data-options="required:true" />
        </div>
        <div>
            <label for="publishDate">发布日期:</label>
            <input id="publishDate" class="easyui-validatebox" type="text" name="publishDate" data-options="required:true" />
        </div>
        <div>
            <input type="button" onclick="addclaer()" value="提交">
        </div>
    </form>
</div>


<div id="tijiaozhang" class="easyui-dialog" title="添加章节" style="width:400px;height:200px;"data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,

">
    <form id="formtzhang" method="post" enctype="multipart/form-data">
        <div>
            <label for="id1">章节id:</label>
            <input id="id1" class="easyui-validatebox" type="text" name="id" data-options="required:true" />
        </div>
        <div>
            <label for="name1">章节名:</label>
            <input id="name1" class="easyui-validatebox" style="width:300px" name="name">
        </div>
        <div>
            <label for="tupian1">音频文件:</label>
            <input id="tupian1" class="easyui-filebox" style="width:300px" name="tupian">
        </div>

        <div>
            <label for="size">大小:</label>
            <input id="size" class="easyui-validatebox" type="text" name="size" data-options="required:true" />
        </div>
        <div>
            <label for="duration">时常:</label>
            <input id="duration" class="easyui-validatebox" type="text" name="duration" data-options="required:true" />
        </div>
        <div>
            <label for="aid">所在专辑:</label>
            <input id="aid" class="easyui-validatebox" type="text" name="aid" data-options="required:true" />
        </div>
        <div>
            <input type="button" onclick="formzhang()" value="提交">
        </div>
    </form>
</div>
<div id="music">
    音频:<audio src=""  controls="controls" autoplay="autoplay" id="audio1"></audio><br>
</div>

