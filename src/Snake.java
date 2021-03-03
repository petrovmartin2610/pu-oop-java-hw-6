import java.awt.*;

public class Snake{
    private int row;
    private int col;
    private int pixelSize=12;
    Color color;

    public Snake(int row, int col, Color color){
        this.row=row;
        this.col=col;
        this.color=color;
    }



    public void render(Graphics g) {
        int tileX = this.row * pixelSize;
        int tileY = this.col * pixelSize;
        g.setColor(this.color);
        g.fillRect(tileX, tileY, pixelSize, pixelSize);
    }

}
