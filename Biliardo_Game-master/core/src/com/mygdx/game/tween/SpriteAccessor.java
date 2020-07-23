package com.mygdx.game.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAccessor implements TweenAccessor<Sprite> {

    public static final int ALPHA =0;
    @Override
    public int getValues(Sprite traget, int TweenType, float[] returnValues) {
        switch (TweenType)
        {
            case ALPHA:
                returnValues[0]= traget.getColor().a;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Sprite target, int TweenType, float[] newValue) {
        switch (TweenType)
        {
            case ALPHA:
                target.setColor(target.getColor().r ,target.getColor().g,target.getColor().b ,newValue[0]);
            default:
                assert false;

        }
    }
}
