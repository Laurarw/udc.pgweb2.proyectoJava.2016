<%-- 
    Document   : form
    Created on : 09/09/2016, 01:15:59
    Author     : usuario
--%>


            <div class="form-group ">
                <label class="control-label">  Nombre:</label><br>
                <div class="col-sm-5">
                    <input class="input-sm form-control" type="text" name="nombre" value="<c:out value="${cliente.nombre}" />" ${readonly==true?"readonly":""}>
                </div>
            </div><br>
            <div class="form-group ">
                <label class="control-label">  Apellido:</label><br>
                <div class="col-sm-5">
                    <input class="input-sm form-control" type="text" name="apellido" value="<c:out value="${cliente.apellido}" />"${readonly==true?"readonly":""}>
                </div>
            </div><br>
            <div class="form-group">
                <label class="control-label">Fecha de Nacimiento:</label><br>
                <div class="col-sm-5">
                    <input class="input-sm form-control" type="text" name="fecha_nac" id="datepicker" value="<c:out value="${cliente.fecha_nacimiento}" />"${readonly==true?"readonly":""}>
                </div>
            </div><br>                            
            <div class="form-group">
                <label>Lugar de nacimiento</label><br>
                <div class="col-sm-5">
                    <select name="lugar_nac" class="input-sm form-control" ${readonly==true?"disabled":""}>
                        <option value="">Seleccionar Pais</option>
                        <c:forEach var="pais" items="${paises}" varStatus="counter">
                      
                            <option ${(cliente.nacionalidad != null && cliente.nacionalidad== pais.nombre) ? "selected=selected" : ""}${(nacionalidad_id == pais.id) ? "selected=selected" : ""} value="<c:out value="${pais.id}"/>" > <c:out value="${pais.nombre}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div><br>
            <div class="form-group checkbox ">
                <label class="form-control-label col-sm-1">Activo:</label>
                
                <input class="checkbox-inline" type="checkbox" name="activo" value="true" ${cliente.activo=="Si"||activo==true?"checked":""} ${readonly==true?"readonly":""}/>
                
            </div><br>
