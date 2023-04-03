package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;
import hr.fer.oprpp1.custom.collections.EmptyStackException;
import hr.fer.oprpp1.custom.collections.ObjectStack;

/**
 * Demo class that demonstrate the usage of stack-like collection. Expression
 * that will be evaluated must be in postfix representation. This program always
 * gets just one argument (args.length should be 1 and the args[0] should be the
 * whole expression).
 *
 * Example 1: “8 2 /” means apply / on 8 and 2, so 8/2=4.
 *
 * Example 2: “-1 8 2 / +” means apply / on 8 and 2, so 8/2=4, then apply + on
 * -1 and 4, so the result is 3.
 *
 * @author Veronika Žunar
 *
 */
public class StackDemo {

    /**
     * This is command-line application which accepts a single command-line
     * argument: expression which should be evaluated. Each operator takes two
     * preceding numbers and replaces them with operation result. Program supports
     * only +, -, /, * and %.
     *
     * @param args single command-line argument
     * @throws IllegalArgumentException if there is less or more of one lines of
     *                                  argument
     * @throws EmptyStackException      if stack is empty at the end of evaluation
     */
    public static void main(String[] args){

        if (args.length != 1) {
            throw new IllegalArgumentException();
        }

        ObjectStack stack = new ObjectStack();
        String[] arguments = args[0].split(" ");

        ArrayIndexedCollection operators = new ArrayIndexedCollection(5);
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("%");

        for (String s : arguments) {
            try {
                stack.push(Integer.parseInt(s));
                continue;
            } catch (NumberFormatException ex) {
                if (operators.contains(s)) {
                    Integer firstArg, secondArg;
                    secondArg = (Integer) stack.pop();
                    firstArg = (Integer) stack.pop();
                    if (firstArg == null || secondArg == null) {
                        break;
                    }
                    Integer result;
                    switch (s) {
                        case "+":
                            result = firstArg + secondArg;
                            break;
                        case "-":
                            result = firstArg - secondArg;
                            break;
                        case "*":
                            result = firstArg * secondArg;
                            break;
                        case "/":
                            if(secondArg == 0) {
                                throw new IllegalArgumentException("Disallowed division by zero!");
                            }
                            result = firstArg / secondArg;
                            break;
                        case "%":
                            if(secondArg == 0) {
                                throw new IllegalArgumentException("Disallowed division by zero!");
                            }
                            result = firstArg % secondArg;
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                    stack.push(result);
                } else {
                    stack.clear();
                    break;
                }
            }

        }

        if (stack.size() != 1)
            throw new EmptyStackException();
        else
            System.out.println(stack.pop());
    }
}

