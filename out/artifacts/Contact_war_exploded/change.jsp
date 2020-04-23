<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 400px;">
    <br>
    <br><br>
    <br><br>
    <br><br>
    <div class="text-center article-title">
        <h2>
            修改密码
        </h2>
    </div>
    <form action="${pageContext.request.contextPath}/changePasswordServlet" method="post" >

        <div class="form-group">
            <label for="password">旧密码</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入原来的密码"/>
        </div>
        <div class="form-group">
            <label for="password1">新密码</label>
            <input type="password" class="form-control" id="password1" name="password1" placeholder="请输入新密码"/>
        </div>
        <div class="form-group">
            <label for="password2">再次输入新密码</label>
            <input type="password" class="form-control" id="password2" name="password2" placeholder="请输入再次输入新密码"/>
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary"   type="submit" value="提交"  />
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/loginServlet">返回</a>
        </div>
    </form>
    <div  class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <strong>${msg}</strong>
    </div>
</div>
</body>
</html>