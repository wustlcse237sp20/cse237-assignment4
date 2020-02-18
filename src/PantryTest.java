import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit tests for the Pantry class. You are welcome to examine
 * this file and you should run it to check your work.
 * 
 * You should not modify this file for any reason.
 */
class PantryTest {
    Pantry p = new Pantry();

    @Test
    void testAddFruit() {
        p.addFruit("Bananas", 5);
        p.addFruit("Apples", 6);
        Assert.assertEquals(p.getFruit().get(0), "Bananas");
        Assert.assertEquals(p.getFruit().get(1), "Apples");
        Assert.assertEquals((int) p.getNumFruit().get(0), 5);
        Assert.assertEquals((int) p.getNumFruit().get(1), 6);
    }

    @Test
    void testAddSnack() {
        p.addSnack("Chips", 3);
        p.addSnack("Granola Bars", 8);
        Assert.assertEquals(p.getSnacks().get(0), "Chips");
        Assert.assertEquals(p.getSnacks().get(1), "Granola Bars");
        Assert.assertEquals((int) p.getNumSnacks().get(0), 3);
        Assert.assertEquals((int) p.getNumSnacks().get(1), 8);
    }

    @Test
    void testEnoughFruit() {
        p.addFruit("Oranges", 5);
        Assert.assertTrue(p.enoughFruit("Oranges", 4));
        p.addFruit("Pears", 3);
        Assert.assertFalse(p.enoughFruit("Pears", 5));
    }

    @Test
    void testCountFood() {
        p.addFruit("Grapefruit", 4);
        p.addFruit("Dragon Fruit", 6);
        p.addSnack("Popcorn", 3);
        p.addSnack("Graham Crackers", 10);
        Assert.assertEquals(p.countFood(), 23);
    }

    @Test
    void testRemoveFood() {
        p.addFruit("Grapefruit", 4);
        p.addFruit("Dragon Fruit", 6);
        p.addSnack("Popcorn", 3);
        p.addSnack("Graham Crackers", 10);
        p.removeFood("Grapefruit", true, 2);
        p.removeFood("Graham Crackers", false, 11);
        Assert.assertEquals((int)p.getNumFruit().get(0), 2);
        Assert.assertEquals((int)p.getNumSnacks().get(1), 0);
    }

    @Test
    void testGoShopping() {
        p.addFruit("Grapefruit", 4);
        p.addFruit("Dragon Fruit", 6);
        p.addSnack("Popcorn", 3);
        p.addSnack("Graham Crackers", 10);

        String[] food = {"Grapefruit", "Graham Crackers", "Chips", "Bananas"};
        boolean[] isFruit = {true, false, false, true};
        int[] amountNeeded = {6, 10, 5, 5};
        Map<String, Integer> foodDict = new HashMap<String, Integer>();
        foodDict.put("Grapefruit", 2);
        foodDict.put("Graham Crackers", 1);
        foodDict.put("Chips", 4);
        foodDict.put("Bananas", 2);

        int price = p.goShopping(food, amountNeeded, isFruit, foodDict);
        int expectedPrice = 34;
        Assert.assertEquals(price, expectedPrice);
        Assert.assertEquals((int)p.getNumFruit().get(0), 6);
        Assert.assertEquals(p.getSnacks().get(2), "Chips");
        Assert.assertEquals((int)p.getNumSnacks().get(2), 5);
    }
}