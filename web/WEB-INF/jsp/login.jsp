<%-- 
    Document   : login
    Created on : 10/09/2016, 14:56:12
    Author     : usuario
--%>
<%@include file="layout/header.jsp"%>

<div class="container"> 
    
    <form action="login" method="POST"/>
    <label>Usuario</label>
        <input type="text" name="usuario" />
        <label>Password</label>
        <input type="password" name="pass" />
        <input type="submit" value="Enviar"/>
   </form>
    
    
            
</div>

<%@include file="layout/footer.jsp"%>
