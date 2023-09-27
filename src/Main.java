import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите выражение (пример: \"a\" + \"b\", \"a\" - \"b\", \"a\" * 2, \"a\" / 2):");
            String input = scanner.nextLine().trim();

            Pattern pattern = Pattern.compile("\"([^\"]+)\"\\s*([-+*/])\\s*(\"[^\"]+\"|\\d+)");
            Matcher matcher = pattern.matcher(input);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("Неверный формат ввода");
            }

            String operand1 = matcher.group(1);
            String operator = matcher.group(2);
            String operand2 = matcher.group(3);

            if (operand1.length() > 10) {
                throw new IllegalArgumentException("Длина первой строки не должна превышать 10 символов");
            }

            String result = "";

            switch (operator) {
                case "+" -> {
                    if (operand2.startsWith("\"") && operand2.endsWith("\"")) {
                        operand2 = operand2.substring(1, operand2.length() - 1);
                        if (operand2.length() > 10) {
                            throw new IllegalArgumentException("Длина  второй строки не  должна превышать 10 символов");
                        }
                        result = operand1 + operand2;
                    }
                }
                case "-" -> {
                    if (operand2.startsWith("\"") && operand2.endsWith("\"")) {
                        operand2 = operand2.substring(1, operand2.length() - 1);
                        result = operand1.replace(operand2, "");
                    }
                }
                case "*" -> {
                    int n = Integer.parseInt(operand2);
                    if (n >= 1 && n <= 10) {
                        result = operand1.repeat(n);
                    } else {
                        throw new IllegalArgumentException("Число для умножения должно быть от 1 до 10");
                    }
                }
                case "/" -> {
                    int divisor = Integer.parseInt(operand2);
                    if (divisor >= 1 && divisor <= 10) {
                        int length = operand1.length() / divisor;
                        result = operand1.substring(0, length);
                    } else {
                        throw new IllegalArgumentException("Число для деления должно быть от 1 до 10");
                    }
                }
                default -> throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
            }

            if (result.length() > 40) {
                result = result.substring(0, 40) + "...";
            }

            System.out.println("\"" + result + "\"");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}