package main;

import algorithm.Apriori;

import java.util.*;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        List<List<String>> inputs = List.of(
                List.of(
                        "0 1 3 4 7",
                        "0 1 2",
                        "0 3 4 5 6",
                        "0 1 2 3 4 5 7",
                        "1 2 3 4 5",
                        "1 3 4",
                        "3 7 9",
                        "0 1 2 3 7",
                        "1 2 3 6 7",
                        "0 7 8"
                ),
                List.of(
                        "0 1 3 5",
                        "1 2 3 9",
                        "0 3 5",
                        "4 5 7",
                        "0 1 8 9",
                        "2 4 5 7",
                        "3 4 5 7",
                        "0 8",
                        "1 4 5 6",
                        "6 7"
                ),
                List.of(
                        "0 1 2 3 9",
                        "1 3 6 7 8",
                        "2 4",
                        "1 2 3",
                        "3 5",
                        "1 7 8 9",
                        "2 5",
                        "0 7 8",
                        "3 4",
                        "1 2 3"
                ),
                List.of(
                        "2 5 6 8 9",
                        "7 8 9",
                        "3 4 5 6 9",
                        "2 4 5 6 7 8 9",
                        "4 5 6 7 8",
                        "5 6 8",
                        "0 6 7",
                        "3 6 7 8 9",
                        "3 4 6 7 8",
                        "2 7 9"
                ),
                List.of(
                        "4 6 8 9",
                        "0 6 7 8",
                        "4 6 9",
                        "2 4 5",
                        "0 1 8 9",
                        "2 4 5 7",
                        "2 4 5 6",
                        "1 9",
                        "3 4 5 8",
                        "3 4"
                ),
                List.of(
                        "0 6 7 8 9",
                        "1 2 3 6 8",
                        "5 7",
                        "6 7 8",
                        "4 6",
                        "0 1 2 8",
                        "4 7",
                        "1 2 9",
                        "5 6",
                        "6 7 8"
                )
        );

        for (List<String> inputStrings : inputs)
        {
            for (String inputString : inputStrings)
                System.out.println(inputString);

            System.out.println();

            List<Set<Integer>> input = inputStrings.stream().map(s -> Arrays.stream(s.split(" ")).map(Integer::valueOf).collect(Collectors.toSet())).collect(Collectors.toList());
            Apriori apriori = new Apriori(3, 0.5);

            System.out.println(apriori.getRules(input));
            System.out.println();
        }
    }
}
