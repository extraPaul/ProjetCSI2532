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
            <p><a href="html/help.html" class="btn btn-primary btn-large">New here? Click here for help!</a></p>
            <p>Bonjour Docteur <jsp:getProperty name="Medecin" property="prenom" /> <jsp:getProperty name="Medecin" property="nom" /></p>
        	<a href="modInfoPerson.jsp">Modifier informations personelles</a>
            
            
            
        </header>

        <hr>

        <!-- Title -->
        <div class="row">
            <div class="col-lg-12">
                <h3>Latest Features</h3>
            </div>
        </div>
        <!-- /.row -->

        <!-- Page Features -->
        <div class="row text-center">



<!-- SHOULDNT SHOW UP UNLESS YOU'RE LOGGED IN -->
<!-- SHOULDNT SHOW UP UNLESS YOU'RE LOGGED IN -->
<!-- SHOULDNT SHOW UP UNLESS YOU'RE LOGGED IN -->
<!-- SHOULDNT SHOW UP UNLESS YOU'RE LOGGED IN -->
            <div class="col-md-3 col-sm-6 hero-feature">
                <div class="thumbnail">
                    <img src="img/patient.png" alt="">
                    <div class="caption">
                        <h3>Patients</h3>
<!--                         <p>What operation would you like to perform?</p> -->
                        <p>
                         <form action="Control" method="POST">
                        	<!--<select>
                        		<option value="PaPlaceholder 1">PaPlaceholder 1</option>
                        		<option value="PaPlaceholder 2">PaPlaceholder 2</option>
                        		<option value="PaPlaceholder 3">PaPlaceholder 3</option>
                        		<option value="PaPlaceholder 4">PaPlaceholder 4</option>
                        		<option value="PaPlaceholder 5">PaPlaceholder 5</option>
                        		<option value="PaPlaceholder 6">PaPlaceholder 6</option>
                        		<option value="PaPlaceholder 7">PaPlaceholder 7</option>
                        	</select> -->
                        	<input type="submit" name="openPatients" value="Browse patients" class="leftMargarine">
                        </form>    
                        </p>
                    </div>
                </div>
            </div>
            
            <div class="col-md-3 col-sm-6 hero-feature">
                <div class="thumbnail">
                    <img src="img/consult.png" alt="">
                    <div class="caption">
                        <h3>Consultations</h3>
<!--                         <p>What operation would you like to perform?</p> -->
                        <p>
                         <form action="Control" method="POST">  
                        	<!--<select>
                        		<option value="PaPlaceholder 1">PaPlaceholder 1</option>
                        		<option value="PaPlaceholder 2">PaPlaceholder 2</option>
                        		<option value="PaPlaceholder 3">PaPlaceholder 3</option>
                        		<option value="PaPlaceholder 4">PaPlaceholder 4</option>
                        		<option value="PaPlaceholder 5">PaPlaceholder 5</option>
                        		<option value="PaPlaceholder 6">PaPlaceholder 6</option>
                        		<option value="PaPlaceholder 7">PaPlaceholder 7</option>
                        	</select> -->
                        	<input type="submit" name="openConsultation" value="Browse consultation" class="leftMargarine">
                        </form>    
                        </p>
                    </div>
                </div>
            </div>
			
			<div class="col-md-3 col-sm-6 hero-feature">
                <div class="thumbnail">
                    <img src="img/dr.jpg" alt="">
                    <div class="caption">
                        <h3>Medecins</h3>    
                        <form action="Control" method="POST">  
                        	<!--<select>
                        		<option value="PaPlaceholder 1">PaPlaceholder 1</option>
                        		<option value="PaPlaceholder 2">PaPlaceholder 2</option>
                        		<option value="PaPlaceholder 3">PaPlaceholder 3</option>
                        		<option value="PaPlaceholder 4">PaPlaceholder 4</option>
                        		<option value="PaPlaceholder 5">PaPlaceholder 5</option>
                        		<option value="PaPlaceholder 6">PaPlaceholder 6</option>
                        		<option value="PaPlaceholder 7">PaPlaceholder 7</option>
                        	</select> -->
                        	<input type="submit" name="openMedecin" value="Browse medecin" class="leftMargarine">
                        </form>   
                        </p>
                    </div>
                </div>
            </div>
           

        </div>
        <!-- /.row -->

        <hr>
        
        <a href="Connexion.jsp"> Disconnect</a>

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