package org.example.course.assignments.operators;

public class ScoreCalculator {

    public int evaluateScore(int base, int bonus, boolean hasPenalty) {
        int result = base + bonus - (hasPenalty ? 3 : 0);
        return Math.max(result, 0);
    }
}
