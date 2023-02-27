package model;

import model.Letter.LETTER_TYPE;

import java.util.*;

public class Puzzle {

  public enum GUESS_STATUS {
    ALREADY_GUESSED,
    CORRECT,
    INCORRECT
  }

  // A puzzle is made up of a list of words
  private List<Word> words = new ArrayList<>();

  // So we can remember what letters have been guessed
  private Set<Character> guessedLetters = new HashSet<>();

  /*
   * This method will return a list of Characters.
   *
   * • If the target letter type is CONSONANT, then that list will have the
   *   consonants that have been guessed in the puzzle.
   *
   * • If the given letter type is VOWEL, then that list will have the
   *   vowels that have been guessed in the puzzle.
   *
   * NOTE -- It does not matter what the puzzle is.  We are only concerned
   *         about what has been guessed.  That does not rely on the text of
   *         the puzzle at all.
   *         e.g., if the puzzle is "PENN STATE" or it's "FOOBAR".  That has no
   *         effect on how this method is supposed to work.
   */
  public List<Character> findGuessedChars(LETTER_TYPE letterType) {
    // TODO 12 - [Puzzle.java] findGuessedChars should return a list of the guess chars (either consonants or vowels)
    List<Character> guessedChars = new ArrayList<>();

    for (char letter : guessedLetters) {
      if (letterType == LETTER_TYPE.VOWEL && Letter.isVowel(letter))
        guessedChars.add(letter);
      else if (letterType == LETTER_TYPE.CONSONANT && Letter.isConsonant(letter))
          guessedChars.add(letter);
    }

    return guessedChars;
  }

  public List<Character> getGuessedConsonants() {
    return findGuessedChars(LETTER_TYPE.CONSONANT);
  }

  public List<Character> getGuessedVowels() {
    return findGuessedChars(LETTER_TYPE.VOWEL);
  }

  public Puzzle(String phrase) {

    for (String word : phrase.split("\\s+")) {
      this.words.add(new Word(word));
    }

  }


  public Collection<Letter> getAllLetters() {
    List<Letter> allLetters = new ArrayList<>();

    for (Word w : words) {
      allLetters.addAll(w.getLetters());
    }

    return allLetters;
  }

  private GUESS_STATUS guessLetter(char guessChar, boolean reveal) {

    if (guessedLetters.contains(guessChar)) {
      return GUESS_STATUS.ALREADY_GUESSED;
    }

    //   If param "reveal" is true, then unhide those letters
    GUESS_STATUS status = GUESS_STATUS.INCORRECT;
    for (Letter l : findLetters(guessChar)) {
      status = GUESS_STATUS.CORRECT;
      if (reveal) {
        l.setHidden(false);
      } else {
        break;
      }
    }

    guessedLetters.add(guessChar);

    return status;
  }

  public GUESS_STATUS guessLetter(char guessChar) {
    return guessLetter(guessChar, false);
  }

  public GUESS_STATUS guessAndRevealLetter(char guessChar) {
    return guessLetter(guessChar, true);
  }

  public int countLetter(char ch) {
    // TODO 09 - [Puzzle.java] countLetter returns the number of occurrences of that letter in the puzzle
    int count = 0;

    for (Word word : words){
      for (Letter letter : word.getLetters()) {
        if(letter.getValue() == ch)
          count++;
      }
    }

    return count;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Word w : words) {
      /*
       * Separate words by one space character.
       * Yes, we have a needless space at the beginning.  But we trime that off
       * when returning value;
       */
      sb.append(' ');
      sb.append(w);
    }

    // DO NOT EDIT THIS LINE
    return sb.toString().trim();
  }

  public Iterable<Word> getWords() {
    return words;
  }

  public Iterable<Letter> findLetters(char guess) {
    List<Letter> foundLetters = new ArrayList<>();

    for (Letter l : getAllLetters()) {
      if (l.getValue() == guess) {
        foundLetters.add(l);
      }
    }
    return foundLetters;
  }

  public boolean isSolved() {
    // TODO 10 - [Puzzle.java] isSolved should return true if none of the letters are hidden anymore
    for (Word word : words){
      for (Letter letter : word.getLetters()){
        if (letter.isHidden())
          return false;
      }
    }

    return true;
  }

}
