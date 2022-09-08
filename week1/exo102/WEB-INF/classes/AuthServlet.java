import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

	// TODO : only handle POST request for authentication
	// @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// TODO : get login / password from request parameters
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		if ( login == null || password == null ) throw new ServletException("no login/password");
		boolean succeed = "admin@foo.com".equals(login) && "admin".equals(password);
		
		// TODO : if auth is OK, 
		  // add something in session for next calls, 
		  // then redirect to "welcome.jsp"
		  if (succeed) {
			req.getSession().setAttribute("succeed", true);
			resp.sendRedirect("welcome.jsp");
		  }
		  else{
			RequestDispatcher rd = req.getRequestDispatcher("auth.jsp");
			req.setAttribute("errorMessage", "true");
			rd.forward(req, resp);
		  }

		  

	}
	
	// TODO : allow to disconnect with a GET to /auth with any parameter "logout" value
	// @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
	  // TODO : check for "logout" parameter
	  //   if so : disconnect and show auth.jsp
	  //   if not : Error 500
	  if (req.getParameter("logout") != null) {
		resp.sendRedirect("auth.jsp");
	  }
	  else{
		resp.setStatus(500);
	  }
	}

}