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
                            if(row!=null){
                                if(row.url==null){
                                    $("#zjxq").dialog("open");
                                    $('#zjxq1').form('load',row);
                                    $("#coverImg2").prop("src","${pageContext.request.contextPath}/"+row.coverImg);
                                }

                            }else{
                                alert("请点击专辑");
                            }


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
                            var row=$("#albumall").treegrid("getSelected");
                            if(row!=null){
                                if(row.url==null){
                                    $("#aid").val(row.id);
                                    $("#tijiaozhang").dialog("open");
                                }
                            }else {
                                alert("请选择专辑进行添加");
                            }

                        }
                    },'-',{
                        iconCls: 'icon-help',
                        text:"下载音频",
                        handler: function(){
                            var row= $("#albumall").treegrid("getSelected");
                            //alert(row.url);
                            window.location.href="${pageContext.request.contextPath}/dowload?url="+row.url+"&name="+row.name;
                        }

                    }]
            });
            //双击触发的事件
            $("#albumall").treegrid({
                onDblClickRow:function(row){
                    if(row.url!=null){
                        bf(row);
                    }else{
                        alert("请点击歌曲");
                    }

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
        //双击触发后执行的事件
        function bf(row) {
            $("#audio1").prop("src","${pageContext.request.contextPath}/img/"+row.url);
            $("#music").dialog({
                title:"播放"
            })
        }
    </script>
<%--树状表格--%>
<table id="albumall" style="width:600px;height:400px"></table>

<%--专辑详情--%>
<div id="zjxq" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="zjxq1" method="post" >
        <div>

            <input id="id2" class="easyui-validatebox" type="hidden" name="id" data-options="required:true" />
        </div>
        <div>
            <label for="name2">专辑名:</label>
            <input id="name2" class="easyui-validatebox" type="text" name="name" data-options="required:true" />
        </div>
        <div>
            <label for="coverImg2">专辑封面:</label>
            <img id="coverImg2" src="" alt="">
        </div>
        <div>
            <label for="count2">专辑数量:</label>
            <input id="count2" class="easyui-validatebox" style="width:300px" name="count">
        </div>
        <div>
            <label for="score2">评分:</label>
            <input id="score2" class="easyui-validatebox" type="text" name="score" data-options="required:true" />
        </div>
        <div>
            <label for="author2">作者:</label>
            <input id="author2" class="easyui-validatebox" type="text" name="author" data-options="required:true" />
        </div>
        <div>
            <label for="broadCast2">播音员:</label>
            <input id="broadCast2"class="easyui-validatebox" type="text" name="broadCast" data-options="required:true" />
        </div>
        <div>
            <label for="brief2">简介:</label>
            <input id="brief2" class="easyui-validatebox" type="text" name="brief" data-options="required:true" />
        </div>
        <div>
            <label for="publishDate2">发布日期:</label>
            <input id="publishDate2" class="easyui-validatebox" type="text" name="publishDate" data-options="required:true" />
        </div>
    </form>
</div>

<%--专辑添加--%>
<div id="tijiaozj" class="easyui-dialog" title="添加专辑" style="width:400px;height:200px;"data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
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

<%--章节添加--%>
<div id="tijiaozhang" class="easyui-dialog" title="添加章节" style="width:400px;height:200px;"data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,

">
    <form id="formtzhang" method="post" enctype="multipart/form-data">
        <div>
            <label for="id1">章节id:</label>
            <input id="id1" class="easyui-validatebox" type="text" name="id" data-options="required:true" />
        </div>

        <div>
            <label for="tupian1">音频文件:</label>
            <input id="tupian1" class="easyui-filebox" style="width:300px" name="tupian">
        </div>
        <div>
            <label for="aid">所在专辑:</label>
            <input id="aid" class="easyui-validatebox" type="hidden" name="aid" data-options="required:true" />
        </div>
        <div>
            <input type="button" onclick="formzhang()" value="提交">
        </div>
    </form>
</div>
<%--音频播放--%>
<div id="music">
    音频:<audio src=""  controls="controls" autoplay="autoplay" id="audio1"></audio><br>
</div>

