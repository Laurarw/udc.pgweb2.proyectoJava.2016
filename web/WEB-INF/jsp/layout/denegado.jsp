<%-- 
    Document   : autorizacion
    Created on : 27/09/2016, 20:45:25
    Author     : usuario
--%>

<%@include file="header.jsp"%>
<div class="container">
    <%@include file="exito.jsp"%>
    <%@include file="problema.jsp"%>
    <%@include file="confirm_delete.jsp"%>
    <div style="text-align: center;">Espere unos segundos que será redirigido.</div>
</div>
    
    <script>
        setTimeout(function() {
  window.location.href = "${pageContext.request.contextPath}/index";
}, 2000);
    </script>
    

<%@include file="footer.jsp"%>
