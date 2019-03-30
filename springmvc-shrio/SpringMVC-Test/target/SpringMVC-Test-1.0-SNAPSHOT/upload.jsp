<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>数据生成</title>
</head>
<body>
<div>
    <form method="post" id="file" action="" enctype="multipart/form-data">
            <h3>选择一个文件:</h3>
            <input id="excelFile" type="file" name="uploadFile" />
            <br/><br/>
            <input type="button" value="上传" onclick="uploadFiles();"/>
    </form>
</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
    function uploadFiles(){
// 	var uploadFile = $('#excelFile').get(0).files[0];
        var uploadFile = new FormData($("#file")[0]);
        console.log(uploadFile);
        if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
            $.ajax({
                url:'/manage/fileupload/upload',
                type:'POST',
                data:uploadFile,
                async: false,
                cache: false,
                contentType: false, //不设置内容类型
                processData: false, //不处理数据
                success:function(data){
                    console.log(data);
                    alert(data.msg);
                },
                error:function(){
                    alert("上传失败！");
                }
            })
        }else {
            alert("选择的文件无效！请重新选择");
        }
    }
</script>
</html>