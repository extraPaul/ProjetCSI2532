 <meta http-equiv="Cache-Control" content="no-cache" />


 <% session = request.getSession(false);
   String key=(String) session.getAttribute("key");

if(key !=null){%>
<jsp:useBean id="Medecin"  class="model.dbbeans.Medecin" scope="session" />

    <html>
        <head>
            <title>Accueil</title>
        </head>
        <body>
            <center><H1>Accueil</H1></center>
        <p align="left">
             Bonjour,
        <jsp:getProperty name="Medecin" property="prenom" />
        <jsp:getProperty name="Medecin" property="nom" />
        </p>
            <p align="Center">
               <input type="submit" name="cmdFan" value="Deconnexion">
            </p>
            <ul>
                <li><a href="closesession.jsp"> Log Out </a></li>
            </ul>
         </body>
    </html>


 <%}else{
     response.sendRedirect("error.jsp");
}%>