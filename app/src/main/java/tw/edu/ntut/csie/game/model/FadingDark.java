package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/6/14.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.ReleasableResource;

public class FadingDark implements ReleasableResource
{
    private Animation _fading;
    private boolean _isFading;

    public FadingDark()
    {
        _isFading = false;
    }

    public void Run()
    {
        if (_isFading)
        {
            _fading.move();
            if (_fading.isLastFrame())
            {
                _isFading = false;
            }
        }
    }

    public void ActivateFadeIn()
    {
        _fading = new Animation();
        _fading.addFrame(R.drawable.losing_cover20);
        _fading.addFrame(R.drawable.losing_cover225);
        _fading.addFrame(R.drawable.losing_cover25);
        _fading.addFrame(R.drawable.losing_cover275);
        _fading.addFrame(R.drawable.losing_cover30);
        _fading.addFrame(R.drawable.losing_cover325);
        _fading.addFrame(R.drawable.losing_cover35);
        _fading.addFrame(R.drawable.losing_cover375);
        _fading.addFrame(R.drawable.losing_cover40);
        _fading.addFrame(R.drawable.losing_cover425);
        _fading.addFrame(R.drawable.losing_cover45);
        _fading.setRepeating(false);
        _fading.setDelay(1);
        _isFading = true;
    }

    public void ActivateFadeOut()
    {
        _fading.release();
        _fading = null;
    }

    @Override
    public void release()
    {
        if (_fading != null)
        {
            _fading.release();
        }
    }

    public void Show()
    {
        if (_fading != null)
        {
            _fading.show();
        }
    }

    public boolean IsFadingFinished()
    {
        return _isFading;
    }
}