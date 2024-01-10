import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ogrenciKullanici implements kullanici{
    PreparedStatement preparedStatement = null;
    Connection connection=null;

    ogrenciKullanici(){
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name+"?useUnicode=true&characterEncoding=utf-8&serverTimezone=Turkey";
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(ogrenciKullanici.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (giris()){
            OgrenciIslemler ogrenciIslemler = new OgrenciIslemler();
            System.out.println("giriş 0001");
        }else{
            System.out.println("ogrenci girişi yapılamadı ");
        }

    }
    @Override
    public boolean giris() {
        Scanner scanner = ScannerSingleton.getInstance();
        System.out.println("ogrenci kullanıcı adınızı giriniz");
        String kullaniciadi = scanner.next();
        System.out.println(" ogrenci kullanıcı parolası giriniz");
        String kullaniciparola = scanner.next();


        girisKontrol(kullaniciadi,kullaniciparola);

        if(girisKontrol(kullaniciadi,kullaniciparola)){
            System.out.println("ogrenci kullanıcı adı ve şifre ile giriş sağlandı");
            return true;
        }else{
            System.out.println("ogrenci kullanıcı adı ve şifre ile giriş sağlanamadı");
            return false;
        }

    }




    @Override
    public Boolean girisKontrol(String kullanici_adi, String parola) {
        String sorgu = "Select * From kullaniciler where username = ? and password = ? ";

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
