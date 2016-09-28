<%-- 
    Document   : loginValid
    Created on : 26/09/2016, 09:14:38
    Author     : usuario
--%>
<%@include file="layout/header.jsp"%>

    <%@include file="layout/exito.jsp"%>
    <%@include file="layout/problema.jsp"%>
    <%@include file="layout/confirm_delete.jsp"%>
    <div style="text-align: center;">Espere unos segundos que será redirigido.</div>

    
    <script>
        setTimeout(function() {
  window.location.href = "${pageContext.request.contextPath}/index";
}, 2000);
    </script>
    

<%@include file="layout/footer.jsp"%>