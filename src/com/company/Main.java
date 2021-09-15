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
        while(f.hasNextLine()){
            Scanner l = new Scanner(line);
            evaluate(l, values);
            line = f.nextLine();
        }
    }

    public static String evaluate(Scanner s, Map<String, String> vals){
        Stack<String> stack = new Stack<>();
        //Read each token in the line and add it to the stack
        while(s.hasNext()) {
            String output = s.next();
            stack.push(output);
            output = s.next();
            stack.push(output);
        }
        //If stack size is > 3 pop 3 items, else pop 2
        if(stack.size() == 2){
            String value = stack.pop();
            if(value.length() > 2019){
                value = "undefined";
            }
            String varName = stack.pop();
            vals.put(varName, value);
        } else {
            String param2 = stack.pop();
            if(Character.isLetter(param2.charAt(0))){
                param2 = vals.get(param2);
            }
            String operator = stack.pop();
            String param1 = stack.pop();
            if(Character.isLetter(param1.charAt(0))){
                param1 = vals.get(param1);
            }
            if(operator.equals("..")){
                concatenate(param1, param2);
            } else if (operator.equals("||")){

            } else if (operator.equals("#")){

            }
        }
        return "";
    }

    public static String concatenate(String x, String y) {
        return x + y;
    }
    public static String interleave(String x, String y){
        return "";
    }
    public static String splice(String x, String y){
        int mid = y.length()/2;
        String[] split = new String[2];
        split[0] = y.substring(0, mid);
        split[1] = y.substring(mid);
        return split[0] + x + split[1];
    }
}
