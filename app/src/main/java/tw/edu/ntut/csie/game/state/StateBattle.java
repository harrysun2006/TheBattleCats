package tw.edu.ntut.csie.game.state;

//Created by leon on 2017/3/8.

import android.media.midi.MidiOutputPort;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import java.util.Collections;

public class StateBattle extends GameState
{
    public StateBattle(GameEngine engine)
    {
        //To invoke constructor in tw.edu.ntut.csie.game.state.GameState (super class).
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data)
    {
        _background = new MovingBitmap(R.drawable.test_background);
        _button = new MovingBitmap(R.drawable.test_button, 10, 10);
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
    }

    @Override
    public void move()
    {
    }

    @Override
    public void show()
    {
        _background.show();
        _button.show();
        //_capoo.show();
        for (MovingBitmap element:_capooes)
        {
            element.show();
        }

    }

    @Override
    public void release()
    {
        _background.release();
        _button.release();
        //_capoo.release();
        _music.release();
        for (MovingBitmap element:_capooes)
        {
            element.release();
        }
        _background = null;
        _button = null;
       //_capoo = null;
        _music = null;
        _capooes = null;
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
        if (pressedX > _button.getX() && pressedX < _button.getX() + _button.getWidth())
        {
            if (pressedY > _button.getY() && pressedY < _button.getY() + _button.getHeight())
            {
                Collections.reverse(_capooes);
               // _capoo = new MovingBitmap(R.drawable.test_capoo, i, 150);
                _capooes.add(new MovingBitmap(R.drawable.test_capoo, i, 150));
                i = i-30;
                Collections.reverse(_capooes);
            }
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
    private MovingBitmap _button;
    private MovingBitmap _capoo;
    private Audio _music;
    private int i =300;
    private List<MovingBitmap> _capooes = new ArrayList<>();
}