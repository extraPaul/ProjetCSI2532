 <meta http-equiv="Cache-Control" content="no-cache" />


 <% session = request.getSession(false);
   String key=(String) session.getAttribute("key");

if(key !=null){%>


    <html>
        <head>
            <title> Options</title>
        </head>
        <body>
            <center><H1>Choose an option</H1></center>
            <ul>
                <li><a href="LikeArtist.jsp"> See the artist fans </a></li>
                <li><a href="closesession.jsp"> Log Out </a></li>
            </ul>
         </body>
    </html>


 <%}else{
     response.sendRedirect("error.jsp");
}%>