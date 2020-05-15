import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));

        File file;
        ArrayList<String> listWords;

        while (true) {
            file = new File(fileNameReader.readLine());
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
                listWords = readWordsFromFile(fileReader);
                fileReader.close();
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден. Убедитесь, что файл находится в корнейвой деректории программы или укажите полный путь к файлу.");
            }
        }
        fileNameReader.close();

        TreeMap<String, Integer> resultMap = countUniqueWords(listWords);

        for (Map.Entry<String, Integer> em : resultMap.entrySet()) {
            System.out.println(em.getKey());
        }
        System.out.println("");//для читабельности

        for (Map.Entry<String, Integer> em : resultMap.entrySet()) {
            System.out.println("Cлово \"" + em.getKey() + "\" встречается " + em.getValue() + " раз(а).");
        }
        System.out.println("");//для читабельности

        System.out.println("Наибольшее колчество раз встречается:");
        for (Map.Entry<String, Integer> em : resultMap.entrySet()) {
            if (em.getValue() == findMaxValue(resultMap)) {
                System.out.println("Cлово \"" + em.getKey() + "\" - " + em.getValue() + " раз(а).");
            }
        }
    }

    private static TreeMap<String, Integer> countUniqueWords(ArrayList<String> listWords) {
        TreeMap<String, Integer> resultMap = new TreeMap<>();
        for (String s1 : listWords) {
            int count = 0;
            for (String s2 : listWords) {
                if (s1.equalsIgnoreCase(s2)) {
                    count++;
                }
            }
            if (!s1.isEmpty()) {
                resultMap.put(s1.toLowerCase(), count);
            }
        }
        return resultMap;
    }

    private static ArrayList<String> readWordsFromFile(BufferedReader fileReader) throws IOException {
        ArrayList<String> wordsList = new ArrayList<>();
        while (fileReader.ready()) {
            String[] arr;
            arr = fileReader.readLine().split("[\\t\\n\\s\\p{Punct}]");
            wordsList.addAll(Arrays.asList(arr));
        }
        return wordsList;
    }

    private static int findMaxValue(TreeMap<String, Integer> treeMap) {
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> em : treeMap.entrySet()) {
            if (max < em.getValue()) {
                max = em.getValue();
            }
        }
        return max;
    }
}
