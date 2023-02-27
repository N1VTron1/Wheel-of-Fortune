package wheeloffortune;

import controller.Game;

public class WheelOfFortune {

  public static void main(String[] args) {
    // TODO 00 - [WheelOfFortune.java] Put correct info, and list students on the team

    System.out.println("Course: IST 311");
    System.out.println("Assignment: TD02");
    System.out.println("Team Number: 3");

    /*
     * List the students on the team (name, email, github account)
     */
    System.out.println("• Anthony Biletsky (email: afb5227@psu.edu; github: abiletsky)");
    System.out.println("• Tayler Dove (email: tvd3@psu.edu; github: tvd3)");
    System.out.println("• Nivaldo Acevedo (email: naa5453@psu.edu; github: N1VTron1)");
    System.out.println("• Yihang Liu (email: yvl5590@psu.edu; github: yihang314)");


    Game game = new Game();
    game.playGame();
  }

}
