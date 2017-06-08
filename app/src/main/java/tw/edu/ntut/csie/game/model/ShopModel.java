package tw.edu.ntut.csie.game.model;

/**
 * Created by User on 2017/4/28.
 */

public class ShopModel
{
    private RecordModel _recordModel;

    public ShopModel(RecordModel recordModel)
    {
        _recordModel = recordModel;
    }

    //增加錢包初始最大值
    public void UpgradeMoneyPocket()
    {
        _recordModel.SubtractExperience(_recordModel.GetMoneyPocketLevel() * 1000);
        _recordModel.UpgradeMoneyPocket();
    }

    //增加錢的生產效率
    public void UpgradeWorkEfficiency()
    {
        _recordModel.SubtractExperience(_recordModel.GetWorkEfficiencyLevel() * 1000);
        _recordModel.UpgradeWorkEfficiency();
    }

    //增加友軍主堡體力
    public void UpgradeCastleEnergy()
    {
        _recordModel.SubtractExperience(_recordModel.GetCastleEnergyLevel() * 1000);
        _recordModel.UpgradeCastleEnergy();
    }

    //增加經驗值獲得量
    public void UpgradeExperienceLearning()
    {
        _recordModel.SubtractExperience(_recordModel.GetExperienceLearningLevel() * 1000);
        _recordModel.UpgradeExperienceLearning();
    }

    public int GetMoneyPocketLevel()
    {
        return _recordModel.GetMoneyPocketLevel();
    }

    public int GetWorkEfficiencyLevel()
    {
        return _recordModel.GetWorkEfficiencyLevel();
    }

    public int GetCastleEnergyLevel()
    {
        return _recordModel.GetCastleEnergyLevel();
    }

    public int GetExperienceLearningLevel()
    {
        return _recordModel.GetExperienceLearningLevel();
    }

    public int GetExperience()
    {
        return _recordModel.GetExperience();
    }

    public void FullExperience()
    {
        _recordModel.FullExperience();
    }

    public boolean DeleteRecord()
    {
        return _recordModel.DeleteRecord();
    }
}