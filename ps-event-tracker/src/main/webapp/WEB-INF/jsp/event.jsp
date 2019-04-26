<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Event Page</title>
  <base href="/">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style type="text/css">
  	.error{
  	color: #ff0000;
  	}
  	.errorblock{
  	color: #000;
  	background-color: #ffEEEE;
  	border: 3px solid #ff0000;
  	padding: 8px;
  	margin: 16px;
  	}
  </style>
</head>
<body>
  <form:form modelAttribute="event">
  	<form:errors path="*" cssClass="errorblock" element="div" />
  	<lable for="textinput1">Enter Minutes: </label>
  	<form:input path="name" cssErrorClass="error" />
  	<form:errors path="name" cssClass="error" />
  	<br>
  	<input type="submit" class="btn" value="Enter Event">
  </form:form>
</body>
</html>
