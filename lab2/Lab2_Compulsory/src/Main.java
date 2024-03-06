//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Depot depot= new Depot("Depot", DepotType.regular);
        System.out.println(depot.getName() + " " + depot.getType());

        Vehicle  BMV = new Vehicle("BMV", depot);
        Vehicle  AUDI = new Vehicle("AUDI", depot);

        Client iustin= new Client("Iustin");
        Client stefan= new Client("Stefan");

        System.out.println(" Masini: "+BMV.getName() + " si " + AUDI.getName());
        System.out.println(BMV.getDepot().getType() + " si " + AUDI.getDepot().getType());

        System.out.println(" Clienti: "+ iustin.getName() + " si " +stefan.getName());

        System.out.println(" ToString: "+ iustin.toString() + " si " +stefan.toString());


    }
}