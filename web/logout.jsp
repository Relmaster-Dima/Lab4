<%@ page import="com.example.DBUtil" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    // Получаем текущую сессию (если она существует)
    HttpSession httpSession = request.getSession(false);

    // Если сессия существует, уничтожаем её
    if (httpSession != null) {
        httpSession.invalidate(); // Уничтожаем сессию
    }

    // Перенаправляем на страницу входа
    response.sendRedirect("index.jsp");
%>