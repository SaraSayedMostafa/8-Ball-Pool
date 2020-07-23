package com.mygdx.game.tween;
import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorAccessor implements TweenAccessor<Actor> {
    public static final int ALPHA=1,Y=0;
    @Override
    public int getValues(Actor target, int TweenType, float[] returnValues) {
        switch (TweenType)
        {
            case Y:
                returnValues[0]=target.getY();
                return 1;

            case ALPHA:
                returnValues[0]= target.getColor().a;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Actor target, int TweenType, float[] returnValues) {
        switch (TweenType)
        {
            case Y:
                target.setY(returnValues[0]);
                break;
            case ALPHA:
                target.setColor(target.getColor().r ,target.getColor().g,target.getColor().b ,returnValues[0]);
                break;
            default:
                assert false;

        }
    }
}

