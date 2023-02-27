package view;

import model.Board;
import model.Player;
import model.Puzzle;

public class BoardView {
  // New Line
  private static final String NL = System.getProperty("line.separator");

  private Board board;

  public BoardView(Board board) {
    this.board = board;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("SCORES:" + NL);
    for (Player p : board.getPlayers()) {
      sb.append(String.format("%s: $%d ($%d total for game)%n", p.getName(), p.getPuzzleWinnings(), p.getTotalWinnings()));
    }

    sb.append(NL);

    Puzzle puzzle = board.getPuzzle();
    sb.append(NL + "PUZZLE:" + NL);
    sb.append(PuzzleView.toTextMasked(puzzle));

    sb.append(NL + "GUESSED CONSONANTS: ");
    sb.append(PuzzleView.guessedConsonants(puzzle));

    sb.append(NL + "GUESSED VOWELS: ");
    sb.append(PuzzleView.guessedVowels(puzzle));

    return sb.toString();
  }
}
