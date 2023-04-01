import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;	

public class Practical_04_MaterialServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/material.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	RequestDispatcher rd = request.getRequestDispatcher("/result.jsp");

        // Retrieve the user's input
        String subjectCodeStr = request.getParameter("subjectCode");
        int subjectCode;
        if (subjectCodeStr == null || subjectCodeStr.isEmpty()) {
            subjectCode = 0; 
        } else {
            subjectCode = Integer.parseInt(subjectCodeStr);
        }
        String subName = request.getParameter("subName");	
        String paperDetail = request.getParameter("paperDetail");
        String url = request.getParameter("url");
        String operation = request.getParameter("operation");
        String search = request.getParameter("search");

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = Practical_01_DataBase.getConnection();
            stmt = conn.createStatement();

            // Perform the appropriate operation based on the selected option
            if (operation.equals("add")) {
                Practical_01_DataBase.insertPaperDetail(stmt, new Data(subjectCode, subName, paperDetail, url));
                request.setAttribute("message", "Material Added Successfully!");
                rd.forward(request, response);
            } else if (operation.equals("view")) {
                ResultSet rs = Practical_01_DataBase.searchData(stmt,search);
                String searchResult = Practical_01_DataBase.getSearchResult(rs);
                request.setAttribute("searchResult", searchResult);
                request.getRequestDispatcher("/view.jsp").forward(request, response);
            } else if (operation.equals("modify")) {
                Practical_01_DataBase.updatePaperDetail(stmt, new Data(subjectCode, subName, paperDetail, url));
                request.setAttribute("message", "Material Modified Successfully!");
                rd.forward(request, response);
            } else if (operation.equals("delete")) {
                Practical_01_DataBase.deletePaperDetail(stmt, subjectCode);
                request.setAttribute("message", "Material Deleted Successfully!");
                rd.forward(request, response);
            } else {
                request.setAttribute("message", "Invalid operation!");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
