package cn.itcast.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应消息的数据格式和编码格式
        response.setContentType("text/html;charset=utf-8");
        //1获取登录用户名和密码
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        //2判断用户名是否张三且密码是123
        if("zhangsan".equals(username)&&"123".equals(password)){
            //获取session对象，通过session对象设置username
            request.getSession().setAttribute("username",username );
            //用户名和密码对的话跳转到页面indexServlet
            request.getRequestDispatcher("indexServlet").forward(request,response );
        }else {
            response.sendRedirect(request.getContextPath()+"/login.html");//
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response );
    }
}
