
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript" src="jquery-1.7.2.js"></script>
    <script type="text/javascript" >
      window.onload=function (){
        document.getElementById("image").onmouseenter=function (){
          var image = document.getElementById("image");
          image.style.transform="scale(1.6)";
        };
        document.getElementById("image").onmouseout=function (){
          document.getElementById("image").style.transform="scale(1)"
        };
      };
    </script>

    <style>
      img:hover{
        transform: scale(1.6);
      }
    </style>
  </head>
  <body>
    <form action="upload" method="post" enctype="multipart/form-data">
      <table style="margin-left: 600px; margin-top: 300px;">
        <tr>
          <td>文件描述：</td>
          <td><input  id="1" type="text"></td>
        </tr>

        <tr>
          <td>选择文件：</td>
          <td><input type="file" name="image"></td>
        </tr>

        <tr>
          <td><input type="reset" value="重填"></td>
          <td><input type="submit" value="上传"></td>
        </tr>
      </table>
      
      <table>
        <tr>
          <td >图片：</td>
          <td style="overflow: hidden;"><img id="image" src="D:/uploadFiles/books/1.jpg" style="width: 70px; height: 100px; transition: all 0.6s;"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
