package hackerrank_solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaMap_Phonebook {

    public static void main (String[] args){

        PhonebookKeep pb = new PhonebookKeep();
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        in.nextLine();
        for(int i=0;i<n;i++)
        {
            String name=in.nextLine();
            int phone=in.nextInt();
            pb.addPhone(name, phone);
            in.nextLine();
        }
        while(in.hasNext())
        {
            String s=in.nextLine();
            System.out.println(pb.printPhonDataByName(s));
        }
    }
}


class PhonebookKeep{
    private Map<String, Integer> phoneMp;

    //constructor
    PhonebookKeep(){
        phoneMp = new HashMap();
    }


    /**
     * this method add the person name and their phone number to hashmap
     *
     * @param name
     * @param phone
     */
    void addPhone(String name, Integer phone){
        phoneMp.put(name, phone);
    }

    /**
     * get for th hash map instance
     *
     * @return
     */
    public Map<String, Integer> getMap(){
        return phoneMp;
    }

    /**
     * method to search for person phone number by name
     * if found : outputs the persons name and phone number
     * if the key is not found the method returns string
     * @param name
     * @return
     */
    String printPhonDataByName(String name){
        //  for (Map.Entry me : phoneMp.entrySet()) {
        //    System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
        // }

        // Check is key exists in the Map
        boolean isKeyPresent = phoneMp.containsKey(name);

        if(isKeyPresent) return name + "=" + phoneMp.get(name);
        return "Not found";
    }

}




