package tw.edu.ntut.csie.game.state;

import java.util.Map;
import java.util.List;

import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.model.ShopModel;
import tw.edu.ntut.csie.game.extend.Integer;
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
        _background = new MovingBitmap(R.drawable.shop_background);
        _shopModel = new ShopModel(new RecordModel(_engine));
        _experience = new Integer(5, _shopModel.GetExperience(), 500, 13);
        _moneyPocketButton = new ShopLevelButton(R.drawable.money_pocket, R.drawable.money_pocket_disabled, 10, 60, _shopModel.GetMoneyPocketLevel());
        _workEfficiencyButton = new ShopLevelButton(R.drawable.work_efficiency, R.drawable.work_efficiency_disabled, 200, 60, _shopModel.GetWorkEfficiencyLevel());
        _castleEnergyButton = new ShopLevelButton(R.drawable.castle_enegy, R.drawable.castle_enegy_disabled, 390, 60, _shopModel.GetCastleEnergyLevel());
        _experienceLearningButton = new ShopLevelButton(R.drawable.experience_learning, R.drawable.experience_learning_disabled, 580, 60, _shopModel.GetExperienceLearningLevel());
        _shiftingModule = new ShiftingModule(135);
        InitializeMusic();
        UpdateButtonState();
    }

    private void InitializeMusic()
    {
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _buyingSound = new Audio(R.raw.buy_item);
        _buyingSound.setRepeating(false);
        _music.play();
    }

    //在initialize時跟按下按鈕後更新按鈕狀態，經驗值實作前current跟cost都設成0
    private void UpdateButtonState()
    {
        _experience.setValue(_shopModel.GetExperience());
        _moneyPocketButton.SetEnable(_shopModel.GetExperience(), _shopModel.GetMoneyPocketLevel() * 1000);
        _workEfficiencyButton.SetEnable(_shopModel.GetExperience(), _shopModel.GetWorkEfficiencyLevel() * 1000);
        _castleEnergyButton.SetEnable(_shopModel.GetExperience(), _shopModel.GetCastleEnergyLevel() * 1000);
        _experienceLearningButton.SetEnable(_shopModel.GetExperience(), _shopModel.GetExperienceLearningLevel() * 1000);
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
        _experience.show();
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
        _buyingSound.release();
        _experience.release();
        _moneyPocketButton.release();
        _workEfficiencyButton.release();
        _castleEnergyButton.release();
        _experienceLearningButton.release();
        _background = null;
        _music = null;
        _buyingSound = null;
        _shopModel = null;
        _experience = null;
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
        if (IsPointerOnButton(pointers.get(0), _moneyPocketButton))
        {
            _pressedButton = 1;
        }
        else if (IsPointerOnButton(pointers.get(0), _workEfficiencyButton))
        {
            _pressedButton = 2;
        }
        else if (IsPointerOnButton(pointers.get(0), _castleEnergyButton))
        {
            _pressedButton = 3;
        }
        else if (IsPointerOnButton(pointers.get(0), _experienceLearningButton))
        {
            _pressedButton = 4;
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
        if (pointers.size() == 2)
        {
            _shopModel.FullExperience();
        }
        else if (pointers.size() == 3) //在螢幕上用三根手指點擊來刪除紀錄檔
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
        else if (IsPointerOnButton(pointers.get(0), _moneyPocketButton) && _pressedButton == 1)
        {
            if (_moneyPocketButton.GetIsEnabled())
            {
                _buyingSound.play();
                _moneyPocketButton.Push();
                _shopModel.UpgradeMoneyPocket();
                UpdateButtonState();
            }
        }
        else if (IsPointerOnButton(pointers.get(0), _workEfficiencyButton) && _pressedButton == 2)
        {
            if (_workEfficiencyButton.GetIsEnabled())
            {
                _buyingSound.play();
                _workEfficiencyButton.Push();
                _shopModel.UpgradeWorkEfficiency();
                UpdateButtonState();
            }
        }
        else if (IsPointerOnButton(pointers.get(0), _castleEnergyButton) && _pressedButton == 3)
        {
            if (_castleEnergyButton.GetIsEnabled())
            {
                _buyingSound.play();
                _castleEnergyButton.Push();
                _shopModel.UpgradeCastleEnergy();
                UpdateButtonState();
            }
        }
        else if (IsPointerOnButton(pointers.get(0), _experienceLearningButton) && _pressedButton == 4)
        {
            if (_experienceLearningButton.GetIsEnabled())
            {
                _buyingSound.play();
                _experienceLearningButton.Push();
                _shopModel.UpgradeExperienceLearning();
                UpdateButtonState();
            }
        }
        return false;
    }

    //判斷該指標是否在按鈕上方
    private boolean IsPointerOnButton(Pointer pointer, ShopLevelButton button)
    {
        int pressedX = pointer.getX();
        int pressedY = pointer.getY();
        return (pressedX > button.GetX() && pressedX < button.GetX() + button.GetWidth() &&
                pressedY > button.GetY() && pressedY < button.GetY() + button.GetHeight());
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
    private Audio _buyingSound;
    private ShopModel _shopModel;
    private Integer _experience;
    private ShopLevelButton _moneyPocketButton;
    private ShopLevelButton _workEfficiencyButton;
    private ShopLevelButton _castleEnergyButton;
    private ShopLevelButton _experienceLearningButton;
    private ShiftingModule _shiftingModule;
    private int _currentPressedX;
    private boolean _isPressed;
    private int _pressedButton;
}