package view;

import model.Word;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordViewTest {

  @Test
  void display__compare_word__are_equal() {
    String[] testStrings = {
        "",
        "a",
        "hello"
    };
    for (String testString : testStrings) {
      Word w = new Word(testString);
      assertEquals(testString, w.toString());
    }
  }
}