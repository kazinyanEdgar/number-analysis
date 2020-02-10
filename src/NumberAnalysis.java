import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NumberAnalysis {
    public static void main(String[] args) {

        String pathInput = "C://Users//Edgar//Desktop//numbers//ReadNumbers.txt";

        String pathPalindrome = "C://Users//Edgar//Desktop//numbers//IsPalindrome.txt";
        String pathArmstrong = "C://Users//Edgar//Desktop//numbers//IsArmstrong.txt";
        String pathFactorization = "C://Users//Edgar//Desktop//numbers//Factorization.txt";

        List<Integer> listDataFromFile = new ArrayList<>();

        try (InputStream is = new FileInputStream(pathInput);
             Scanner scanner = new Scanner(is)) {

            while (scanner.hasNextLine()) {
                int nextInt = scanner.nextInt();
                if (nextInt < 1)
                    throw new InputMismatchException();
                listDataFromFile.add(nextInt);
            }

            ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.submit(new IsPalindrome(listDataFromFile, pathPalindrome));
            executorService.submit(new IsArmstrong(listDataFromFile, pathArmstrong));
            executorService.submit(new Factorization(listDataFromFile, pathFactorization));
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.DAYS);

            System.out.println("Все файлы готовы");

        } catch (FileNotFoundException e) {
            System.err.println("По указанному ниже пути не удалось найти файл для считывания\n" + pathInput);
        } catch (InputMismatchException e) {
            System.err.println("Ошибка! В файле должны быть только целые положительные числа");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}