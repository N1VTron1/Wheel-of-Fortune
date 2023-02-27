package view;

import model.Player;

public class PlayerView {
  public static void readName(Player player) {

    System.out.printf("Name of player #%d: ", player.getNumber());
    player.setName(Keyboard.scanner.nextLine());
  }
}
