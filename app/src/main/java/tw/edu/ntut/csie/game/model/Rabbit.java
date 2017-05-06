package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/1.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Rabbit extends Units
{
    public static int COOLDOWN = 6; //cooldown time
    public static int COST = 200; //產兵花費

    public Rabbit(int x, int y, int shiftedX)
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
        _movingActive.addFrame(R.drawable.rabbit_move1);
        _movingActive.addFrame(R.drawable.rabbit_move2);
        _movingActive.addFrame(R.drawable.rabbit_move3);
        _movingActive.addFrame(R.drawable.rabbit_move4);
        _movingActive.addFrame(R.drawable.rabbit_move5);
        _movingActive.addFrame(R.drawable.rabbit_move6);
        _movingActive.setDelay(2);
    }

    private void InitializeAttackActive()
    {
        _attackActive = new Animation();
        _attackActive.setLocation(_displayX, _displayY);
        _attackActive.addFrame(R.drawable.rabbit_attack1);
        _attackActive.addFrame(R.drawable.rabbit_attack2);
        _attackActive.addFrame(R.drawable.rabbit_attack3);
        _attackActive.addFrame(R.drawable.rabbit_attack4);
        _attackActive.addFrame(R.drawable.rabbit_attack5);
        _attackActive.addFrame(R.drawable.rabbit_attack6);
        _attackActive.addFrame(R.drawable.rabbit_attack5);
        _attackActive.addFrame(R.drawable.rabbit_attack4);
        _attackActive.addFrame(R.drawable.rabbit_attack3);
        _attackActive.addFrame(R.drawable.rabbit_attack2);
        _attackActive.setVisible(false);
        _attackActive.setDelay(2);
    }
}