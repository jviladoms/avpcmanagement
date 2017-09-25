<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<meta charset="UTF-8">
<head>
    <title>Spring Security Example </title>
    <link type="text/css" rel="stylesheet" href="/styles/login.css">
</head>
<body>


<div class="login">
  <div class="heading">
    <h2>Accés a l'administració de AVPC Sant Vicenç de Castellet'</h2>
    <form th:action="@{/login}" method="post">

      <div class="input-group input-group-lg">
        <span class="input-group-addon"><i class="fa fa-user"></i></span>
        <input type="text" name="username" class="form-control" placeholder="DNI">
      </div>
      </hr>
      <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-lock"></i></span>
          <input type="password" name="password" class="form-control" placeholder="Password">
      </div>

        <button type="submit" class="float">Login</button>
    </form>

    <a href="/">Torna a la pàgina inicial</a>
  </div>
</div>
</body>
</html>