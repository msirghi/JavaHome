package firstHomework;

import java.util.Scanner;

public class CelsiusConverter {
  private static double convertCelsius(double celsius) {
    return 9.0 / 5 * celsius + 32;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Please, enter celsius: ");
    double celsius = scanner.nextDouble();
    System.out.println(celsius + " Celsius is " + convertCelsius(celsius) + " in Fahrenheit: ");
  }
}
