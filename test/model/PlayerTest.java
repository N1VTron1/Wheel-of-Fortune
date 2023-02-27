package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  @Test
  void addToTotalWinnings__add_to_winnings__total_is_correct() {
    Player player = new Player(1);

    assertEquals(0, player.getTotalWinnings());

    player.setPuzzleWinnings(3);
    player.addToTotalWinnings();
    assertEquals(3, player.getTotalWinnings());

    player.setPuzzleWinnings(5);
    player.addToTotalWinnings();
    assertEquals(8, player.getTotalWinnings());

  }

  @Test
  void addToPuzzleWinnings__add_to_winnings__total_is_correct() {
    Player player = new Player(1);

    assertEquals(0, player.getPuzzleWinnings());

    player.addToPuzzleWinnings(3);
    assertEquals(3, player.getPuzzleWinnings());

    player.addToPuzzleWinnings(5);
    assertEquals(8, player.getPuzzleWinnings());
  }

  @Test
  void subtractFromPuzzleWinnings__add_to_winnings__total_is_correct() {
    Player player = new Player(1);

    assertEquals(0, player.getPuzzleWinnings());

    player.setPuzzleWinnings(10);

    player.subtractFromPuzzleWinnings(2);
    assertEquals(8, player.getPuzzleWinnings());

    player.subtractFromPuzzleWinnings(0);
    assertEquals(8, player.getPuzzleWinnings());

    player.subtractFromPuzzleWinnings(8);
    assertEquals(0, player.getPuzzleWinnings());
  }
}