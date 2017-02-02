public class Hash {

private Hashtable<String, Integer> table = new Hashtable<String, Integer>();

public void readFile() {

    File file = new File("file.txt");

    try {

        Scanner sc = new Scanner(file);

        String words;

        while (sc.hasNext()) {
            words = sc.next();
            words = words.toLowerCase();

            if (words.length() >= 2) {
                table.put(words, 1);
                add(words);
            }
        }
        sc.close();

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}

public void add(String words) {

    Set<String> keys = table.keySet();
    for (String count : keys) {
        if (table.containsKey(count)) {
            table.put(count, table.get(count) + 1);
        } else {
            table.put(count, 1);
        }
    }
}

public void show() {

    for (Entry<String, Integer> entry : table.entrySet()) {
        System.out.println(entry.getKey() + "\t" + entry.getValue());
    }
}

public static void main(String args[]) {

    Hash abc = new Hash();

    abc.readFile();

    abc.show();
}
}