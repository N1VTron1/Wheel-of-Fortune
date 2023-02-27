package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Word {
  // A word is a list of letters
  private List<Letter> letters = new ArrayList<>();

  public void addLetter(Letter letter) {
    letters.add(letter);
  }

  public Word(String word) {
    for (char ch : word.toCharArray()) {
      this.addLetter(new Letter(ch));
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (Letter l : letters) {
      sb.append(l);
    }

    return sb.toString();
  }

  public Collection<Letter> getLetters() {
    return letters;
  }
}
