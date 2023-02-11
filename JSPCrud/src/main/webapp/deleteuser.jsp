<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title></title>
  </head>
  <body>
    <%@page import="com.jspcrud.dao.UserDao"%>
    <jsp:useBean id="u" class="com.jspcrud.model.User"></jsp:useBean>
    <jsp:setProperty property="*" name="u" />
    <% UserDao ud = new UserDao(); ud.delete(u);
    response.sendRedirect("viewusers.jsp"); %>
  </body>
</html>
