
<meta http-equiv="Cache-Control" content="no-cache" />


 <% session = request.getSession(false);
   String key=(String) session.getAttribute("key");
   System.out.println("Opening Medecin List");

if(key !=null){%>
<jsp:useBean id="Medecin"  class="model.dbbeans.Medecin" scope="session" />

 
<html lang="en">

<head>
<!-- to recommit -->

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>PAJME</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/heroic-features.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Page Content -->
    <div class="container">
        <!-- Jumbotron Header -->
        <header class="jumbotron hero-spacer">
          	<h1>Medecin Info</h1>
            <p><a href="HelpAccueil.jsp" class="btn btn-primary btn-large">New here? Click here for help!</a></p>
            <a href="Accueil.jsp">Retourner a l'accueil</a>
            <br><br>
<!--             <p>View another doctor's patients (by ID):</p> -->
<!--             <form action="Control" method="post" ><input type="text" name="drInput"> <button name="drInputSubmit">View patient list</button></form> -->
            <p>Medecins</p>
        	<table border = 1>
        	<tr>
        		<td class="hockeyPads">
        			Nom
        		</td>
        		<td class="hockeyPads">
        			Prenom
        		</td>
        		<td class="hockeyPads">
        			Num
        		</td>
        		<td class="hockeyPads">
        			Rue
        		</td>
        		<td class="hockeyPads">
        			Ville
        		</td>
        		<td class="hockeyPads">
        			numTelephone
        		</td>
        		<td class="hockeyPads">
        			Specialite
        		</td>
        		
        	</tr>
        	<jsp:getProperty name="Medecin" property="medecins" />
        	</table> 
        
            
            
        </header>

        

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; PAJME 2016</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>



 <%}else{
     response.sendRedirect("error.jsp");
}%>