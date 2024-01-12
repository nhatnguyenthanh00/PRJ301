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
public class Class extends DBContext {

    private String ClassID, CateID, ClassName, TeacherName;

    public Class() {
        connect();
    }

    public Class(String ClassID, String CateID, String ClassName, String TeacherName) {
        this.ClassID = ClassID;
        this.CateID = CateID;
        this.ClassName = ClassName;
        this.TeacherName = TeacherName;
        connect();
    }

    public String getClassID() {
        return ClassID;
    }

    public String getCateID() {
        return CateID;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setClassID(String ClassID) {
        this.ClassID = ClassID;
    }

    public void setCateID(String CateID) {
        this.CateID = CateID;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }
    Connection cnn; // ket noi data base
    Statement stm; // thuc hien cac cau lenh sql
    ResultSet rs; // dung de luu tru va xu ly du lieu
    PreparedStatement pstm;

    private void connect() {
        cnn = super.connection;
        if (cnn != null) {
            System.out.println("Connect success");
        } else {
            System.out.println("Connect fail!");
        }
    }

    public Class getClassByID(String id) {
        try {
            String strSQL = "select * from Class Where ClassID=?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String ClassID = rs.getString(1);
                String CateID = rs.getString(2);
                String ClassName = rs.getString(3);
                String TeacherName = rs.getString(4);
                Class nc = new Class(ClassID, CateID, ClassName, TeacherName);
                return nc;
            }
        } catch (Exception e) {
            System.out.println("getClassByID:" + e.getMessage());
        }
        return null;
    }

    public ArrayList<Class> getFullClass() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Class> SearchClass(String kt) {
        ArrayList<Class> data = new ArrayList<Class>();
        try {
            String strSQL = "select * from Class";
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String ClassID = rs.getString(1);
                String CateID = rs.getString(2);
                String ClassName = rs.getString(3);
                String TeacherName = rs.getString(4);
                String copyClassName = ClassName.replaceAll(" ", "");
                copyClassName = copyClassName.toLowerCase();
                String copykt = kt.replaceAll(" ", "");
                copykt = copykt.toLowerCase();
                if (copyClassName.contains(copykt)) {
                    Class cnew = new Class(ClassID, CateID, ClassName, TeacherName);
                    data.add(cnew);
                }
            }
        } catch (Exception e) {
            System.out.println("SearchClass:" + e.getMessage());
        }
        return data;
    }
    public String getCateName(){
        try{
            String strSQL = "select * from Categorys where CateID = ? ";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, CateID);
            
            rs = pstm.executeQuery();
            while(rs.next()){
                String CateName = rs.getString(2);
                return CateName;
            }
        } catch(Exception e){
            System.out.println("getCateName:"+e.getMessage());
        }
        return null;
    }

    public ArrayList<Document> getLink() {
         ArrayList<Document> data = new ArrayList<Document>();
        try {
            String strSQL = "select * from ListDocument where ClassID=?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, ClassID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Document dnew = new Document();
                String DocumentID = rs.getString(3);
                String DocumentLink = dnew.FindDocumentLinkByID(DocumentID);
                data.add(new Document(DocumentID,DocumentLink));
            }
        } catch (Exception e) {
            System.out.println("SearchClass:" + e.getMessage());
        }
        return data;
    }

    public boolean checkExist(String CateID,String ClassName,String TeacherName) {
        try{
            String strSQL = "select * from Class where CateID=? and ClassName=? and TeacherName=?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, CateID);
            pstm.setString(2, ClassName);
            pstm.setString(3, TeacherName);
            rs = pstm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch(Exception e){
            System.out.println("checkExist:"+e.getMessage());
        }
        return false;
    }

    public boolean addClass(String CateID, String ClassName, String TeacherName) {
        try{
            String strSQL = "insert Class (CateID,ClassName,TeacherName) VALUES(?,?,?)";
            pstm = cnn.prepareStatement(strSQL);
           // pstm.setString(1, CateID);
            pstm.setInt(1, Integer.parseInt(CateID));
            pstm.setString(2, ClassName);
            pstm.setString(3, TeacherName);
            pstm.execute();
            return true;
        } catch(Exception e){
            System.out.println("addClass:"+e.getMessage());
        }
        return false;
    }
}
