<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>注册soul论坛</title>
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  <meta name="author" content="Vincent Garreau" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  
  <link rel="stylesheet" href="layui/css/layui.css"></script> 
  <link rel="stylesheet" media="screen" href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/reset.css"/>
  
  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
</head>
<body>

    <div id="particles-js">
		<div class="reg" style="height: 500px;">
			<div class="reg-top">
				注册
			</div>
			
			<form class="soul-form layui-form" method="POST">
				<div class="reg-center clearfix">
					<div class="reg-center-img layui-icon layui-icon-username" style="font-size: 25px;"></div>
					<div class="reg-center-input">
						<input type="article" class="layui-input " name="username"  lay-verify="username" value="" placeholder="请输入您的用户名" autocomplete="off"
						onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户名'"/>
						<div class="reg-center-input-article">用户名</div>
					</div>
				</div>
				
				<div class="reg-center clearfix">
					<div class="reg-center-img layui-icon layui-icon-password" style="font-size: 25px;"></div>
					<div class="reg-center-input">
						<input type="password" class="layui-input" name="password" value=""  lay-verify="password"  placeholder="请输入您的密码" autocomplete="off"
						onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
						<div class="reg-center-input-article">密码</div>
					</div>
				</div>

				<div class="reg-center clearfix">
					<div class="reg-center-img layui-icon layui-icon-password" style="font-size: 25px;"></div>
					<div class="reg-center-input">
						<input type="password" class="layui-input" name="password" value=""  lay-verify="password"  placeholder="请输入您的密码" autocomplete="off"
						onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
						<div class="reg-center-input-article">密码</div>
					</div>
				</div>
			
				<div class="reg-center clearfix">
					<div class="reg-center-img layui-icon layui-icon-email" style="font-size: 25px;"></div>
					<div class="reg-center-input">
						<input type="password" class="layui-input" name="password" value=""  lay-verify="password"  placeholder="请输入您的密码" autocomplete="off"
						onfocus="this.placeholder=''" onblur="this.placeholder='邮箱'"/>
						<div class="reg-center-input-article">邮箱</div>
					</div>
				</div>

				<div class="reg-center clearfix">
					<div class="reg-center-img layui-icon layui-icon-vercode" style="font-size: 25px;"></div>
					<div class="reg-center-input">
						<input type="password" class="layui-input" name="password" value=""  lay-verify="password"  placeholder="请输入您的密码" autocomplete="off"
						onfocus="this.placeholder=''" onblur="this.placeholder='验证码'"/>
						<div class="reg-center-input-article">验证码</div>
					</div>
				</div>

				<button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="reg" type="submit">注册</button>
			</form>

			<!-- <div class="reg-button">
				<input type="button" value="登录">
			</div> -->
			
		</div>
		<div class="sk-rotating-plane"></div>
</div>




<!-- scripts -->
<script src="js/particles/particles.js"></script>
<script src="js/particles/app.js"></script>
<script src="js/verify.js"></script>
<script type="text/javascript" src="layui/layui.js" charset="utf-8"></script>
</body>
</html>
