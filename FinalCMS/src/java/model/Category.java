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
public class Category extends DBContext {
    private String CateID,name;

    public Category() {
        connect();
    }

    public Category(String CateID, String name) {
        this.CateID = CateID;
        this.name = name;
        connect();
    }

    public String getCateID() {
        return CateID;
    }

    public String getName() {
        return name;
    }

    public void setCateID(String CateID) {
        this.CateID = CateID;
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

    public ArrayList<Category> getListCate() {
        ArrayList<Category> data = new ArrayList<Category>();
        try{
            String strSQL = "select * from Categorys";
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();
            while(rs.next()){
                String CateID = rs.getString(1);
                String name = rs.getString(2);
                Category cnew = new Category(CateID,name);
                data.add(cnew);
            }
        } catch(Exception e){
            System.out.println("getListCate:"+e.getMessage());
        }
        return data;
    }
    
    
}
