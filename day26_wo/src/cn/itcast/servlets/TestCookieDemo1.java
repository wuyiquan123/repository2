package cn.itcast.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/TestCookieDemo1")
public class TestCookieDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应消息的数据格式和编码格式
        response.setContentType("text/html;charset=utf-8");
        //1获取所有Cookie
        Cookie[] cookies=request.getCookies();
        boolean flag=false;//默认没有cookie设置为lastTime
        //2遍历cookies数组
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                //3判断cookie名称
                String name=cookie.getName();
                String value1 = cookie.getValue();
                System.out.println(name+":"+value1);
                //4判断名称是否是lastTime
                if("lastTime".equals(name)){
                    //获取cookie的value，时间
                    String value=cookie.getValue();
                    //有该cookie，表示不是第一次访问
                    flag=true;//有名为lastTime的cookie
                    //设置Cookie的value
                    //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                    Date date=new Date();//获取日期对象
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM年dd日 HH:mm:ss");//设置日期指定格式
                    String str_date=sdf.format(date);//把日期按照指定格式存到字符串
                    //URL编码
                    str_date=URLEncoder.encode(str_date,"utf-8" );//把编码后的字符串重新赋值该字符串变量
                    System.out.println("编码后"+str_date);
                    cookie.setValue(str_date);//把指定格式后的日期字符串设置为cookie的值,设置前先编码
                    //设置cookie的存活时间
                    cookie.setMaxAge(60*60*24*30);//一个月
                    response.addCookie(cookie);//用响应对象把这个名为lastTime的cookie重新加载到cookie数组
                    //响应数据
                    str_date = cookie.getValue();//获取cookie中的时间值
                    str_date= URLDecoder.decode(str_date,"utf-8" );//解码
                    System.out.println("解码后"+str_date);
                    //把之前cookie的值也就是之前访问时间输出到页面
                    response.getWriter().write("<h1>欢迎回来，您上次访问时间为"+str_date+"</h1>");
                    break;//找到一个名为lastTime的cookie就够
                }
            }
        }

        //如果没有cookies
        if(cookies==null||cookies.length==0||flag==false){
            //没有，表示第一次访问
            //设置Cookie的value
            //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
            Date date=new Date();//获取日期对象
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM年dd日HH:mm:ss");//设置日期指定格式
            String str_date=sdf.format(date);//把日期按照指定格式存到字符串
            Cookie cookie = new Cookie("lastTime", str_date);//用构造函数创建名为lastTime的cookie并设置其值为日期
            //设置cookie的存活时间
            cookie.setMaxAge(60*60*24*30);//一个月
            response.addCookie(cookie);
            //响应数据
            response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");
            //JSESSIONID:34D17841B837E8380A683B4909C7BC99


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response );
    }
}
