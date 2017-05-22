package tw.edu.ntut.csie.game.state;

import java.util.Map;
import java.util.List;

import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.model.ShopModel;
import tw.edu.ntut.csie.game.model.RecordModel;
import tw.edu.ntut.csie.game.model.ShopLevelButton;
import tw.edu.ntut.csie.game.model.ShiftingModule;

/**
 * Created by User on 2017/4/26.
 */

public class StateShop extends GameState
{
    public StateShop(GameEngine engine)
    {
        //To invoke constructor in tw.edu.ntut.csie.game.state.GameState (super class).
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data)
    {
        _background = new MovingBitmap(R.drawable.background);
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
        _shopModel = new ShopModel(new RecordModel(_engine));
        _moneyPocketButton = new ShopLevelButton(R.drawable.money_pocket, R.drawable.money_pocket, 10, 120, _shopModel.GetMoneyPocketLevel());
        _workEfficiencyButton = new ShopLevelButton(R.drawable.work_efficiency, R.drawable.work_efficiency, 200, 120, _shopModel.GetWorkEfficiencyLevel());
        _castleEnergyButton = new ShopLevelButton(R.drawable.castle_enegy, R.drawable.castle_enegy, 390, 120, _shopModel.GetCastleEnergyLevel());
        _experienceLearningButton = new ShopLevelButton(R.drawable.experience_learning, R.drawable.experience_learning, 580, 120, _shopModel.GetExperienceLearningLevel());
        _shiftingModule = new ShiftingModule(135);
        UpdateButtonState();
    }

    //在initialize時跟按下按鈕後更新按鈕狀態，經驗值實作前current跟cost都設成0
    private void UpdateButtonState()
    {
        _moneyPocketButton.SetEnable(0, 0);
        _workEfficiencyButton.SetEnable(0, 0);
        _castleEnergyButton.SetEnable(0, 0);
        _experienceLearningButton.SetEnable(0, 0);
    }

    @Override
    public void move()
    {
        RunShiftingModule();
    }

    private void RunShiftingModule()
    {
        _shiftingModule.Run(_currentPressedX);

        if (_shiftingModule.IsAutoSlidingEnabled() || _shiftingModule.IsSpecifiedSlidingEnabled())
        {
            Translation(_shiftingModule.GetShifting(), 0);
        }
    }

    private void Translation(int shiftedX, int shiftedY)
    {
        _moneyPocketButton.Translation(shiftedX, shiftedY);
        _workEfficiencyButton.Translation(shiftedX, shiftedY);
        _castleEnergyButton.Translation(shiftedX, shiftedY);
        _experienceLearningButton.Translation(shiftedX, shiftedY);
    }

    @Override
    public void show()
    {
        _background.show();
        _moneyPocketButton.Show();
        _workEfficiencyButton.Show();
        _castleEnergyButton.Show();
        _experienceLearningButton.Show();
    }

    @Override
    public void release()
    {
        _background.release();
        _music.release();
        _moneyPocketButton.release();
        _workEfficiencyButton.release();
        _castleEnergyButton.release();
        _experienceLearningButton.release();
        _background = null;
        _music = null;
        _shopModel = null;
        _moneyPocketButton = null;
        _workEfficiencyButton = null;
        _castleEnergyButton = null;
        _experienceLearningButton = null;
    }

    @Override
    public void keyPressed(int keyCode)
    {
    }

    @Override
    public void keyReleased(int keyCode)
    {
    }

    @Override
    public void orientationChanged(float pitch, float azimuth, float roll)
    {
    }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ)
    {
    }

    @Override
    public boolean pointerPressed(List<Pointer> pointers)
    {
        int pressedX = pointers.get(0).getX();
        int pressedY = pointers.get(0).getY();
        if (pressedX > _moneyPocketButton.GetX() && pressedX < _moneyPocketButton.GetX() + _moneyPocketButton.GetWidth() &&
                pressedY > _moneyPocketButton.GetY() && pressedY < _moneyPocketButton.GetY() + _moneyPocketButton.GetHeight())
        {
            if (_moneyPocketButton.GetIsEnabled())
            {
                _moneyPocketButton.Push();
                _shopModel.UpgradeMoneyPocket();
                UpdateButtonState();
            }
        }
        else if (pressedX > _workEfficiencyButton.GetX() && pressedX < _workEfficiencyButton.GetX() + _workEfficiencyButton.GetWidth() &&
                pressedY > _workEfficiencyButton.GetY() && pressedY < _workEfficiencyButton.GetY() + _workEfficiencyButton.GetHeight())
        {
            if (_workEfficiencyButton.GetIsEnabled())
            {
                _workEfficiencyButton.Push();
                _shopModel.UpgradeWorkEfficiency();
                UpdateButtonState();
            }
        }
        else if (pressedX > _castleEnergyButton.GetX() && pressedX < _castleEnergyButton.GetX() + _castleEnergyButton.GetWidth() &&
                pressedY > _castleEnergyButton.GetY() && pressedY < _castleEnergyButton.GetY() + _castleEnergyButton.GetHeight())
        {
            if (_castleEnergyButton.GetIsEnabled())
            {
                _castleEnergyButton.Push();
                _shopModel.UpgradeCastleEnergy();
                UpdateButtonState();
            }
        }
        else if (pressedX > _experienceLearningButton.GetX() && pressedX < _experienceLearningButton.GetX() + _experienceLearningButton.GetWidth() &&
                pressedY > _experienceLearningButton.GetY() && pressedY < _experienceLearningButton.GetY() + _experienceLearningButton.GetHeight())
        {
            if (_experienceLearningButton.GetIsEnabled())
            {
                _experienceLearningButton.Push();
                _shopModel.UpgradeExperienceLearning();
                UpdateButtonState();
            }
        }
        else
        {
            _isPressed = true;
            _currentPressedX = pointers.get(0).getX();
            _shiftingModule.HandlePointerPressed(_currentPressedX);
        }
        return true;
    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers)
    {
        if (pointers.size() == 3) //在螢幕上用三根手指點擊來刪除紀錄檔
        {
            _shopModel.DeleteRecord();
        }
        if (_isPressed)
        {
            _currentPressedX = pointers.get(0).getX();
            _shiftingModule.HandlePointerMoved(_currentPressedX);
            Translation(_shiftingModule.GetTempShifting(), 0);
        }
        return false;
    }

    @Override
    public boolean pointerReleased(List<Pointer> pointers)
    {
        if (_isPressed)
        {
            _isPressed = false;
            _shiftingModule.HandlePointerReleased();
        }
        return false;
    }

    @Override
    public void pause()
    {
        _music.pause();
    }

    @Override
    public void resume()
    {
        _music.play();
    }

    private MovingBitmap _background;
    private Audio _music;
    private ShopModel _shopModel;
    private ShopLevelButton _moneyPocketButton;
    private ShopLevelButton _workEfficiencyButton;
    private ShopLevelButton _castleEnergyButton;
    private ShopLevelButton _experienceLearningButton;
    private ShiftingModule _shiftingModule;
    private int _currentPressedX;
    private boolean _isPressed;
}