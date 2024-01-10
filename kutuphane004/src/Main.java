

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = ScannerSingleton.getInstance();
        kullanicisecimi(scanner);


    }

    private static void kullanicisecimi(Scanner scanner) {
        System.out.println("kullanici tipini giriniz \nogrenci\nadmin");
        String kullanicitipi = scanner.next();
        kullaniciFactory kullaniciFactory = new kullaniciFactory();
        kullaniciFactory.createKullanici(kullanicitipi);

    }
}