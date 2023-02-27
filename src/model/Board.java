package model;

import java.util.Collection;

public class Board {
  private Puzzle puzzle;
  private Collection<Player> players;

  public Board(Puzzle puzzle, Collection<Player> players) {
    this.puzzle = puzzle;
    this.players = players;
  }

  public Puzzle getPuzzle() {
    return puzzle;
  }

  public Collection<Player> getPlayers() {
    return players;
  }
}
