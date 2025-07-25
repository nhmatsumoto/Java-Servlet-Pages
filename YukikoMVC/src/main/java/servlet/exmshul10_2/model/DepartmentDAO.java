package servlet.exmshul10_2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao registrar o driver MariaDB:");
            e.printStackTrace();
        }
    }

    private final String URL = "jdbc:mariadb://localhost:3306/playground";
    private final String USER = "admin";
    private final String PASSWORD = "admin123";


    public List<Department> getAllDepartments() throws SQLException {

        List<Department> departments = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT dep_id, dep_name FROM department")) {

            while (rs.next()) {
                String id = rs.getString("dep_id");
                String name = rs.getString("dep_name");
                departments.add(new Department(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return departments;
    }
}