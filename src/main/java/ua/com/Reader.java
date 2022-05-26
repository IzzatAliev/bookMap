package ua.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Reader {

    private static final String fileName = "input.txt";
    private static final String splitter = ",";

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        MainLogic mainLogic = new MainLogic();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Writer.init();

            String thisLine;
            boolean firstLine = true;
            while ((thisLine = br.readLine()) != null) {
                List<String> list = Arrays.asList(thisLine.split(splitter));
                String string = String.valueOf(mainLogic.request(list));
                if (!string.equals("null")) {
                    if (!firstLine) {
                        Writer.write("\r\n" + string);
                    } else {
                        Writer.write(string);
                        firstLine = false;
                    }
                }
            }
            Writer.close();
            System.out.println(System.currentTimeMillis() - start + "ms");
        } catch (Exception e) {
            e.printStackTrace();
            Writer.close();
        }
    }
}
