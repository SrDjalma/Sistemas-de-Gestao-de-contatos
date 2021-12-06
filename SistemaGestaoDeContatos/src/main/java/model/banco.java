/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import view.ListaContatos;
import java.awt.Event;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Natan de Paula
 * @author Eriani Moreira
 */
public class banco {

    public static void conectaBanco() {
        Connection c = null;

        try {
            File diretorio = new File("dados/");
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dados/BancoContatos.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void criarTabela() {
        try {
            conectaBanco();
            Connection c = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dados/BancoContatos.db");
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE CONTATO "
                    + "(ID INTEGER PRIMARY KEY     AUTOINCREMENT,"
                    + " NOME           TEXT    NOT NULL, "
                    + " TELEFONE            CHAR(50)     NOT NULL UNIQUE)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
        }
    }

    public static void inserebanco(Contato cont) {
        try {
            conectaBanco();
            Statement stmt = null;
            Connection c = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dados/BancoContatos.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO CONTATO (NOME,TELEFONE) " + "VALUES (?,?);";

            PreparedStatement pstm = c.prepareStatement(sql);

            pstm.setString(1, cont.getNome());
            pstm.setString(2, cont.getTelefone());

            pstm.execute();

            pstm.close();
            //stmt.executeUpdate(sql);
            //stmt.close();
            c.commit();
            c.close();
            System.out.println("foi");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static DefaultTableModel selectBanco(DefaultTableModel tableContatos) {
        try {
            conectaBanco();
            Connection c = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dados/BancoContatos.db");
            Statement stmt = c.createStatement();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTATO;");

            Object[] objetos = new Object[2];
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");

                objetos[0] = nome;
                objetos[1] = telefone;
                tableContatos.addRow(objetos);

            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return tableContatos;

    }

    public void DeleteBanco(Contato cont) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM CONTATO WHERE NOME = ? AND TELEFONE = ?";
        conectaBanco();
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dados/BancoContatos.db");
            PreparedStatement pstm = c.prepareStatement(sql);

            pstm.setString(1, cont.getNome());
            pstm.setString(2, cont.getTelefone());

            pstm.execute();

            pstm.close();
            c.close();
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao excluir");
        }

    }
    
    public void UpdateBanco(Contato cont) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE FROM CONTATO SET NOME = ? , TELEFONE = ?"
                + "WHERE NOME = ? AND TELEFONE = ?";
        conectaBanco();
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dados/BancoContatos.db");
            PreparedStatement pstm = c.prepareStatement(sql);

            pstm.setString(1, cont.getNome());
            pstm.setString(2, cont.getTelefone());

            pstm.execute();

            pstm.close();
            c.close();
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao atualizar");
        }

    }
}
