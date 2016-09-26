<%-- 
    Document   : errores
    Created on : 09/09/2016, 18:07:25
    Author     : usuario
--%>

<c:if test="${not empty errores}">
            <div class="error">
                Error al procesar el formulario.
                <ul>
                
                     <c:forEach items="${errores}" var="options">
                        <li class="text-danger">${options.key } : ${options.value }</li>
                     </c:forEach>
                   
               
                </ul>
            </div>
        </c:if>