package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/21.

import tw.edu.ntut.csie.game.engine.GameEngine;

public class RecordModel
{
    private GameEngine _gameEngine;
    private StringBuilder _builder;

    public RecordModel(GameEngine gameEngine)
    {
        _gameEngine = gameEngine;
        _builder = new StringBuilder(_gameEngine.ReadRecord());
        if (_builder.length() == 0)
        {
            _gameEngine.WriteRecord("1111");
            _builder = new StringBuilder(_gameEngine.ReadRecord());
        }
    }

    public void UpgradeMoneyPocket()
    {
        _builder.setCharAt(0, (char) ((int) _builder.charAt(0) + 1));
        _gameEngine.WriteRecord(_builder.toString());
    }

    public void UpgradeWorkEfficiency()
    {
        _builder.setCharAt(1, (char) ((int) _builder.charAt(1) + 1));
        _gameEngine.WriteRecord(_builder.toString());
    }

    public void UpgradeCastleEnergy()
    {
        _builder.setCharAt(2, (char) ((int) _builder.charAt(2) + 1));
        _gameEngine.WriteRecord(_builder.toString());
    }

    public void UpgradeExperienceLearning()
    {
        _builder.setCharAt(3, (char) ((int) _builder.charAt(3) + 1));
        _gameEngine.WriteRecord(_builder.toString());
    }

    public int GetMoneyPocketLevel()
    {
        return Character.getNumericValue(_builder.charAt(0));
    }

    public int GetWorkEfficiencyLevel()
    {
        return Character.getNumericValue(_builder.charAt(1));
    }

    public int GetCastleEnergyLevel()
    {
        return Character.getNumericValue(_builder.charAt(2));
    }

    public int GetExperienceLearningLevel()
    {
        return Character.getNumericValue(_builder.charAt(3));
    }

    public int GetInitialMaxMoney()
    {
        int result = 1000;
        for (int i = 1; i < Character.getNumericValue(_builder.charAt(0)); i++)
        {
            result += 100;
        }
        return result;
    }

    public int GetMoneyMaxAddScale()
    {
        int result = 100;
        for (int i = 1; i < Character.getNumericValue(_builder.charAt(0)); i++)
        {
            result += 100;
        }
        return result;
    }

    public int GetMoneyAddSpeed()
    {
        int result = 10;
        for (int i = 1; i < Character.getNumericValue(_builder.charAt(1)); i++)
        {
            result += 2;
        }
        return result;
    }

    public int GetAllyNexusHealth()
    {
        int result = 1000;
        for (int i = 1; i < Character.getNumericValue(_builder.charAt(2)); i++)
        {
            result += 1000;
        }
        return result;
    }

    public boolean DeleteRecord()
    {
        return _gameEngine.DeleteRecord();
    }
}