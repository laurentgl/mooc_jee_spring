import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/dist")
public class MyServlet extends HttpServlet {

	// @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// TODO : show a HTML form with 4 input text (2 for each point)
		//        your input must be named 'p1lat', 'p1lng', 'p2lat', 'p2lng'

		resp.setContentType("text/html");

		Writer out = resp.getWriter();
		out.write("Show form");
		out.write("<HTML><BODY>");
		out.write("<form ACTION= \"dist\" METHOD=POST>");
		out.write("p1lat <input NAME=\"p1lat\"> <P>");
		out.write("p1lng <input NAME=\"p1lng\"> <P>");
		out.write("p2lat <input NAME=\"p2lat\"> <P>");
		out.write("p2lng <input NAME=\"p2lng\"> <P>");
		out.write("<INPUT TYPE=SUBMIT VALUE=\"Envoi\">");
		out.write("</form></BODY> </HTML>");		
	}
	
	// @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// TODO : get first point latitude / longitude
		double lat1 = Double.parseDouble(req.getParameter("p1lat"));
		double lon1 = Double.parseDouble(req.getParameter("p1lng"));
		// TODO : get second point latitude / longitude
		double lat2 = Double.parseDouble(req.getParameter("p2lat"));
		double lon2 = Double.parseDouble(req.getParameter("p2lng"));
		// TODO : compute distance between two points
		
		// TODO : display distance, in kilometer with 1 decimal
		double R = 6371e3; // metres
		double φ1 = lat1 * Math.PI/180; // φ, λ in radians
		double φ2 = lat2 * Math.PI/180;
		double Δφ = (lat2-lat1) * Math.PI/180;
		double Δλ = (lon2-lon1) * Math.PI/180;

		double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
		          Math.cos(φ1) * Math.cos(φ2) *
		          Math.sin(Δλ/2) * Math.sin(Δλ/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		double d = R * c; // in metres



		resp.setContentType("text/html");

		Writer out = resp.getWriter();
		//out.write("Display result");
		out.write("<HTML><BODY>");
		out.write(String.format("%.1f km", d/1000));
		out.write("</BODY> </HTML>");
	}

}
