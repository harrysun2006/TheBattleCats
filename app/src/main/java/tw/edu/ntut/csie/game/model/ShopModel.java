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
        Money._maxMoney = Money._maxMoney + 100;
        Money.MONEYMAXADDSCALE = Money.MONEYMAXADDSCALE + 100;
    }

    //增加前的生產效率
    public void AddMoneySpeed()
    {
        Money.SPEED = Money.SPEED - 2;
    }

}
