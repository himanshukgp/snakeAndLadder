package game;

import java.io.*;
import java.util.*;

public class SnakeAndLadder {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        Board board = new Board(100);
        Dice dice = new Dice();

        try {
            File file = new File("src/input.txt");  // Adjust if needed
            Scanner scanner = new Scanner(file);

            int snakesNum=scanner.nextInt();
            for(int i=0;i<snakesNum;i++){
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                board.addSnake(x,y);
            }
            int ladderNum=scanner.nextInt();
            for(int i=0;i<ladderNum;i++){
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                board.addLadder(x,y);
            }
            int numOfPlayers=scanner.nextInt();
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String a = scanner.nextLine().trim();
                System.out.println(a);
                players.add(new Player(a));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found!");
            return;
        }

        boolean gameOver = false;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("src/output.txt"));
            while (!gameOver) {
                for (Player player : players) {
                    int roll = dice.roll();
                    writer.print(player.getName() + " rolled a " + roll);
                    writer.print(" and moved from " + player.getPosition());
                    board.movePlayer(player, roll);
                    writer.println(" to " + player.getPosition());

                    if (player.getPosition() == 100) {
                        writer.println("🎉 " + player.getName() + " wins!");
                        gameOver = true;
                        break;
                    }
                }

                writer.println("-----");

            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
