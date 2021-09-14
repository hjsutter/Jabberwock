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
//            if(vals.get(varName) != null) {
                System.out.println(vals.get(varName));
//            }
        } else {
            String param2 = stack.pop();
            String operator = stack.pop();
            String param1 = stack.pop();
        }
        return "";
    }
}
