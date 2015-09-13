/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.light.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Simone
 */
public class Conexao {

    final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=light";
    final String user = "sa";
    final String password = "42301886";
    private Connection conexao;
    public ResultSet resultSet;
    public Statement stmt;

    public Connection getConection(){

        boolean result = false;
        conexao = null;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);

            if (conexao != null) {
                result = true;
                System.out.println("Banco de dados conectado!!");
            }

        } catch (ClassNotFoundException Driver) {
            System.out.println("Driver não localizado: " + Driver);
        }catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        return conexao;
    }

    public Statement getSteStatement() {

        Statement retorno = null;

        try {

            retorno = getConection().createStatement();

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        return retorno;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {

         return getConection().prepareStatement(sql);

    }
    
     public void closeAll(){
          
         try{
          if(conexao != null){
              conexao.close();
          }
         }catch(SQLException e){
             System.out.println("Erro: Não foi possível encontrar a instrução");
         }
      }
}
