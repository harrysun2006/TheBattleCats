package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/4/14.

import java.util.List;
import java.util.ArrayList;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.ReleasableResource;

public class Bar implements ReleasableResource
{
    protected int _x;
    protected int _y;
    private int _totalLength;
    private int _currentLength;
    private int _currentPercentage;
    protected List<MovingBitmap> _bar;

    public Bar(int x, int y, int totalLength)
    {
        _x = x;
        _y = y;
        _totalLength = totalLength;
        _currentPercentage = 100;
        _bar = new ArrayList<>();
    }

    public void UpdateCurrentLength()
    {
        _currentLength = _totalLength * _currentPercentage / 100;
    }

    public void SetCurrentPercentage(int currentPercentage)
    {
        _currentPercentage = currentPercentage;
    }

    public void SetLocation(int x, int y)
    {
        _x = x;
        _y = y;

        for (int i = 0; i < _totalLength; i++)
        {
            _bar.get(i).setLocation(_x, _y);
        }
    }

    public void Show()
    {
        UpdateCurrentLength();
        for (int i = 0; i < _totalLength; i++)
        {
            if (i < _currentLength)
            {
                _bar.get(i).setVisible(true);
            }
            else
            {
                _bar.get(i).setVisible(false);
            }
            _bar.get(i).show();
        }
    }

    public void release()
    {
        _bar.clear();
    }
}