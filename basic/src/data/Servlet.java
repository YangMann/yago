package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Yang ZHANG on 2014/11/4.
 */
public class Servlet extends javax.servlet.http.HttpServlet {

    private final static String TYPE_1 = "name";
    private final static String TYPE_2 = "id1";
    private final static String TYPE_3 = "type";
    private final static String TYPE_4 = "entity1";
    private final static String TYPE_5 = "entity2";
    private final static String TYPE_6 = "id2";

    private final static String URL = "jdbc:postgresql://59.78.11.38:5432/yagodb";
    private final static String USER = "postgres";
    private final static String PASSWORD = "123";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String type = request.getParameter("query-type");
        String content = request.getParameter("query-content");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        switch (type) {
            case TYPE_1:
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("SELECT * " +
                            "FROM yagoentities " +
                            "WHERE to_tsvector('english',subject) @@ to_tsquery('english','" + content + "');");
                    out.println("<table class='striped eight columns centered query-result'><thead><tr><th>#</th><th>Result of <strong>" + content + "</strong></th></tr></thead><tbody>");
                    long count = 0;
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td><td>" + resultSet.getString(1).replace("<", "").replace(">", "") + "</td></tr>");
//                    System.out.println(resultSet.getString(1));
                    }
                    out.println("</tbody></table>");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TYPE_2:
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("SELECT * " +
                            "FROM yagofact " +
                            "WHERE id='<" + content.replace("'", "''") + ">';");
                    int columnNum = resultSet.getMetaData().getColumnCount();
                    out.println("<table class='striped eight columns centered query-result'><caption>Result of ID <strong>"
                            + content + "</strong></caption><thead><tr><th>#</th>" +
                            "<th>ID</th><th>Subject</th><th>Predicate</th>" +
                            "<th>Object</th><th>Value</th></tr></thead><tbody>");
                    long count = 0;
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    resultSet = statement.executeQuery("SELECT * " +
                            "FROM yagofact " +
                            "WHERE id='" + content.replace("'", "''") + "';");
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    out.println("</tbody></table>");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TYPE_3:
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("SELECT * " +
                            "FROM yagotype " +
                            "WHERE (subject='<" + content.replace("'", "''") + ">' OR object='<" + content.replace("'", "''") + ">') AND (predicate='rdfs:range' OR predicate='rdfs:domain');");
                    int columnNum = resultSet.getMetaData().getColumnCount();
                    out.println("<table class='striped eight columns centered query-result'><caption>Result of type <strong>"
                            + content + "</strong></caption><thead><tr><th>#</th>" +
                            "<th>Subject</th><th>Predicate</th>" +
                            "<th>Object</th></tr></thead><tbody>");
                    long count = 0;
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    resultSet = statement.executeQuery("SELECT * " +
                            "FROM yagotype " +
                            "WHERE (subject='" + content.replace("'", "''") + "' OR object='" + content.replace("'", "''") + "') AND (predicate='rdfs:range' OR predicate='rdfs:domain');");
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    out.println("</tbody></table>");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TYPE_4:
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    statement = connection.createStatement();
//                    System.out.println("select * " +
//                            "from yagofacts " +
//                            "where subject='<" + content.replace("'", "''") + ">' or object='<" + content.replace("'", "''") + ">';");
                    resultSet = statement.executeQuery("select * " +
                            "from yagofact " +
                            "where subject='<" + content.replace("'", "''") + ">' or object='<" + content.replace("'", "''") + ">';");
                    int columnNum = resultSet.getMetaData().getColumnCount();
                    out.println("<table class='striped eight columns centered query-result'>" +
                            "<caption>Result of <strong>" + content + "</strong></caption>" +
                            "<thead><tr><th>#</th>" +
                            "<th>ID</th><th>Subject</th><th>Predicate</th>" +
                            "<th>Object</th><th>Value</th></tr></thead><tbody>");
                    long count = 0;
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    resultSet = statement.executeQuery("select * " +
                            "from yagofact " +
                            "where subject='" + content.replace("'", "''") + "' or object='" + content.replace("'", "''") + "';");
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    out.println("</tbody></table>");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TYPE_5:
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("select object " +
                            "from yagotype " +
                            "where subject='<" + content.replace("'", "''") + ">' and predicate='rdf:type';");
                    out.println("<table class='striped eight columns centered query-result'><thead><tr><th>#</th><th>Result of <strong>" + content + "</strong></th></tr></thead><tbody>");
                    long count = 0;
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td><td>" + resultSet.getString(1).replace("<", "").replace(">", "") + "</td></tr>");
//                    System.out.println(resultSet.getString(1));
                    }
                    resultSet = statement.executeQuery("select object " +
                            "from yagotype " +
                            "where subject='" + content.replace("'", "''") + "' and predicate='rdf:type';");
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td><td>" + resultSet.getString(1).replace("<", "").replace(">", "") + "</td></tr>");
//                    System.out.println(resultSet.getString(1));
                    }
                    out.println("</tbody></table>");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TYPE_6:
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("select * " +
                            "from yagotime " +
                            "where subject='<" + content.replace("'", "''") + ">';");
                    int columnNum = resultSet.getMetaData().getColumnCount();
                    out.println("<table class='striped eight columns centered query-result'><caption>Result of ID <strong>"
                            + content + "</strong></caption><thead><tr><th>#</th>" +
                            "<th>ID</th><th>Subject</th><th>Predicate</th>" +
                            "<th>Object</th><th>Value</th></tr></thead><tbody>");
                    long count = 0;
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    resultSet = statement.executeQuery("select * " +
                            "from yagotime " +
                            "where subject='" + content.replace("'", "''") + "';");
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    resultSet = statement.executeQuery("select * " +
                            "from yagoplace " +
                            "where subject='<" + content.replace("'", "''") + ">';");
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    resultSet = statement.executeQuery("select * " +
                            "from yagoplace " +
                            "where subject='" + content.replace("'", "''") + "';");
                    while (resultSet.next()) {
                        out.println("<tr><td>" + (++count) + "</td>");
                        for (int i = 1; i <= columnNum; i++) {
                            if (resultSet.getString(i) != null) {
                                out.println("<td>" + resultSet.getString(i).replace("<", "").replace(">", "") + "</td>");
                            } else {
                                out.println("<td>" + resultSet.getString(i) + "</td>");
                            }
                        }
                        out.println("</tr>");
//                    System.out.println(resultSet.getString(1) + resultSet.getString(2));
                    }
                    out.println("</tbody></table>");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println(request.toString());
    }
}
