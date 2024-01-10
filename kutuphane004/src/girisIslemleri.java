import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class girisIslemleri {

    PreparedStatement preparedStatement = null;
    Connection connection=null;

    public  girisIslemleri(){

        Baglan();




    }

    public Boolean girisYap(String kullanici_adi,String parola){


        String sorgu = "Select * From adminler where username = ? and password = ? ";

        try {

            preparedStatement=connection.prepareStatement(sorgu);
            preparedStatement.setString(1,kullanici_adi);
            preparedStatement.setString(2, parola);

            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException ex) {


            return  false;
        }


    }


    public void Baglan() {
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name;
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(girisIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
