package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.extend.Integer;

/**
 * Created by User on 2017/4/2.
 */

public class Money
{
    private Integer _curentInteger;
    private Integer _maxInteger;
    private int _maxMoney;
    private int _currentMoney;
    private int _speed;

    public Money()
    {
        _curentInteger = new Integer(4, _currentMoney, 450, 10);
        _maxInteger = new Integer(4, _maxMoney, 550, 10);
        _maxMoney = 1000;
        _currentMoney = 0;
        _speed = 10;
    }

    //產兵扣錢
    public boolean SubtractMoney(int cost)
    {
        if (_currentMoney >= cost)
        {
            _currentMoney -= cost;
            return true;
        }
        return false;
    }

    //增加錢的最大值
    public void AddMoneyMax()
    {
        _maxMoney += 100;
        _currentMoney -= 50;
    }

    public void AddMoney()
    {
        if (_currentMoney < _maxMoney)
        {
            _currentMoney += _speed;
        }
    }

    public void show()
    {
        _curentInteger.setValue(_currentMoney);
        _maxInteger.setValue(_maxMoney);

        _curentInteger.show();
        _maxInteger.show();
    }
}