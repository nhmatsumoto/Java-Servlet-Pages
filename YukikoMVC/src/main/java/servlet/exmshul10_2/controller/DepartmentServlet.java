package servlet.exmshul10_2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.exmshul10_2.model.Department;
import servlet.exmshul10_2.model.DepartmentDAO;

@WebServlet("/GetDepartments")
public class DepartmentServlet extends HttpServlet {

	private static final long serialVersionUID = 3383236895477465237L;

	private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        departmentDAO = new DepartmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Department> departments = departmentDAO.getAllDepartments();
            JSONArray jsonArray = new JSONArray();

            for (Department dept : departments) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", dept.getDepId());
                jsonObject.put("name", dept.getDepName());
                jsonArray.put(jsonObject);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonArray.toString());
            out.flush();

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Erro ao acessar o banco de dados.");
        }
    }
}