<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>File控件</title>
</head>

<body>
<form action="/manage/fileupload/upload" enctype="multipart/form-data" method="post">
    <p>上传者：<input type="text" name="user"></p>
    <p>选择文件：<input type="file" name="mainfile"></p>
    <p>选择文件：<input type="file" name="lunbofile"></p>
    <p><input type="submit" value="提交"></p>
</form>
</body>
</html>