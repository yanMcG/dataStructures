import java.util.*;


class hashmapdemo2 {
    public static void main(String[] args) {
    // create a hashmap with string and integer
    HashMap<String, Integer> numbersmap = new HashMap<>();

    System.out.println("Initial HashMap: " + numbersmap);

    // put() method to add elements
    numbersmap.put("One", 1);
    numbersmap.put("Two", 2);
    numbersmap.put("Three", 3);
    System.out.println("HashMap after put(): " + numbersmap);


    // iterate through keys only
    System.out.print("Keys: ");
    for (String key : numbersmap.keySet()) {
        System.out.print(key + ", ");
    }


    // iterate through values only
    System.out.print("\nValues: ");
    for (Integer value : numbersmap.values()) {
        System.out.print(value + ", ");
    }



    // iterate through key/value entries
    System.out.print("\nEntries: ");


    //Don't forget the extra import statement
    for (Map.Entry<String, Integer> entry : numbersmap.entrySet()) {
        System.out.print(entry);
        System.out.print(", ");
    }
    }
}