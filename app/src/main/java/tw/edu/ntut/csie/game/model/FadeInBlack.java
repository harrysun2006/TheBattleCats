package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/6/11.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.ReleasableResource;

public class FadeInBlack implements ReleasableResource
{
    private Animation _fading;
    private MovingBitmap _faded;
    private boolean _isFaded;

    public FadeInBlack()
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
        _fading.addFrame(R.drawable.losing_cover475);
        _fading.addFrame(R.drawable.losing_cover50);
        _fading.addFrame(R.drawable.losing_cover525);
        _fading.addFrame(R.drawable.losing_cover55);
        _fading.addFrame(R.drawable.losing_cover575);
        _fading.addFrame(R.drawable.losing_cover60);
        _fading.addFrame(R.drawable.losing_cover625);
        _fading.addFrame(R.drawable.losing_cover65);
        _fading.addFrame(R.drawable.losing_cover675);
        _fading.addFrame(R.drawable.losing_cover70);
        _fading.addFrame(R.drawable.losing_cover725);
        _fading.addFrame(R.drawable.losing_cover75);
        _fading.addFrame(R.drawable.losing_cover775);
        _fading.addFrame(R.drawable.losing_cover80);
        _fading.addFrame(R.drawable.losing_cover825);
        _fading.addFrame(R.drawable.losing_cover85);
        _fading.addFrame(R.drawable.losing_cover875);
        _fading.addFrame(R.drawable.losing_cover90);
        _fading.addFrame(R.drawable.losing_cover925);
        _fading.addFrame(R.drawable.losing_cover95);
        _fading.addFrame(R.drawable.losing_cover975);
        _fading.addFrame(R.drawable.losing_cover100);
        _fading.setDelay(1);
        _fading.setVisible(false);

        _faded = new MovingBitmap(R.drawable.losing_cover100);
        _faded.setVisible(false);

        _isFaded = false;
    }

    public void Run()
    {
        if (_isFaded) //如果已經淡入結束則直接return
        {
            return;
        }
        if (!_fading.isLastFrame()) //尚未淡入結束的時候
        {
            _fading.setVisible(true);
            _fading.move();
        }
        else //淡入結束的時候把_fading動畫release掉，同時讓畫面只有_faded這個黑色畫面
        {
            _fading.release();
            _fading = null;
            _faded.setVisible(true);
            _isFaded = true;
        }
    }

    @Override
    public void release()
    {
        if (!_isFaded)
        {
            _fading.release();
        }
        _faded.release();
    }

    public void Show()
    {
        if (!_isFaded)
        {
            _fading.show();
        }
        _faded.show();
    }

    public boolean IsFadeInFinished()
    {
        return _isFaded;
    }
}