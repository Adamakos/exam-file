package com.codecool.chessopen;

import java.io.*;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {

        File file = new File(fileName);
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        if (input != null) {
            List<String> names = new ArrayList<>();
            HashMap<String, Integer> nameAndPoints = new HashMap<>();
            String[] extracts;
            Integer sum = 0;
            while (input.hasNextLine()) {
                extracts = input.nextLine().split(",");
                names.add(extracts[0]);
                for (int i = 1; i < extracts.length; i++) { sum += Integer.parseInt(extracts[i]); }
                nameAndPoints.put(extracts[0], sum);
            }
            input.close();
            names = nameAndPoints.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey).collect(Collectors.toList());
            names.sort(Collections.reverseOrder());
            return names;
        }
        return null;
    }

}
