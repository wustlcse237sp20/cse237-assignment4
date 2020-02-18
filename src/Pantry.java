import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Pantry class which contains a variety of food for the user as well as other useful functions to simulate a pantry.
 * Currently all except one of the functions work, but they are written poorly. It is your job to clean up this code while making sure it still works.
 *
 * author: Puneet Sachdeva
 */

public class Pantry {
    // TODO 1: Some of these variables are poorly named for a variety of reasons - fix them
	// A list of fruits by name
    List<String> f;
    List<Integer> theNumberOfFruitsInThePantry;
    // A list of snacks by name
    List<String> s;
    List<Integer> theNumberOfSnacksInThePantry;

    public Pantry() {
        this.f = new ArrayList<>();
        this.theNumberOfFruitsInThePantry = new ArrayList<>();
        this.s = new ArrayList<>();
        this.theNumberOfSnacksInThePantry = new ArrayList<>();
    }

    public List<String> getFruit() {
        return this.f;
    }

    public List<Integer> getNumFruit() {
        return this.theNumberOfFruitsInThePantry;
    }

    public List<String> getSnacks() {
        return this.s;
    }

    public List<Integer> getNumSnacks() {
        return this.theNumberOfSnacksInThePantry;
    }
    
    /**
     * Adds a fruit to the pantry
     * @param name the name of the fruit to be added
     * @param quantity the quantity of the fruit to be added
     */
    public void addFruit(String name, int quantity) {
        this.f.add(name);
        this.theNumberOfFruitsInThePantry.add(quantity);
    }

    /**
     * Adds a snack along with its quantity to the pantry
     * @param name the name of the snack
     * @param quantity the amount of the snack
     */
    public void addSnack(String name, int quantity) {
        this.s.add(name);
        this.theNumberOfSnacksInThePantry.add(quantity);
    }

    // TODO 2: This simple function is written poorly - fix it so that the code is not redundant and organized in a logical way
    /**
     * Determines if there is enough of the given fruit - if the fruit does not exist, return False
     * @param fruit the name of the fruit that needs to be checked
     * @param minimum the minimum amount of fruit that needs to be present
     * @return
     */
    public boolean enoughFruit(String fruit, int minimum) {
        for (int i = 0; i < this.f.size(); i++ ) {
            // Check if this is the fruit we want
            if (this.f.get(i).equals(fruit) ){
                // If it is, then check if the amount required is necessary
                if (this.theNumberOfFruitsInThePantry.get(i) >= minimum) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // TODO 3: This function is written very poorly - fix it so that the variable names are proper and the loops are written correctly
    /**
     * Counts the total number of food items in the entire pantry
     * @return The quantity of food
     */
    public int countFood() {
        // Counter for amount of food
        int x = 0;
        // Counter for fruit
        int i = 0;
        // Counter for snacks
        int j = 0;

        while (true) {
            if (i >= this.theNumberOfFruitsInThePantry.size()) {
                break;
            }

            if (j >= this.theNumberOfSnacksInThePantry.size()) {
                break;
            }

            // Add the number of fruits and snacks to new variables and set it to x
            int y = x + this.theNumberOfFruitsInThePantry.get(i);
            int z = y + this.theNumberOfSnacksInThePantry.get(j);
            x = z;

            i++;
            j++;
        }

        return x;
    }

    //TODO 4: This function has some serious problems with variable names. Fix it up!
    /**
     * Removes a fruit or snack from the pantry
     * @param food the name of the food to be removed
     * @param isFruit true if the food being removed is a fruit
     * @param quantity how many of the food should be removed
     * @return
     */
    public int removeFood(String food, boolean isFruit, int quantity) {
        List<String> f = isFruit ? this.f : this.s;
        List<Integer> q = isFruit ? this.theNumberOfFruitsInThePantry : this.theNumberOfSnacksInThePantry;

        int c = 0;
        for (int i = 0; i < f.size(); i++) {
            if (f.get(i).equals(food)) {
                c = q.get(i);
                if (c < quantity) {
                    c = 0;
                } else {
                    c -= quantity;
                }
                q.set(i, c);
                break;
            }
        }
        return c;
    }

    // TODO 5: This function has a lot going on and should be broken down into separate smaller functions. Keep the flow of the code the same,
    //  just split it up into different functions. The variable names could also use some attention!

    /**
     * Takes in an array of food, an array of prices for each food, an array of the amount needed of each food, and an array indicating if each food is a fruit or not
     * and returns the cost of buying this list of groceries. If the food is already contained in the pantry, then subtract its quantity from the amount when calculating
     * the total price. For example, if we need to buy 5 Bananas, but currently our pantry already has 3 bananas, then we only need to buy 2 which means the cost of 2 should
     * be considered, not 5.
     *
     * The function also updates the pantry with the new food items
     *
     * @param f array of food
     * @param amt array of the necessary amounts
     * @param isF array of booleans indicating if this is a fruit or not
     * @param prices map which contains the price of each food
     * @return The total price of this shopping list
     */
    public int goShopping(String[] f, int[] amt, boolean[] isF, Map<String, Integer> prices) {
        // Determine how many of each food we need
        for (int i = 0; i < f.length; i++){
            // If it is a fruit, check how much of that fruit we have and subtract it from amountNeeded
            if (isF[i]) {
                // Once we find the fruit, we subtract. If we do not find it, we leave the amount needed as is
                for (int j = 0; j < this.f.size(); j++) {
                    if (this.f.get(j).equals(f[i])) {
                        amt[i] -= this.theNumberOfFruitsInThePantry.get(j);
                    }
                }
            } else {
                // Do the same, but for the snacks
                for (int j = 0; j < this.s.size(); j++) {
                    if (this.s.get(j).equals(f[i])) {
                        amt[i] -= this.theNumberOfSnacksInThePantry.get(j);
                    }
                }
            }
            // We do not want to consider negative amounts
            if (amt[i] < 0) {
                amt[i] = 0;
            }
        }

        int price = 0;
        // Calculate the total price of all the items we want
        for (int i = 0; i < f.length; i++){
            price += prices.get(f[i]) * amt[i];
        }

        // Update our pantry with the new amounts
        for (int i = 0; i < f.length; i++) {
            // If it is a fruit, update the fruit entries, otherwise update the snacks
            if (isF[i]) {
                // If the fruit is already there, update it. Otherwise, add a new fruit
                if (this.f.contains(f[i])) {
                    int index = this.f.indexOf(f[i]);
                    this.theNumberOfFruitsInThePantry.set(index, this.theNumberOfFruitsInThePantry.get(index) + amt[i]);
                } else {
                    this.f.add(f[i]);
                    this.theNumberOfFruitsInThePantry.add(amt[i]);
                }
            } else {
                // Same logic as with fruit, update if it exists otherwise add it
                if (this.s.contains(f[i])) {
                    int index = this.s.indexOf(f[i]);
                    this.theNumberOfSnacksInThePantry.set(index, this.theNumberOfSnacksInThePantry.get(index) + amt[i]);
                } else {
                    this.s.add(f[i]);
                    this.theNumberOfSnacksInThePantry.add(amt[i]);
                }
            }
        }
        return price;
    }
}
