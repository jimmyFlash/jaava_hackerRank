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

    // pattern to match valid html open container tags example <div>
    private static final Pattern patternOpenTag = Pattern.compile("<(div|i|p|em|b)>+");

    // pattern to match valid html close container tags example </div>
    private static final Pattern patternClosedTag = Pattern.compile("</(div|i|p|em|b)>+");


    public static void main(String[] args) {
        Scanner sc = new Scanner(testHtml5);
        System.out.println(stringChallenge(sc.nextLine()));
    }

    /**
     * validates the html input string
     * @param str html string to validate
     * @return returns true if html string is valid or string representation of the invalid tags
     */
    private static String stringChallenge(String str){

        // two list to store open and closed tags scanned
        List<String> closeStack = new ArrayList<>();
        List<String> openStack = new ArrayList<>();

        // match patterns against input
        Matcher openTags = patternOpenTag.matcher(str);
        Matcher closedTags = patternClosedTag.matcher(str);


        // looping while matching patterns are found in the string

        while (openTags.find()) {
//            System.out.println("open-Tag: " + openTags.group(1));
            openStack.add(openTags.group(1)); // group 1 is the matched string set, using 0 includes whole string
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


        // check both lists are not empty
        if(openStack.size() > 0 && closeStack.size() > 0){
            // Creating an iterator
            int i = 0;
            int j = 0;
            String filteredStr = str;
            while (i < openStack.size() ) { // loop against the open tags list
                // inner loop against the closed tags list while making sure the open list is still not empty
                // to make sure both lists are matched in size as we remove matching elements
                while (j < closeStack.size() && openStack.size() > 0) {
                    // extract the open and close tag values from  lists
                    String s1 = openStack.get(i);
                    String s2 = closeStack.get(j);
                    // convert them to html representation
                    String openTag = "<" + s1 + ">";
                    String closedTag = "</" + s2 + ">";
                    if (s1.equalsIgnoreCase(s2) // match case and check they're equal
                    && filteredStr.indexOf(openTag) < filteredStr.indexOf(closedTag) // check order of tags in html valid
                    ) {
                        // remove matches from the both open/closed lists
                        openStack.remove(s1);
                        closeStack.remove(s2);

                        // remove them from the html string also replacing them with ""
                        filteredStr = filteredStr.replaceFirst(openTag, "");
                        filteredStr = filteredStr.replaceFirst(closedTag, "");

                        j = -1; // decrement j by 1 to keep checking with current index
                        i = 0; // reset the i to 0 to start from open list beginning
                    }
                    j++;
                }
                i++;
                j = 0; // reset counter for j to start form closed list beginning
            }
            System.out.println("Remaining, opening tags: " + openStack.size() +": " + openStack +
                    ", closed tags: " + closeStack.size() + ": " + closeStack +
                    ", remaining html tags: " + filteredStr);
        }
        return (openStack.size() == 0 && closeStack.size() == 0) ? "true" :
                openStack.toString();
    }
}