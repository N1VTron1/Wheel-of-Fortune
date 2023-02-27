package view;

import model.Puzzle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PuzzleViewTest {

  private static List<String> testStrings;
  private static List<String> unmaskedExpecteds;
  private static List<String> maskedExpecteds;

  @BeforeAll
  static void setUp() {
    testStrings = Arrays.asList(
        "PSU",
        "PSU ABINGTON",
        "PENN STATE ABINGTON",
        "A",
        ""
    );

    unmaskedExpecteds = Arrays.asList(
        "P S U",
        "P S U   A B I N G T O N",
        "P E N N   S T A T E   A B I N G T O N",
        "A",
        ""
    );

    maskedExpecteds = Arrays.asList(
        "_ _ _",
        "_ _ _   _ _ _ _ _ _ _ _",
        "_ _ _ _   _ _ _ _ _   _ _ _ _ _ _ _ _",
        "_",
        ""
    );

  }

  // e.g., if the phrase is "PSU", we expect the method to return "_ _ _"
  @Test
  void toTextMasked__create_new_puzzle__masked_correctly() {
    for (int i = 0; i < testStrings.size(); i++) {
      String testString = testStrings.get(i);
      Puzzle p = new Puzzle(testString);
      String expected = maskedExpecteds.get(i);
      String actual = PuzzleView.toTextMasked(p);
      assertEquals(expected, actual);
    }
  }

  // e.g., if the phrase is "PSU", we expect the method to return "P S U"
  @Test
  void textUnmasked__create_new_puzzle__unmasked_correctly() {
    for (int i = 0; i < testStrings.size(); i++) {
      String testString = testStrings.get(i);
      Puzzle p = new Puzzle(testString);
      String expected = unmaskedExpecteds.get(i);
      String actual = PuzzleView.toTextUnmasked(p);
      assertEquals(expected, actual);
    }
  }

  // e.g., if the puzzle is "PSU", and "S" is guessed, then we expect "_ S _"
  @Test
  void guessAndRevealLetter__guess_correct__letter_is_revealed() {
    Puzzle puzzle = new Puzzle("ABC ABC");
    char guessChar = 'C';

    puzzle.guessAndRevealLetter(guessChar);

    String expected = "_ _ C   _ _ C";
    String actual = PuzzleView.toTextMasked(puzzle);

    assertEquals(expected, actual);

  }
}
