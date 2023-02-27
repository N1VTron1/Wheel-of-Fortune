package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LetterTest {

  @Test
  void isVowel__pass_vowel__return_true() {
    char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    for (char vowel : vowels) {
      assertTrue(Letter.isVowel(vowel));
    }
  }

  @Test
  void isVowel__pass_nonvowel__return_false() {
    char[] vowels = {'_', 'z', '0', '9'};
    for (char vowel : vowels) {
      assertFalse(Letter.isVowel(vowel));
    }
  }

  @Test
  void isConsonant__pass_consonant__returns_true () {
    for (char ch : "bcdfghjklmnpqrstvwxyz".toCharArray()) {
      char toUpper = Character.toUpperCase(ch);
      assertTrue(Letter.isConsonant(toUpper), "Testing " + toUpper);
      char toLower = Character.toLowerCase(ch);
      assertTrue(Letter.isConsonant(toLower), "Testing " + toLower);
    }
  }

  @Test
  void isConsonant__pass_nonconsonant__returns_false () {
    for (char ch : "aeiou1@*".toCharArray()) {
      char toUpper = Character.toUpperCase(ch);
      assertFalse(Letter.isConsonant(toUpper), "Testing " + toUpper);
      char toLower = Character.toLowerCase(ch);
      assertFalse(Letter.isConsonant(toLower), "Testing " + toLower);
    }
  }
}
