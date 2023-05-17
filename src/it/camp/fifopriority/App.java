package it.camp.fifopriority;

import it.camp.fifopriority.core.FIFOPriority;

public class App {
    public static void main(String[] args) {
        FIFOPriority fifoPriority = FIFOPriority.getInstance();

        fifoPriority.push("pierwszy", 15);
        fifoPriority.push("drugi", 12);
        fifoPriority.push("trzeci", 19);
        fifoPriority.push("czwarty", 40);
        fifoPriority.push("piąty", 19);

        System.out.println(fifoPriority.popFirst());
        System.out.println(fifoPriority.popHighest());
        System.out.println(fifoPriority.popHighThree());
    }
}
