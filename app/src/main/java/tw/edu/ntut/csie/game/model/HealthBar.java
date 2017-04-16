package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/4/14.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

public class HealthBar extends Bar
{
    public HealthBar(int x, int y, int totalLength)
    {
        super(x, y, totalLength);

        for (int i = 0; i < totalLength; i++)
        {
            _bar.add(new MovingBitmap(R.drawable.bar_unit_green, _x + i, _y));
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