package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordTest {

  // e.g., if you do 'new Word("hello")', then toString() should give you "hello"
  //
  // Test these variations:
  //   • empty string
  //   • single-letter word
  //   • multi-letter word
  @Test
  void toString__compare_tostring_to_orig__are_equal() {
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
