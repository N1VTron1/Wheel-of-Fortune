package view;

import model.Letter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LetterViewTest {

  @Test
  void textMasked__hidden_letter__is_underscore() {
    char c = 'X';
    Letter l = new Letter(c);
    char expected = '_';
    char actual = LetterView.textMasked(l);
    assertEquals(expected, actual);
  }

  @Test
  void textMasked__revealed_letter__is_letter() {
    char c = 'X';
    Letter l = new Letter(c);
    l.setHidden(false);
    char expected = c;
    char actual = LetterView.textMasked(l);
    assertEquals(expected, actual);
  }

  @Test
  void textUnmasked__revealed_letter__is_letter() {
    char c = 'X';
    Letter l = new Letter(c);

    char expected = c;
    char actual = LetterView.textUnmasked(l);
    assertEquals(expected, actual);
  }

  @Test
  void textUnmasked__hidden_letter__is_letter() {
    char c = 'X';
    Letter l = new Letter(c);

    char expected = c;
    char actual = LetterView.textUnmasked(l);
    assertEquals(expected, actual);
  }
}
