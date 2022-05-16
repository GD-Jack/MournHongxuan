package save.filter;
/**
 * À¹½Ø¶ñÒâµÇÂ¼
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session= null;
        String uri = request.getRequestURI();
        if(uri.indexOf("login")!=-1 || "/myWeb/".equals(uri)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        session = request.getSession(false);
        if(session!=null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        request.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
    }
}
