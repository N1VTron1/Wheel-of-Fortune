package view;

import model.Letter;

public class LetterView {

  public static char textMasked(Letter letter) {
    if (letter.isHidden()) {
      return '_';
    } else {
      return textUnmasked(letter);
    }

  }

  public static char textUnmasked(Letter letter) {
    return letter.getValue();
  }
}
