package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/21.

import tw.edu.ntut.csie.game.engine.GameEngine;

public class RecordModel
{
    private GameEngine _gameEngine;
    private String[] _records;

    public RecordModel(GameEngine gameEngine)
    {
        _gameEngine = gameEngine;
        if (_gameEngine.ReadRecord().length() == 0)
        {
            _gameEngine.WriteRecord("1 1 1 1 0");
        }
        _records = _gameEngine.ReadRecord().split(" ");
    }

    public void UpgradeMoneyPocket()
    {
        _records[0] = String.valueOf(Integer.parseInt(_records[0]) + 1);
        WriteRecord();
    }

    public void UpgradeWorkEfficiency()
    {
        _records[1] = String.valueOf(Integer.parseInt(_records[1]) + 1);
        WriteRecord();
    }

    public void UpgradeCastleEnergy()
    {
        _records[2] = String.valueOf(Integer.parseInt(_records[2]) + 1);
        WriteRecord();
    }

    public void UpgradeExperienceLearning()
    {
        _records[3] = String.valueOf(Integer.parseInt(_records[3]) + 1);
        WriteRecord();
    }

    public void AddExperience(int experience)
    {
        _records[4] = String.valueOf(Integer.parseInt(_records[4]) + experience);
        WriteRecord();
    }

    public void SubtractExperience(int experience)
    {
        _records[4] = String.valueOf(Integer.parseInt(_records[4]) - experience);
        WriteRecord();
    }

    public int GetMoneyPocketLevel()
    {
        return Integer.parseInt(_records[0]);
    }

    public int GetWorkEfficiencyLevel()
    {
        return Integer.parseInt(_records[1]);
    }

    public int GetCastleEnergyLevel()
    {
        return Integer.parseInt(_records[2]);
    }

    public int GetExperienceLearningLevel()
    {
        return Integer.parseInt(_records[3]);
    }

    public int GetExperience()
    {
        return Integer.parseInt(_records[4]);
    }

    //工作狂貓錢包每提升一提，得到額外100初始金錢
    public int GetInitialMaxMoney()
    {
        int result = 1000;
        for (int i = 1; i < Integer.parseInt(_records[0]); i++)
        {
            result += 100;
        }
        return result;
    }

    //工作狂貓錢包每提升一提，升級時得到額外100金錢上限
    public int GetMoneyMaxAddScale()
    {
        int result = 100;
        for (int i = 1; i < Integer.parseInt(_records[0]); i++)
        {
            result += 100;
        }
        return result;
    }

    //工作狂貓的工作效率每提升一級，每1/15秒得到額外2金錢
    public int GetMoneyAddSpeed()
    {
        int result = 10;
        for (int i = 1; i < Integer.parseInt(_records[1]); i++)
        {
            result += 2;
        }
        return result;
    }

    //城堡體力每提升一級，得到額外1000血量
    public int GetAllyNexusHealth()
    {
        int result = 1000;
        for (int i = 1; i < Integer.parseInt(_records[2]); i++)
        {
            result += 1000;
        }
        return result;
    }

    //學習力每提升一級，得到額外0.25倍的經驗值
    public double GetExperienceLearningScale()
    {
        double result = 1;
        for (int i = 1; i < Integer.parseInt(_records[3]); i++)
        {
            result += 0.25;
        }
        return result;
    }

    public void FullExperience()
    {
        _records[4] = String.valueOf(99999);
        _gameEngine.NoticeFullExperience();
        WriteRecord();
    }

    public boolean DeleteRecord()
    {
        return _gameEngine.DeleteRecord();
    }

    private void WriteRecord()
    {
        StringBuilder builder = new StringBuilder();

        for (String record:_records)
        {
            builder.append(record).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        _gameEngine.WriteRecord(builder.toString());
    }
}