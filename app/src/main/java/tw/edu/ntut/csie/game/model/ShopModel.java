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
        _recordModel.UpgradeMoneyPocket();
    }

    //增加錢的生產效率
    public void UpgradeWorkEfficiency()
    {
        _recordModel.UpgradeWorkEfficiency();
    }

    //增加友軍主堡體力
    public void UpgradeCastleEnergy()
    {
        _recordModel.UpgradeCastleEnergy();
    }

    //增加經驗值獲得量
    public void UpgradeExperienceLearning()
    {
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

    public boolean DeleteRecord()
    {
        return _recordModel.DeleteRecord();
    }
}