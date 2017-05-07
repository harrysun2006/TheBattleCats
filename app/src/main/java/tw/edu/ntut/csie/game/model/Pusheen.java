package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/4/17.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Pusheen extends Units
{
    public static int COOLDOWN = 6; //cooldown time
    public static int COST = 200; //產兵花費

    public Pusheen(int x, int y, int shiftedX)
    {
        super();

        _health = 100;
        _currentHealth = _health;
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
        _currentHealth -= damage;

        if (_currentHealth <= 0)
        {
            KnockedBack();
            SetIsDying(true);
        }
        else if (_currentHealth > _health / 2 && _health / 2 > _currentHealth - damage)
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
        _movingActive.addFrame(R.drawable.pusheen_move1);
        _movingActive.addFrame(R.drawable.pusheen_move2);
        _movingActive.addFrame(R.drawable.pusheen_move1);
        _movingActive.addFrame(R.drawable.pusheen_move3);
        _movingActive.setDelay(1);
    }

    private void InitializeAttackActive()
    {
        _attackActive = new Animation();
        _attackActive.setLocation(_displayX, _displayY);
        _attackActive.addFrame(R.drawable.pusheen_attack1);
        _attackActive.addFrame(R.drawable.pusheen_attack2);
        _attackActive.addFrame(R.drawable.pusheen_attack3);
        _attackActive.addFrame(R.drawable.pusheen_attack4);
        _attackActive.addFrame(R.drawable.pusheen_attack4);
        _attackActive.addFrame(R.drawable.pusheen_attack3);
        _attackActive.addFrame(R.drawable.pusheen_attack2);
        _attackActive.addFrame(R.drawable.pusheen_attack1);
        _attackActive.setVisible(false);
        _attackActive.setDelay(1);
    }
}