 <meta http-equiv="Cache-Control" content="no-cache" />


 <% session = request.getSession(false);
   String key=(String) session.getAttribute("key");

if(key !=null){%>
<jsp:useBean id="Prenom"  class="model.dbbeans.Medecin" scope="session" />
<jsp:useBean id="nom"  class="model.dbbeans.Medecin" scope="session" />

    <html>
        <head>
            <title>Accueil</title>
        </head>
        <body>
            <center><H1>Accueil</H1></center>
        <p align="left">
             Bonjour médecin
        <jsp:getProperty name="Medecin" property="nom" />
        <jsp:getProperty name="Medecin" property="Prenom" />
        </p>
            <ul>
                <li><a href="LikeArtist.jsp"> See the artist fans </a></li>
                <li><a href="closesession.jsp"> Log Out </a></li>
            </ul>
         </body>
    </html>


 <%}else{
     response.sendRedirect("error.jsp");
}%>