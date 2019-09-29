package com.m1474.pfr.servlets.managers;

import javax.servlet.annotation.WebServlet;

import com.m1474.pfr.managers.AbstractManager;
import com.m1474.pfr.managers.Managers;
import com.m1474.pfr.model.feed.Feed;

@WebServlet(
	    urlPatterns = {"/feeds"}
	)
public class FeedServlet extends AbstractModelManagerServlet<Feed>{

	private static final long serialVersionUID = -7463925549069061029L;

	@Override
	protected AbstractManager<Feed> getManager() {
		// TODO Auto-generated method stub
		return Managers.FEED_MANAGER;
	}

}
