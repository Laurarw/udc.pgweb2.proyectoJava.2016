<%-- 
    Document   : index
    Created on : 02/09/2016, 14:44:25
    Author     : usuario
--%>



<%@include file="layout/header.jsp"%>



      
      

    <%@include file="layout/exito.jsp"%>
    <%@include file="layout/problema.jsp"%>
    <%@include file="layout/confirm_delete.jsp"%>
    
    
    <h3>Clientes</h3>
    <a href="${pageContext.request.contextPath}/crear" class="btn btn-sm btn-success">Nuevo</a>
<br><br>
    <table class="table table-hover">
      <thead>
        <tr>       
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Edad</th>      
          <th>Nacionalidad</th>
          <th>Activo</th>
          <th>Operación</th>
        </tr>
      </thead>
      <tbody>
          
             <c:forEach var="cliente" items="${clientes}" varStatus="counter">
              <tr>
                <td> <c:out value="${cliente.nombre}"/></td>
                <td><c:out value="${cliente.apellido}"/></td>
                <td><c:out value="${cliente.edad}"/></td>
                <td><c:out value="${cliente.nacionalidad}"/></td>
                <td><c:out value="${cliente.activo}"/></td>
                <td><a href="${pageContext.request.contextPath}/edit?id=${cliente.id}" class="btn btn-xs btn-warning">Modificar</a>
                    <a href="${pageContext.request.contextPath}/show?id=${cliente.id}" class="btn btn-xs btn-info">Ver</a>
                 <input name=<c:out value="${cliente.id}"/> type="button"  value="Baja" class="btn btn-xs btn-danger"/>



                  <input name="cliente" type="hidden" value="<c:out value="${cliente.id}"/>" id="cliente_id"/></td></td>
              </tr>
             </c:forEach>
         
      
      
       
        
      </tbody>
    </table>

<%@include file="layout/footer.jsp"%>


        


