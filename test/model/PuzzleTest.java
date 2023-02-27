package model;

import model.Puzzle.GUESS_STATUS;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {


  @Test
  void puzzle__construct__all_letters_hidden() {
    Puzzle puzzle = new Puzzle("ABC ABC");

    for (Letter l : puzzle.getAllLetters()) {
      assertTrue(l.isHidden());
    }
  }

  // Puzzle.toString should match the phrase used to create the instance
  @Test
  void toString__create_puzzle__string_matches_phrase() {
    String[] phrases = {
        "",
        "PENN STATE ABINGTON",
        "PSU MAIN CAMPUS"
    };

    for (String phrase : phrases) {
      Puzzle puzzle = new Puzzle(phrase);
      assertEquals(phrase, puzzle.toString());
    }
  }

  // If you guess an incorrect letter, the method should return GUESS_STATUS.INCORRECT
  @Test
  void guessLetter__guess_incorrect__returns_incorrect() {
    Puzzle puzzle = new Puzzle("ABC ABC");
    GUESS_STATUS expected = GUESS_STATUS.INCORRECT;
    GUESS_STATUS actual = puzzle.guessLetter('X');
    assertEquals(expected, actual);
  }


  //   Test at least 3 phrases
  //     • One that has the letter once
  //     • One that has that letter twice (once in each word)
  //     • One that has that letter twice (twice in one word)
  @Test
  void guessLetter__guess_correct__returns_correct() {
    String[] phrases = {
        "ABC ABC",
        "CBA",
        "AA BB"
    };
    for (String phrase : phrases) {
      Puzzle puzzle = new Puzzle(phrase);
      GUESS_STATUS expected = GUESS_STATUS.CORRECT;
      GUESS_STATUS actual = puzzle.guessLetter('A');
      assertEquals(expected, actual);
    }
  }

  @Test
  void guessLetter__repeat_guess__returns_already_guessed() {
    Puzzle puzzle = new Puzzle("ABC ABC");
    GUESS_STATUS expected = GUESS_STATUS.ALREADY_GUESSED;
    puzzle.guessLetter('X'); // we don't need the returned value
    GUESS_STATUS actual = puzzle.guessLetter('X');
    assertEquals(expected, actual);
  }


  //    Test at least 3 phrases
  //      • One that has the letter once
  //      • One that has that letter twice (once in each word)
  //      • One that has that letter twice (twice in one word)
  @Test
  void guessAndRevealLetter__guess_correct__letter_not_hidden_anymore() {
    String[] phrases = {
        "ABC ABC",
        "CBA",
        "AA BB"
    };
    char guessChar = 'B';
    for (String phrase : phrases) {
      Puzzle puzzle = new Puzzle(phrase);

      puzzle.guessAndRevealLetter(guessChar);

      for (Letter l : puzzle.findLetters(guessChar)) {
        assertFalse(l.isHidden());
      }
    }
  }

  @Test
  void guessLetter__guess_incorrect__letter_still_hidden() {
    Puzzle puzzle = new Puzzle("ABC ABC");
    char guess = 'X';

    puzzle.guessLetter(guess);

    for (Letter l : puzzle.findLetters(guess)) {
      assertTrue(l.isHidden());
    }
  }


  // Test these aspects of findLetters:
  //   (1) That the letter is found the expected number of times
  //   (2) That the value of each Letter object is indeed the letter you're
  //       looking for
  //
  // Test at least 3 phrases
  //   • One that has the letter once
  //   • One that has that letter twice (once in each word)
  //   • One that has that letter twice (twice in one word)
  @Test
  void findLetters__find_existing_letters__returns_letters() {
    String[] phrases = {
        "ABC ABC",
        "CBA",
        "AA BB"
    };
    int[] occurrences = {
        2,
        1,
        2
    };

    int i = 0;
    for (String phrase : phrases) {
      Puzzle puzzle = new Puzzle(phrase);

      char searchFor = 'B';
      int count = 0;
      for (Letter l : puzzle.findLetters(searchFor)) {
        count++;
        assertEquals(searchFor, l.getValue());
      }

      assertEquals(occurrences[i], count);

      i++;
    }

  }


  // Test:
  //   • a phrase that does not have that letter
  //   • an empty-string phrase
  @Test
  void findLetters__find_nonexistent_letters__returns_empty() {
    String[] phrases = {
        "ABC ABC",
        ""
    };
    for (String phrase : phrases) {
      Puzzle puzzle = new Puzzle(phrase);

      char searchFor = 'X';
      for (Letter l : puzzle.findLetters(searchFor)) {
        fail(String.format("'%s' should not have been found in '%s'", searchFor, phrase));
      }
    }

  }

  @Test
  void isSolved__guess_all_letters__returns_true() {
    Puzzle puzzle = new Puzzle("PENN STATE");

    char[] letters = {'P', 'E', 'N', 'S', 'A', 'T'};

    for (char letter : letters) {
      puzzle.guessAndRevealLetter(letter);
    }

    assertTrue(puzzle.isSolved());
  }

  @Test
  void countLetter__create_puzzle__count_is_correct () {
    Puzzle puzzle = new Puzzle("PENN STATE");

    assertEquals(1, puzzle.countLetter('P'), "P count");
    assertEquals(2, puzzle.countLetter('E'), "E count");
    assertEquals(2, puzzle.countLetter('N'), "N count");
    assertEquals(1, puzzle.countLetter('S'), "S count");
    assertEquals(2, puzzle.countLetter('T'), "T count");
    assertEquals(1, puzzle.countLetter('A'), "A count");

    assertEquals(0, puzzle.countLetter('X'), "X count");
  }

  @Test
  void findGuessedChars__guess_letters__they_are_found() {
    Puzzle puzzle = new Puzzle("DOES NOT MATTER");

    String letters = "PENSAT";
    String consonants = "PNST";
    String vowels = "EA";


    /*
     * With no guessing done, there should be nothing found
     */
    Collection<Character> guessedConsonants = puzzle.findGuessedChars(Letter.LETTER_TYPE.CONSONANT);
    int numGuessedConsonants = guessedConsonants.size();
    assertEquals(0, numGuessedConsonants, "Should be no guessed consonants");

    Collection<Character> guessedVowels = puzzle.findGuessedChars(Letter.LETTER_TYPE.VOWEL);
    int numGuessedVowels = guessedVowels.size();
    assertEquals(0, numGuessedVowels, "Should be no guessed vowels");

    /*
     * Guess one consonant
     */
    puzzle.guessLetter('P');
    assertEquals(1, puzzle.findGuessedChars(Letter.LETTER_TYPE.CONSONANT).size(), "Should be one consonant");

    /*
     * Guess one vowel
     */
    puzzle.guessLetter('E');
    assertEquals(1, puzzle.findGuessedChars(Letter.LETTER_TYPE.VOWEL).size(), "Should be one guessed vowel");

    /*
     * Guess all the chars
     */
    for (char letter : letters.toCharArray()) {
      puzzle.guessLetter(letter);
    }

    /*
     * Counts of the founds should match the counts of the consonants and vowels
     */
    assertEquals(consonants.length(), puzzle.findGuessedChars(Letter.LETTER_TYPE.CONSONANT).size(), "Should be no guessed consonants");
    assertEquals(vowels.length(), puzzle.findGuessedChars(Letter.LETTER_TYPE.VOWEL).size(), "Should be no guessed vowels");

    /*
     * Check that each found consonant is among those we guessed
     */
    for (char guessedLetter : puzzle.findGuessedChars(Letter.LETTER_TYPE.CONSONANT)) {
      assertTrue(consonants.indexOf(guessedLetter) >= 0, String.format("'%c' should be in %s", guessedLetter, consonants));
    }

    /*
     * Check that each found vowel is among those we guessed
     */
    for (char guessedLetter : puzzle.findGuessedChars(Letter.LETTER_TYPE.VOWEL)) {
      assertTrue(vowels.indexOf(guessedLetter) >= 0, String.format("'%c' should be in %s", guessedLetter, vowels));
    }

  }
}
