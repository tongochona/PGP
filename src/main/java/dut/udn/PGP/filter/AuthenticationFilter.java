package dut.udn.PGP.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import dut.udn.PGP.model.User;

public class AuthenticationFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = httpRequest.getRequestURI();

		if (uri.startsWith(httpRequest.getContextPath() + "/resources") || uri.endsWith(".css") || uri.endsWith(".js")
				|| uri.endsWith(".png") || uri.endsWith(".jpg")) {
			chain.doFilter(request, response);
			return;
		}

		User user = (User) httpRequest.getSession().getAttribute("loggedInUser");

		if (user == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			return;
		}
		
		httpRequest.setAttribute("user", user);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		//
	}

}
