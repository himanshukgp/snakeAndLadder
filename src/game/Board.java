package game;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();

    public Board(int size) {
        this.size = size;
    }

    public void addSnake(int head, int tail) {
        snakes.put(head, tail);
    }

    public void addLadder(int start, int end) {
        ladders.put(start, end);
    }

    public void movePlayer(Player player, int roll) {
        int pos = player.getPosition() + roll;
        if (pos > size) return;


        if (snakes.containsKey(pos)) {
            System.out.println(" 🐍 Snake! Down from " + pos + " to " + snakes.get(pos));
            pos = snakes.get(pos);
        } else if (ladders.containsKey(pos)) {
            System.out.println(" 🪜 Ladder! Up from " + pos + " to " + ladders.get(pos));
            pos = ladders.get(pos);
        }

        player.setPosition(pos);
    }
}
