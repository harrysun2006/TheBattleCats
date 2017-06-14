package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/9.

import tw.edu.ntut.csie.game.core.MovingBitmap;

public class VerticalTransition
{
    private MovingBitmap _movingBitmap;
    private int _destinationY;
    private int _acceleration;
    private int _initialSpeed;
    private int _speed;
    private int _initialX;
    private int _initialY;
    private int _x;
    private int _y;
    private boolean _isActive;
    private boolean _isFinished;

    public VerticalTransition(MovingBitmap movingBitmap, int destinationY, int speed)
    {
        _movingBitmap = movingBitmap;
        _movingBitmap.setVisible(false);
        _destinationY = destinationY;
        _acceleration = 0;
        _initialSpeed = speed;
        _speed = _initialSpeed;
        _initialX = _movingBitmap.getX();
        _initialY = _movingBitmap.getY();
        _x = _initialX;
        _y = _initialY;
        _isActive = false;
        _isFinished = false;
    }

    public void Run()
    {
        if (_isActive && !_isFinished)
        {
            _speed += _acceleration;
            _y += _speed;
            if (_y > _destinationY)
            {
                _y = _destinationY;
                _isFinished = true; //轉場動畫結束的時候讓_isFinished = true
            }
            _movingBitmap.setLocation(_x, _y);
        }
    }

    //啟動轉場動畫讓MovingBitmap滑動到指定位置
    public void Activate()
    {
        _movingBitmap.setVisible(true);
        _isActive = true;
    }

    public boolean IsTransitionFinished()
    {
        return _isFinished;
    }

    public void AssignAcceleration(int acceleration)
    {
        _acceleration = acceleration;
    }

    public void Reset()
    {
        _speed = _initialSpeed;
        _x = _initialX;
        _y = _initialY;
        _movingBitmap.setLocation(_x, _y);
        _movingBitmap.setVisible(false);
        _isActive = false;
        _isFinished = false;
    }
}