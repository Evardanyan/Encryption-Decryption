package Test;

import java.io.*;
import java.util.*;

public class Main {
    public static String out = "";

    public static String getOut() {
        return out;
    }

    public static void main(String[] args) {
        String mode = "enc";
        String data = "";
        String in = "";
        String alg = "";
        boolean check = false;
        int count = 0;

        int key = 0;

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-mode")) {
                mode = args[i + 1];
                if (mode == "") {
                    check = true;
                    count++;
                }

            }
            if (args[i].equals("-data") ) {
                data = args[i + 1];
                if (data == "") {
                    check = true;
                    count++;
                }
            }
            if (args[i].equals("-key")) {
                key = Integer.valueOf(args[i + 1]);
                String str_key = args[i + 1];
                if (str_key == "") {
                    check = true;
                    count++;
                }
            }
            if (args[i].equals("-in")) {
                in = args[i + 1];
                if (in == "") {
                    check = true;
                    count++;
                }
            }
            if (args[i].equals("-out")) {
                out = args[i + 1];
                if (out == "") {
                    check = true;
                    count++;
                }
            }

            if (args[i].equals("-alg")) {
                alg = args[i + 1];
                if (alg == "shift") {
                    alg = "shift";
                } else if (alg == "unicode") {
                    alg = "unicode";
                }
            }

            if (in == "" && data == "") {
                data = "";
            }
            if (in != "" && data != "") {
                in = data;
            }
            if (in != "" && data == "") {
                data = in;
            }

        }


        if (count > 0) {
            System.out.println("Error");
        } else if ("dec".equals(mode) && "shift".equals(alg)) {
            new Shift().Decode(data, key);
        } else if ("enc".equals(mode) && "shift".equals(alg)) {
            new Shift().Encode(data, key);
        } else if ("dec".equals(mode) && "unicode".equals(alg)) {
            new Unicode().Decode(data, key);
        } else if ("enc".equals(mode) && "unicode".equals(alg)) {
            new Unicode().Encode(data, key);
        } else if ("dec".equals(mode)) {
            new Shift().Decode(data, key);
        } else if ("enc".equals(mode)) {
            new Shift().Encode(data, key);
        }
    }

//    private static void Decode(String input, int step) {
//        String temp_input = "";
//        char temp_ch = '\0';
//
//        if (input.contains(".txt")) {
//
//            try {
//                File file = new File(input);
//                Scanner myReader = new Scanner(file);
//                while (myReader.hasNextLine()) {
//                    temp_input = temp_input + myReader.nextLine();
////                    System.out.println(temp_input);
//                }
//                myReader.close();
//                System.out.println(temp_input + " as result");
//
//            } catch (FileNotFoundException e) {
//                System.out.println("Error");
//            }
//
//            char[] chars = temp_input.toCharArray();
//            char[] encode = new char[chars.length];
//
//            for (int i = 0; i < chars.length; i++) {
//                encode[i] = (char)(chars[i] - step);
//            }
//
//            String result = String.valueOf(encode);
//
//            if (out != "") {
//
//                try (FileWriter fileWriter = new FileWriter(out)) {
//                    fileWriter.write(result);
//                } catch (IOException e) {
//                    // Cxception handling
//                    System.out.println("Error");
//                }
//            } else {
//                System.out.println(result);
//            }
//
//        } else {
//
//            char[] chars = input.toCharArray();
//            char[] encode = new char[chars.length];
//
//            for (int i = 0; i < chars.length; i++) {
//                encode[i] = (char) (chars[i] - step);
//            }
//            String result = String.valueOf(encode);
//            System.out.println(result);
//        }
//    }
//
//    private static void Encode(String input, int step) {
//        String temp_input = "";
//        char temp_ch = '\0';
//
//        if (input.contains(".txt")) {
//            try {
//                File file = new File(input);
//                Scanner myReader = new Scanner(file);
//                while (myReader.hasNextLine()) {
//                    temp_input = temp_input + myReader.nextLine();
////                    System.out.println(temp_input);
//                }
//                myReader.close();
//                System.out.println(temp_input + " as result");
//
//            } catch (FileNotFoundException e) {
//                System.out.println("Error");
//            }
//            char[] chars = temp_input.toCharArray();
//            char[] encode = new char[chars.length];
//
//            for (int i = 0; i < chars.length; i++) {
//                encode[i] = (char) (chars[i] + step);
//            }
//
//            String result = String.valueOf(encode);
//
//            if (out != "") {
//
//                try (FileWriter fileWriter = new FileWriter(out)) {
//                    fileWriter.write(result);
//                } catch (IOException e) {
//                    // Cxception handling
//                    System.out.println("Error");
//                }
//            } else {
//                System.out.println(result);
//            }
//
//        } else {
//            char[] chars = input.toCharArray();
//            char[] encode = new char[chars.length];
//
//            for (int i = 0; i < chars.length; i++) {
//                encode[i] = (char) (chars[i] + step);
//            }
//            String result = String.valueOf(encode);
//            System.out.println(result);
//        }
//    }
}

