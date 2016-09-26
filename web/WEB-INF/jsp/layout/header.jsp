<%-- 
    Document   : header
    Created on : 04/09/2016, 16:51:47
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

      <!--  <script type="text/JavaScript" src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.js"></script>
       <script src="/bower_components/jquery/dist/jquery.js"></script>
        <script src="WEB-INF/lib/bower_components/bootstrap/dist/js/bootstrap.js"></script>
        <link rel=stylesheet href="WEB-INF/lib/bower_components/bootstrap/dist/css/bootstrap.css"/>
        <link rel=stylesheet href="WEB-INF/lib/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
        
       -->
        
        
   
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
