package com.exam.assignment;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "postsServlet", value = "/posts-servlet")
public class PostsServlet extends HttpServlet {
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/assigment";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = new ArrayList<>();

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM posts";
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("comment"),
                        rs.getString("author")
                );
                posts.add(post);
            }

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<table>");
            out.println("<thead><tr><th>ID</th><th>Title</th><th>Description</th><th>Comment</th><th>Author</th></tr></thead>");
            out.println("<tbody>");
            posts.forEach(post -> {
                out.println("<tr>");
                out.println("<td>" + post.getId() + "</td>");
                out.println("<td>" + post.getTitle() + "</td>");
                out.println("<td>" + post.getDescription() + "</td>");
                out.println("<td>" + post.getComment() + "</td>");
                out.println("<td>" + post.getAuthor() + "</td>");
                out.println("</tr>");
            });
            out.println("</tbody>");
            out.println("</table>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String comment = req.getParameter("comment");
        String author = req.getParameter("author");

        PrintWriter out = resp.getWriter();

        String query = "INSERT INTO posts (title, description, comment, author) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, comment);
            stmt.setString(4, author);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                String redirectUrl = "posts-servlet";
                resp.setStatus(HttpServletResponse.SC_FOUND);
                resp.setHeader("Location", redirectUrl);
            } else {
                out.println("<h1>Error creating post</h1>");
            }

            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            out.println("<h1>Error creating post</h1>");
            e.printStackTrace();
        }
    }
}
