package com.m1474.pfr.servlets;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

@WebServlet(
	    urlPatterns = {"/login.jsp"}
	)
public class GithubApiProxyServlet extends HttpServlet {
	
	private static String CLIENT_ID = "87389c7eec94ac686dea";
	private static String CLIENT_SECRET = "d72dfb51fd8f1bc02b3451cb0aa6ca50eb3532f7";
	private static String HOST_NAME = "http://35.167.220.157/";
	private static String SESSION_ATTRIBUTE = "accessToken";
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		String code = req.getParameter("code");
		Object tokenObj = req.getSession().getAttribute(SESSION_ATTRIBUTE);
		if(tokenObj == null && code == null){
			String redirectCallback = HOST_NAME + "pfr/login.jsp";
			resp.sendRedirect("https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID + "&redirect_uri="
					+ URLEncoder.encode(redirectCallback, StandardCharsets.UTF_8.toString()));
			return;
		} else if(tokenObj == null){
			if(initGithubApi(code, req.getSession())){
				req.getRequestDispatcher("/index.html").forward(req, resp);		
			} else {
				resp.setContentType("text/html");		
				resp.getWriter().write("ERROR");
			}
		}
		req.getRequestDispatcher("/index.html").forward(req, resp);		
	}
	
	
	private boolean initGithubApi(String code, HttpSession session) {
		try {
			String token = getAcessToken(code);
			session.setAttribute(SESSION_ATTRIBUTE, token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String getAcessToken(String code){
		try {
			String params = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + code;
			URL obj = new URL("https://github.com/login/oauth/access_token");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(params);
			wr.flush();
			wr.close();
			String result = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
			System.out.println(result);
			List<NameValuePair> returnParams = URLEncodedUtils.parse(
					result, StandardCharsets.UTF_8);
			NameValuePair accessToken = returnParams.stream().filter(
					nvp -> nvp.getName().equals("access_token")).findFirst().get();
			return accessToken.getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}