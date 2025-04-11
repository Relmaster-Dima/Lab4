<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.DBUtil" %> <!-- Указываем полное имя класса -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center text-primary">Welcome, Admin!</h2>
    <p class="text-center">Here is the list of all drivers:</p>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>License Number</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                ResultSet drivers = DBUtil.getAllDrivers();
                while (drivers.next()) {
        %>
        <tr>
            <td><%= drivers.getInt("driver_id") %></td>
            <td><%= drivers.getString("name") %></td>
            <td><%= drivers.getString("phone_number") %></td>
            <td><%= drivers.getString("email") %></td>
            <td><%= drivers.getString("license_number") %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
        </tbody>
    </table>
    <a href="logout.jsp" class="btn btn-secondary d-block mx-auto w-25">Logout</a>
</div>
</body>
</html>