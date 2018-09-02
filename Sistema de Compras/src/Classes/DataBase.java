/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ifsc
 */
public class DataBase {
   
    public static void getLista(){   
    //public static LinkedList<> getLista(){   
        Connection conexao = DBUtils.getConexao();
        
        //Peguar as informaçoes no banco de dados e poe na lista
        //LinkedList<> lNoticias = new ArrayList<>();        
        String sql = "SELECT * FROM noticias";
        Statement st = null;
        ResultSet rs =  null;
        
        try {
            st =  conexao.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //Noticia noticia = new Noticia();
                //noticia.setId(Integer.parseInt((rs.getString("id"))));
                //noticia.setBooleano(Integer.parseInt((rs.getString("booleano"))));
                //noticia.setTexto(rs.getString("texto"));
                //noticia.setImage_path(rs.getString("imagem_path")); 
                //lNoticias.add(noticia);                
            }
            
            rs.close();
            st.close();
            conexao.close();
            
        } catch (SQLException ex) {
        	System.err.println("Error: "+ex.getMessage());
        }

        //return lNoticias;
    }
    
}