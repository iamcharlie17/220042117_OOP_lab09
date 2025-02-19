import java.util.List;
import java.util.Scanner;

public class YogurtShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<YogurtFlavor> flavors = List.of(
                new YogurtFlavor("Cookies and Cream", 2.80),
                new YogurtFlavor("Chocolate Fudge", 3.00),
                new YogurtFlavor("Pistachio Delight", 3.25)
        );

        List<Topping> toppings = List.of(
                new Topping("Sprinkles", 0.30),
                new Topping("Mixed Chopped Nuts", 0.80),
                new Topping("Crushed Gingerbread", 0.75),
                new Topping("Fresh Strawberries", 1.00)
        );

        System.out.print("Would you like a Glass Jar ($5.00 extra)? (1 for Yes, 0 for No): ");
        boolean isGlassJar = scanner.nextInt() == 1;
        Order order = new Order(isGlassJar);

        int choice;
        while (true) {
            System.out.println("\nAvailable Yogurt Flavors:");
            for (int i = 0; i < flavors.size(); i++) {
                System.out.println((i + 1) + ". " + flavors.get(i).name + " - $" + flavors.get(i).price);
            }
            System.out.print("Select a flavor (0 to stop): ");
            choice = scanner.nextInt();
            if (choice == 0) break;
            order.addFlavor(flavors.get(choice - 1));
        }

        while (true) {
            System.out.println("\nAvailable Toppings:");
            for (int i = 0; i < toppings.size(); i++) {
                System.out.println((i + 1) + ". " + toppings.get(i).name + " - $" + toppings.get(i).price);
            }
            System.out.print("Select a topping (0 to stop): ");
            choice = scanner.nextInt();
            if (choice == 0) break;
            order.addTopping(toppings.get(choice - 1));
        }

        System.out.print("\nChoose invoice format:\n1. Text File\n2. CSV File\n");
        int formatChoice = scanner.nextInt();
        if (formatChoice == 1) {
            order.generateInvoiceText();
        } else {
            order.generateInvoiceCSV();
        }

        System.out.println("Thank you for your order!");
        scanner.close();
    }
}