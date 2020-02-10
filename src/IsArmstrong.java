import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IsArmstrong implements Runnable {

    private List<Integer> list;
    private String pathArmstrong;

    public IsArmstrong(List<Integer> list, String pathArmstrong) {
        this.list = list;
        this.pathArmstrong = pathArmstrong;
    }

    @Override
    public void run() {

        Map<Integer, String> map = new LinkedHashMap<>();

        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            int count = counter(current);

            int temp = 0;
            for (int j = 0; j < count; j++) {
                temp += Math.pow(current % 10, count);
                current /= 10;
            }
            if (list.get(i) == temp)
                map.put(list.get(i), "является");
            else
                map.put(list.get(i), "не является");

        }

        try (Writer writer = new PrintWriter(pathArmstrong)) {
            for (Map.Entry<Integer, String> item : map.entrySet())
                writer.write(item.getKey() + " " + item.getValue() + " числом Армстронга\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Файл с числами Армстронга готов");
    }

    private int counter(int number) {
        int count = 1;

        while (number >= 10) {
            count++;
            number /= 10;
        }
        return count;
    }
}
