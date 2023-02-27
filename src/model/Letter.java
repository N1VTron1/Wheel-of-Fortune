package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Letter {
  public enum LETTER_TYPE {
    CONSONANT,
    VOWEL
  }

  private char value;
  private boolean hidden;
  private boolean isVowel;

  public Letter(char ch) {
    this.value = ch;
    hidden = true;
    isVowel = isVowel(ch);
  }

  public static boolean isVowel(char ch) {
    return ("AEIOU".indexOf(Character.toUpperCase(ch))) >= 0;
  }

  public static boolean isConsonant(char ch) {
    // TODO 08 - [Letter.java] isConsonant returns true if the char is a consonant
    // Yihang Liu

    // create an list for ASCII code of all the vowel, both lower and upper case
    ArrayList<Character> vowelList = new ArrayList<Character>(Arrays.asList('a','e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    ArrayList<Integer> asciiIndexFoVowel = new ArrayList<>();
    for (int i = 0; i < vowelList.size(); i++) {
      asciiIndexFoVowel.add((int) vowelList.get(i));
    }

    // create create an list for ASCII, from a-z and A-Z
    ArrayList<Integer> consonantAsciiIndex = new ArrayList<>();
    for (int i = 65; i < 91; i++) {
      consonantAsciiIndex.add(i);
    }
    for (int i = 97; i < 123; i++) {
      consonantAsciiIndex.add(i);
    }

    // remove the vowels from the list
    consonantAsciiIndex.removeAll(asciiIndexFoVowel);

    // get the ASCII code for input char
    int asciiIndexForInputChar = (int) ch;

    boolean consonantChecker;

    if (consonantAsciiIndex.contains(asciiIndexForInputChar)) {
      consonantChecker = true;
    } else consonantChecker = false;


    return consonantChecker;
  }

  @Override
  public String toString() {
    return String.valueOf(this.value);
  }

  public boolean isHidden() {
    return hidden;
  }

  public char getValue() {
    return value;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }
}
