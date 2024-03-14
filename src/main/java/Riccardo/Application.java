package Riccardo;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Random random = new Random();
        // Genero una lista con 3 categorie
        Supplier<String> categorie = () -> {
            Random randomCategory = new Random();
            List<String> lista = new ArrayList<>();
            lista.add("Books");
            lista.add("Baby");
            lista.add("Boys");
            return lista.get(randomCategory.nextInt(lista.size()));
        };

        // nuovo prodotto
        Supplier<Product> productSupplier = () -> new Product(random.nextLong(1, 5000), "prodotto" + random.nextInt() , categorie.get(), random.nextDouble(1, 300) );

        // Genero lista di prodotti
        Supplier<List<Product>> randomList = () -> {
            List<Product> productList = new ArrayList<>();
            for (int i = 0; i < random.nextInt(1, 20); i++) {
                productList.add(productSupplier.get());
            }
            return productList;
        };

        // Genero lista clienti
        List<Customer> listaClienti = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer cliente1 =  new Customer(random.nextLong(1, 5000), "cliente" + (i + 1 ), random.nextInt(1, 5));
            listaClienti.add(cliente1);
//            System.out.println(cliente1);
        }
        // DATE
        LocalDate data = LocalDate.of(2021,2,20);
        LocalDate dataSpedizione = LocalDate.of(2021,2,24);
        System.out.println(data);
        // GENERO ORDINE

        List<Order> listaOrdini = new ArrayList<>();
        Supplier<Order> ordine1 = () -> {
            Random randomI = new Random();
            Customer lunghezzaCasuale = listaClienti.get(randomI.nextInt(listaClienti.size()));
            Order ordine = new Order(random.nextInt(), "in consegna", data, dataSpedizione, randomList.get(), lunghezzaCasuale );
            return ordine;
        };
        for (int i = 0; i < 5; i++) {
            listaOrdini.add(ordine1.get());
        }
        System.out.println(listaOrdini);



        // LISTA PRODOTTI
        List<Product> products = randomList.get();
        System.out.println(products);
    products.forEach(prezzo -> System.out.println(prezzo));

        System.out.println("----------------- ESERCIZIO 1 ----------------------");
        Map<Customer, List<Order>> raggrupamentoOrdiniClienti = listaOrdini.stream().collect(Collectors.groupingBy(cliente -> cliente.getCustomer()));
            raggrupamentoOrdiniClienti.forEach((clienti, ordini) -> System.out.println("Cliente: " + clienti + "lista ordini: " + ordini));
        System.out.println("----------------- ESERCIZIO 2 ----------------------");
        Map<Object, Double> prezzoTotOrdineCliente = listaOrdini.stream().collect(Collectors.groupingBy(cliente -> cliente.getCustomer(), Collectors.summingDouble(prodotti -> prodotti.getProducts().stream().mapToDouble(Product::getPrice).sum())));
            prezzoTotOrdineCliente.forEach((cliente, prezzo) -> System.out.println("Cliente: " + cliente + " Prezzo totale: " + prezzo));
        System.out.println("----------------- ESERCIZIO 3 ----------------------");
        List<Product> prodottiPiuCostosi = products.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(3).toList();
        System.out.println("Prodotto piu costoso: " + prodottiPiuCostosi);
        System.out.println("----------------- ESERCIZIO 4 ----------------------");
        double average = listaOrdini.stream().collect(Collectors.averagingDouble(ordini -> ordini.getProducts().stream().map(Product::getPrice).reduce((double) 0,(a, b) -> a + b)));
        System.out.println(average);
        System.out.println("----------------- ESERCIZIO 5 ----------------------");
        Map<String, Double> sommaProdottiCategorie = products.stream().collect(Collectors.groupingBy(Product::getCategory,Collectors.summingDouble(Product::getPrice) ));
        sommaProdottiCategorie.forEach((categoria, totale) -> System.out.println("Categoria: " + categoria + "Totale: " + totale));

        System.out.println("----------------- ESERCIZIO 6 ----------------------");

        System.out.println("----------------- ESERCIZIO 7 ----------------------");
    }
}
