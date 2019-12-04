package cn.itcast.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/indexServlet")
public class indexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取session
        HttpSession session = request.getSession();
        //2从session中获取数据
        String name = (String) session.getAttribute("name");
        //3判断name是否为null或空字符串
        if(name!=null||!"".equals(name)){
            //设置响应消息的数据格式和编码格式
            response.setContentType("text/html;charset=utf-8");
            //输出欢迎页面，也是首页
            response.getWriter().write("欢迎您,"+name);
        }else {
            //跳转到登录页面
            response.sendRedirect(request.getContextPath()+"/login.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response );
    }
}
