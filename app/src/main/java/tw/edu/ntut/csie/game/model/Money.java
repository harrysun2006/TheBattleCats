package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.extend.Integer;

/**
 * Created by User on 2017/4/2.
 */

public class Money
{
    public Integer _integer;
    public int _maxMoney;
    public int _currentMoney;
    public int _speed;

    public Money()
    {
        _integer = new Integer(4, _currentMoney, 550, 10);
        _maxMoney = 1000;
        _currentMoney = 0;
        _speed = 10;
    }

    //產兵扣錢
    public void SubtractMoney(int cost)
    {
        _currentMoney -= cost;
    }

    //增加錢的最大值
    public void AddMoneyMax()
    {
        _maxMoney += 100;
    }

    public void AddMoney()
    {
        _currentMoney += _speed;
    }

    public void show()
    {
        _integer.setValue(_currentMoney);
        _integer.show();
    }
}