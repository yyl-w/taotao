<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/8 0008
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>初始化数据</title>
</head>
<body>
<div>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="importAll()">一键导入商品数据到索引库</a>
</div>
<script type="text/javascript">

    function importAll() {
        $.post("/index/importall",null,function(data){
            if (data.status==200) {
                $.messager.alert('提示','商品数据导入成功！');
            } else {

                $.messager.alert('提示','商品数据导入失败！');
            }

        });
    }
</script>
</body>
</html>
