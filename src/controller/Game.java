package controller;

import edu.psu.consolemenu.Menu;
import edu.psu.consolemenu.MenuChoice;
import edu.psu.consolemenu.MenuDisplay;
import model.*;
import model.Letter.LETTER_TYPE;
import view.BoardView;
import view.Keyboard;
import view.PlayerView;
import view.PuzzleView;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static controller.Game.GAME_STATUS.CONTINUE;
import static controller.Game.TURN_STATUS.*;
import static model.Letter.LETTER_TYPE.*;
import static model.Player.PLAYER_STATUS.ACTIVE;

public class Game {
    enum GAME_STATUS {
        CONTINUE,
        FINISHED
    }

    enum TURN_STATUS {
        GO_AGAIN,
        DONE_TURN,
        SOLVED_PUZZLE
    }

    private static final int MAX_PLAYERS = 3;
    private static final int COST_OF_VOWEL = 250;
    List<Player> players = new ArrayList<>();
    Wheel wheel = new Wheel();

    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<Player>();

        for (int i = 1; i <= MAX_PLAYERS; i++) {
            players.add(new Player(i));
        }

        return players;
    }

    private void readPlayerNames(Collection<Player> players) {
        PuzzleView.displayMessageLine();
        PuzzleView.displayMessageLine("Enter Player Names");
        for (Player p : players) {
            PlayerView.readName(p);
        }
        PuzzleView.displayMessageLine();
    }

    private Puzzle choosePuzzle() {
        String[] puzzleStrings = {
                "PENN STATE ABINGTON",
                "I HATE CORONAVIRUS",
                "PSU",
                "PENN STATE"
        };

        // TODO 01 - [Game.java] choosePuzzle returns a random puzzle
        int randomInt = ThreadLocalRandom.current().nextInt(puzzleStrings.length);

        return new Puzzle(puzzleStrings[randomInt]);
    }

    public void playGame() {
        // TODO 07 - [Game.java] playGame must have the "Wheel of Fortune" menu choices
        /*
         *  .------------------.
         *  | Wheel of Fortune |
         *  '------------------'
         *  1) Play
         *  2) Quit
         *  Choice:
         *
         */

        /*
         * I have placeholders here for the variables you'll need
         * Until you have this working, you'll have errors when you run the
         * program.
         * To get the menus working, you should not need to change anything beyond
         * this group of lines.
         */
        Menu muGame = new Menu("Wheel of Fortune");
        MenuChoice mcPlay = muGame.addMenuChoice("Play");
        MenuChoice mcQuit = muGame.getMenuChoiceQuit();
        MenuDisplay mdGame = new MenuDisplay(muGame);


        players = createPlayers();
        readPlayerNames(players);

        int gamesPlayed = 0;
        MenuChoice mcChosen;
        do {
            mcChosen = mdGame.displayAndChoose();
            if (mcChosen == mcPlay) {
                gamesPlayed++;
                playPuzzle();
            }
        } while (mcChosen != mcQuit);

        if (gamesPlayed > 0) {
            showOverallResults();
        }

    }

    private void resetAllPlayersForNewPuzzle() {
        for (Player p : players) {
            p.setPuzzleWinnings(0);
            p.setStatus(ACTIVE);
        }
    }

    public void playPuzzle() {

        Puzzle puzzle = choosePuzzle();
        Board board = new Board(puzzle, players);
        BoardView boardView = new BoardView(board);

        int playerNum = 0;
        TURN_STATUS turnStatus = null;
        int inactivePlayerCount = 0;
        GAME_STATUS gameStatus = CONTINUE;
        resetAllPlayersForNewPuzzle();

        while ((gameStatus == CONTINUE) && (turnStatus != SOLVED_PUZZLE)) {
            Player player = players.get(playerNum);
            if (player.getStatus() == Player.PLAYER_STATUS.INACTIVE) {
                PuzzleView.displayMessageLine(String.format("Skipping %s because inactive", player.getName()));
                turnStatus = DONE_TURN;
                inactivePlayerCount++;
                if (inactivePlayerCount == players.size()) {
                    PuzzleView.displayMessageLine("NO MORE ACTIVE PLAYERS!  NO WINNER!");
                    gameStatus = GAME_STATUS.FINISHED;
                }
            } else {
                turnStatus = playTurn(boardView, puzzle, player);
            }
            switch (turnStatus) {
                case DONE_TURN:
                    playerNum = (playerNum + 1) % MAX_PLAYERS;
                    break;
                case GO_AGAIN:
                    PuzzleView.displayMessage("%s gets to go again!%n%n", player.getName());
                    break;
                case SOLVED_PUZZLE:
                    showResults(puzzle, player);
                    player.addToTotalWinnings();
                    break;
            }
        }
    }

    private void showResults(Puzzle puzzle, Player player) {
        System.out.println();
        System.out.printf("%s IS THE WINNER!%n", player.getName());
        System.out.printf("The puzzle was: %s%n", PuzzleView.toTextUnmasked(puzzle));
        System.out.printf("PUZZLE WINNINGS: $%d%n", player.getPuzzleWinnings());
        System.out.printf("TOTAL WINNINGS: $%d%n", player.getTotalWinnings());
        System.out.println();
    }

    private void showOverallResults() {
        Player winner = null;
        int highestWinnings = 0;

        PuzzleView.displayMessageLine();
        PuzzleView.displayMessageLine("OVERALL GAME RESULTS:");
        for (Player p : players) {
            int totalWinnings = p.getTotalWinnings();
            String msg = String.format("%s: $%d", p.getName(), totalWinnings);
            PuzzleView.displayMessageLine(msg);

            // BUG: We don't consider tie scores.  That's fine.
            if (totalWinnings > highestWinnings) {
                highestWinnings = totalWinnings;
                winner = p;
            }

        }

        /*
         * Could be no winner if all players used their very first turn to guess
         * the puzzle, and were incorrect.
         */
        if (winner != null) {
            PuzzleView.displayMessageLine("The game winner is... " + winner.getName());
        }
    }

    private char readLetter(LETTER_TYPE letterType) {
        // TODO 11 - [Game.java] readLetter will return either a vowel or a consonant (depending on the parameter)
        /*
         * I'm giving these two lines as the starting point
         */
        char guessChar = ' ';
        String messageToDisplay = "Guess a " + letterType + ": ";
        String errorMessageToDisplay = "MUST ENTER A " + letterType;
        System.out.print(messageToDisplay);
        String userInput = Keyboard.scanner.nextLine();
        boolean isValid = false;

        while (!isValid){
            if (userInput.trim().length() != 0){
                if (letterType == CONSONANT && Letter.isConsonant(userInput.charAt(0))){
                    isValid = true;
                } else if (letterType == VOWEL && Letter.isVowel(userInput.charAt(0))){
                    isValid = true;
                }
            } if (!isValid){
                System.out.println(errorMessageToDisplay);
                System.out.print(messageToDisplay);
                userInput = Keyboard.scanner.nextLine();
            } else {
                guessChar = userInput.charAt(0);
            }
        }

        return guessChar;
    }

    private TURN_STATUS handleSpin(Puzzle puzzle, Player player) {
        Wedge wedge = wheel.spin();
        System.out.printf("%nWheel landed on: %s%n%n", wedge);
        TURN_STATUS turnStatus = DONE_TURN;

        switch (wedge.getWedgeType()) {
            case LOSE_TURN:
                break;
            case BANKRUPT:
                player.setPuzzleWinnings(0);
                break;
            case MONEY:
                char guessChar = readLetter(CONSONANT);

                Puzzle.GUESS_STATUS guessStatus = puzzle.guessAndRevealLetter(guessChar);
                switch (guessStatus) {
                    case CORRECT:
                        System.out.println("RIGHT!!");
                        // TODO 06 - [Game.java] handleSpin will adjust the puzzle winnings
                        // Tayler Dove (email: tvd3@psu.edu; github: tvd3)
                        player.addToPuzzleWinnings(wedge.getValue());

                        turnStatus = GO_AGAIN;
                        break;
                    case INCORRECT:
                        System.out.printf("Letter '%c' is not in the puzzle%n", guessChar);
                        break;
                    case ALREADY_GUESSED:
                        System.out.printf("Letter '%c' was already guessed%n", guessChar);
                        break;
                }
                break;
        }

        return turnStatus;
    }

    private TURN_STATUS handleVowel(Puzzle puzzle, Player player) {
        TURN_STATUS turnStatus = null;

        if (player.getPuzzleWinnings() < COST_OF_VOWEL) {
            System.out.println("Not enough money");
            turnStatus = GO_AGAIN;
        } else {
            player.subtractFromPuzzleWinnings(COST_OF_VOWEL);
            char guessChar = readLetter(VOWEL);
            Puzzle.GUESS_STATUS guessStatus = puzzle.guessAndRevealLetter(guessChar);
            System.out.println(guessStatus);
            switch (guessStatus) {
                case CORRECT:
                    turnStatus = GO_AGAIN;
                    break;
                case INCORRECT:
                case ALREADY_GUESSED:
                    turnStatus = DONE_TURN;
            }
        }

        return turnStatus;
    }

    private TURN_STATUS handleSolve(Puzzle puzzle, Player player) {
        TURN_STATUS turnStatus = null;

        System.out.println("Type your guess for the puzzle solution: ");
        String guess = Keyboard.scanner.nextLine();
        String solution = puzzle.toString();
        if (solution.equals(guess)) {
            turnStatus = SOLVED_PUZZLE;
        } else {
            PuzzleView.displayMessageLine("INCORRECT! YOU ARE OUT FOR THE REST OF THIS PUZZLE!");
            player.setStatus(Player.PLAYER_STATUS.INACTIVE);
            turnStatus = DONE_TURN;
        }

        return turnStatus;

    }

    private TURN_STATUS playTurn(BoardView boardView, Puzzle puzzle, Player player) {
        // DO NOT CHANGE THIS LINE
        Menu muPlayerTurn = new Menu(String.format("%s's Turn", player.getName()), false);

        // TODO 05 - [Game.java] playTurn must have the player "Turn" menu choices
        // Tayler Dove (email: tvd3@psu.edu; github: tvd3)
        /*
         *  .--------------.
         *  | Larry's Turn |
         *  '--------------'
         *  1) Spin
         *  2) Buy a Vowel
         *  3) Solve the Puzzle
         *  Choice:
         *
         */

        /*
         * NOTE that I already have the player name in the title from when I
         * created the Menu object for you.
         *
         * I have placeholders here for the variables you'll need
         * Until you have this working, you'll have errors when you run the
         * program.
         * To get the menus working, you should not need to change anything beyond
         * this group of lines.
         */
        MenuChoice mcSpin = muPlayerTurn.addMenuChoice("Spin");
        MenuChoice mcVowel = muPlayerTurn.addMenuChoice("Buy a Vowel");
        MenuChoice mcSolve = muPlayerTurn.addMenuChoice("Solve the Puzzle");
        MenuDisplay mdGame = new MenuDisplay(muPlayerTurn);


        System.out.println(boardView);

        MenuChoice mcChoice = mdGame.displayAndChoose();

        TURN_STATUS turnStatus = null;
        if (mcChoice == mcSpin) {
            turnStatus = handleSpin(puzzle, player);
        } else if (mcChoice == mcVowel) {
            turnStatus = handleVowel(puzzle, player);
        } else if (mcChoice == mcSolve) {
            turnStatus = handleSolve(puzzle, player);
        }

        /*
         * Either the player solved the puzzle by guessing it (the turn status
         * would tell us.
         * Or, the puzzle is solved because the player guessed all of the letters.
         */
        if ((turnStatus == SOLVED_PUZZLE) || puzzle.isSolved()) {
            turnStatus = SOLVED_PUZZLE;
        }

        return turnStatus;
    }
}
