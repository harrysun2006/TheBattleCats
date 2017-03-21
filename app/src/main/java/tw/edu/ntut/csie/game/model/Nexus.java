package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/3/20.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Nexus extends Units
{
    public Nexus(int x, int y)
    {
        _health = 100;
        _currentHeath = _health;
        _x = x;
        _y = y;

        _movingActive = new Animation();
        _movingActive.setLocation(_x, _y);
        _movingActive.addFrame(R.drawable.door);
        _movingActive.setDelay(0);

        _knockedBackActive = new Animation();
        _knockedBackActive.setLocation(_x, _y);
        _knockedBackActive.addFrame(R.drawable.door);
        _knockedBackActive.addFrame(R.drawable.apple);
        _knockedBackActive.setDelay(3);
    }

    public void Moving()
    {

    }

    public void Attack()
    {

    }

    public void Attacked(int damage)
    {
        if (_currentHeath - damage <= 0)
        {
            Died();
        }
        else
        {
            _currentHeath -= damage;
        }
    }

    protected void KnockedBack()
    {
        _knockedBackActive.move();
        _knockedBackActive.move();
    }

    protected void Died()
    {
        _movingActive.setLocation(_x, _y + 100);
    }

    public void Show()
    {
        _movingActive.show();
        _knockedBackActive.show();
    }
}