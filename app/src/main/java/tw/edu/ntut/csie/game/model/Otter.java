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
        super();

        _health = 100;
        _currentHeath = _health;
        _x = x;
        _y = y;
        _attackDamage = 50;
        _moveSpeed = -5;
        _attackSpeed = 1;

        InitializeMovingActive();
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
        _currentHeath -= damage;

        if (_currentHeath <= 0)
        {
            KnockedBack();
            SetIsDying(true);
        }
        else if (_currentHeath > _health / 2 && _health / 2 > _currentHeath - damage)
        {
            KnockedBack();
        }
    }

    public void KnockedBack()
    {

    }

    public void Dying()
    {
        super.Dying();
    }

    public void Show()
    {
        _movingActive.show();
        _dyingActive.show();
    }

    private void InitializeMovingActive()
    {
        _movingActive = new Animation();
        _movingActive.setLocation(_x, _y);
        _movingActive.addFrame(R.drawable.otter_move1);
        _movingActive.addFrame(R.drawable.otter_move2);
        _movingActive.addFrame(R.drawable.otter_move3);
        _movingActive.addFrame(R.drawable.otter_move3);
        _movingActive.addFrame(R.drawable.otter_move3);
        _movingActive.addFrame(R.drawable.otter_move2);
        _movingActive.addFrame(R.drawable.otter_move1);
        _movingActive.addFrame(R.drawable.otter_move1);
        _movingActive.setDelay(1);
    }
}