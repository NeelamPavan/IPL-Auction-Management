import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private ArrayList<Item> items;
    private double discount;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.discount = 0;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(String itemName) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total - (total * discount);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
class Main3{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Shopping App!");
        System.out.println("Available Carts: Grocery, Electronics, Clothes");
        System.out.println("Choose a Cart to Add Products:");
        System.out.println("1. Grocery Cart");
        System.out.println("2. Electronics Cart");
        System.out.println("3. Clothing Cart");
        System.out.println("4. Place Order");
        System.out.println("5. Exit");


        HashMap<String, ShoppingCart> carts = new HashMap<>();
        carts.put("GROCERY", new ShoppingCart());
        carts.put("ELECTRONICS", new ShoppingCart());
        carts.put("CLOTHING", new ShoppingCart());

        while (true) {

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    addToCart(carts.get("GROCERY"), scanner);
                    break;
                case 2:
                    addToCart(carts.get("ELECTRONICS"), scanner);
                    break;
                case 3:
                    addToCart(carts.get("CLOTHING"), scanner);
                    break;
                case 4:
                    System.out.println("Enter the cart to place the order (e.g., grocery, electronics, clothing):");
                    String cartName = scanner.nextLine().toUpperCase();

                    ShoppingCart cart = carts.get(cartName);

                    if (cart == null) {
                        System.out.println("Can't Place Order: Cart is Either empty or Invalid Cart");
                        System.out.println("Returning to Main Site: Continue Shopping");
                        continue;
                    }

                    placeOrder(cart, scanner, carts);
                    break;
                case 5:
                    System.out.println("Exiting Shopping App");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void addToCart(ShoppingCart cart, Scanner scanner) {
        System.out.println("Enter the name and price of the product (e.g., Milk 45):");
        String[] input = scanner.nextLine().split(" ");

        String itemName = input[0];
        double itemPrice = Double.parseDouble(input[1]);

        Item item = new Item(itemName, itemPrice);
        cart.addItem(item);

        System.out.println(itemName + " has been added to the cart " + cart.getClass().getSimpleName());
    }

    private static void placeOrder(ShoppingCart cart, Scanner scanner, HashMap<String, ShoppingCart> carts) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Can't Place Order: Cart is Either empty or Invalid Cart");
            System.out.println("Returning to Main Site: Continue Shopping");
            return;
        }

        System.out.println("Do you want to remove any existing item from the cart? (yes/no)");
        String removeChoice = scanner.nextLine().toLowerCase();

        if (removeChoice.equals("yes")) {
            System.out.println("Enter the name of the item to remove:");
            String itemToRemove = scanner.nextLine();
            cart.removeItem(itemToRemove);
            System.out.println(itemToRemove + " has been removed from the cart");
        }

        double discount = 0;
        switch (cart.getClass().getSimpleName()) {
            case "Grocery":
                discount = 0.1;
                break;
            case "Electronics":
                discount = 0.2; // Updated to 20%
                break;
            case "Clothing":
                discount = 0.3; // Updated to 30%
                break;
        }

        cart.setDiscount(discount);
        double totalBill = cart.calculateTotal();

        System.out.printf("%s CART - Total Bill (%.0f%% discount applied): Rs.%.2f/-\n",
                cart.getClass().getSimpleName(), discount * 100, totalBill);

        System.out.println("Order Placed!! Thank You!!!");

        // Continue shopping in different carts
        System.out.println("Do you want to continue shopping in another cart? (continue/exit)");
        String continueShopping = scanner.nextLine().toLowerCase();

        if (continueShopping.equals("continue")) {
            System.out.println("Enter the cart to continue shopping (e.g., grocery, electronics, clothing):");
            String nextCart = scanner.nextLine().toUpperCase();
            placeOrder(carts.get(nextCart), scanner, carts);
        } else {
            System.out.println("Exiting Shopping App");
            cart.getItems().clear();  // Clear cart items after placing the order
            System.exit(0);
        }
    }
}