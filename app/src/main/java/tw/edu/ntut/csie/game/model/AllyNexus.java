package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/4/1.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class AllyNexus extends Units
{
    private boolean _invincible;
    public static int HEALTH = 1000;

    public AllyNexus(int x, int y, int shiftedX)
    {
        _health = HEALTH;
        _currentHealth = _health;
        _x = x;
        _y = y;
        _displayX = _x - shiftedX;
        _displayY = _y;

        _invincible = false;

        _movingActive = new Animation();
        _movingActive.setLocation(_displayX, _displayY);
        _movingActive.addFrame(R.drawable.ally_nexus);
        _movingActive.setDelay(0);
        _movingActive.setRepeating(false);

        _knockedBackActive = new Animation();
        _knockedBackActive.setLocation(_displayX, _displayY);
        _knockedBackActive.addFrame(R.drawable.ally_nexus);
        _knockedBackActive.addFrame(R.drawable.ally_nexus_attacked);
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
        if (!_invincible)
        {
            _currentHealth -= damage;
        }
        if (_currentHealth <= 0)
        {
            _invincible = true; //血量歸零時獲得無敵狀態
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

    public void SetIsAttacking(boolean isAttacking)
    {
    }
}