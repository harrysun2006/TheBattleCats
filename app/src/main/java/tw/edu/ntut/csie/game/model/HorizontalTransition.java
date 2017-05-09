package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/9.

import tw.edu.ntut.csie.game.core.MovingBitmap;

public class HorizontalTransition
{
    private MovingBitmap _movingBitmap;
    private int _destinationX;
    private int _x;
    private int _y;
    private boolean _active;

    public HorizontalTransition(MovingBitmap movingBitmap, int destinationX)
    {
        _movingBitmap = movingBitmap;
        _movingBitmap.setVisible(false);
        _destinationX = destinationX;
        _x = _movingBitmap.getX();
        _y = _movingBitmap.getY();
        _active = false;
    }

    public void Run()
    {
        if (_active && _x != _destinationX)
        {
            _x += 30;
            if (_x > _destinationX)
            {
                _x = _destinationX;
            }
            _movingBitmap.setLocation(_x, _y);
        }
    }

    //啟動轉場動畫讓MovingBitmap滑動到指定位置
    public void Activate()
    {
        _movingBitmap.setVisible(true);
        _active = true;
    }
}