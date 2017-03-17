package tw.edu.ntut.csie.game.model;

import java.util.List;
import java.util.ArrayList;
import tw.edu.ntut.csie.game.ReleasableResource;

/**
 * Created by User on 2017/3/10.
 */

public class BattleModel implements ReleasableResource
{
    private List<Units> _units;
    private int _countOtterTime = 0;

    public BattleModel()
    {
        _units = new ArrayList<>();
    }

    public void Run()
    {
        for (Units element:_units)
        {
            element.Moving();
        }
    }

    public void GenerateCapoo()
    {
        _units.add(new Capoo(400, 200));
    }

    public void GenerateOtter()
    {
        Otter test =new Otter(30, 30);
        if(_countOtterTime == 0)
        {
            _countOtterTime = test.GetDelay();
            _units.add(new Otter(30, 200));
        }
        else
        {
            _countOtterTime--;
        }
    }

    public void ShowAll()
    {
        for (Units element:_units)
        {
            element.Show();
        }
    }

    public void release()
    {
        _units.clear();
        _units = null;
    }
}