package view;

import model.Puzzle;
import model.Word;

import java.util.Arrays;
import java.util.List;

public class PuzzleView {
  private static final String NL = System.getProperty("line.separator");

  public static String toTextMasked(Puzzle puzzle) {
    return toText(puzzle, true);
  }

  public static String toTextUnmasked(Puzzle puzzle) {
    return toText(puzzle, false);
  }

  // If the puzzle is "PSU", then return "P S U"
  // If the puzzle is "WE ARE", then return "W E   A R E"
  public static String toText(Puzzle puzzle, boolean masked) {
    StringBuffer sb = new StringBuffer();

    String s;
    for (Word w : puzzle.getWords()) {
      /*
       * Why 3 spaces?
       * Because we space out each character by one space
       * "WE ARE" will be displayed as "W E   A R E"
       * The space itself will have a space after it.
       * So in "W E   A R E"
       *           123
       * Space #1 is E's spacer
       * Space #2 is the original space char
       * Space #3 is the space char's spacer
       */
      sb.append("   ");
      if (masked) {
        sb.append(WordView.toTextMasked(w));
      } else {
        sb.append(WordView.toTextUnmasked(w));
      }
    }

    /*
     * Need to trim here, because of the leading space character(s) that were
     * put in at the top of the loop.
     */
    return sb.toString().trim();
  }

  private static String charListToString(List<Character> charList) {
    StringBuilder sb = new StringBuilder();

    for (char ch : charList) {
      sb.append(ch);
    }

    return sb.toString();
  }

  public static String guessedConsonants(Puzzle puzzle) {
    return charListToString(puzzle.getGuessedConsonants());
  }

  public static String guessedVowels(Puzzle puzzle) {
    return charListToString(puzzle.getGuessedVowels());
  }

  public static void displayMessage() {
    displayMessage("");
  }

  public static void displayMessageLine() {
    displayMessage(NL);
  }

  public static void displayMessageLine(String message) {
    displayMessage(message + NL);
  }

  public static void displayMessage(String message) {
    System.out.print(message);
  }

  public static void displayMessage(String... args) {
    System.out.printf(args[0], (Object[]) Arrays.copyOfRange(args, 1, args.length));
  }

}
