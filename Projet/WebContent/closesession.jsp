<% session = request.getSession(false);
   
   session.invalidate();
   response.sendRedirect("index.jsp");
%>