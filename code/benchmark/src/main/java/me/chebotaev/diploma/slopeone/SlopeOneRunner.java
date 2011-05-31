package me.chebotaev.diploma.slopeone;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SlopeOneRunner {

    static List<Item> allItems = new LinkedList<Item>();

    public static void main(String args[]){
        // this is my data base
        Map<User,Map<Item,Float>> data = new HashMap<User,Map<Item,Float>>();

        // items
        Item item1 = new Item("       candy");
        Item item2 = new Item("         dog");
        Item item3 = new Item("         cat");
        Item item4 = new Item("         war");
        Item item5 = new Item("strange food");

        allItems.add(item1);
        allItems.add(item2);
        allItems.add(item3);
        allItems.add(item4);
        allItems.add(item5);

        //I'm going to fill it in
        HashMap<Item,Float> user1 = new HashMap<Item,Float>();
        user1.put(item1,1.0f);
        user1.put(item2,0.5f);
        user1.put(item4,0.1f);
        data.put(new User("Bob"),user1);

        HashMap<Item,Float> user2 = new HashMap<Item,Float>();
        user2.put(item1,1.0f);
        user2.put(item3,0.5f);
        user2.put(item4,0.2f);
        data.put(new User("Jane"),user2);

        HashMap<Item,Float> user3 = new HashMap<Item,Float>();
        user3.put(item1,0.9f);
        user3.put(item2,0.4f);
        user3.put(item3,0.5f);
        user3.put(item4,0.1f);
        data.put(new User("Jo"),user3);

        HashMap<Item,Float> user4 = new HashMap<Item,Float>();
        user4.put(item1,0.1f);
        //user4.put(item2,0.4f);
        //user4.put(item3,0.5f);
        user4.put(item4,1.0f);
        user4.put(item5,0.4f);
        data.put(new User("StrangeJo"),user4);

        // next, I create my predictor engine
        SlopeOne so = new SlopeOne(data);
        System.out.println("Here's the data I have accumulated...");
        printData(so);

        // then, I'm going to test it out...
        HashMap<Item,Float> user = new HashMap<Item,Float>();
        System.out.println("Ok, now we predict...");

        user.put(item5,0.4f);
        System.out.println("Inputting...");
        print(user);
        System.out.println("Getting...");
        print(so.predict(user));

        user.put(item4,0.2f);
        System.out.println("Inputting...");
        print(user);
        System.out.println("Getting...");
        print(so.predict(user));
    }

    private static void printData(SlopeOne so) {
        for(User user : so.data.keySet()) {
            System.out.println(user);
            print(so.data.get(user));
        }
        for (int i=0; i< allItems.size(); i++) {
            System.out.print("\n" + allItems.get(i) + ":");
            printMatrixes(so.diffMatrix.get(allItems.get(i)), so.frequencyMatrix.get(allItems.get(i)));
        }
    }

    private static void printMatrixes(Map<Item,Float> ratings, Map<Item,Integer> frequencies) {
        for (int j=0; j< allItems.size(); j++) {
            System.out.format("%10.3f", ratings.get(allItems.get(j)));
            System.out.print(" ");
            System.out.format("%10d", frequencies.get(allItems.get(j)));
        }
        System.out.println();
    }

    public static void print(Map<Item,Float> user) {
        for (Item j : user.keySet()) {
            System.out.println(" "+ j+ " --> "+user.get(j).floatValue());
        }
    }


}
