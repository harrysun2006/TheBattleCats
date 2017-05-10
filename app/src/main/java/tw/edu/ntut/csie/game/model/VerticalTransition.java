package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/9.

import tw.edu.ntut.csie.game.core.MovingBitmap;

public class VerticalTransition
{
    private MovingBitmap _movingBitmap;
    private int _destinationY;
    private int _x;
    private int _y;
    private boolean _isActive;
    private boolean _isFinished;

    public VerticalTransition(MovingBitmap movingBitmap, int destinationY)
    {
        _movingBitmap = movingBitmap;
        _movingBitmap.setVisible(false);
        _destinationY = destinationY;
        _x = _movingBitmap.getX();
        _y = _movingBitmap.getY();
        _isActive = false;
        _isFinished = false;
    }

    public void Run()
    {
        if (_isActive && !_isFinished)
        {
            _y += 25;
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
}