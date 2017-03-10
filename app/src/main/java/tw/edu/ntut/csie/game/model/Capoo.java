package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by User on 2017/3/10.
 */

public class Capoo extends Units
{
    public Capoo(int x, int y)
    {
        _health = 100;
        _currentHeath = 100;
        _attackDamage = 10;
        _moveSpeed = 10;
        _attackSpeed = 1;
        _appearance = new MovingBitmap(R.drawable.test_capoo, x, y);
    }

    public void Moving()
    {

    }

    public void Attack()
    {

    }

    public void Attacked()
    {

    }

    protected void knockedBack()
    {

    }

    protected void Died()
    {

    }

    public void Show()
    {
        _appearance.show();
    }
}