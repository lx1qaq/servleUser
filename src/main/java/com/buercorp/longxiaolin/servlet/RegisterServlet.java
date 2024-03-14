package com.buercorp.longxiaolin.servlet;

import com.buercorp.longxiaolin.pojo.User;
import com.buercorp.longxiaolin.utils.DruidUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Aron.li
 * @date 2021/2/15 17:44
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 在最前面解决乱码问题:请求参数的中文乱码，响应的中文乱码
        //解决请求参数的中文乱码
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html;charset=utf-8");

        //2. 获取所有的请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("接收到的参数: ");
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            for (String value : entry.getValue()) {
                System.out.println(entry.getKey() + ": " + value);
            }
        }

        //3. 使用BeanUtils 将parameterMap中的数据，存储到User对象中
        User user = new User();

        //设置默认的status为"0"
        user.setStatus("0");

        try {
            // 使用 BeanUtils.populate 将接收到的参数保存到 user 对象中
            BeanUtils.populate(user,parameterMap);

            //4. 使用DBUtils将用户信息存储到数据库
            //这里需要mysql驱动、druid、dbutils的jar包
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "insert into user values (null,?,?,?,?,?,?,?)";

            //将user用户存储的数据 插入 到数据库中
            queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getAddress(),
                    user.getNickname(),user.getGender(),user.getEmail(),user.getStatus());

            //如果注册成功，则向浏览器响应一句"注册成功"
            response.getWriter().write("注册成功");

        } catch (Exception e) {
            e.printStackTrace();

            //如果注册失败，则向浏览器响应一句"注册失败"
            response.getWriter().write("注册失败");
        }
    }
}