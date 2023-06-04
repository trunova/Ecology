package trunova.model;

import trunova.build.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelDAO {
    private final String PATH = "src\\main\\resources\\Models";
    private static final String CSV_SEPARATOR = ",";

    private int createNextCSV(){
        try {
        int counter = 0;
        File newFile = new File(PATH + "\\" + counter + ".csv");
        while (newFile.exists()) {
            counter++;
            newFile = new File(PATH + "\\" + counter + ".csv");
        }
            newFile.createNewFile();
        return counter;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeModelToCSV(Model model, String file){
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(model.toCSVString(CSV_SEPARATOR));
            writer.close();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public String save(Model model){
        int i = createNextCSV();
        String s = PATH + "\\" + i + ".csv";
        writeModelToCSV(model, s);
        return i + ".csv";
    }

    public Model getModel(int i) {
        List<String> readFile = readFile(PATH + "\\" + i + ".csv");
        List<Factory> cities = new ArrayList<>();
        Model res = parseModel(readFile.get(0));
        for (int j = 1; j < readFile.size(); j++) {
            cities.add(parseFactory(readFile.get(j)));
        }
        res.setFactories(cities);
        return res;
    }

    public List<String> readFile(String filename){
            List<String> res = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                res.add(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public Model parseModel(String s) {
        String[] tokens = s.split(CSV_SEPARATOR);
        int carCount = Integer.parseInt(tokens[0]);
        int realiseForCar = Integer.parseInt(tokens[1]);
        int square = Integer.parseInt(tokens[2]);
        int money = Integer.parseInt(tokens[3]);
        int filterCount = Integer.parseInt(tokens[5]);
        int filterCost = Integer.parseInt(tokens[5]);
        int release = Integer.parseInt(tokens[6]);

        return new Model(new ArrayList<>(), carCount, realiseForCar, square, money, filterCount, filterCost, release);
    }

    public Factory parseFactory(String s) {
        String[] tokens = s.split(CSV_SEPARATOR);

        int posX = Integer.parseInt(tokens[0]);
        int posY = Integer.parseInt(tokens[1]);
        int maxRelease = Integer.parseInt(tokens[2]);
        int tax = Integer.parseInt(tokens[3]);

        return new Factory(posX,
                posY,
                maxRelease,
                tax);
    }
}
