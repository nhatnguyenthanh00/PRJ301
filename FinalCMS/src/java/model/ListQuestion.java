/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class ListQuestion extends DBContext {
    private String ListQuestionID,QuizzID,QuestionID;

    public ListQuestion() {
    }

    public ListQuestion(String ListQuestionID, String QuizzID, String QuestionID) {
        this.ListQuestionID = ListQuestionID;
        this.QuizzID = QuizzID;
        this.QuestionID = QuestionID;
    }

    public String getListQuestionID() {
        return ListQuestionID;
    }

    public void setListQuestionID(String ListQuestionID) {
        this.ListQuestionID = ListQuestionID;
    }

    public String getQuizzID() {
        return QuizzID;
    }

    public void setQuizzID(String QuizzID) {
        this.QuizzID = QuizzID;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }
     Connection cnn; // ket noi data base
    Statement stm; // thuc hien cac cau lenh sql
    ResultSet rs; // dung de luu tru va xu ly du lieu
    PreparedStatement pstm;
    private void connect() {
        cnn = super.connection;
        if (cnn!=null){
            System.out.println("Connect success");
        }
        else{
            System.out.println("Connect fail!");
        }
    }
    
}
