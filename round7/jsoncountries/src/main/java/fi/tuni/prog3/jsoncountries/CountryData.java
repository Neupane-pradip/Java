package fi.tuni.prog3.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CountryData {

    // Method to read data from JSON files and create a list of Country objects
    public static List<Country> readFromJsons(String areaFile, String populationFile, String gdpFile) {
        TreeMap<String, String> areaData;
        TreeMap<String, String> populationData;
        TreeMap<String, String> gdpData;

        try {
            // Read data from JSON files
            areaData = readJsonFile(areaFile);
            populationData = readJsonFile(populationFile);
            gdpData = readJsonFile(gdpFile);
        } catch (IOException e) {
            System.out.println("Error reading files");
            return null;
        }

        List<Country> countries = new ArrayList<>();
        
        // Iterate over each country name in the area data
        for (String countryName : areaData.keySet()) {
            
            // Check if population and GDP data exist for the country
            if (populationData.containsKey(countryName) && gdpData.containsKey(countryName)) {
                
                // Parse data and create Country object
                double area = Double.parseDouble(areaData.get(countryName));
                long population = Long.parseLong(populationData.get(countryName));
                double gdp = Double.parseDouble(gdpData.get(countryName));
                countries.add(new Country(countryName, area, population, gdp));
            }
        }
        return countries;
    }

    // Method to read JSON file and extract relevant data
    
    private static TreeMap<String, String> readJsonFile(String filename) throws IOException {
        TreeMap<String, String> countryDataPoint = new TreeMap<>();
        Gson gson = new Gson();
        
        // Read JSON file using Gson library
        try (FileReader fileReader = new FileReader(filename)) {
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            
            // Extract relevant data from JSON structure
            JsonArray recordArray = jsonElement.getAsJsonObject()
                    .getAsJsonObject("Root")
                    .getAsJsonObject("data")
                    .getAsJsonArray("record");
            for (JsonElement element : recordArray) {
                JsonObject fieldObject = element.getAsJsonObject().getAsJsonArray("field").get(0).getAsJsonObject();
                String countryName = fieldObject.get("value").getAsString();
                String value = fieldObject.getAsJsonArray("attributes").get(0).getAsJsonObject().get("value").getAsString();
                countryDataPoint.put(countryName, value);
            }
        }
        return countryDataPoint;
    }

    // Method to write list of Country objects to a JSON file
    
    public static void writeToJson(List<Country> countries, String countryFile) throws IOException {
        Gson gson = new Gson();
        // Write list of Country objects to JSON file using Gson
        
        try (FileWriter fileWriter = new FileWriter(countryFile)) {
            gson.toJson(countries, fileWriter);
        }
    }
}
