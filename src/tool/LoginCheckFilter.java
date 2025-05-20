package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" })
public class LoginCheckFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);

        // セッションが無効 or ユーザー属性が無い場合
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("timeout.jsp");
            return;
        }

        // 通過させる
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
