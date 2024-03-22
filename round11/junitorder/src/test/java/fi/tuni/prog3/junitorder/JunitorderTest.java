package fi.tuni.prog3.junitorder;

import fi.tuni.prog3.junitorder.Order.Entry;
import fi.tuni.prog3.junitorder.Order.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JunitorderTest {
     public JunitorderTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddItems_OrderItem() {
        System.out.println("testAddItems_OrderItem");
        int count = 1;
        Order instance = new Order();
        Item item = new Item("Juice", 0.8);
        boolean expResult = true;
        boolean result = instance.addItems(item, count);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddItems_String() {
        System.out.println("testAddItems_String");
        int count = 1;
        Order instance = new Order();
        Item item = new Item("Juice", 0.8);

        instance.addItems(item, count);

        String name = "Juice";
        int juiceCount = 2;

        boolean expResult = true;
        boolean result = instance.addItems(name, juiceCount);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetEntries() {
        System.out.println("testGetEntries");

        int juiceCount = 1;
        Order instance = new Order();
        Item item = new Item("Juice", 0.8);
        Entry entry = new Entry(item, juiceCount);

        int mangoCount = 1;
        Item item2 = new Item("Mango", 1.5);
        Entry entry2 = new Entry(item2, mangoCount);

        instance.addItems(item, juiceCount);
        instance.addItems(item2, mangoCount);

        String expResult = "Juice";
        assertEquals(expResult, instance.getEntries().get(0).getItem().getName());
    }

    @Test
    public void testGetEntryCount() {
        System.out.println("testGetEntryCount");

        int juiceCount = 1;
        Order instance = new Order();
        Item item = new Item("Juice", 0.8);

        int mangoCount = 2;
        Item item2 = new Item("Mango", 1.5);

        instance.addItems(item, juiceCount);
        instance.addItems(item2, mangoCount);

        int expResult = 2;
        assertEquals(expResult, instance.getEntryCount());
    }
     @Test
    public void testGetItemCount() {
        System.out.println("testGetItemCount");

        int juiceCount = 1;
        Order instance = new Order();
        Item item = new Item("Juice", 0.8);

        int mangoCount = 2;
        Item item2 = new Item("Mango", 1.5);

        instance.addItems(item, juiceCount);
        instance.addItems("Juice", 2);
        instance.addItems(item2, mangoCount);

        int expResult = 5;
        assertEquals(expResult, instance.getItemCount());
    }

    @Test
    public void testGetTotalPrice() {
        System.out.println("testGetTotalPrice");

        int juiceCount = 1;
        Order instance = new Order();
        Item item = new Item("Juice", 1);

        int mangoCount = 1;
        Item item2 = new Item("Mango", 1.5);

        instance.addItems(item, juiceCount);
        instance.addItems(item2, mangoCount);

        double expResult = 2.5;
        assertEquals(expResult, instance.getTotalPrice(), 0);
    }
}