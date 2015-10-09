package com.mygdx.game.Plant;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameObjectActor.Card;
import com.mygdx.game.GameObjectActor.Plant;
import com.mygdx.game.Utils.Log;


/**
 * Created by Administrator on 2015/9/16.
 */
public class PlantFactory {
    public Plant newPlant(Card.Kind kind, Stage stage, float x, float y) {
        if (kind == Card.Kind.Peashooter) {
            return new Peashooter(stage, x, y);
        } else if (kind == Card.Kind.SunFlower) {
            return new SunFlower(stage, x, y);
        }
        return null;
    }
}
