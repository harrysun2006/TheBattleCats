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

    public BattleModel()
    {
        _units = new ArrayList<>();
    }

    public void GenerateCapoo()
    {
        _units.add(new Capoo(300, 150));
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