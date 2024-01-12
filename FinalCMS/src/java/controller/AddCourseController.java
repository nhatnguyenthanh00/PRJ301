/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Category;
import model.Class;
import model.Student;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddCourseController", urlPatterns = {"/addcourse"})
public class AddCourseController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCourseController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Category ca = new Category();
        ArrayList<Category> data = ca.getListCate();
        request.setAttribute("data", data);
        if (request.getParameter("create") != null) {
            System.out.println("create thoi");
            String CateID = request.getParameter("cateid");
            String ClassName = request.getParameter("classname");
            String TeacherName = request.getParameter("teachername");
            Class cl = new Class();
            String mes = "";
            if (cl.checkExist(CateID, ClassName, TeacherName) == true) {
                mes = "Course exist!";
                request.setAttribute("mes", mes);
            } else {
                if (cl.addClass(CateID, ClassName, TeacherName) == true) {
                    Student st = new Student();
                    ArrayList<Class> listclass = st.getListClass();
                    HttpSession session = request.getSession();
                    session.setAttribute("listclass", listclass);
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                }
            }
        }

        request.getRequestDispatcher("createcourse.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
