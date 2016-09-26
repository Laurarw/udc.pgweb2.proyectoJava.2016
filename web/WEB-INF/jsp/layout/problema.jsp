<%-- 
    Document   : problema
    Created on : 09/09/2016, 21:29:08
    Author     : usuario
--%>

<c:if test="${noEncontrado !=null}" >
        <div class="bg-danger">
        <h2>${noEncontrado}</h2>
        </div>
       <c:remove var="noEncontrado" scope="session"/>
    </c:if>
