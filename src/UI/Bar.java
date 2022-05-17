package UI;

import java.awt.*;

public class Bar {
    protected int x, y, width, height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void btnAnimation(Graphics g, Bttn btn) {
        if (btn.isMouseOver())
            g.setColor(Color.white);
        else
            g.setColor(Color.blue);

        g.drawRect(btn.x, btn.y, btn.width, btn.height);

        if (btn.isMousePressed()) {
            g.drawRect(btn.x + 1, btn.y + 1, btn.width - 2, btn.height - 2);
            g.drawRect(btn.x + 2, btn.y + 2, btn.width - 4, btn.height - 4);
        }
    }
}
