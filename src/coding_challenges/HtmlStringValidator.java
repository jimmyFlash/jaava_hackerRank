package coding_challenges;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlStringValidator {

    private static final String testHtml1 = "<div><div><b></b></div></p>";
    private static final String testHtml2 = "<div>jimmy</div><p>hi<b>bold me</b></p>";
    private static final String testHtml3 = "<div>abc</div><p><em><i>test test test</b></em></p>";
    private static final String testHtml4 = "<div><i>hello</i>world</b>";
    private static final String testHtml5 = "<div><i>5555</i></b><i><b></div>";
    private static final String testHtml6 = "<div><invalid>5555</invalid><b><i></i></em><div>555</div></div>";
    private static final String testHtml7 = "<div><invalid><p><b><i>italic text</i></b></p></div>";


//    private static final Pattern patternOpenTag = Pattern.compile("<(\\w+)>+");
//    private static final Pattern patternClosedTag = Pattern.compile("</(\\w+)>+");

    private static final Pattern patternOpenTag = Pattern.compile("<(div|i|p|em|b)>+");
    private static final Pattern patternClosedTag = Pattern.compile("</(div|i|p|em|b)>+");


    public static void main(String[] args) {
        Scanner sc = new Scanner(testHtml5);
        System.out.println(stringChallenge(sc.nextLine()));
    }

    private static String stringChallenge(String str){

        List<String> closeStack = new ArrayList<>();
        List<String> openStack = new ArrayList<>();

        Matcher openTags = patternOpenTag.matcher(str);
        Matcher closedTags = patternClosedTag.matcher(str);


        while (openTags.find()) {
//            System.out.println("open-Tag: " + openTags.group(1));
            openStack.add(openTags.group(1));
        }
        while (closedTags.find()) {
//            System.out.println("close-Tag: " + closedTags.group(1));
            closeStack.add(closedTags.group(1));
        }

        // reversing the order to start validation
        // from most inner tag going outwards
        Collections.reverse(openStack);

        System.out.println("HTML string: " + str);
        System.out.println("openStack: " + openStack);
        System.out.println("closedStack: " + closeStack);


        if(openStack.size() > 0 && closeStack.size() > 0){
            // Creating an iterator
            int i = 0;
            int j = 0;
            String filteredStr = str;
            while (i < openStack.size() ) {
                while (j < closeStack.size() && openStack.size() > 0) {
                    String s1 = openStack.get(i);
                    String s2 = closeStack.get(j);
                    String openTag = "<" + s1 + ">";
                    String closedTag = "</" + s2 + ">";
                    if (s1.equalsIgnoreCase(s2)
                    && filteredStr.indexOf(openTag) < filteredStr.indexOf(closedTag)) {
                        openStack.remove(s1);
                        closeStack.remove(s2);

                        filteredStr = filteredStr.replaceFirst(openTag, "");
                        filteredStr = filteredStr.replaceFirst(closedTag, "");
                        j = -1;
                        i = 0;
                    }
                    j++;
                }
                i++;
                j = 0;
            }
            System.out.println("Remaining, opening tags: " + openStack.size() +": " + openStack +
                    ", closed tags: " + closeStack.size() + ": " + closeStack +
                    ", remaining html tags: " + filteredStr);
        }
        return (openStack.size() == 0 && closeStack.size() == 0) ? "true" :
                openStack.toString();
    }
}