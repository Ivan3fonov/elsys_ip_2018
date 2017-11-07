package org.elsys.ip.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LogOutServlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();


		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;


		String username = request.getParameter("name");
		String password = request.getParameter("password");

		boolean authorized = false;

		if(httpServletRequest.getCookies()!= null) {
			for (Cookie cookie : httpServletRequest.getCookies()) {
				if (cookie.getName().equals("Session") && cookie.getValue().equals("1")) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		if(username == null && password == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}

		if(username.equals("gosho") && password.equals("pesho"))
			authorized = true;
		
		// check username and password (can be hardcoded, can use the userService)
		// add if the person is logged in to a cookie (Google it), so that we do not check at every page
		if (authorized) {
			Cookie cookie = new Cookie("Session","1");
			httpServletResponse.addCookie(cookie);
			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "Wrong username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
