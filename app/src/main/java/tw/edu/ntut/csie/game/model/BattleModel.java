package tw.edu.ntut.csie.game.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by User on 2017/3/10.
 */

public class BattleModel
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
}