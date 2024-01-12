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
public class Question extends DBContext {
    private String QuestionID,QuestionDetail,Choice1,Choice2,corect,val;

    public Question() {
        connect();
    }

    public Question(String QuestionID, String QuestionDetail, String Choice1, String Choice2, String corect,String val) {
        this.QuestionID = QuestionID;
        this.QuestionDetail = QuestionDetail;
        this.Choice1 = Choice1;
        this.Choice2 = Choice2;
        this.corect = corect;
        this.val = val;
        connect();
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public String getQuestionDetail() {
        return QuestionDetail;
    }

    public String getChoice1() {
        return Choice1;
    }

    public String getChoice2() {
        return Choice2;
    }

    public String getCorect() {
        return corect;
    }

    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }

    public void setQuestionDetail(String QuestionDetail) {
        this.QuestionDetail = QuestionDetail;
    }

    public void setChoice1(String Choice1) {
        this.Choice1 = Choice1;
    }

    public void setChoice2(String Choice2) {
        this.Choice2 = Choice2;
    }

    public void setCorect(String corect) {
        this.corect = corect;
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

    public ArrayList<Question> getListQuestion(String quizzid) {
         ArrayList<Question> data = new ArrayList<Question>();
        try{
            String strSQL="select q.QuestionID,QuestionDetail,Choice1,Choice2,correct,val from Question q join"
                    + "(select * from ListQuestion where QuizzID = ?) t on q.QuestionID = t.QuestionID";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, quizzid);
            rs = pstm.executeQuery();
            while(rs.next()){
                String QuestionID = rs.getString(1);
                String QuesionDetail = rs.getString(2);
                String Choice1 = rs.getString(3);
                String Choice2 = rs.getString(4);
                String correct = rs.getString(5);
                String val = rs.getString(6);
                Question qnew = new Question(QuestionID,QuesionDetail,Choice1,Choice2,correct,val);
                data.add(qnew);
            }
        }catch(Exception e){
            System.out.println("getListQuestion"+e.getMessage());
        }
        return data;
    }

    public void changeVal(String yc) {
        try{
            String strSQL = "update Question set val=? where QuestionID = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, yc);
            pstm.setString(2, QuestionID);
            pstm.execute();
        }catch(Exception e){
            System.out.println("changeVal"+e.getMessage());
        }
    }
}
