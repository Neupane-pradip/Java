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
    @Test
    public void testIsEmpty() {
        System.out.println("testIsEmpty");

        int juiceCount = 1;
        Order instance = new Order();
        Item item = new Item("Juice", 1);

        int mangoCount = 1;
        Item item2 = new Item("Mango", 1.5);

        instance.addItems(item, juiceCount);
        instance.addItems(item2, mangoCount);

        assertFalse(instance.isEmpty());
    }

    @Test
    public void testRemoveItems() {
        System.out.println("testRemoveItems");

        int juiceCount = 1;
        Order instance = new Order();
        Item item = new Item("Juice", 1);

        instance.addItems(item, juiceCount);

        instance.removeItems("Juice", juiceCount);

        assertTrue(instance.isEmpty());
    }

    @Test
    public void testItemIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item(null, -3);
        });
    }

    @Test
    public void testEntryIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Entry(new Item("fsd", 1), -3);
        });
    }

    @Test
    public void testAddItemsIllegalArgumentException() {
        Order instance = new Order();
        Item item = new Item("Juice", 1);
        assertThrows(IllegalArgumentException.class, () -> {
            instance.addItems(item, -1);
        });
    }
     @Test
    public void testRemoveItemsIllegalArgumentException() {
        Order instance = new Order();
        Item item = new Item("Juice", 1);
        instance.addItems(item, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            instance.removeItems("Juice", 4);
        });
    }

    @Test
    public void testAddItemsNoSuchElementException() {
        Order instance = new Order();
        assertThrows(NoSuchElementException.class, () -> {
            instance.addItems("daffd", 1);
        });
    }
    @Test
    public void testRemoveItemsNoSuchElementException() {
        Order instance = new Order();
        assertThrows(NoSuchElementException.class, () -> {
            instance.removeItems("fadsafd", 1);
        });
    }

    @Test
    public void testAddItemsIllegalStateException() {
        Order instance = new Order();
        Item item = new Item("Juice", 0.8);
        instance.addItems(item, 1);
        Item item2 = new Item("Juice", 1.2);
        assertThrows(IllegalStateException.class, () -> {
            instance.addItems(item2, 1);
        });
    }
}