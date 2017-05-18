package tw.edu.ntut.csie.game.state;

import java.util.Map;
import java.util.List;

import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.model.ShopModel;
import tw.edu.ntut.csie.game.model.ShopLevelButton;

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
        _shopModel = new ShopModel();
        _moneyPocketButton = new ShopLevelButton(R.drawable.money_pocket, R.drawable.money_pocket, 10, 120);
        _workEfficiencyButton = new ShopLevelButton(R.drawable.work_efficiency, R.drawable.work_efficiency, 200, 120);
    }

    @Override
    public void move()
    {
    }

    @Override
    public void show()
    {
        _background.show();
        _moneyPocketButton.Show();
        _workEfficiencyButton.Show();
    }

    @Override
    public void release()
    {
        _background.release();
        _music.release();
        _moneyPocketButton.release();
        _workEfficiencyButton.release();
        _background = null;
        _music = null;
        _moneyPocketButton = null;
        _workEfficiencyButton = null;
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
            _moneyPocketButton.Push();
            _shopModel.AddMoneyPocket();
            /*if (_moneyPocketButton.GetIsEnabled() == true)
            {//錢包大小升級測試
                _moneyPocketButton.Push();
                _shopModel.AddMoneyPocket();
            }*/
        }
        else if (pressedX > _workEfficiencyButton.GetX() && pressedX < _workEfficiencyButton.GetX() + _workEfficiencyButton.GetWidth() &&
                pressedY > _workEfficiencyButton.GetY() && pressedY < _workEfficiencyButton.GetY() + _workEfficiencyButton.GetHeight())
        {
//            if (_workEfficiencyButton.GetIsEnabled())
//            {
                _workEfficiencyButton.Push();
                _shopModel.AddMoneySpeed();
//            }
        }
        return true;
    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers)
    {
        return false;
    }

    @Override
    public boolean pointerReleased(List<Pointer> pointers)
    {
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
}