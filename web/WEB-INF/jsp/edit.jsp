<%-- 
    Document   : edit
    Created on : 09/09/2016, 18:26:51
    Author     : usuario
--%>

<%@include file="layout/header.jsp"%>


             <h3>Cliente: ${cliente.nombre},${cliente.apellido}</h3>

             <%@include file="layout/errores.jsp"%>
            
             
             <form class="form-horizontal form-group" action="${pageContext.request.contextPath}/edit?id=${cliente.id}" method="POST">
                 
                 
                 
                <%@include file="form.jsp"%>

                          <input type="submit" class="btn btn-primary" value="Enviar">
                           <a href="${pageContext.request.contextPath}/index" class="btn btn-info">Volver</a>
                 </form> 
                    
              
             
             
             
        


<%@include file="layout/footer.jsp"%>

