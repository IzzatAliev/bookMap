package ua.com;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Writer {

    private static final String fileName = "output.txt";
    private static BufferedWriter bufferedWriter;

    public static void init() throws IOException {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
    }

    public static void write(String messageToWrite) throws IOException {
            bufferedWriter.write(messageToWrite);
    }

    public static void close() throws IOException {
            bufferedWriter.close();
    }
}
