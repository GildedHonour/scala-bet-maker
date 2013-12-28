package io.bet.betzilla.betcore;

import java.io.*;

public class FileUtils {
    static public String readFile(String fileName) {
        File file = new File(fileName);

        char[] buffer = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            buffer = new char[(int) file.length()];
            int i = 0;
            int c = bufferedReader.read();
            while (c != -1) {
                buffer[i++] = (char) c;
                c = bufferedReader.read();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        return new String(buffer);
    }

    static public void writeFile(String fileName, String data) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(data);
            out.close();
        } catch (IOException e) {

        }
    }

    static public void writeText(String filename, String line, boolean append) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, append)));
            out.println(line);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}