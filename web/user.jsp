<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.DBUtil" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center text-success">Welcome, User!</h2>
    <p class="text-center">Here is the list of all passengers:</p>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                ResultSet passengers = DBUtil.getAllPassengers();
                while (passengers.next()) {
        %>
        <tr>
            <td><%= passengers.getInt("passenger_id") %></td>
            <td><%= passengers.getString("name") %></td>
            <td><%= passengers.getString("phone_number") %></td>
            <td><%= passengers.getString("email") %></td>
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