package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

// TODO: this class should extends something to be a usable servlet.
// TODO: add an annotation here to map your servlet on an URL.
@WebServlet("/bag")
public class BagServlet extends HttpServlet {
	
	Bag myBag = new Bag();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		res.setContentType("text/html");

		Bag sessionBag = (Bag)req.getSession().getAttribute("myBag");

		if (sessionBag == null) {
			sessionBag = new Bag();
			req.getSession().setAttribute("myBag", sessionBag);
		}

		out.write("<HTML><BODY>");
		out.write("<form action= \"bag\" method=post>");
		
		sessionBag.print(out);
		out.write("ref <input name=\"ref\"> <P>");
		out.write("qty <input name=\"qty\"> <P>");
		out.write("<input type=submit value=\"Envoi\">");
		out.write("</form></BODY> </HTML>");		
		
		// TODO : print a html form using printwriter.
	}

	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		// TODO : Get parameters, check null
		String ref = req.getParameter("ref");
		String qty = req.getParameter("qty");

		if ( ref == null || ref.isEmpty() || qty == null || qty.isEmpty()) {
			res.setStatus(400);
			return;
			
			
		}
		
		// TODO : print reference and quantity

		res.setContentType("text/html");

		
		
		out.write("<HTML><BODY>");
		/*out.write("ref = "+ ref);
		out.write("<P>");
		out.write("qty = "+qty); */

		Bag sessionBag = (Bag)req.getSession().getAttribute("myBag");

		if (sessionBag == null) {
			sessionBag = new Bag();
			req.getSession().setAttribute("myBag", sessionBag);
		}

		sessionBag.setItem(ref, Integer.parseInt(qty));
		//myBag.print(out);
		res.sendRedirect("bag.jsp");
		out.write("</BODY> </HTML>");

		

	}
	
	
	

}
