package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.R;
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
        _x = x;
        _y = y;
        _attackDamage = 10;
        _moveSpeed = 8;
        _attackSpeed = 1;

        _movingActive = new Animation();
        _movingActive.setLocation(_x, _y);
        _movingActive.addFrame(R.drawable.test_capoo);
        _movingActive.addFrame(R.drawable.capoo_attack);
        _movingActive.setDelay(4);
    }

    public void Moving()
    {
        _x -= _moveSpeed;
        _movingActive.setLocation(_x, _y);
        _movingActive.move();
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
        _movingActive.show();
    }
}