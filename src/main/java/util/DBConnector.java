/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leleg
 */
public class DBConnector {
    private static final String USUARIO = "root";
   private static final String SENHA = "";
   private static final String BANCO = "AtividadePOO";
   private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
   private static final String STRINGCONEXAO = "jdbc:mysql://localhost:3306/";
   
   /**
    * obtém uma conexão com o banco de dados dbloja no servidor MySql local
    * @return um objeto representando uma conexão com o banco de dados
    */
   public static Connection getConexao(){
       Connection con = null;
       try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection(STRINGCONEXAO+BANCO,USUARIO, SENHA);
       } catch (ClassNotFoundException e){
                throw new RuntimeException("Driver não encontrado: "+ e.getMessage());
       } catch (SQLException e){
           throw new RuntimeException("Erro ao conectar: "+e.getMessage());
       }
       return con;
   }
   
   /**
    * fecha uma conexão com o banco de dados
    * @param con = a conexão a ser fechada
    */
   public static void fechaConexao(Connection con){
       try{
           if (con != null) {
               con.close();
           }
       } catch (SQLException e){
           throw new RuntimeException("Impossível fechar conexão: "+e.getMessage());
       }
   }
   
   /**
    * fecha uma conexão com o bonco de dados e um comando SQL
    * @param con  a conexão a ser fechada
    * @param pstmt o comando PreparedStatement a ser fechado
    */
   public static void fechaConexao(Connection con, PreparedStatement pstmt){
      try{
          if (con != null){
              fechaConexao(con);
          }
          if (pstmt != null){
              pstmt.close();
          } 
      } catch (SQLException e){
          throw new RuntimeException("Não foi possível fechar o comando: "+ e.getMessage());
      } 
   }
   
   /**
    * fecha a conexão, o comando Sql e o objeto ResultSet manipulado
    * @param con = a conexão a ser fechada
    * @param pstmt = o comando PreparedStatement a ser fechado
    * @param rs = o objeto ResultSet
    */
   public static void fechaConexao(Connection con, PreparedStatement pstmt, ResultSet rs){
       fechaConexao(con, pstmt);
       try {
           if (rs != null){
               rs.close();
           }
       } catch (SQLException e){
           throw new RuntimeException ("Não foi possível fechar o ResultSet: "+e.getMessage());
       }
}
}
