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
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Quizz extends DBContext {
    private String QuizzID,QuizzName,ClassID;

    public Quizz() {
        connect();
    }

    public Quizz(String QuizzID, String QuizzName, String ClassID) {
        this.QuizzID = QuizzID;
        this.QuizzName = QuizzName;
        this.ClassID = ClassID;
        connect();
    }

    public String getQuizzID() {
        return QuizzID;
    }

    public void setQuizzID(String QuizzID) {
        this.QuizzID = QuizzID;
    }

    public String getQuizzName() {
        return QuizzName;
    }

    public void setQuizzName(String QuizzName) {
        this.QuizzName = QuizzName;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String ClassID) {
        this.ClassID = ClassID;
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

    public ArrayList<Quizz> getListQuizz(String ClassID) {
        ArrayList<Quizz> data = new ArrayList<Quizz>();
        try{
            String strSQL = "select * from Quizz where ClassID = ? ";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, ClassID);
            rs = pstm.executeQuery();
            while(rs.next()){
                String QuizzID = rs.getString(1);
                String QuizzName = rs.getString(2);
                Quizz qnew = new Quizz(QuizzID,QuizzName,ClassID);
                data.add(qnew);
            }
        }catch(Exception e){
            System.out.println("getListQuizz"+e.getMessage());
        }
        return data;
    }
}
