package com.m1474.pfr.servlets.managers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.m1474.pfr.managers.AbstractManager;
import com.m1474.pfr.model.BaseModel;

public abstract class AbstractModelManagerServlet<M extends BaseModel> extends HttpServlet{

	private static final long serialVersionUID = -7184941108880037749L;
	private static String ACTION_PARAM = "action";
	private static String MODEL_PARAM = "model";
	private static String ADD_ACTION = "ADD";
	private static String UPDATE_ACTION = "UPDATE";
	private static String DELETE_ACTION = "DELETE";

	protected abstract AbstractManager<M> getManager();
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Boolean loggedIn = session.getAttribute("accessToken")!=null;
		if(loggedIn!=null && loggedIn){
			 String action = req.getParameter(ACTION_PARAM);
			 String model = req.getParameter(MODEL_PARAM);
			 //Get
			 if(action==null && model==null){
				 OutputStream os = resp.getOutputStream();
				 byte[] utf8JsonString = getManager().getAllJson().getBytes("UTF8");
				 os.write(utf8JsonString, 0, utf8JsonString.length);
				 os.close();
			 } else {//Perform
				 if(action.equals(ADD_ACTION)){
					 M models = getManager().fromJson(model);
					 getManager().add(models);
				 } else if(action.equals(UPDATE_ACTION)){
					 M models = getManager().fromJson(model);
					 getManager().update(models);
				 } else if(action.equals(DELETE_ACTION)){
					 getManager().delete(model);//Actually id
				 }
			 }
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("NOT SIGNED IN");
			writer.close();
		}
		
	}
	
}