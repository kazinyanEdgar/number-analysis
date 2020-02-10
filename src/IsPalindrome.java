import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IsPalindrome implements Runnable {

    private List<Integer> list;
    private String pathPalindrome;

    public IsPalindrome(List<Integer> list, String pathPalindrome) {
        this.list = list;
        this.pathPalindrome = pathPalindrome;
    }

    @Override
    public void run() {

        Map<Integer, String> map = new LinkedHashMap<>();

        for (int i = 0; i < list.size(); i++) {

            String s = String.valueOf(list.get(i));
            char[] chars = s.toCharArray();
            String temp = "";
            for (int j = chars.length - 1; j >= 0; j--) {
                temp += chars[j];
            }
            if (s.equals(temp))
                map.put(list.get(i), "является");
            else
                map.put(list.get(i), "не является");
        }

        try (Writer writer = new PrintWriter(pathPalindrome)) {
            for (Map.Entry<Integer, String> item : map.entrySet())
                writer.write(item.getKey() + " " + item.getValue() + " палиндромом\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Файл с числами палиндромами готов");
    }
}
