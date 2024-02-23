package fi.tuni.prog3.sevenzipsearch;

import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Sevenzipsearch {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("File: \n");
            String fileName = reader.readLine();

            System.out.print("Query: \n");
            String searchWord = reader.readLine();

            System.out.println();

            searchWordIn7z(fileName, searchWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchWordIn7z(String fileName, String searchWord) {
        try (SevenZFile sevenZFile = new SevenZFile(new File(fileName))) {
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".txt")) {
                    System.out.println(entry.getName());
                    searchWordInTxt(sevenZFile, entry, searchWord);
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchWordInTxt(SevenZFile sevenZFile, SevenZArchiveEntry entry, String searchWord) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(sevenZFile.getInputStream(entry), StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(searchWord.toLowerCase())) {
                    String formattedLine = line.replaceAll("(?i)" + searchWord, searchWord.toUpperCase());
                    System.out.println(lineNumber + ": " + formattedLine);
                }
                lineNumber++;
            }
        }
    }
}
