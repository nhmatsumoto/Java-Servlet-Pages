package servlet.exmshul10_2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.exmshul10_2.model.EmployeeDAO;

import java.io.IOException;

@WebServlet("/EmployeeDeleteServlet")
public class EmployeeDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("empId");
        if (empId != null && !empId.isEmpty()) {
            boolean deleted = employeeDAO.delete(empId);
            if (deleted) {
                response.sendRedirect("EmployeeServlet?message=Funcionário excluído com sucesso.");
            } else {
                response.getWriter().println("Erro ao excluir funcionário.");
            }
        } else {
            response.sendRedirect("EmployeeServlet?message=ID do funcionário inválido para exclusão.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}