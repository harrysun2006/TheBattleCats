package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/28.

public class Score
{
    private int _enemyKilled;

    public Score()
    {
    }

    public void IncreaseEnemyKilled()
    {
        _enemyKilled++;
    }

    public int GetScore()
    {
        return _enemyKilled * 100;
    }
}