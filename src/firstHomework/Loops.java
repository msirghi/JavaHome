package firstHomework;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Loops {
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    List list = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    System.out.print("Please, enter a string: ");
    String input = scanner.nextLine();
    int vowels = 0;
    int consonants = 0;

    for (int i = 0; i < input.length(); i++) {
      char character = input.charAt(i);
      if (character != ' ') {
        if (list.contains(Character.toLowerCase(character))) {
          vowels++;
          continue;
        }
        consonants++;
      }
    }

    System.out.println("The number of vowels is " + vowels);
    System.out.println("The number of consonants is " + consonants);

  }
}
