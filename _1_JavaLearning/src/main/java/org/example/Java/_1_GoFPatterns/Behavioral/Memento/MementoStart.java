package org.example.Java._1_GoFPatterns.Behavioral.Memento;

class GameMemento {
    private final String level;
    private final int score;

    public GameMemento(String level, int score) {
        this.level = level;
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }
}

class Game {
    private String level;
    private int score;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public GameMemento save() {
        return new GameMemento(level, score);
    }

    public void restore(GameMemento memento) {
        level = memento.getLevel();
        score = memento.getScore();
    }
}


public class MementoStart {
    public static void main(String[] args) {
        Game game = new Game();


        game.setLevel("Level 1");
        game.setScore(100);
        GameMemento memento = game.save();

        game.setLevel("Level 2");
        game.setScore(200);


        game.restore(memento);

        System.out.println(game.getLevel());
        System.out.println(game.getScore());
    }
}
