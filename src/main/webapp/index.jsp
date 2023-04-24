<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>assigment#2</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/posts-servlet">
  <div>
    <label>Title: </label>
    <input type="text" name="title" placeholder="title">
  </div>
  <div>
    <label>Description: </label>
    <input type="text" name="description" placeholder="description">
  </div>
  <div>
    <label>Comment: </label>
    <input type="text" name="comment" placeholder="comment">
  </div>
  <div>
    <label>Author: </label>
    <input type="text" name="author" placeholder="author">
  </div>
  <button type="submit"> Save </button>
</form>
</body>
</html>