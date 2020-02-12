package firstHomework;

import java.util.Scanner;

public class PalindromeNumber {
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.print("Please, enter 3 digit number: ");
    int threeDigitNumber = scanner.nextInt();
    int remaining = threeDigitNumber % 100;

    System.out.println(threeDigitNumber +
        (((threeDigitNumber / 100 == remaining % 10) ? " is a " : " is not a ") + "palindrome."));
  }
}
