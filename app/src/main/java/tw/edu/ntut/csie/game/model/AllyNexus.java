package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/4/1.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class AllyNexus extends Units
{
    public AllyNexus(int x, int y)
    {
        _health = 500;
        _currentHeath = _health;
        _x = x;
        _y = y;

        _movingActive = new Animation();
        _movingActive.setLocation(_x, _y);
        _movingActive.addFrame(R.drawable.ally_nexus);
        _movingActive.setDelay(0);
        _movingActive.setRepeating(false);

        _knockedBackActive = new Animation();
        _knockedBackActive.setLocation(_x, _y);
        _knockedBackActive.addFrame(R.drawable.ally_nexus);
        _knockedBackActive.addFrame(R.drawable.ally_nexus_attacked);
        _knockedBackActive.setDelay(0);
    }

    public void Moving()
    {

    }

    public void Attack()
    {

    }

    public void Attacked(int damage)
    {
        _currentHeath -= damage;

        if (_currentHeath <= 0)
        {
            Dying();
        }
        SetIsAttacked(true);
    }

    public void KnockedBack()
    {
        _knockedBackActive.move();
    }

    public void Dying()
    {
        _movingActive.setLocation(_x, _y - 100);
        _isDying = true;
    }

    public void Show()
    {
        _movingActive.show();
        _knockedBackActive.show();
    }
}