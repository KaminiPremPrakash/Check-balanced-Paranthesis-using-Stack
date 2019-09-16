
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.lang.model.SourceVersion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template scanner, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kamini Prakash
 */
public class HomeWork1 {

    public static String JAVA_FILE_PATH = "C:\\Users\\Kamini Prakash\\Documents\\NetBeansProjects\\JavaApplication1\\src\\TestAssignment.java";
    private static final String specialCharacters = "[].+-*/;@#$%&";

    static void countIdentifiers(String fileName, Map<String, Integer> words) throws FileNotFoundException {
        int count = 0;
        Scanner scan = new Scanner(new File(fileName));
        while (scan.hasNext()) {
            // while (!(scan.nextLine().startsWith("*"))) {
            StringTokenizer token = new StringTokenizer(scan.next(), ".,;' ");
            while (token.hasMoreTokens()) {
                String currentToken = token.nextToken();
                if (words.containsKey(currentToken)) {
                    int currCount = words.get(currentToken);
                    count = currCount + 1;
                } else {
                    count = 1;
                }
                words.put(currentToken, count);
            }
        }
        // }

        scan.close();
    }

    static boolean isCurlyBracesBalanced(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
        String input = file.next();
        Stack stack = new Stack();
        for (int num = 0; num < input.length(); num++) {
            if (input.charAt(num) == '(' || input.charAt(num) == '{' || input.charAt(num) == '[') {
                stack.push(input.charAt(num));
            }
            if (input.charAt(num) == ')' || input.charAt(num) == '}' || input.charAt(num) == ']') {
                if (stack.empty()) {
                    return false;
                }
                Character topCharacter = (Character) stack.pop();

                if ((topCharacter == '(' && input.charAt(num) != ')') || (topCharacter == '{' && input.charAt(num) != '}') || (topCharacter == '[' && input.charAt(num) != ']')) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer> words = new HashMap<String, Integer>();
        countIdentifiers(JAVA_FILE_PATH, words);
        Iterator it = words.entrySet().iterator();
        System.out.println("----------Identifiers and its counts----------");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

//isName function checks in the map and returns the values which are valid identifiers and its count
            if (SourceVersion.isName(pair.getKey().toString())) {
                System.out.println(pair.getKey() + " = " + pair.getValue());
            }
        }
        System.out.println("----------Special Characters and its count----------");
        Iterator it1 = words.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair = (Map.Entry) it1.next();

//This condition checks in the map and returns the count of special characters
            if (specialCharacters.contains(pair.getKey().toString())) {
                System.out.println(pair.getKey() + " = " + pair.getValue());
            }
        }
        System.out.println("----------Keywords and its counts----------");
        Iterator it2 = words.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pair = (Map.Entry) it2.next();

// isKeyword checkd if the string is a reserved java keyword
            if (SourceVersion.isKeyword(pair.getKey().toString())) {
                System.out.println(pair.getKey() + " = " + pair.getValue());
            }
        }
        System.out.println("-------------------------------------------------");
        //This if condition checks if the given java program has balanced braces
        if (isCurlyBracesBalanced(JAVA_FILE_PATH)) {
            System.out.println("The given expression is balanced");
        } else {
            System.out.println("The given expression is not balanced");
        }

    }
}
