package hackerrank_solutions;

import java.util.*;
import java.util.stream.Collectors;

class JavaStack{

    private static final String patStrOpen = "[({\\[]";
    private static final String patStrClose = "[)}\\]]";
    private static final String barcesPattern = "\\[]|\\(\\)|\\{}";

    private static final String test1 = "(){}";       // }), {(
    private static final String test2 = "{{}}";       // }}, {{
    private static final String test3 = "{()}";       // }), ({
    private static final String test4 = "[]";
    private static final String test5 = "()";
    private static final String test6 = "{(})";       // )}, ({
    private static final String test7 = "{)(}";       // }), ({
    private static final String test8 = "}}}}";       // }), ({
    private static final String test9 = "{{{{";       // }), ({
    private static final String test10 = "}{";       // }), ({


    public static void main(String []argh)
    {
        Scanner sc = new Scanner(test10);
//        Scanner sc = new Scanner(System.in);

        Stack closeStack = new Stack<String>();
        Stack openStack = new Stack<String>();
        String input;
        while (sc.hasNext()) {
            input=sc.next();
            input.chars()
                    .mapToObj(i -> (char) i)
                    .collect(Collectors.toList())
                    .forEach(i -> {
                        // System.out.println(String.valueOf(i));
                        if(String.valueOf(i).matches(patStrOpen)){
                            openStack.push(String.valueOf(i));
                            //System.out.println("<-" + String.valueOf(i) + "," + openStack.size());
                        }else if(String.valueOf(i).matches(patStrClose)){
                            closeStack.push(String.valueOf(i));
                            //System.out.println("<-" + String.valueOf(i) + "," + closeStack.size());
                            if(!openStack.empty()){
                                String compiled =  openStack.peek() + "" + closeStack.peek();
                                if( compiled.matches(barcesPattern)){
                                    openStack.pop();
                                    closeStack.pop();
                                }
                            }
                        }
                    });

            if(!openStack.empty() || !closeStack.empty()){
                System.out.println("false");
            }else{
                System.out.println("true");
            }
            openStack.clear();
            closeStack.clear();
        }

    }
}
/*
    never use inner/nested class (unless declared static) in the main class if it's NOT BEING INSTANTIATED
    otherwise compiler with throw exception
    hence it's better using a class that resides in the same java file or same package
 */
class StackManager{

    private Stack<String> stringStack;

    // package private constructor
    StackManager(){
        stringStack = new Stack<>();
    }

    void pushToStack(String str){
        stringStack.push(str);
    }

    void clearStack(){
        stringStack.clear();
    }

    Stack getStack(){
        return stringStack;
    }

    void printStack(){
        stringStack.stream().forEach(System.out::println);
    }

    Integer searchStack(String str){
        return stringStack.search(str);
    }

    String peekStackHead(){
        return stringStack.peek();
    }

    void pushStringCharsToStack(String str){
        str.chars().forEach(c -> pushToStack(String.valueOf((char)c)));
    }

}

/* also nice solution, but would fail test(s) n# 8
class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(map.containsKey(c)){
                stack.push(c);
            } else if(!stack.empty() && map.get(stack.peek())==c){
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }
}
 */

/* test cases

({}[])
(({()})))
({(){}()})()({(){}()})(){()}
{}()))(()()({}}{}
}}}}
))))
{{{
(((
[]{}(){()}((())){{{}}}{()()}{{}{}}
[[]][][]
}{

 */
