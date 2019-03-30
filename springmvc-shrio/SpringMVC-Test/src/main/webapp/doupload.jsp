<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.io.*,java.util.*,java.lang.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>上传处理页面</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String uploadFileName = ""; //上传的文件名
    String fieldName = "";  //表单字段元素的name属性值
    //请求信息中的内容是否是multipart类型
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    //上传文件的存储路径（服务器文件系统上的绝对文件路径）
    //String uploadFilePath = request.getSession().getServletContext().getRealPath("upload/" );
    String uploadFilePath = "E:\\upload";
    if (isMultipart) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //解析form表单中所有文件
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {   //依次处理每个文件
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()){  //普通表单字段
                    fieldName = item.getFieldName();   //表单字段的name属性值
                    if (fieldName.equals("user")){
                        //输出表单字段的值
                        out.print(item.getString("UTF-8")+"上传了文件。<br/>");
                        System.out.println(item.getString("UTF-8")+"上传了文件。<br/>");
                    }
                }else{  //文件表单字段
                    fieldName = item.getFieldName();

                    String fileName = item.getName();
                    if (fileName != null && !fileName.equals("")) {
                        File fullFile = new File(item.getName());
                        File saveFile = new File(uploadFilePath, fullFile.getName());
                        item.write(saveFile);
                        uploadFileName = fullFile.getName();
                        System.out.println("上传成功后的文件名是："+uploadFileName);
                        System.out.println("\t\t下载链接:"+"<a href='download.action?name="+uploadFileName+"'>"+uploadFileName+"</a>");
                        System.out.println("<br/>");
                        out.print("上传成功后的文件名是："+uploadFileName);
                        out.print("\t\t下载链接:"+"<a href='download.action?name="+uploadFileName+"'>"+uploadFileName+"</a>");
                        out.print("<br/>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>
</body>
</html>