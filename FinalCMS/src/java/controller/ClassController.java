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
import model.Student;
import model.Class;
import model.Document;
import model.List;
import model.ListQuestion;
import model.Quizz;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ClassController", urlPatterns = {"/class"})
public class ClassController extends HttpServlet {

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
            out.println("<title>Servlet ClassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassController at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Student st = (Student) session.getAttribute("student");
        String ClassID = request.getParameter("classid");
        boolean kt = st.checkEnroll(ClassID);
        System.out.println(kt);
        Class myclass = new Class();
        myclass = myclass.getClassByID(ClassID);
        System.out.println(myclass.getClassName());
        // TH lop nay chua enroll
        if (kt == false) {
            request.setAttribute("myclass", myclass);
            request.getRequestDispatcher("enroll_class.jsp").forward(request, response);
        }

        // TH lop nay da enroll
        if (kt == true) {
            ArrayList<Document> listlink = myclass.getLink();
            request.setAttribute("listlink", listlink);
            request.setAttribute("myclass", myclass);
            Quizz q = new Quizz();
            ArrayList<Quizz> listquizz = q.getListQuizz(myclass.getClassID());
            request.setAttribute("listquizz", listquizz);
            request.getRequestDispatcher("detail_class.jsp").forward(request, response);
        }
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
        System.out.println("dopost" + request.getParameter("classid"));
        HttpSession session = request.getSession();
        Student st = (Student) session.getAttribute("student");
        String ClassID = request.getParameter("classid");
        // TH ENROLL
        System.out.println("check" + request.getParameter("enroll"));
        if (request.getParameter("enroll") != null) {
            List l = new List();
            if (l.EnrollClass(st.getStudentID(), ClassID) == true) {
                System.out.println("Enroll success");
                ArrayList<Class> listclass = st.getListClass();
                session.setAttribute("listclass", listclass);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        }
        // TH UNENROLL
        if (request.getParameter("unenroll")!=null){
            List l = new List();
            if (l.unEnrollClass(st.getStudentID(), ClassID) == true) {
                System.out.println("Unenroll success");
                ArrayList<Class> listclass = st.getListClass();
                session.setAttribute("listclass", listclass);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        }
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
