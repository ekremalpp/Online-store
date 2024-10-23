package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online com.pluralsight.Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        // This method should read a CSV file with product information and
        // populate the inventory ArrayList with com.pluralsight.Product objects. Each line
        // of the CSV file contains product information in the following format:
        //
        // id,name,price
        //
        // where id is a unique string identifier, name is the product name,
        // price is a double value representing the price of the product
        try {
             BufferedReader reader = new BufferedReader(new FileReader(fileName));
             String line;
             while ((line = reader.readLine()) != null) {
                 String[] parts = line.split("\\|");
                 String sku = parts[0];
                 String productName = parts[1];
                 double price = Double.parseDouble(parts[2]);

                 Product product = new Product(sku,productName, price);
                 inventory.add(product);

             }

        } catch (Exception e) {
            System.err.println("Related file can not exist.");
        }

    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        // This method should display a list of products from the inventory,
        // and prompt the user to add items to their cart. The method should
        // prompt the user to enter the ID of the product they want to add to
        // their cart. The method should
        // add the selected product to the cart ArrayList.
        System.out.println("Available products: ");
        for (Product product : inventory) {
            System.out.println(product);
        }

        System.out.print("Enter the SKU of the product to add to cart or type 'back' to go back: ");
        String input = scanner.nextLine();

        if (!input.equalsIgnoreCase("back")) {
            Product selectedProduct = findProductById(input, inventory);
            if(selectedProduct !=null) {
                cart.add(selectedProduct);
                System.out.println(selectedProduct.getProduct() + "has been added to your cart.");

            } else {
                System.out.println("Product not found ");
            }
        }



    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        // This method should display the items in the cart ArrayList, along
        // with the total cost of all items in the cart. The method should
        // prompt the user to remove items from their cart by entering the ID
        // of the product they want to remove. The method should update the cart ArrayList and totalAmount
        // variable accordingly.
        if (cart.isEmpty()) {
            System.out.println("your cart is empty ");
            return;
        }

        System.out.println("items in your cart");
        totalAmount = 0.0;

        for (Product product : cart) {
            System.out.println(product);
            totalAmount += product.getPrice();
        }
        System.out.println("Total Amount: " + totalAmount);

        System.out.println("If you want to continue write 'checkout' please: ");
        String input = scanner.nextLine();


        if (input.equalsIgnoreCase("checkout")) {
            checkOut(cart, totalAmount);
        } else if (!input.equalsIgnoreCase("back")) {
            Product productToRemove = findProductById(input, cart);
            if(productToRemove !=null) {
                cart.remove(productToRemove);
                System.out.println(productToRemove.getProduct() + "has been removed from your cart");
            } else {
                System.out.println("Product not found in your cart.");
            }

        }

    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and deduct the total cost
        // from their account if they confirm.
        if(cart.isEmpty()) {
            System.out.println("Your cart is empty");
            return;
        }

        totalAmount = 0.0;
        for(Product product : cart) {
            totalAmount += product.getPrice();
        }


        System.out.println("Shopping Summary");
        System.out.println("---------------------------");

        for (Product product : cart) {
            System.out.println(product.getProduct()+ "-" +product.getPrice());
        }

        System.out.println("Total amount" + totalAmount);

        System.out.print("Do you confrim your shopping? (Yes/No):");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();



        if(confirmation.equalsIgnoreCase("Yes")) {
            System.out.print("Enter Total amount");
            double payment = scanner.nextDouble();

            if (payment>= totalAmount) {
                double change = payment - totalAmount;
                System.out.println("Payment is successful! chance : " +change);
                System.out.println("Your shopping is completed");
                cart.clear();
            } else {
                System.out.println("There is not enough money. Your shopping couldn't completed ");

            }

        } else {
            System.out.println("Your shopping cancelled.");
        }



    }


    public static Product findProductById(String id, ArrayList<Product> inventory) {
        // This method should search the inventory ArrayList for a product with
        // the specified ID, and return the corresponding com.pluralsight.Product object. If
        // no product with the specified ID is found, the method should return
        // null.
        
}
