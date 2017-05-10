package tw.edu.ntut.csie.game.state;

import java.util.Map;
import java.util.List;

import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.model.LevelButton;
import tw.edu.ntut.csie.game.model.ShopModel;

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
        _black = new LevelButton(R.drawable.android_black, R.drawable.android_green, 10, 150);
        _green = new LevelButton(R.drawable.android_green, R.drawable.android_black, 100, 150);
        _shopModel = new ShopModel();
    }

    @Override
    public void move()
    {
    }

    @Override
    public void show()
    {
        _background.show();
        _black.Show();
        _green.Show();
    }

    @Override
    public void release()
    {
        _background.release();
        _music.release();
        _black.release();
        _green.release();
        _background = null;
        _music = null;
        _black = null;
        _green = null;
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
        if (pressedX > _black.GetX() && pressedX < _black.GetX() + _black.GetWidth() && pressedY > _black.GetY() && pressedY < _black.GetY() + _black.GetHeight())
        {
            _black.Push();
            _shopModel.AddMoneyPocket();
            /*if (_black.GetIsEnabled() == true)
            {//錢包大小升級測試
                _black.Push();
                _shopModel.AddMoneyPocket();
            }*/
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
    private LevelButton _black;
    private LevelButton _green;
    private ShopModel _shopModel;
}