package firstHomework;

import java.util.Scanner;

public class PalindromeInteger {
  private static Scanner scanner = new Scanner(System.in);

  private static boolean isPalindrome(int number) {
    return number == reverse(number);
  }

  private static int reverse(int number) {
    StringBuilder reverse = new StringBuilder();
    String stringNumber = String.valueOf(number);

    for (int i = stringNumber.length() - 1; i >= 0; i--) {
      reverse.append(stringNumber.charAt(i));
    }
    return Integer.parseInt(reverse.toString());
  }

  public static void main(String[] args) {
    System.out.print("Please, enter an integer: ");

    System.out.print("Entered number "
            + (isPalindrome(scanner.nextInt()) ? " is " : " is not ")
            + "a palindrome.");
  }
}
