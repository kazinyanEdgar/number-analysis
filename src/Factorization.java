import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Factorization implements Runnable {

    private List<Integer> listNumbers;
    private String pathFactorization;

    public Factorization(List<Integer> listNumbers, String pathFactorization) {
        this.listNumbers = listNumbers;
        this.pathFactorization = pathFactorization;
    }

    @Override
    public void run() {
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();

        for (Integer item : listNumbers) {
            List<Integer> list = factorization(item);
            map.put(item, list);
        }

        try (Writer writer = new PrintWriter(pathFactorization)) {
            for (Map.Entry<Integer, List<Integer>> item : map.entrySet())
                writer.write(item.getKey() + " в виде произведения простых чисел = " + item.getValue() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Файл с факторизацией чисел готов");
    }

    private List<Integer> factorization(int n) {
        List<Integer> listOfPrimes = arrayOfPrimes(n);
        List<Integer> listFactorization = new ArrayList<>();

        for (int i = 0; i < listOfPrimes.size(); ) {
            if ((n % listOfPrimes.get(i)) == 0) {
                listFactorization.add(listOfPrimes.get(i));
                n /= listOfPrimes.get(i);
            } else
                i++;


        }
        return listFactorization;
    }

    private List<Integer> arrayOfPrimes(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            int j = 2;
            while (i >= j) {

                if (i % j == 0) {
                    if (i == j) {
                        list.add(i);
                    }
                    break;
                }
                j++;

            }
        }
        return list;
    }
}
