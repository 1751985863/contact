<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>首页</title>

  <!-- 1. 导入CSS的全局样式 -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
  <script src="js/jquery-2.1.0.min.js"></script>
  <!-- 3. 导入bootstrap的js文件 -->
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript">
  </script>
</head>
<body>


<div class="container">
  <div class="jumbotron">
    <h1>欢迎登陆</h1>
    <p>${loginUser.username},您上次登录的时间是${loginUser.lasttime}</p>

    <br>

    <p><a class="btn btn-primary btn-lg" role="button" href="${pageContext.request.contextPath}/quitLoginUserServlet">
      退出登录</a>
    </p>
    <p><a class="btn btn-primary btn-lg" role="button" href="${pageContext.request.contextPath}/change.jsp">
      修改密码</a>
    </p>


    <p><a class="btn btn-primary btn-lg" role="button" href="${pageContext.request.contextPath}/findUserByPageServlet">
      管理通讯录</a>
    </p>
    <p><a class="btn btn-primary btn-lg" role="button" href="${pageContext.request.contextPath}/backup.jsp">
      备份数据到邮箱</a>
    </p>
    </div>







</body>
</html>