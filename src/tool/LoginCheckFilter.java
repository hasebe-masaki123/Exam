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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初期化処理が必要であればここに記述（今回は不要）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // URIを取得（例：/exam_login/scoremanager/StudentList.action）
        String uri = req.getRequestURI();

        // セッションとユーザーの存在チェック
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // ログインページとログイン処理などを除外
        boolean indexPage = uri.contains("index.jsp");
        boolean isLoginPage = uri.contains("login.jsp");
        boolean timeoutPage = uri.contains("timeout.jsp");
        boolean isLogoutPage = uri.contains("logout.jsp");
        boolean isLoginAction = uri.contains("/scoremanager/Login.action");
        boolean isLoginExecuteAction = uri.contains("/scoremanager/LoginExecute.action");
        boolean isLogoutAction = uri.contains("/scoremanager/Logout.action");

        // 静的リソース（CSS, JS, 画像など）も除外
        boolean isStatic = uri.contains("/css") || uri.contains("/js") || uri.contains("/images");


        if (isLoggedIn || indexPage || isLoginPage || timeoutPage ||  isLogoutPage || isLoginAction ||  isLoginExecuteAction || isLogoutAction || isStatic) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/timeout.jsp");
        }
    }

    @Override
    public void destroy() {
        // アプリケーション終了時の後処理が必要であればここに記述（今回は不要）
    }
}
