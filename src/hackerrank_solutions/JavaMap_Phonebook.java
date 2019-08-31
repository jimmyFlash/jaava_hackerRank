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

    PhonebookKeep(){
        phoneMp = new HashMap();
    }


    void addPhone(String name, Integer phone){
        phoneMp.put(name, phone);
    }

    public Map<String, Integer> getMap(){
        return phoneMp;
    }

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




