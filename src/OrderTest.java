import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;

public class OrderTest {

    @Test
    void testSubtotalCalculation() {
        Order order = new Order(false);
        order.addFlavor(new YogurtFlavor("Chocolate Fudge", 3.00));
        order.addFlavor(new YogurtFlavor("Pistachio Delight", 3.25));
        order.addTopping(new Topping("Sprinkles", 0.30));

        assertEquals(6.55, order.calculateSubtotal(), 0.01);
    }

    @Test
    void testTotalWithTax() {
        Order order = new Order(false);
        order.addFlavor(new YogurtFlavor("Chocolate Fudge", 3.00));
        order.addFlavor(new YogurtFlavor("Pistachio Delight", 3.25));
        order.addTopping(new Topping("Sprinkles", 0.30));

        double expectedTotal = 6.55 + (6.55 * 0.08);
        assertEquals(expectedTotal, order.calculateTotal(), 0.01);
    }

    @Test
    void testGlassJarPriceAdded() {
        Order order = new Order(true);
        order.addFlavor(new YogurtFlavor("Cookies and Cream", 2.80));

        double expectedTotal = (2.80 + 5.00) + ((2.80 + 5.00) * 0.08);
        assertEquals(expectedTotal, order.calculateTotal(), 0.01);
    }

    @Test
    void testInvoiceTextFileCreation() throws IOException {
        Order order = new Order(false);
        order.addFlavor(new YogurtFlavor("Cookies and Cream", 2.80));
        order.generateInvoiceText();

        File file = new File("invoice.txt");
        assertTrue(file.exists());
    }

    @Test
    void testInvoiceCSVFileCreation() throws IOException {
        Order order = new Order(false);
        order.addFlavor(new YogurtFlavor("Chocolate Fudge", 3.00));
        order.generateInvoiceCSV();

        File file = new File("invoice.csv");
        assertTrue(file.exists());
    }
}
