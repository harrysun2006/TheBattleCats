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

    protected int _attackDelayCounter = 0; //控制攻擊頻率 (因為畫面更新頻率太快，所以攻擊要delay一下，不然會攻擊太快)

    protected Animation _movingActive; //移動動畫
    protected Animation _attackActive; //攻擊動畫
    protected Animation _knockedBackActive; //擊退動畫

    protected boolean _isAttacking = false; //正在攻擊的狀態
    protected boolean _isAttacked = false; //被攻擊的狀態
    protected boolean _isDied = false; //是否死掉

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

    public int GetAttackDelayCounter()
    {
        return _attackDelayCounter;
    }

    public void SetAttackDelayCounter(int attackDelayCounter)
    {
        _attackDelayCounter = attackDelayCounter;
    }

    public abstract void Moving();

    public abstract void Attack();

    public abstract void Attacked(int damage);

    protected abstract void KnockedBack(); //被擊退

    protected abstract void Died();

    public abstract void Show();

    public boolean GetIsAttacking()
    {
        return _isAttacking;
    }

    //如果正在攻擊的狀態，會讓移動的動畫隱藏
    public void SetIsAttacking(boolean isAttacking)
    {
        if (isAttacking)
        {
            _movingActive.setVisible(false);
        }
        else
        {
            _movingActive.setVisible(true);
        }
        _isAttacking = isAttacking;
    }

    public boolean GetIsAttacked()
    {
        return _isAttacked;
    }

    //如果正在被攻擊的狀態，會讓移動的動畫隱藏
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

    public boolean GetIsDied()
    {
        return _isDied;
    }
}