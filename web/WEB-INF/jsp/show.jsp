<%-- 
    Document   : show
    Created on : 09/09/2016, 22:00:44
    Author     : usuario
--%>

<%@include file="layout/header.jsp"%>


             <h3>Cliente: ${cliente.nombre},${cliente.apellido}</h3>

           
            
             
              <%@include file="form.jsp"%>
              <br>
               <br>
              
              <a href="${pageContext.request.contextPath}/edit?id=${cliente.id}" class="btn btn-warning">Editar</a>
               <a href="${pageContext.request.contextPath}/index" class="btn btn-info">Volver</a>


<%@include file="layout/footer.jsp"%>