import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Order {
    private List<YogurtFlavor> flavors = new ArrayList<>();
    private List<Topping> toppings = new ArrayList<>();
    private boolean isGlassJar;
    private static final double TAX_RATE = 0.08;
    private static final double GLASS_JAR_PRICE = 5.00;

    public Order(boolean isGlassJar) {
        this.isGlassJar = isGlassJar;
    }

    public void addFlavor(YogurtFlavor flavor) {
        flavors.add(flavor);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (YogurtFlavor flavor : flavors) {
            subtotal += flavor.price;
        }
        for (Topping topping : toppings) {
            subtotal += topping.price;
        }
        if (isGlassJar) {
            subtotal += GLASS_JAR_PRICE;
        }
        return subtotal;
    }

    public double calculateTax() {
        return calculateSubtotal() * TAX_RATE;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }

    public void generateInvoiceText() {
        try (FileWriter file = new FileWriter("invoice.txt")) {
            file.write("Yogurt Shop Invoice\n");
            for (YogurtFlavor flavor : flavors) {
                file.write(flavor.name + " - 1 scoop: $" + String.format("%.2f", flavor.price) + "\n");
            }
            for (Topping topping : toppings) {
                file.write(topping.name + " - 1 time: $" + String.format("%.2f", topping.price) + "\n");
            }
            if (isGlassJar) {
                file.write("Glass Jar - 1 time: $" + String.format("%.2f", GLASS_JAR_PRICE) + "\n");
            }
            file.write("Subtotal: $" + String.format("%.2f", calculateSubtotal()) + "\n");
            file.write("Tax: $" + String.format("%.2f", calculateTax()) + "\n");
            file.write("Total Amount Due: $" + String.format("%.2f", calculateTotal()) + "\n");
            System.out.println("Invoice saved as invoice.txt");
        } catch (IOException e) {
            System.out.println("Error writing invoice file!");
        }
    }

    public void generateInvoiceCSV() {
        try (FileWriter file = new FileWriter("invoice.csv")) {
            file.write("Ingredients,Amount,Price\n");
            for (YogurtFlavor flavor : flavors) {
                file.write(flavor.name + ",1," + String.format("%.2f", flavor.price) + "\n");
            }
            for (Topping topping : toppings) {
                file.write(topping.name + ",1," + String.format("%.2f", topping.price) + "\n");
            }
            if (isGlassJar) {
                file.write("Glass Jar,1," + String.format("%.2f", GLASS_JAR_PRICE) + "\n");
            }
            file.write("Subtotal,-," + String.format("%.2f", calculateSubtotal()) + "\n");
            file.write("Tax,-," + String.format("%.2f", calculateTax()) + "\n");
            file.write("Total Amount Due,-," + String.format("%.2f", calculateTotal()) + "\n");
            System.out.println("Invoice saved as invoice.csv");
        } catch (IOException e) {
            System.out.println("Error writing CSV file!");
        }
    }
}