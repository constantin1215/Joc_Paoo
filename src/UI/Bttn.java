package UI;

import java.awt.*;

public class Bttn {
    private int x,y,width,height;
    private String text;

    private Rectangle limits;

    public Bttn(String text,int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initLimits();
    }

    private void initLimits() {
        this.limits = new Rectangle(x,y,width,height);
    }

    public void draw(Graphics g) {
        //Body
        g.setColor(Color.yellow);
        g.fillRect(x,y,width,height);

        //Border
        g.setColor(Color.blue);
        g.drawRect(x,y,width,height);

        //Text
        drawText(g);

    }

    private void drawText(Graphics g) {
        int txtWidth = g.getFontMetrics().stringWidth(text);
        int txtHeight = g.getFontMetrics().getHeight();

        g.drawString(text,x + width/2 - txtWidth/2,y + height/2 + txtHeight/2);
    }

    public Rectangle getLimits() {
        return limits;
    }
}
