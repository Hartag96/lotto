import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Losowanie> collection = new ArrayList<Losowanie>();
        wypelnij(collection);
        System.out.println(String.format("Wyniki %d losowania: %s", 4, collection.get(4).getNumbers()));
        occurrence(collection, 6);
    }


    public static void wypelnij(ArrayList<Losowanie> collection){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/lotto.txt"));
            String line = reader.readLine();
            while (line != null) {
                collection.add(new Losowanie(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void occurrence(ArrayList<Losowanie> collection, int n){
        int j = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 1; i < 50; i++)
            map.put(i, 0);

        for (Losowanie losowanie: collection) {
            int [] numbers = losowanie.getLotto();
            for(int i = 0; i < numbers.length; i++){
                map.put(numbers[i], map.get(numbers[i]) + 1);
            }
        }

        System.out.println(map);
        Map<Integer, Integer> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(String.format("%d najzadzysz wystapien liczb", n));
        for(Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if(j == n) {
                break;
            }else {
                System.out.println(String.format("liczba %d - %d razy", entry.getKey(), entry.getValue()));
                j++;
            }
        }
    }


}
