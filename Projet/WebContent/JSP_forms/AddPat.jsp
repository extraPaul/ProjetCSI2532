
<meta http-equiv="Cache-Control" content="no-cache" />


 <% session = request.getSession(false);
   String key=(String) session.getAttribute("key");

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
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../css/heroic-features.css" rel="stylesheet">

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
          	<h1>Add a patient</h1>
            <p><a href="help.html" class="btn btn-primary btn-large">New here? Click here for help!</a></p>
           <a href="accueil.jsp">Retourner a l'accueil</a>
            <br><br>
        	
        	<br>
             
            <form action="Control" method="post" >
            		<table>
        			<tr><td>idPatient:</td>		    <td><input type="text" name="AddPatId"> </td></tr>
        			<tr><td>Nom:</td>	 			<td><input type="text" name="AddPatNom"> </td></tr>
        			<tr><td>Prenom:</td>	 		<td><input type="text" name="AddPatPrenom"> </td></tr>
        			<tr><td>Num:</td>	 			<td><input type="text" name="AddPatNum"> </td></tr>
        			<tr><td>Rue:</td>	 			<td><input type="text" name="AddPatRue"> </td></tr>
        			<tr><td>Ville:</td>	 			<td><input type="text" name="AddPatVille"> </td></tr>
        			<tr><td>numTelephone:</td>	 	<td><input type="text" name="AddPatNumTel"> </td></tr>
        			<tr><td>NAS:</td>	 			<td><input type="text" name="AddPatNas"> </td></tr>
        			<tr><td>dateNaissance:</td>	    <td><input type="text" name="AddPatDateN"> </td></tr>
        			<tr><td>Sexe:</td>	 			<td><input type="text" name="AddPatSexe"> </td></tr>
            		</table>
            		<br>
            		<button name="AddPatSubmit">Submit</button>
            </form>

        
            
            
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