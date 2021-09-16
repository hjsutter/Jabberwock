package com.company;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //Read in file name as input
        Map<String, String> values = new HashMap<>();
        Scanner s = new Scanner(System.in);
        String filename = s.next();
        //Scan each line of the file one by one
        File file = new File(filename);
        Scanner f = new Scanner(file);
        String line = f.nextLine();
        while (f.hasNextLine()) {
            Scanner l = new Scanner(line);
            evaluate(l, values);
            line = f.nextLine();
        }
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.print(key + " = ");
            System.out.println(value);
        }
    }

    public static void evaluate(Scanner s, Map<String, String> vals) {
        Stack<String> stack = new Stack<>();
        //Read each token in the line and add it to the stack
        while (s.hasNext()) {
            String output = s.next();
            stack.push(output);
            output = s.next();
            stack.push(output);
        }
        //If stack size is > 2 pop 3 items
        while (stack.size() > 2) {
            String result = "";
            String param2 = stack.pop();
            String operator = stack.pop();
            String param1 = stack.pop();
            //If parameter is a variable check the map to see if it has an assignment, otherwise set to undefined
            if (Character.isLetter(param2.charAt(0))) {
                param2 = vals.get(param2);
                if (param2 == null) {
                    param2 = "undefined";
                }
            }
            //If parameter is a variable check the map to see if it has an assignment, otherwise set to undefined
            if (Character.isLetter(param1.charAt(0))) {
                param1 = vals.get(param1);
                if (param1 == null) {
                    param1 = "undefined";
                }
            }
            //Calls the appropriate method depending on the operator
            switch (operator) {
                case "..":
                    result = concatenate(param1, param2);
                    break;
                case "||":
                    result = interleave(param1, param2);
                    break;
                case "#":
                    result = splice(param1, param2);
                    break;
            }
            //Pushes the result of the operation on the stack and continues to the next operation
            stack.push(result);
            //At this point all operations should be done and the variable should be stored in the map
            String value = stack.pop();
            if (value.length() > 2019) {
                value = "undefined";
            }
            String varName = stack.pop();
            vals.put(varName, value);
        }
    }

    public static String concatenate(String x, String y) {
        if(x.equals("undefined") || y.equals("undefined")){
            return "undefined";
        } else {
            return x + y;
        }
    }

    public static String interleave(String x, String y) {
        int count;
        char[] charsX = x.toCharArray();
        char[] charsY = y.toCharArray();
        StringBuilder result = new StringBuilder();
        if (charsX.length == charsY.length) {
            for (int i = 0; i < charsX.length; i++) {
                result.append(charsX[i]);
                result.append(charsY[i]);
            }
        } else if (charsX.length < charsY.length) {
            count = 0;
            for (int i = 0; i < charsX.length; i++) {
                result.append(charsX[i]);
                result.append(charsY[i]);
                count++;
            }
            for (int i = count; i < charsY.length; i++) {
                result.append(charsY[i]);
            }
        } else {
            count = 0;
            for (int i = 0; i < charsY.length; i++) {
                result.append(charsX[i]);
                result.append(charsY[i]);
                count++;
            }
            for (int i = count; i < charsX.length; i++) {
                result.append(charsX[i]);
            }
        }
        if(x.equals("undefined") || y.equals("undefined")){
            result.replace(0, result.length(), "undefined");
        }
        return result.toString();
    }

    public static String splice(String x, String y) {
        if(x.equals("undefined") || y.equals("undefined")){
            return "undefined";
        }
        int mid = y.length() / 2;
        String[] split = new String[2];
        split[0] = y.substring(0, mid);
        split[1] = y.substring(mid);
        return split[0] + x + split[1];
    }
}
