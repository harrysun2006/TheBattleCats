package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by User on 2017/3/10.
 */

public abstract class Units
{
    protected int _health; //血量
    protected int _currentHeath; //當前血量

    protected int _x; //X座標
    protected int _y; //Y座標

    protected int _attackDamage; //攻擊力
    protected int _moveSpeed; //移動速度 (單位: 每1/15秒1像素)
    protected int _attackSpeed; //攻擊速度

    protected Animation _movingActive; //移動動畫
    protected Animation _attackActive; //攻擊動畫
    protected Animation _knockedBackActive; //擊退動畫

    protected boolean _isAttacking = false; //正在攻擊的狀態
    protected boolean _isAttacked = false; //被攻擊的狀態

    public int GetX()
    {
        return _x;
    }

    public int GetY()
    {
        return _y;
    }

    public int GetRightSideX()
    {
        return _x + _movingActive.getWidth();
    }

    public int GetAttackDamage()
    {
        return _attackDamage;
    }

    public int GetAttackSpeed()
    {
        return _attackSpeed;
    }

    public abstract void Moving();

    public abstract void Attack();

    public abstract void Attacked(int damage);

    protected abstract void KnockedBack(); //被擊退

    protected abstract void Died();

    public abstract void Show();

    public void SetIsAttacking(boolean isAttacking)
    {
        _isAttacking = isAttacking;
    }

    public boolean GetIsAttacking()
    {
        return _isAttacking;
    }

    public void SetIsAttacked(boolean isAttacked)
    {
        if (isAttacked)
        {
            _movingActive.setVisible(false);
        }
        else
        {
            _movingActive.setVisible(true);
        }
        _isAttacked = isAttacked;
    }

    public boolean GetIsAttacked()
    {
        return _isAttacked;
    }
}