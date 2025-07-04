package servlet.exmshul10_2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.exmshul10_2.model.Employee;
import servlet.exmshul10_2.model.EmployeeDAO;

import java.io.IOException;

@WebServlet("/EmployeeEditServlet")
public class EmployeeEditServlet extends HttpServlet {
    
	private static final long serialVersionUID = 6972923168786411584L;
	private EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("empId");
        if (empId != null && !empId.isEmpty()) {
            Employee employee = employeeDAO.find(empId);
            if (employee != null) {
                request.setAttribute("employee", employee);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/employeeEditForm.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("/WEB-INF/EmployeeServlet?message=Funcionário não encontrado para edição.");
            }
        } else {
            response.sendRedirect("/WEB-INF/EmployeeServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implementaremos a lógica de atualização aqui posteriormente
    }
}