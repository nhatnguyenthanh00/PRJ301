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
public class Document extends DBContext {
    private String DocumentID,DocumentLink;

    public Document() {
        connect();
    }

    public Document(String DocumentID, String DocumentLink) {
        this.DocumentID = DocumentID;
        this.DocumentLink = DocumentLink;
        connect();
    }

    public String getDocumentID() {
        return DocumentID;
    }

    public String getDocumentLink() {
        return DocumentLink;
    }

    public void setDocumentID(String DocumentID) {
        this.DocumentID = DocumentID;
    }

    public void setDocumentLink(String DocumentLink) {
        this.DocumentLink = DocumentLink;
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

    String FindDocumentLinkByID(String DocumentID) {
        try{
            String strSQL = "select * from Document where DocumentID=?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, DocumentID);
            rs = pstm.executeQuery();
            while(rs.next()){
                String DocumentLink = rs.getString(2);
                return DocumentLink;
            }
        } catch(Exception e){
            System.out.println("FindDocumentNameByID"+e.getMessage());
        }
        return null;
    }
    
}
