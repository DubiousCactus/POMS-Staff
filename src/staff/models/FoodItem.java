package staff.models;

/**
 * Created by transpalette on 5/28/17.
 */
public class FoodItem {

    private String name;
    private String ingredients;
    private int quantity;
    private String size;
    private String[] toppings;

    public FoodItem(String name, String ingredients, int quantity, String size, String[] toppings) {
        this.name = name;
        this.ingredients = ingredients;
        this.quantity = quantity;
        this.size = size;
        this.toppings = toppings;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String[] getToppings() {
        return toppings;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }
}
