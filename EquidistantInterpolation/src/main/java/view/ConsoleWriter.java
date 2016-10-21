package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ольга on 21.09.2016.
 */
public class ConsoleWriter {

    public static void printDouble(String infoString, Double x) {
        System.out.println(infoString + x);
    }

    public static Double getDouble(String queryString) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Double x;
        while (true) {
            System.out.print(queryString);
            String str = br.readLine();
            try {
                x = Double.parseDouble(str);
                break;
            } catch (NullPointerException | NumberFormatException e) {
                // continue
            }
        }
        return x;
    }

    public static Double getLimitedDouble(String queryString, Double limit) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Double x;
        while (true) {
            System.out.print(queryString);
            String str = br.readLine();
            try {
                x = Double.parseDouble(str);
                if (x > limit)
                    break;
            } catch (NullPointerException | NumberFormatException e) {
                // continue
            }
        }
        return x;
    }

    public static int getInt(String queryString) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x;
        while (true) {
            System.out.print(queryString);
            String str = br.readLine();
            try {
                x = Integer.parseInt(str);
                if (x > 0)
                    break;
            } catch (NullPointerException | NumberFormatException e) {
                // continue
            }
        }
        return x;
    }

    public static int getLimitedInt(String queryString, int limit) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x;
        while (true) {
            System.out.print(queryString);
            String str = br.readLine();
            try {
                x = Integer.parseInt(str);
                if (x > 0 && x <= limit)
                    break;
            } catch (NullPointerException | NumberFormatException e) {
                // continue
            }
        }
        return x;
    }

    public static void  printString(String string) {
        System.out.println(string);
    }
}
