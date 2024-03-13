import org.w3c.dom.Attr;

import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {



        Trip Iasi= new Trip("Iasi", "Weekend", List.of( new Statue("Mihai Gura de Aur"),
                new Church("Adormirea Maicii Domnului"),
                new Concert("Untold")));

        Trip Suceava= new Trip("Suceava", "Weekend", List.of( new Statue("Mihai Eminescu"),
                new Church("Catedrala Romana"),
                new Concert("Andra si Connecte-R")));

        Iasi.displayInformation();
        Suceava.displayInformation();
        System.out.println(Iasi.getName().compareTo(Suceava.getName()));
    }
}