package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/4/14.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

public class HealthBar extends Bar
{
    private int _displayX;
    private int _displayY;

    public HealthBar(int x, int y, int totalLength)
    {
        super(x, y, totalLength);

        _displayX = _x;
        _displayY = _y;

        for (int i = 0; i < totalLength; i++)
        {
            _bar.add(new MovingBitmap(R.drawable.bar_unit_green, _displayX + i, _displayY));
        }
    }

    public void Translation(int shiftedX, int shiftedY)
    {
        _displayX = _x - shiftedX;
        _displayY = _y - shiftedY;

        for (int i = 0; i < _totalLength; i++)
        {
            _bar.get(i).setLocation(_displayX + i, _displayY);
        }
    }

    public void Show()
    {
        ChangeColor();
        super.Show();
    }

    private void ChangeColor()
    {
        if (_currentPercentage < 70)
        {
            for (int i = 0; i < _totalLength; i++)
            {
                _bar.get(i).loadBitmap(R.drawable.bar_unit_orange);
            }
        }
        if (_currentPercentage < 40)
        {
            for (int i = 0; i < _totalLength; i++)
            {
                _bar.get(i).loadBitmap(R.drawable.bar_unit_red);
            }
        }
    }
}