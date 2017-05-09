package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/9.

import tw.edu.ntut.csie.game.core.MovingBitmap;

public class VerticalTransition
{
    private MovingBitmap _movingBitmap;
    private int _destinationY;
    private int _x;
    private int _y;
    private boolean _active;

    public VerticalTransition(MovingBitmap movingBitmap, int destinationY)
    {
        _movingBitmap = movingBitmap;
        _movingBitmap.setVisible(false);
        _destinationY = destinationY;
        _x = _movingBitmap.getX();
        _y = _movingBitmap.getY();
        _active = false;
    }

    public void Run()
    {
        if (_active && _y != _destinationY)
        {
            _y += 25;
            if (_y > _destinationY)
            {
                _y = _destinationY;
            }
            _movingBitmap.setLocation(_x, _y);
        }
    }

    public void Activate()
    {
        _movingBitmap.setVisible(true);
        _active = true;
    }
}