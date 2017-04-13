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
            _bar.add(new MovingBitmap(R.drawable.bar_unit, _x + i, _y));
        }
    }

    public void Show()
    {
        super.Show();
    }
}