interface EncodingDecoding {

    void Encode(String input, int step);

    void Decode(String input, int step);
}

class Shift implements EncodingDecoding {

    @Override
    public void Encode(String input, int step) {
        String out = Main.getOut();

        String temp_input = "";
        char temp_ch = '\0';

        if (input.contains(".txt")) {
            try {
                File file = new File(input);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    temp_input = temp_input + myReader.nextLine();
//                    System.out.println("inputed file content >> " + temp_input);
                }
                myReader.close();
//                System.out.println(temp_input);

            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }
            char[] chars = temp_input.toCharArray();
            char[] encode = new char[chars.length];

            for (int i = 0; i < chars.length; i++) {

                if (chars[i] + step > 122 && chars[i] <= 122 && chars[i] >= 97)  {
                    int diff = Math.abs(chars[i] + step - 123);
                    encode[i] = (char)(97 + diff);
//                    System.out.println("XXXXX " + encode[i]);
                } else if (chars[i] + step > 90 && chars[i] <= 90 && chars[i] >= 65) {
                    int diff = Math.abs(chars[i] + step - 91);
                    encode[i] = (char)(65 + diff);
//                    System.out.println("ZXZXZX " + encode[i]);
                } else if (chars[i] >= 65 && chars[i] <= 90 || chars[i] >= 97 && chars[i] <= 122) {
                    encode[i] = (char) (chars[i] + step);
//                    System.out.println("ZZZZZ " + encode[i]);
                } else {
                    encode[i] = chars[i];
                }
            }

            String result = String.valueOf(encode);
//            System.out.println("<< " + result);

            if (!out.equals("")) {

                try (FileWriter fileWriter = new FileWriter(out)) {
                    fileWriter.write(result);
                } catch (IOException e) {
                    // Cxception handling
                    System.out.println("Error");
                }
            } else {
                System.out.println(result);
            }

        }
//        else {
//            char[] chars = input.toCharArray();
//            char[] encode = new char[chars.length];
//
//            for (int i = 0; i < chars.length; i++) {
//                if (chars[i] + step > 90) {
//                    int diff = chars[i] + step - 90;
//                    encode[i] = (char)(65 + diff);
//                } else if (chars[i] + step > 122) {
//                    int diff = chars[i] + step - 122;
//                    encode[i] = (char)(97 + diff);
//                } else {
//                    encode[i] = (char) (chars[i] + step);
//                }
//            }
//            String result = String.valueOf(encode);
//            System.out.println(result);
//        }

    }

    @Override
    public void Decode(String input, int step) {
        String out = Main.getOut();
        String temp_input = "";
        char temp_ch = '\0';

        if (input.contains(".txt")) {

            try {
                File file = new File(input);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    temp_input = temp_input + myReader.nextLine();
//                    System.out.println(temp_input);
                }
                myReader.close();
//                System.out.println(temp_input);

            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }

            char[] chars = temp_input.toCharArray();
            char[] encode = new char[chars.length];

            for (int i = 0; i < chars.length; i++) {
                if (chars[i] - step < 65 && chars[i] <= 90 && chars[i] >= 65) {
                    int diff = Math.abs(chars[i] - step - 64);
                    encode[i] = (char)(90 - diff);
                } else if (chars[i] - step < 97 && chars[i] <= 122 && chars[i] >= 97) {
                    int diff = Math.abs(chars[i] - step - 96);
                    encode[i] = (char)(122 - diff);
                } else if (chars[i] >= 65 && chars[i] <= 90 || chars[i] >= 97 && chars[i] <= 122) {
                    encode[i] = (char)(chars[i] - step);
                } else {
                    encode[i] = chars[i];
                }
            }

            String result = String.valueOf(encode);
//            System.out.println(result);

            if (out != "") {

                try (FileWriter fileWriter = new FileWriter(out)) {
                    fileWriter.write(result);
                } catch (IOException e) {
                    // Cxception handling
                    System.out.println("Error");
                }
            } else {
                System.out.println(result);
            }

        } else {

            char[] chars = input.toCharArray();
            char[] encode = new char[chars.length];

            for (int i = 0; i < chars.length; i++) {
                if (chars[i] - step < 65) {
                    int diff = Math.abs(chars[i] - step - 65);
                    encode[i] = (char)(90 - diff);
                } else if (chars[i] - step < 97) {
                    int diff = Math.abs(chars[i] - step - 97);
                    encode[i] = (char)(122 - diff);
                } else {
                    encode[i] = (char)(chars[i] - step);
                }
            }
            String result = String.valueOf(encode);
            System.out.println(result);
        }


    }
}

