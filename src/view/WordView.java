package view;

import model.Letter;
import model.Word;

public class WordView {

  public static String toTextMasked(Word word) {
    return toText(word, true);
  }

  public static String toTextUnmasked(Word word) {
    return toText(word, false);
  }

  // So, if the word is made up of 3 Letter objects, concatenate them.
  // e.g., the Word for PSU will be those 3 letter objects
  // You would return the string "PSU"
  //
  // BUT if parameter "masked" is true, then returned the masked version of the
  // word.
  // e.g., if the word is "PSU", but only 'S' has been guessed, then
  // return "_S_".
  //
  private static String toText(Word word, boolean masked) {
    StringBuilder sb = new StringBuilder();

    for (Letter letter : word.getLetters()) {
      char ch;
      if (masked) {
        ch = LetterView.textMasked(letter);
      } else {
        ch = LetterView.textUnmasked(letter);
      }
      sb.append(" " + ch);
    }

    return sb.toString().trim();
  }
}
