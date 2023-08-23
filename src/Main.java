import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Ввод числового выражения
        try (Scanner scanner = new Scanner(System.in)){
            while (scanner.hasNext()) {
                String expression = scanner.nextLine();
                System.out.println(calc(expression));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Распарсиваем строку на отдельные числа и знак выражения
    public static String calc(String input) throws IOException {

        char[] array = input.toCharArray();
        int first;
        int second;
        char sign = ' ';
        int isCorrectSign = 0;
        String[] arrayNotSpace = input.split(" ");

        if(arrayNotSpace.length > 3 || arrayNotSpace.length <= 1)
            throw new IOException("Cтрока не является математической операцией");

        for (char c : array) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                sign = c;
                isCorrectSign++;
            }
        }

        if(isCorrectSign != 1){
            throw new IOException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)!");
        }
        switch (sign){
            case '+' -> arrayNotSpace = input.split(" \\+ ");
            case '-' -> arrayNotSpace = input.split(" - ");
            case '*' -> arrayNotSpace = input.split(" \\* ");
            case '/' -> arrayNotSpace = input.split(" / ");
        }
        first = Integer.parseInt(arrayNotSpace[0]);
        second = Integer.parseInt(arrayNotSpace[1]);


        return Integer.toString(expression(first, second, sign));
    }


    // Проверяем на корректный ввод чисел в диапазоне
    public static boolean inCorrect(int number){
        return number >= 1 && number <= 10;
    }

    // Рассчитываем выражение
    public static int expression(int first, int second, char sign) throws IOException {
        int answer = -1;
        if(inCorrect(first) && inCorrect(second)){
            switch (sign) {
                case ('+') -> answer = first + second;

                case ('-') -> answer = first - second;

                case ('*') -> answer = first * second;

                case ('/') -> {
                    try {
                        answer = first / second;
                    } catch (ArithmeticException e) {
                        System.err.println("Результатом операции могут быть только целые числа!");
                    }
                }
                default -> throw new IllegalArgumentException("Неверный знак операции");
            }
        } else {
            throw new IOException("Числа не находятся в диапазоне от 1 до 10!");
        }
        return answer;
    }
    }