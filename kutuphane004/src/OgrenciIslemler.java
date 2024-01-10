import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OgrenciIslemler {
    PreparedStatement preparedStatement = null;
    Connection connection=null;

    Scanner scanner = ScannerSingleton.getInstance();
    OgrenciIslemler(){
        while(true){

        System.out.println("yapılacak işlemi seçiniz\n" +
                "1-tüm kitapları listele\n" +
                "2-kitap ara \n"+
                "3-\n" +
                "4-\n"+
                "5-\n"+
                "0-cıkış");
        int secim = scanner.nextInt();
        if (secim==1){
            kitapListele();
        } else if (secim==2) {
            kitapAra();
        }else if (secim==3){

        }else if(secim==4){

        } else if (secim==5) {

        } else if (secim==0){
            break;
        }}

    }

    private void kitapAra() {
        String url ="jdbc:mysql://"+DatabaseMySql.db_host+":"+DatabaseMySql.db_port+"/"+DatabaseMySql.db_name;
        try {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,DatabaseMySql.db_user,DatabaseMySql.db_pass);
        } catch (SQLException ex) {

            Logger.getLogger(girisIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("kitap adi giriniz");
        String kitapAdi = scanner.next();
        kitapAdi = "%"+kitapAdi+"%";
        String sorgu = "SELECT * FROM kitaplar WHERE `kitapAdi` LIKE ?;";
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            preparedStatement.setString(1,kitapAdi);
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






}
