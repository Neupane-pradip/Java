package fi.tuni.prog3.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CountryData {

    public static List<Country> readFromJsons(String areaFile,
                                              String populationFile,
                                              String gdpFile) {
        // Read JSON files into TreeMap
        TreeMap<String, Double> areaData = readJsonFile(areaFile);
        TreeMap<String, Long> populationData = readJsonFile(populationFile);
        TreeMap<String, Double> gdpData = readJsonFile(gdpFile);

        // Combine data from all files
        List<Country> countries = new ArrayList<>();
        for (Map.Entry<String, Double> entry : areaData.entrySet()) {
            String countryName = entry.getKey();
            if (populationData.containsKey(countryName) && gdpData.containsKey(countryName)) {
                double area = entry.getValue();
                long population = populationData.get(countryName);
                double gdp = gdpData.get(countryName);
                countries.add(new Country(countryName, area, population, gdp));
            }
        }
        return countries;
    }

    private static <T> TreeMap<String, T> readJsonFile(String filename) {
        TreeMap<String, T> dataMap = new TreeMap<>();
        try (FileReader fileReader = new FileReader(filename)) {
            JsonObject jsonObject = JsonParser.parseReader(fileReader).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                dataMap.put(entry.getKey(), (T) entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return dataMap;
    }

    public static void writeToJson(List<Country> countries, String countryFile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(countryFile)) {
            gson.toJson(countries, fileWriter);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + countryFile);
        }
    }
}
