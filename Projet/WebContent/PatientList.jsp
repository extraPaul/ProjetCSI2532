
<meta http-equiv="Cache-Control" content="no-cache" />


 <% session = request.getSession(false);
   String key=(String) session.getAttribute("key");

if(key !=null){%>
<jsp:useBean id="Medecin"  class="model.dbbeans.Medecin" scope="session" />

 
<html lang="en">

<head>

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
            <h1>Welcome to PAJME</h1>
            <p>PAJME is a medical information platform and stands for Paul, Alex, Jon Medical Encoding. </p>
            <p><a class="btn btn-primary btn-large">New here? Click here for help!</a></p>
            <p>View another doctor's patients:</p>
            <form action="Control" method="post" ><input type="text" name="drInput"> <button name="drInputSubmit">View patient list</button></form>
            <p>Doctor <jsp:getProperty name="Medecin" property="prenom" /> <jsp:getProperty name="Medecin" property="nom" />'s patients</p>
        	<table border = 1>
			

        	<tr>
        		<td>
        			<b>idPatient</b>
        		</td>
        		<td>
        			<b>Nom</b>
        		</td>
        		<td>
        			<b>Prenom</b>
        		</td>
        		<td>
        			<b>Num</b>
        		</td>
        		<td>
        			<b>Rue</b>
        		</td>
        		<td>
        			<b>Ville</b>
        		</td>
        		<td>
        			<b>numTelephone</b>
        		</td>
        		<td>
        			<b>NAS</b>
        		</td>
        		<td>
        			<b>dateNaissance</b>
        		</td>
        		<td>
        			<b>Sexe</b>
        		</td>
        		<td><button name="patViewDetails">View patient details</button></td>
        	</tr>
        	
        	<jsp:getProperty name="Medecin" property="patients" />
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