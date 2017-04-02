package tw.edu.ntut.csie.game.model;

/**
 * Created by User on 2017/4/2.
 */

public class Money
{
    public  int _maxMoney;
    public  int _currentMoney;

    public Money()
    {
        _maxMoney =1000;
        _currentMoney = 0;
    }

    //產兵扣錢
    public void SubtractMoney(int cost)
    {
        _currentMoney -= cost;
    }

    //增加錢的最大值
    public  void AddMoneyMax()
    {
        _maxMoney += 100;
    }

}
