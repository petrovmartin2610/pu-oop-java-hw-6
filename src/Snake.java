import java.awt.*;

/**
 * @author Martin Petrov
 * Клас генериращ характеристиките на змията
 */
public class Snake extends GameScreenLayout{
    public int row;
    public int col;
    private int pixelSize=12;
    Color color;

    public Snake(int row, int col, Color color){
        super(row, col, color);
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