class Unicode implements EncodingDecoding {

    @Override
    public void Encode(String input, int step) {
        String out = Main.getOut();

        String temp_input = "";
        char temp_ch = '\0';

        if (input.contains(".txt")) {
            try {
                File file = new File(input);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    temp_input = temp_input + myReader.nextLine();
//                    System.out.println(temp_input);
                }
                myReader.close();
                System.out.println(temp_input);

            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }
            char[] chars = temp_input.toCharArray();
            char[] encode = new char[chars.length];

            for (int i = 0; i < chars.length; i++) {
                encode[i] = (char) (chars[i] + step);
            }

            String result = String.valueOf(encode);

            if (out != "") {

                try (FileWriter fileWriter = new FileWriter(out)) {
                    fileWriter.write(result);
                } catch (IOException e) {
                    // Cxception handling
                    System.out.println("Error");
                }
            } else {
                System.out.println(result);
            }

        } else {
            char[] chars = input.toCharArray();
            char[] encode = new char[chars.length];

            for (int i = 0; i < chars.length; i++) {
                encode[i] = (char) (chars[i] + step);
            }
            String result = String.valueOf(encode);
            System.out.println(result);
        }

    }

    @Override
    public void Decode(String input, int step) {
        String out = Main.getOut();
        String temp_input = "";
        char temp_ch = '\0';

        if (input.contains(".txt")) {

            try {
                File file = new File(input);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    temp_input = temp_input + myReader.nextLine();
//                    System.out.println(temp_input);
                }
                myReader.close();
                System.out.println(temp_input);

            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }

            char[] chars = temp_input.toCharArray();
            char[] encode = new char[chars.length];

            for (int i = 0; i < chars.length; i++) {
                encode[i] = (char)(chars[i] - step);
            }

            String result = String.valueOf(encode);

            if (out != "") {

                try (FileWriter fileWriter = new FileWriter(out)) {
                    fileWriter.write(result);
                } catch (IOException e) {
                    // Cxception handling
                    System.out.println("Error");
                }
            } else {
                System.out.println(result);
            }

        } else {

            char[] chars = input.toCharArray();
            char[] encode = new char[chars.length];

            for (int i = 0; i < chars.length; i++) {
                encode[i] = (char) (chars[i] - step);
            }
            String result = String.valueOf(encode);
            System.out.println(result);
        }
    }


}

