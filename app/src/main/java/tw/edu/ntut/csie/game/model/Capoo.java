package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by User on 2017/3/10.
 */

public class Capoo extends Units
{
    public Capoo(int x, int y, int shiftedX)
    {
        super();

        _health = 100;
        _currentHeath = _health;
        _x = x;
        _y = y;
        _displayX = _x - shiftedX;
        _displayY = _y;

        _attackDamage = 50;
        _moveSpeed = 5;
        _attackSpeed = 1;
        _cost = 100;

        InitializeMovingActive();
        InitializeAttackActive();
    }

    public void Moving()
    {
        _x -= _moveSpeed;
        _displayX -= _moveSpeed;
//        _movingActive.setLocation(_displayX, _displayY);
        _movingActive.move();
    }

    public void Attack()
    {
        SetIsAttacking(true);
        _attackActive.setLocation(_displayX, _displayY);
        _attackActive.move();
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
        _movingActive.setLocation(_displayX, _displayY);
        _movingActive.show();
        _attackActive.show();
        _dyingActive.show();
    }

    private void InitializeMovingActive()
    {
        _movingActive = new Animation();
        _movingActive.setLocation(_displayX, _displayY);
        _movingActive.addFrame(R.drawable.capoo_move1);
        _movingActive.addFrame(R.drawable.capoo_move2);
        _movingActive.setDelay(4);
    }

    private void InitializeAttackActive()
    {
        _attackActive = new Animation();
        _attackActive.setLocation(_displayX, _displayY);
        _attackActive.addFrame(R.drawable.capoo_attack1);
        _attackActive.addFrame(R.drawable.capoo_attack2);
        _attackActive.addFrame(R.drawable.capoo_attack3);
        _attackActive.setVisible(false);
        _attackActive.setDelay(1);
    }
}