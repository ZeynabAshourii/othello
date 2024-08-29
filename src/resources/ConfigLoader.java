package src.resources;

import java.io.*;
import java.util.ArrayList;

public class ConfigLoader {
   private static ArrayList<String> lines = new ArrayList<>();
    public static void load() {
        try {
            String configAddress = "D:\\github\\othello\\src\\resources\\config.txt";
            File file = new File(configAddress);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getLines() {
        return lines;
    }
}
