package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/6.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

public class Hoshi extends Units
{
    public static int COOLDOWN = 4; //cooldown time

    public Hoshi(int x, int y, int shiftedX)
    {
        super();

        _health = 100;
        _currentHealth = _health;
        _x = x;
        _y = y;
        _displayX = _x - shiftedX;
        _displayY = _y;

        _attackDamage = 50;
        _moveSpeed = -5;
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
        _movingActive.addFrame(R.drawable.hoshi_move0);
        _movingActive.addFrame(R.drawable.hoshi_move1);
        _movingActive.addFrame(R.drawable.hoshi_move2);
        _movingActive.addFrame(R.drawable.hoshi_move3);
        _movingActive.addFrame(R.drawable.hoshi_move4);
        _movingActive.addFrame(R.drawable.hoshi_move5);
        _movingActive.addFrame(R.drawable.hoshi_move6);
        _movingActive.addFrame(R.drawable.hoshi_move7);
        _movingActive.addFrame(R.drawable.hoshi_move8);
        _movingActive.addFrame(R.drawable.hoshi_move9);
        _movingActive.addFrame(R.drawable.hoshi_move10);
        _movingActive.addFrame(R.drawable.hoshi_move11);
        _movingActive.addFrame(R.drawable.hoshi_move12);
        _movingActive.setDelay(1);
    }

    private void InitializeAttackActive()
    {
        _attackActive = new Animation();
        _attackActive.setLocation(_displayX, _displayY);
        _attackActive.addFrame(R.drawable.hoshi_attack0);
        _attackActive.addFrame(R.drawable.hoshi_attack1);
        _attackActive.addFrame(R.drawable.hoshi_attack2);
        _attackActive.addFrame(R.drawable.hoshi_attack3);
        _attackActive.addFrame(R.drawable.hoshi_attack4);
        _attackActive.addFrame(R.drawable.hoshi_attack5);
        _attackActive.addFrame(R.drawable.hoshi_attack6);
        _attackActive.addFrame(R.drawable.hoshi_attack7);
        _attackActive.addFrame(R.drawable.hoshi_attack8);
        _attackActive.addFrame(R.drawable.hoshi_attack9);
        _attackActive.setVisible(false);
        _attackActive.setDelay(2);
    }
}