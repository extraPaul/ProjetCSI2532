<% session = request.getSession(false);
   
   session.invalidate();
   response.sendRedirect("Connexion.jsp");
%>