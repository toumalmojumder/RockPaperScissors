package com.JG.rockpaperscissors.Model;

public class Score {
    int id;
    int timestamp;
    int win;
    int loss;
    int tie;

    public Score() {
    }

    public Score(int id, int timestamp, int win, int loss, int tie) {
        this.id = id;
        this.timestamp = timestamp;
        this.win = win;
        this.loss = loss;
        this.tie = tie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getTie() {
        return tie;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }


}
