package com.m1474.pfr.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
	    urlPatterns = {"/index.html"}
	)
public class FrontControllerServlet extends HttpServlet{
	
	private static final long serialVersionUID = -2976193196448061241L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		Object accessToken = session.getAttribute("accessToken");
		if(accessToken!=null){	
			 req.getRequestDispatcher("personal_feed.jsp").forward(req, resp);
		} else {
			 req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
        
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	
	
}
