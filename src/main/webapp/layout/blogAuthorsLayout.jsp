<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%--
  *@ Jsp homeLayout.jsp
  *@ description This page is the home layout.
  *@ version 1.0
  *@ author Abinaya R.
  *@ since 1.0
  *@ since 14/10/2022
  --%>
  
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" xmlns:og="http://ogp.me/ns#">
  <head>
    <title>Blog author </title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />  
  </head>
  <body>
    <tiles:insertAttribute name="header"></tiles:insertAttribute>
  <div class="main cntr">
  <aside class="lft-cont">
        <tiles:insertAttribute name="left"></tiles:insertAttribute></aside>
        <tiles:insertAttribute name="right"></tiles:insertAttribute>
        <tiles:insertAttribute name="middle"></tiles:insertAttribute>
        
    </div>
    <tiles:insertAttribute name="footer"></tiles:insertAttribute>
  </body>
</html>