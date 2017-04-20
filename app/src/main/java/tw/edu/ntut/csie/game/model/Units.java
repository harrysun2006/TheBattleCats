package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;

/**
 * Created by User on 2017/3/10.
 */

public abstract class Units
{
    protected int _health; //血量
    protected int _currentHeath; //當前血量
    protected int _cost; //產兵花費

    protected int _x; //X座標
    protected int _y; //Y座標

    protected int _displayX; //實際顯示的X座標
    protected int _displayY; //實際顯示的Y座標

    protected int _attackDamage; //攻擊力
    protected int _moveSpeed; //移動速度 (單位: 每1/15秒1像素)
    protected int _attackSpeed; //攻擊速度

    protected int _attackDelayCounter = 0; //控制攻擊頻率 (因為畫面更新頻率太快，所以攻擊要delay一下，不然會攻擊太快)

    protected Animation _movingActive; //移動動畫
    protected Animation _attackActive; //攻擊動畫
    protected Animation _knockedBackActive; //擊退動畫
    protected Animation _dyingActive; //死亡動畫

    protected boolean _isAttacking = false; //正在攻擊的狀態
    protected boolean _isAttacked = false; //被攻擊的狀態
    protected boolean _isDying = false; //是否死掉

    public Units()
    {
        _dyingActive = new Animation();
        _dyingActive.addFrame(R.drawable.die1);
        _dyingActive.addFrame(R.drawable.die2);
        _dyingActive.setVisible(false);
        _dyingActive.setDelay(1);
    }

    public int GetCurrentHealthPercentage()
    {
        return 100 * _currentHeath / _health;
    }

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

    public abstract void KnockedBack(); //被擊退

    public void Dying()
    {
        _y -= 10;
        _displayY -= 10;
        _dyingActive.setLocation(_displayX, _displayY);
        _dyingActive.move();
    }

    public void Transition(int shiftedX, int shiftedY)
    {
        _displayX = _x - shiftedX;
        _displayY = _y - shiftedY;
    }

    public abstract void Show();

    public boolean GetIsAttacking()
    {
        return _isAttacking;
    }

    //進入攻擊的狀態時，會隱藏移動的動畫
    public void SetIsAttacking(boolean isAttacking)
    {
        _movingActive.setVisible(!isAttacking);
        _attackActive.setVisible(isAttacking);
        _isAttacking = isAttacking;
    }

    public boolean GetIsAttacked()
    {
        return _isAttacked;
    }

    //進入被攻擊的狀態時，會隱藏移動的動畫
    public void SetIsAttacked(boolean isAttacked)
    {
        _movingActive.setVisible(!isAttacked);
        _isAttacked = isAttacked;
    }

    public boolean GetIsDying()
    {
        return _isDying;
    }

    //進入播放死亡動畫的狀態時，會隱藏移動的動畫，顯示死亡的動畫
    public void SetIsDying(boolean isDying)
    {
        _movingActive.setVisible(!isDying);
//        _attackActive.setVisible(!isDying);
        _dyingActive.setVisible(isDying);
        _isDying = isDying;
    }
}