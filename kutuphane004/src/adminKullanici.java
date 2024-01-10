import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class adminKullanici implements kullanici{
    Scanner scanner = ScannerSingleton.getInstance();
    PreparedStatement preparedStatement = null;
    Connection connection=null;
    adminKullanici(){
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name+"?useUnicode=true&characterEncoding=utf-8&serverTimezone=Turkey";
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(ogrenciKullanici.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (giris()){
            AdminIslemler adminIslemler = new AdminIslemler();
            System.out.println("giriş 0002");
        }else{
            System.out.println("admin girişi yapılamadı ");
        }

    }
    @Override
    public boolean giris() {


        System.out.println("admin kullanıcı adınızı giriniz");
        String kullaniciadi = scanner.next();
        System.out.println("admin kullanıcı parolası giriniz");
        String kullaniciparola = scanner.next();


        girisKontrol(kullaniciadi,kullaniciparola);

        if(girisKontrol(kullaniciadi,kullaniciparola)){
            System.out.println("admin kullanıcı adı ve şifre ile giriş sağlandı");
            return true;
        }else{
            System.out.println("admin kullanıcı adı ve şifre ile giriş sağlanamadı");
            return false;
        }

    }

    @Override
    public Boolean girisKontrol(String kullanici_adi,String parola) {

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
}
