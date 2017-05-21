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
        _builder = new StringBuilder(_gameEngine.ReadFile());
        if (_builder.length() == 0)
        {
            _gameEngine.WriteFile("1111");
            _builder = new StringBuilder(_gameEngine.ReadFile());
        }
    }

    public void IncreaseMoneyPocketLevel()
    {
        _builder.setCharAt(0, (char) ((int) _builder.charAt(0) + 1));
        _gameEngine.WriteFile(_builder.toString());
    }

    public int GetMoneyPocketLevel()
    {
        return Character.getNumericValue(_builder.charAt(0));
    }

    public void IncreaseWorkEfficiencyLevel()
    {
        _builder.setCharAt(1, (char) ((int) _builder.charAt(1) + 1));
        _gameEngine.WriteFile(_builder.toString());
    }

    public int GetWorkEfficiencyLevel()
    {
        return Character.getNumericValue(_builder.charAt(1));
    }

    public void IncreaseCastleEnergyLevel()
    {
        _builder.setCharAt(2, (char) ((int) _builder.charAt(2) + 1));
        _gameEngine.WriteFile(_builder.toString());
    }

    public int GetCastleEnergyLevel()
    {
        return Character.getNumericValue(_builder.charAt(2));
    }

    public void IncreaseExperienceLearningLevel()
    {
        _builder.setCharAt(3, (char) ((int) _builder.charAt(3) + 1));
        _gameEngine.WriteFile(_builder.toString());
    }

    public int GetExperienceLearningLevel()
    {
        return Character.getNumericValue(_builder.charAt(3));
    }
}