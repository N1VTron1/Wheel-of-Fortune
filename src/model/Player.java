package model;

public class Player {

  public enum PLAYER_STATUS {
    ACTIVE,
    INACTIVE
  }

  private String name;
  private int number;

  private int puzzleWinnings;
  private int totalWinnings;

  PLAYER_STATUS status = PLAYER_STATUS.ACTIVE;

  public Player(int number) {
    this.number = number;
  }

  public void setStatus(PLAYER_STATUS status) {
    this.status = status;
  }

  public PLAYER_STATUS getStatus() {
    return status;
  }

  public int getNumber() {
    return this.number;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public int getPuzzleWinnings() {
    return puzzleWinnings;
  }

  public void setPuzzleWinnings(int puzzleWinnings) {
    this.puzzleWinnings = puzzleWinnings;
  }

  public void addToPuzzleWinnings(int moreWinnings) {
    // TODO 02 - [Player.java] addToPuzzleWinnings adds given value from puzzle total
    setPuzzleWinnings(getPuzzleWinnings() + moreWinnings);

  }

  public void subtractFromPuzzleWinnings(int lessWinnings) {
    // TODO 03 - [Player.java] subtractFromPuzzleWinnings deducts given value from puzzle total
    setPuzzleWinnings(getPuzzleWinnings() - lessWinnings);

  }

  public int getTotalWinnings() {
    return totalWinnings;
  }

  public void addToTotalWinnings() {
    // TODO 04 - [Player.java] addToTotalWinnings add puzzle winnings to the total
    totalWinnings = getTotalWinnings()+getPuzzleWinnings();
  }


}
