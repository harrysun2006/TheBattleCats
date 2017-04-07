package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/3/20.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class EnemyNexus extends Units
{
    public EnemyNexus(int x, int y, int shiftedX)
    {
        _health = 500;
        _currentHeath = _health;
        _x = x;
        _y = y;
        _displayX = _x - shiftedX;
        _displayY = _y;

        _movingActive = new Animation();
        _movingActive.setLocation(_displayX, _displayY);
        _movingActive.addFrame(R.drawable.door);
        _movingActive.setDelay(0);
        _movingActive.setRepeating(false);

        _knockedBackActive = new Animation();
        _knockedBackActive.setLocation(_displayX, _displayY);
        _knockedBackActive.addFrame(R.drawable.door);
        _knockedBackActive.addFrame(R.drawable.door_attacked);
        _knockedBackActive.setDelay(1);
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
        _movingActive.setLocation(_displayX, _displayY - 100);
        _isDying = true;
    }

    public void Show()
    {
        _movingActive.setLocation(_displayX, _displayY);
        _movingActive.show();
        _knockedBackActive.setLocation(_displayX, _displayY);
        _knockedBackActive.show();
    }
}