package fi.tuni.prog3.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CountryData {

    // Method to read data from multiple JSON files and create Country objects
    public static List<Country> readFromJsons(String areaFile,
                                              String populationFile,
                                              String gdpFile){
        TreeMap<String, String> areaData;
        TreeMap<String, String> populationData;
        TreeMap<String, String> gdpData;

        try {
            // Read data from JSON files
            areaData = readJsonFile(areaFile);
            populationData = readJsonFile(populationFile);
            gdpData = readJsonFile(gdpFile);
        }
        catch (IOException e){
            System.out.println("Error reading files");
            return null;
        }

        // Create Country objects from the data
        List<Country> countries = new ArrayList<>();
        for(String countryName : areaData.keySet()){
            if (populationData.containsKey(countryName) &&
                    gdpData.containsKey(countryName)){
                double area = Double.parseDouble(areaData.get(countryName));
                long population = Long.parseLong(populationData.get(countryName));
                double gdp = Double.parseDouble(gdpData.get(countryName));
                countries.add(new Country(countryName, area, population, gdp));
            }
        }
        return countries;
    }

    // Method to read data from a JSON file
    private static TreeMap<String,String> readJsonFile(String filename) throws IOException{
        TreeMap<String,String> countryData = new TreeMap<>();
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(filename);
        JsonReader reader = gson.newJsonReader(fileReader);

        // Parse JSON structure
        reader.beginObject();
        while (reader.hasNext()){
            String name = reader.nextName();
            if (name.equals("Root") || name.equals("data")){
                reader.beginObject();
            }
            else if (name.equals("record")){
                reader.beginArray();
                while (reader.hasNext()) {
                    reader.beginObject();
                    while(reader.hasNext()) {
                        String field = reader.nextName();
                        if (field.equals("field")) {
                            reader.beginArray();
                            String storeValue = "";
                            String countryName = "";
                            String value = "";

                            while (reader.hasNext()) {
                                reader.beginObject();
                                while (reader.hasNext()) {
                                    String attribute = reader.nextName();
                                    if (attribute.equals("value")) {
                                        storeValue = reader.nextString();
                                    } else if (attribute.equals("attributes")) {
                                        reader.beginObject();
                                        while (reader.hasNext()) {
                                            String attrName = reader.nextName();
                                            if (attrName.equals("name")) {
                                                String nextString = reader.nextString();
                                                if (nextString.equals("Country or Area")) {
                                                    countryName = storeValue;
                                                } else if (nextString.equals("Value")) {
                                                    value = storeValue;
                                                }
                                            } else if (attrName.equals("key")){
                                                reader.skipValue();
                                            }
                                            else {
                                                reader.skipValue();
                                            }
                                        }
                                        reader.endObject();
                                    } else {
                                        reader.skipValue();
                                    }
                                }
                                reader.endObject();
                            }
                            reader.endArray();
                            countryData.put(countryName, value);
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                }
                reader.endArray();
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        reader.endObject();
        reader.endObject();
        reader.close();

        return countryData;
    }

    // Method to write list of Country objects to a JSON file
    public static void writeToJson(List<Country> countries, String countryFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(PrintStream output = new PrintStream(new FileOutputStream(countryFile))) {
            output.print(gson.toJson(countries));
        }
    }
}
