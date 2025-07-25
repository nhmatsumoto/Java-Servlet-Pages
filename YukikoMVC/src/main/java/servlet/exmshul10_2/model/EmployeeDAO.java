package servlet.exmshul10_2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	
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
    
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT e.emp_id, e.emp_name, d.dep_id, d.dep_name, e.registration_datetime FROM employee e JOIN department d ON e.dep_id = d.dep_id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getString("emp_id"));
                emp.setEmpName(rs.getString("emp_name"));
                
                Timestamp ts = rs.getTimestamp("registration_datetime");
                if (ts != null) {
                    emp.setRegistrationDateTime(ts.toLocalDateTime());
                    emp.setRegistrationDateUtil(new java.util.Date(ts.getTime()));
                } else {
                    emp.setRegistrationDateTime(null);
                    emp.setRegistrationDateUtil(null);
                }
                
                Department dep = new Department();
                dep.setDepId(rs.getString("dep_id"));
                dep.setDepName(rs.getString("dep_name"));
                
                emp.setDepartment(dep);
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public boolean save(Employee employee) {
        String sql = "INSERT INTO employee (emp_id, emp_name, dep_id, registration_datetime) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getEmpId());
            stmt.setString(2, employee.getEmpName());
            stmt.setString(3, employee.getDepartment().getDepId());

            if (employee.getRegistrationDateTime() != null) {
                stmt.setTimestamp(4, Timestamp.valueOf(employee.getRegistrationDateTime()));
            } else {
                stmt.setTimestamp(4, null);
            }

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Employee find(String empId) {
        String sql = "SELECT e.emp_id, e.emp_name, d.dep_id, d.dep_name, e.registration_datetime FROM employee e JOIN department d ON e.dep_id = d.dep_id WHERE e.emp_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpId(rs.getString("emp_id"));
                employee.setEmpName(rs.getString("emp_name"));

                Department department = new Department();
                department.setDepId(rs.getString("dep_id"));
                department.setDepName(rs.getString("dep_name"));
                employee.setDepartment(department);

                employee.setRegistrationDateUtil(rs.getTimestamp("registration_datetime"));
                return employee;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean update(Employee employee) {
        String sql = "UPDATE employee SET emp_name = ?, dep_id = ?, registration_datetime = ? WHERE emp_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getEmpName());
            pstmt.setString(2, employee.getDepartment().getDepId());
            if (employee.getRegistrationDateTime() != null) {
                pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(employee.getRegistrationDateTime()));
            } else {
                pstmt.setTimestamp(3, null);
            }
            pstmt.setString(4, employee.getEmpId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(String empId) {
        String sql = "DELETE FROM employee WHERE emp_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
