package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by User on 2017/3/15.
 */

public class Otter extends Units
{
    public Otter(int x, int y)
    {
        _health = 100;
        _currentHeath = _health;
        _attackDamage = 10;
        _moveSpeed = -8;
        _attackSpeed = 1;
        _x = x;
        _y = y;

        _active = new Animation();
        _active.setLocation(_x, _y);
        _active.addFrame(R.drawable.sea);
        _active.addFrame(R.drawable.otter_attack);
        _active.setDelay(4);
    }

    public void Moving()
    {
        _x -= _moveSpeed;
        _active.setLocation(_x, _y);
        _active.move();
    }

    public void Attack()
    {

    }

    public void Attacked(int damage)
    {
        if (_currentHeath - damage <= 0)
        {
            KnockedBack();
            Died();
        }
        else if (_currentHeath > _health / 2 && _health / 2 > _currentHeath - damage)
        {
            KnockedBack();
            _currentHeath -= damage;
        }
    }

    protected void KnockedBack()
    {

    }

    protected void Died()
    {

    }

    public void Show()
    {
        _active.show();
    }
}