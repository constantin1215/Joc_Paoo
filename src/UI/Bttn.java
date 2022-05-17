package UI;

import java.awt.*;

public class Bttn {
    public int x, y, width, height, id;
    private final String text;
    private Rectangle limits;
    private boolean mouseOver, mousePressed;

    public Bttn(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;

        initLimits();
    }

    public Bttn(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initLimits();
    }

    private void initLimits() {
        this.limits = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        drawBody(g);
        drawBorder(g);
        drawText(g);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(x, y, width, height);
        if (mousePressed) {
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
    }

    private void drawBody(Graphics g) {
        if (mouseOver)
            g.setColor(Color.red);
        else
            g.setColor(Color.yellow);
        g.fillRect(x, y, width, height);
    }

    private void drawText(Graphics g) {
        int txtWidth = g.getFontMetrics().stringWidth(text);
        int txtHeight = g.getFontMetrics().getHeight();

        g.drawString(text, x + width / 2 - txtWidth / 2, y + height / 2 + txtHeight / 2);
    }

    public Rectangle getLimits() {
        return limits;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void resetBool() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public int getId() {
        return id;
    }
}
