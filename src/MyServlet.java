import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyServlet extends GenericServlet
{
	private ServletConfig config = null;

	public void init(ServletConfig config)
	{
		this.config = config;
	}
	
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		String msg = this.config.getInitParameter("WelcomeMSG");
		String userName = req.getParameter("UserName");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();		
		out.println("<b>"+msg+"</b>");	
		out.println("<br><br> The name you entered is "+userName);
		out.close();
		
	}

}
