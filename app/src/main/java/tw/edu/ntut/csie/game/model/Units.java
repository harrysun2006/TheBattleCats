package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.core.MovingBitmap;

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
    protected int _moveSpeed; //移動速度
    protected int _attackSpeed; //攻擊速度

    protected MovingBitmap _appearance; //長相

    public abstract void Moving();

    public abstract void Attack();

    public abstract void Attacked();

    protected abstract void knockedBack(); //被擊退，供Attacked呼叫

    protected abstract void Died();

    public abstract void Show();
}