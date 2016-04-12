<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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
            <p><a href="HelpConnect.jsp" class="btn btn-primary btn-large">New here? Click here for help!</a></p>
            </p>
            <p style="margin-bottom:4px;">Login here: </p>
            <form action="Control" method="POST">
            	ID:
 				<input style="margin-left:3px" type="text" name="username">
                <input type='submit' name='logIn' value='Confirmer'>
  				
            </form>
        </header>

<!--         <hr> -->

<!--         Title -->
<!--         <div class="row"> -->
<!--             <div class="col-lg-12"> -->
<!--                 <h3>Latest Features</h3> -->
<!--             </div> -->
<!--         </div> -->
<!--         /.row -->

<!--         Page Features -->
<!--         <div class="row text-center"> -->

<!--         </div> -->
<!--         /.row -->

        <hr>

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

