package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.extend.Integer;

/**
 * Created by User on 2017/4/2.
 */

public class Money
{
    private Integer _currentInteger;
    private Integer _maxInteger;
    private int _currentMoney;
    private int _maxMoney;
    public static int INITIAL_MAX_MONEY = 1000;
    public static int SPEED = 10;
    public static int MONEY_MAX_ADD_SCALE = 100;

    public Money()
    {
        _maxMoney = INITIAL_MAX_MONEY;
        _currentInteger = new Integer(4, _currentMoney, 450, 10);
        _maxInteger = new Integer(4, _maxMoney, 550, 10);
        _currentMoney = 0;
    }

    //產兵扣錢
    public void SubtractMoney(int cost)
    {
        _currentMoney -= cost;
    }

    //增加錢的最大值
    public void AddMoneyMax()
    {
        if (_currentMoney > 200)
        {
            _maxMoney += MONEY_MAX_ADD_SCALE;
            _currentMoney -= MONEY_MAX_ADD_SCALE;
        }
    }

    //產錢
    public void AddMoney()
    {
        if (_currentMoney < _maxMoney)
        {
            _currentMoney += SPEED;
        }
    }

    public int GetCurrentMoney()
    {
        return _currentMoney;
    }

    public void show()
    {
        _currentInteger.setValue(_currentMoney);
        _maxInteger.setValue(_maxMoney);
        _currentInteger.show();
        _maxInteger.show();
    }
}