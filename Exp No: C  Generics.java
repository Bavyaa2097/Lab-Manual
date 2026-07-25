// Step 2: Generic class Box<T>
class Box<T> {
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }

    public void showType() {
        if (item != null) {
            System.out.println("Type of stored item : " + item.getClass().getName());
        }
    }
}

// Step 3: Generic class Pair<K, V>
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void display() {
        System.out.println(key + " = " + value);
    }
}

public class GenericDemo {

    // Step 4: Generic method findMax() with a bounded type parameter <T extends Comparable<T>>
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // Step 5: Create a Box<Integer> object, store a value, and display it
        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("Integer Box Value : " + intBox.get());
        intBox.showType();

        // Step 6: Create a Box<String> object, repeat store-and-display steps
        Box<String> strBox = new Box<>();
        strBox.set("Hello Generics");
        System.out.println("String Box Value : " + strBox.get());
        strBox.showType();

        System.out.println("\n---- Key-Value Pairs ----");
        
        // Step 7: Create two Pair objects with different type combinations
        Pair<String, Integer> pair1 = new Pair<>("Rahul", 88);
        Pair<Integer, String> pair2 = new Pair<>(101, "CSE");

        pair1.display();
        pair2.display();

        System.out.println();

        // Step 8: Create Integer, String, and Double arrays and pass each to findMax()
        Integer[] intArray = {25, 89, 45, 12};
        String[] strArray = {"Rahul", "Sneha", "Kiran", "Arjun"};
        Double[] doubleArray = {45.5, 92.3, 78.1, 12.4};

        System.out.println("Maximum Number : " + findMax(intArray));
        System.out.println("Maximum (Alphabetical) : " + findMax(strArray));
        System.out.println("Maximum Marks : " + findMax(doubleArray));
    }
}
