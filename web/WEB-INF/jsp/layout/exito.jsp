<%-- 
    Document   : exito
    Created on : 09/09/2016, 21:29:00
    Author     : usuario
--%>

<c:if test="${Exito !=null}" >
        <div class="bg-success">
        <h2>${Exito}</h2>
        </div>
       <c:remove var="Exito" scope="session"/>
    </c:if>
