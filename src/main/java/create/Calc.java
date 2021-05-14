package create;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="Calc", urlPatterns="/JavaPDF") //���������� �������� � URL
public class Calc extends HttpServlet {

	public static String NumberGet;
	public static String GroupGet;
	public static String FIOGet;
	public static String PointsGet;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestCalc Calc = RequestCalc.fromRequestParameters(request);
		Calc.setAsRequestAttributes(request);
		
		
		request.getRequestDispatcher("/Results.jsp").forward(request, response);
				 
		CreatePDF PDF = new CreatePDF();
		String goals = "Hello";
		PDF.Create(goals);
		
	}
	
	private static class RequestCalc {
		private final String TextNumber;
		private final String TextGroup;
		private final String TextFIO;
		private final String TextPoints;

						
		private RequestCalc (String Number, String Group, String FIO, String Points) {
			this.TextNumber = Number;
			this.TextGroup = Group;
			this.TextFIO = FIO;
			this.TextPoints = Points;
			
			NumberGet=TextNumber;
			GroupGet=TextGroup;
			FIOGet=TextFIO;
			PointsGet=TextPoints;
			
			}
		
		public static RequestCalc fromRequestParameters(HttpServletRequest request) {
			return new RequestCalc(
			request.getParameter("Number"),
			request.getParameter("Group"),
			request.getParameter("FIO"),
			request.getParameter("Points"));
			}
			
		public void setAsRequestAttributes(HttpServletRequest request) {
			
			request.setAttribute("Number", TextNumber);
			request.setAttribute("Group", TextGroup);
			request.setAttribute("FIO", TextFIO);
			request.setAttribute("Points", TextPoints);

		}
		
	}
	
	
}
