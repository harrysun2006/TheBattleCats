package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.extend.Integer;

/**
 * Created by User on 2017/4/2.
 */

public class Money
{
    public int _maxMoney;
    public int _currentMoney;
    public Integer integer = new Integer(4,_currentMoney,700,300);

    public Money()
    {
        _maxMoney =1000;
        _currentMoney = 10;
    }

    //產兵扣錢
    public void SubtractMoney(int cost)
    {
        _currentMoney -= cost;
        integer.setValue(_currentMoney);
    }

    //增加錢的最大值
    public void AddMoneyMax()
    {
        _maxMoney += 100;
    }

    public void show()
    {
        integer.setValue(_currentMoney);
        integer.show();
    }
}
