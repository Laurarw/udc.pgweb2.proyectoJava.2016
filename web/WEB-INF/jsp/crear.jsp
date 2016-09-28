<%-- 
    Document   : crear
    Created on : 09/09/2016, 01:04:26
    Author     : usuario
--%>

<%@include file="layout/header.jsp"%>


             <h3>Crear Cliente</h3>
             
             <%@include file="layout/errores.jsp"%>
             
             <form class="form-horizontal form-group" action="${pageContext.request.contextPath}/crear" method="POST">
                 
                 
                 
       <%@include file="form.jsp"%>

                 <input type="submit" class="btn btn-primary" value="Enviar">
                 <a href="${pageContext.request.contextPath}/index" class="btn btn-info">Volver</a>
            
        </form> 
       
   

<%@include file="layout/footer.jsp"%>
