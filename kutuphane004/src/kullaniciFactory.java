import java.util.Objects;

public class kullaniciFactory {

    public kullanici createKullanici(String tip){

            if (Objects.equals(tip, "ogrenci")){
                return new ogrenciKullanici();
            } else if (Objects.equals(tip,"admin")) {
                return new adminKullanici();
            }
        System.out.println("seçim gerçekleşemedi");
        return null;
    }
}
