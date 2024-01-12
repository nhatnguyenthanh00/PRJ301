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
import model.Question;

/**
 *
 * @author Admin
 */
@WebServlet(name = "QuizzController", urlPatterns = {"/quizz"})
public class QuizzController extends HttpServlet {

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
            out.println("<title>Servlet QuizzController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizzController at " + request.getContextPath() + "</h1>");
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
        String quizzid = request.getParameter("quizzid");

        HttpSession session = request.getSession();
        Question q = new Question();
        ArrayList<Question> listquestion = q.getListQuestion(quizzid);
        if (request.getParameter("submitquizz") != null) {
            int maxscore = listquestion.size();
            int i = 0, count = 0;
            for (Question qcheck : listquestion) {
                String yc;
                if (request.getParameter("ans_" + i) == null) {
                    yc = "0";
                } else {
                    yc = request.getParameter("ans_" + i);
                    qcheck.changeVal(yc);
                }
                if (qcheck.getCorect().equals(yc)) {
                    count++;
                }
                i++;
            }
            System.out.println("your score : " + count);
            request.setAttribute("maxscore", String.valueOf(maxscore));
            request.setAttribute("score", count);
            request.setAttribute("quizzid", quizzid);
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }

        request.setAttribute("quizzid", quizzid);
        request.setAttribute("listquestion", listquestion);
        // truyen them list choice da chon
        request.getRequestDispatcher("quizz_class.jsp").forward(request, response);
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
