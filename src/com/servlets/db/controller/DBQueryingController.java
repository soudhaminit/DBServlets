package com.servlets.db.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.servlets.db.backend.AccountCheck;
import com.servlets.db.beans.User;

/**
 * Servlet implementation class DBQueryingController
 */
public class DBQueryingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataSource ds;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBQueryingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initctx=new InitialContext();
			Context ctx=(Context)initctx.lookup("java:comp/env");
			ds=(DataSource)ctx.lookup("jdbc/webshop");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action==null){
			//msg="action parameter is null";
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}else if(action.equals("login")) {
			request.setAttribute("errormsg", "");
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else if(action.equals("createlogin")){
			request.setAttribute("errormsg", "");
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("reenterpassword", "");
			request.getRequestDispatcher("/createlogin.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		
		Connection con=null;
		User user=new User(email,password);
		
		if(action==null){
			//msg="action parameter is null";
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else if(action.equals("dologin")) {
			if(user.validate()){
				try {
					con=ds.getConnection();
					AccountCheck acc=new AccountCheck(con);
					if(acc.authenticate(email, password)){
						request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
					}else{
						request.setAttribute("errormsg", "Email and Password not autheticated!. Pl use correct email and password");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				request.setAttribute("errormsg", user.getMessage());
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}	
		}else if(action.equals("docreatelogin")) {
			String reenterpassword=request.getParameter("password");
			request.setAttribute("reenterpassword", reenterpassword);
			if(user.validate()){
				try {
					con=ds.getConnection();
					AccountCheck acc=new AccountCheck(con);
					if(!acc.emailExists(email)){
						acc.createLogin(email,password);
						request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
					}else{
						request.setAttribute("errormsg", "Email already exists. Pl use different email");
						request.getRequestDispatcher("/createlogin.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}else{
				request.setAttribute("errormsg", user.getMessage());
				request.getRequestDispatcher("/createlogin.jsp").forward(request, response);
			}
		}
	}

}
