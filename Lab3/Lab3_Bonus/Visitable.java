public interface Visitable {

    String getVisitable();


    default String showOpeningHour() {
        return "Programele incep depa ora 10 in fiecare zi cu costul de ";
    }
}
