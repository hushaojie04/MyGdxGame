package com.mygdx.game.Model;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Utils.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2015/9/14.
 */
public class Cell {
    public final float rowLen, colLen;
    public final Rectangle rectangle;
    private List<CellInfo> cells = new ArrayList<CellInfo>();

    public Cell(Rectangle rectangle, float row, float col) {
        this.rectangle = rectangle;
        this.rowLen = row;
        this.colLen = col;
        float width_item = rectangle.width / col;
        float height_item = rectangle.height / row;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                CellInfo info = new CellInfo(new Rectangle(rectangle.x + width_item * j,
                        rectangle.y + height_item * i,
                        width_item, height_item), i, j);
                Log.show("cellinfo:", rectangle.x + width_item * i,
                        rectangle.y + height_item * j,
                        width_item, height_item);
                cells.add(info);
            }
        }

    }

    public List<CellInfo> getCells() {
        return cells;
    }

    public CellInfo getCellInfo(float x, float y) {
        Iterator<CellInfo> iterator = cells.iterator();
        while (iterator.hasNext()) {
            CellInfo cellInfo = iterator.next();
            if (cellInfo.rectangle.contains(x, y)) {
                return cellInfo;
            }
        }
        return null;
    }

    public static class CellInfo {
        public final int row, col;
        public final Rectangle rectangle;
        public boolean isGrow = false;

        public CellInfo(Rectangle rectangle, int row, int col) {
            this.row = row;
            this.col = col;
            this.rectangle = rectangle;
        }

        private Object tag;

        public Object getTag() {
            return tag;
        }

        public void setTag(Object object) {
            this.tag = object;
        }
    }
}
