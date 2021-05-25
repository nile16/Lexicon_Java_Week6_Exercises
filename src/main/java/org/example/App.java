package org.example;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void processor(List<Product> products, Conditional conditional, Action action) {
        for (Product product : products) {
            if (conditional.test(product)) {
                action.execute(product);
            }
        }
    }

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        Action none = (p) -> {}; // ?
        Action print = System.out::println; // (p) -> System.out.println(p)
        Action hikePrice = p -> p.setPrice(p.getPrice() * 1.5);

        Conditional outOfStock = p -> p.getStock() == 0;
        Conditional lowOnStock = p -> p.getStock() < 10 && p.getStock() > 0;
        Conditional startsWithB = p -> p.getProductName().charAt(0) == 'B';
        Conditional price100to150 = p -> p.getPrice() > 100 && p.getPrice() < 500;
        Conditional all = p -> true;

        products.add(new Product("Apple", 12, 0));
        products.add(new Product("Banana", 5, 500));
        products.add(new Product("Pear", 8, 200));
        products.add(new Product("Orange", 10, 500));
        products.add(new Product("Pineapple", 120, 5));

        System.out.println("\nOut of stock");
        processor(products, outOfStock, print);

        System.out.println("\nName starts with B");
        processor(products, startsWithB, print);

        System.out.println("\nPrice between 100 and 150");
        processor(products, price100to150, print);

        System.out.println("\nPrice hike on low stock");
        processor(products, lowOnStock, hikePrice);
        processor(products, lowOnStock, print);
    }
}
