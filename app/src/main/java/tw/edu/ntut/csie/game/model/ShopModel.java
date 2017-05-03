package tw.edu.ntut.csie.game.model;

/**
 * Created by User on 2017/4/28.
 */

public class ShopModel
{
    public ShopModel()
    {
    }

    //增加錢包初始最大值
    public void AddMoneyPocket()
    {
        Money.INITIAL_MAX_MONEY = Money.INITIAL_MAX_MONEY + 100;
        Money.MONEY_MAX_ADD_SCALE = Money.MONEY_MAX_ADD_SCALE + 100;
    }

    //增加錢的生產效率
    public void AddMoneySpeed()
    {
        Money.SPEED = Money.SPEED + 2;
    }
}