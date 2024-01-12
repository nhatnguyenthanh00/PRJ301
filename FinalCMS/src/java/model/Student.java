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
public class Student extends DBContext {
    private String StudentID,account,password,name;

    public Student() {
        connect();
    }

    public Student(String account, String password) {
        this.account = account;
        this.password = password;
        connect();
    }
    

    public Student(String StudentID, String account, String password, String name) {
        this.StudentID = StudentID;
        this.account = account;
        this.password = password;
        this.name = name;
        connect();
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
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
    public Student checkLogin() {
        try{
            String strSQL="SELECT * FROM Students WHERE account = ? and Password = ?";
          
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, account);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while(rs.next()){
                StudentID = rs.getString(1);
                name = rs.getString(4);
                return this;
            }
        } catch(Exception e){
            
            System.out.println("checkLogin:" + e.getMessage());
        }
        return null;
    }
    public ArrayList<Class> getListClass(){
        ArrayList<Class> data = new ArrayList<Class>();
        try{
            String strSQL="SELECT * FROM List WHERE StudentID = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, StudentID);
            rs = pstm.executeQuery();
            while(rs.next()){
                String ClassID = rs.getString(3);
                Class nc = new Class();
                nc = nc.getClassByID(ClassID);
                data.add(nc);
            }
        } catch (Exception e){
            System.out.println("getListClass:"+e.getMessage());
        }
        return data;
    }

    public boolean checkEnroll(String ClassID) {
        try{
            String strSQL = "select * from List where StudentID = ? and ClassID = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, StudentID);
            pstm.setString(2, ClassID);
            rs = pstm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch(Exception e){
            System.out.println("checkEnroll:"+e.getMessage());
        }
        return false;
    }
}
