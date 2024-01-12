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
public class List extends DBContext {
    private String LissID,StudentID,ClassID;

    public List() {
        connect();
    }

    public List(String LissID, String StudentID, String ClassID) {
        this.LissID = LissID;
        this.StudentID = StudentID;
        this.ClassID = ClassID;
    }

    public String getLissID() {
        return LissID;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setLissID(String LissID) {
        this.LissID = LissID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
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

    public boolean EnrollClass(String StudentID, String ClassID) {
        try{
            String strSQL = "insert into List(StudentID,ClassID) values(?,?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, StudentID);
            pstm.setString(2, ClassID);
            pstm.execute();
            return true;
        } catch(Exception e){
            System.out.println("EnrollClass:"+e.getMessage());
        }
        return false;
    }

    public boolean unEnrollClass(String StudentID, String ClassID) {
         try{
            String strSQL = "delete from List where StudentID = ? and ClassID=?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, StudentID);
            pstm.setString(2, ClassID);
            pstm.execute();
            return true;
        } catch(Exception e){
            System.out.println("unEnrollClass:"+e.getMessage());
        }
        return false;
    }
    
}
