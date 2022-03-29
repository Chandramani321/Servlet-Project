

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

	
/**
 * Servlet implementation class Url
 */
@WebServlet("/servlet1")
public class Url extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Url() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{  
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
          
        String name=request.getParameter("userName");
        
        String pass=request.getParameter("password");  
        
        out.print("Welcome "+ name+" "+ pass);  
        System.out.println(pass);
       
        out.print("<a href='servlet2?uname=" + name + " upassword= "+ pass + "'> visit</a>");  
                  
        out.close();  
  
                }catch(Exception e){System.out.println(e);}  
     
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	

}
