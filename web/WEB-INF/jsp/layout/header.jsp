<%-- 
    Document   : header
    Created on : 04/09/2016, 16:51:47
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
   
    <head>
        <title>Clientes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
<script src="${pageContext.request.contextPath}/lib/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <link rel=stylesheet href="${pageContext.request.contextPath}/lib/bower_components/bootstrap/dist/css/bootstrap.css"/>
        <link rel=stylesheet href="${pageContext.request.contextPath}/lib/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
        <script src="${pageContext.request.contextPath}/lib/bower_components/jquery-ui/jquery-ui.js"></script>
        
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/script.js"></script>


        
   
<script>
    $(document).ready(function() {
        $.datepicker.regional['es'] = {
 closeText: 'Cerrar',
 prevText: '<Ant',
 nextText: 'Sig>',
 currentText: 'Hoy',
 monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
 monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
 dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
 dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
 dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
 weekHeader: 'Sm',
 dateFormat: 'dd/mm/yy',
 firstDay: 1,
 isRTL: false,
 showMonthAfterYear: false,
 yearSuffix: ''
 };
 $.datepicker.setDefaults($.datepicker.regional['es']);

            $( "#datepicker" ).datepicker({
                changeMonth: true,
                changeYear: true,
                yearRange: "1800:2016"
              });
              
 
    });
     function goBack() {
        window.history.back();
    }
</script>
    </head>
   
<body>

 <div class="container"> 
     <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Gestion de Clientes</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"> ${usuario_registrado.nombre }
                
                
                
                </a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Usuario <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Perfil</a></li>
            <li><a href="#">Gestion de roles</a></li>
            
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/logout">Sign Up</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>