import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseMySql extends Database{

   public static String db_name="demo";
    public static String db_user="root";
    public static String db_pass="";
    public static String db_host="";
    public static int    db_port=3306;

    Connection connection=null;
    public void baglan(){
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name;
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(girisIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
