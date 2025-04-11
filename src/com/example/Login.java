package com.example;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // Получаем роль пользователя
        String role = DBUtil.checkUserAndGetRole(name, pass);

        if (role != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", name);
            session.setAttribute("role", role);

            // Перенаправляем в зависимости от роли
            if ("admin".equals(role)) {
                response.sendRedirect("admin.jsp");
            } else if ("user".equals(role)) {
                response.sendRedirect("user.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}