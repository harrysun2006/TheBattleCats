package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by User on 2017/3/10.
 */

public class Capoo extends Units
{
    public Capoo(int x, int y)
    {
        _health = 100;
        _currentHeath = _health;
        _attackDamage = 10;
        _moveSpeed = 10;
        _attackSpeed = 1;
        _x = x;
        _y = y;
        _delay = 20;
        _appearance = new MovingBitmap(R.drawable.test_capoo, _x, _y);
        //_appearancetwo = new MovingBitmap(R.drawable.capoo_attack,_x,_y);
        _active = new Animation();
        _active.setLocation(560, 310);
        _active.addFrame(R.drawable.test_capoo);
        _active.addFrame(R.drawable.capoo_attack);
        _active.setDelay(4);
    }

    public void Moving()
    {
        _x -= _moveSpeed;
        //_appearance.setLocation(_x, _y);
        _active.setLocation(_x,_y);
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
    public int GetDelay()
    {
        return (_delay);
    }

    public void SetDelay(int delay)
    {
        _delay = delay;
    }
}