package com.m1474.pfr.servlets.managers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.m1474.pfr.managers.LinkManager;
import com.m1474.pfr.model.Link;

@WebServlet(
	    urlPatterns = {"/links"}
	)
public class LinksServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = -3508328812824691483L;
	private static String ACTION_PARAM="action";
	private static String LINK_PARAM="link";
	private static String ADD_ACTION="ADD";
	private static String UPDATE_ACTION="UPDATE";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Boolean loggedIn = session.getAttribute("accessToken")!=null;
		if(loggedIn!=null && loggedIn){
			 String action = req.getParameter(ACTION_PARAM);
			 String link = req.getParameter(LINK_PARAM);
			 //Get Links
			 if(action==null && link==null){
				 OutputStream os = resp.getOutputStream();
				 byte[] utf8JsonString = LinkManager.getAllLinksJson().getBytes("UTF8");
				 os.write(LinkManager.getAllLinksJson().getBytes("UTF8"), 0, utf8JsonString.length);
				 os.close();
			 } else {//Perform action on links
				 if(action.equals(ADD_ACTION)){
					 Link linkObj = LinkManager.fromJson(link);
					 LinkManager.addLinks(linkObj);
				 } else if(action.equals(UPDATE_ACTION)){
					 Link linkObj = LinkManager.fromJson(link);
					 LinkManager.updateLinks(linkObj);
				 }
			 }
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("NOT SIGNED IN");
			writer.close();
		}
		
	}
	
}