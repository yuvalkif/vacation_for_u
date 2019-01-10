package dbObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rank {

    private double averageScore;
    private String lastTimeRanked;
    private double rankersCount;
    private double totalScore;


    public Rank() {
        this.lastTimeRanked = "never";
        this.rankersCount = 0;
        this.totalScore = 0;
        this.averageScore = 0;
    }

    public void addRanker(double score){
        rankersCount++;
        totalScore+=score;
        averageScore = totalScore/rankersCount;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.lastTimeRanked = LocalDateTime.now().format(formatter);
    }

    public String getLastTimeRanked() {
        return lastTimeRanked;
    }

    public double getRankersCount() {
        return rankersCount;
    }

    public double getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "averageScore=" + averageScore +
                ", lastTimeRanked='" + lastTimeRanked + '\'' +
                ", rankersCount=" + rankersCount +
                '}';
    }
}
