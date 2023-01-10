public class Program {
    public static void main(String[] args) {
        int number = 479598;
        System.out.println(division(number));
    }

    public static int division(int number) {
        if (number < 10)
            return number;
        return number % 10 + division(number / 10);
    }
}
