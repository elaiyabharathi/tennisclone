import java.util.Scanner;

/**
 * Created by vedsharma on 04-Aug-16.
 */

class Score {
    String playerName;
    private int set;
    private int game;
    private String points;

    public Score(String playerName) {
        set=0;
        game=0;
        points="0";
        this.playerName=playerName;
    }

    public String getPlayerNAme() {
        return playerName;
    }

    public void setPlayer(String playerName) {
        this.playerName = playerName;
    }

    public String getPoints() {
        return points;
    }
    public void setPoints(String points) {
        this.points=points;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public String toString() {
        return playerName+":"+set+":"+game+":"+points;
    }
}

class Scoreboard {
    Score playerA;
    Score playerB;
    String winner;
    public Scoreboard() {
        playerA = new Score("Player A");
        playerB = new Score("Player B");
        winner="";
    }

    public void incrementSet(Score firstPlayer, Score secondPlayer) {
        int firstPlayerSet = firstPlayer.getSet();
        int secondPlayerSet = secondPlayer.getSet();
        firstPlayer.setGame(0);
        secondPlayer.setGame(0);
        switch (firstPlayerSet) {
            case 0:
                firstPlayer.setSet(1);
                break;
            case 1:
                firstPlayer.setSet(2);
                winner=firstPlayer.getPlayerNAme();
        }
    }

    public void incrementGame(Score firstPlayer, Score secondPlayer) {
        int firstPlayerGame = firstPlayer.getGame();
        int secondPlayerGame = secondPlayer.getGame();
        firstPlayer.setPoints("0");
        secondPlayer.setPoints("0");
        switch (firstPlayer.getGame()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                firstPlayer.setGame(firstPlayerGame+1); break;
            case 5:
                if(secondPlayerGame<=4)
                    incrementSet(firstPlayer,secondPlayer);
                else
                    firstPlayer.setGame(firstPlayerGame+1);
                break;
            case 6:
                incrementSet(firstPlayer,secondPlayer);
        }
    }

    public void incrementPoints(Score first, Score second) {
        String firstPoints = first.getPoints();
        String secondPoints = second.getPoints();
        switch (firstPoints) {
            case "0": first.setPoints("15"); break;
            case "15": first.setPoints("30"); break;
            case "30": first.setPoints("40"); break;
            case "40":
                if(secondPoints.equalsIgnoreCase("40"))
                    first.setPoints("A");
                else if(secondPoints.equalsIgnoreCase("A")) {
                    first.setPoints("40");
                    second.setPoints("40");
                }
                else
                    incrementGame(first,second);
                break;
            case "A":
                incrementGame(first,second);
        }
    }

    public void updateScoreboard(String scoringList) {
        System.out.println("------------------------------");
        for (char point: scoringList.toCharArray()) {
            if(point=='A')
                incrementPoints(playerA,playerB);
            else
                incrementPoints(playerB,playerA);
            System.out.println(playerA);
            System.out.println(playerB);
            System.out.println("------------------------------");
        }
    }
}

public class Match {
    public static void main(String[] args) {
        System.out.print("Enter scoring list : ");
        Scanner sc = new Scanner(System.in);
        String scoringList = sc.nextLine();
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.updateScoreboard(scoringList);
    }
}
