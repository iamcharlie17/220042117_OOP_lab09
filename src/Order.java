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


}