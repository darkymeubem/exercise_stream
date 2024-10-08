package org.example.application;

import org.example.entities.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);


        String path = "src/main/resources/in.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Product> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = br.readLine();
            }
            double med = list.stream()
                    .map(p -> p.getPrice())
                    .reduce(0D, (x, y) -> x + y) / list.size();
            System.out.println("Média: " + String.format("%.2f", med));


            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
            List<String> names = list.stream().filter(p -> p.getPrice() < med).map(p -> p.getName()).sorted(comp.reversed()).collect(Collectors.toList());

           names.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}