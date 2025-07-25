package servlet.exmshul10_2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import servlet.exmshul10_2.model.Department;
import servlet.exmshul10_2.model.Employee;
import servlet.exmshul10_2.model.EmployeeDAO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
  
	private static final long serialVersionUID = -7116269893743570677L;
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> list = dao.findAll();
		
        request.setAttribute("employees", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/employee.jsp");
        dispatcher.forward(request, response);
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String empId = java.util.UUID.randomUUID().toString();
		
        String empName = request.getParameter("empName");
        String depId = request.getParameter("depId");
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

        boolean saved = employeeDAO.save(employee);

        if (saved) {
            response.sendRedirect("EmployeeServlet");
        } else {
            response.getWriter().println("Erro ao cadastrar funcionário.");
        }
    }
}
