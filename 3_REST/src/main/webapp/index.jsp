<%--
  Created by IntelliJ IDEA.
  User: milko.mitropolitsky
  Date: 11/29/17
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <body>

  <form  action="/api/foods/download" method="get">
    Download Food list: <input type="submit" value="Download">
  </form>

  <form action="/api/foods/upload" method="post" enctype="multipart/form-data">
    Upload Csv file: <input type="file" name="file" accept="text/csv"><br>
    <input type="submit" value="Upload">
  </form>


  </body>
</html>
