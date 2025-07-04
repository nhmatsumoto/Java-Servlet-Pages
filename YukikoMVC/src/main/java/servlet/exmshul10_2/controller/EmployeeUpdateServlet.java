package servlet.exmshul10_2.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.exmshul10_2.model.Department;
import servlet.exmshul10_2.model.Employee;
import servlet.exmshul10_2.model.EmployeeDAO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/EmployeeUpdateServlet")
public class EmployeeUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7615961228537025100L;
	private EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("empId");
        String depId = request.getParameter("depId");
        String empName = request.getParameter("empName");
        String registrationDateStr = request.getParameter("registrationDate");
       
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employee.setEmpName(empName);

        Department department = new Department();
        department.setDepId(depId);
        employee.setDepartment(department);

        if (registrationDateStr != null && !registrationDateStr.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime registrationDateTime = LocalDateTime.parse(registrationDateStr, formatter);
                employee.setRegistrationDateTime(registrationDateTime);
            } catch (Exception e) {
                e.printStackTrace();
                employee.setRegistrationDateTime(null);
            }
        }

        boolean updated = employeeDAO.update(employee);

        if (updated) {
            response.sendRedirect("EmployeeServlet?message=Funcionário atualizado com sucesso.");
        } else {
            response.getWriter().println("Erro ao atualizar funcionário.");
        }
    }
}