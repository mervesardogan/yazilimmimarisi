import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminIslemler {
    PreparedStatement preparedStatement = null;
    Connection connection=null;

    Scanner scanner = ScannerSingleton.getInstance();
    AdminIslemler(){
        while(true){

        System.out.println("yapılacak işlemi seçiniz\n" +
                "1-kitap ekle\n" +
                "2-kitap sil\n"+
                "3-kitap listele\n" +
                "4-kullanici ekle\n"+
                "5-kullanici listele\n"+
                "0-cıkış");
        int secim = scanner.nextInt();
        if (secim==1){
            kitapEkle();
        } else if (secim==2) {
            kitapSil();
        }else if (secim==3){
            kitapListele();
        }else if(secim==4){
            if(kullaniciEkle()){
                System.out.println("kullanici eklendi");
            }else{
                System.out.println("kullanici eklenemedi");
            }
        } else if (secim==5) {
            kullaniciListele();
        } else if (secim==0){
            break;
        }}

    }

    private void kullaniciListele() {
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name;
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(girisIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sorgu = "SELECT * FROM kullaniciler";
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getNString("username");
                String password = resultSet.getNString("password");


                System.out.println("id = " + id + " kullanici adi = "+ username + " kullanici password = " + password );

            }

        } catch (SQLException ex) {
        }
    }

    private boolean kullaniciEkle() {

        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name;
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(girisIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ogrenci kullanıcı adınızı giriniz");
        String kullaniciadi = scanner.next();
        System.out.println(" ogrenci kullanıcı parolası giriniz");
        String kullaniciparola = scanner.next();
        String sorgu = "INSERT INTO `kullaniciler` (`id`, `username`, `password`) VALUES (NULL,? , ?);";
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            preparedStatement.setString(1,kullaniciadi);
            preparedStatement.setString(2, kullaniciparola);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return  false;
        }
    }

    private void kitapListele() {
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name;
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(girisIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sorgu = "SELECT * FROM kitaplar";
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String kitapadi = resultSet.getNString("kitapAdi");
                String yazaradi = resultSet.getNString("yazarAdi");
                int sayfasayisi = resultSet.getInt("sayfaSayisi");

                System.out.println("id = " + id + " kitap adi = "+ kitapadi + " yazar adi = " + yazaradi + " sayfa sayisi = " + sayfasayisi );

            }

        } catch (SQLException ex) {
        }

    }

    private void kitapSil() {
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name;
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(girisIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Silinecek kitap id giriniz");
        int kitapAdi = scanner.nextInt();

        String sorgu = "DELETE FROM kitaplar WHERE `kitaplar`.`id`  = ?;";

        try {
            preparedStatement=connection.prepareStatement(sorgu);
            preparedStatement.setInt(1,kitapAdi);

            preparedStatement.executeUpdate();
            System.out.println("kitap silindi");
        } catch (SQLException ex) {
            System.out.println("kitap silinemedi");
        }
    }

    private void kitapEkle() {
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name;
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(girisIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("kitap adi giriniz");
        String kitapAdi = scanner.next();
        System.out.println("Yazar adi giriniz");
        String yazarAdi = scanner.next();
        System.out.println("Sayfa sayisi giriniz");
        int sayfaSayisi = scanner.nextInt();
        String sorgu = "INSERT INTO `kitaplar` (`id`, `kitapAdi`, `yazarAdi`, `sayfaSayisi`) VALUES (NULL, ?, ?, ?);";
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            preparedStatement.setString(1,kitapAdi);
            preparedStatement.setString(2, yazarAdi);
            preparedStatement.setInt(3, sayfaSayisi);
            preparedStatement.executeUpdate();
            System.out.println("kitap eklendi");
        } catch (SQLException ex) {
            System.out.println("kitap eklenemedi");
        }
    }


}
