import java.awt.*;

/**
 * @author Martin Petrov
 * Клас, генериращ общи характеристики на всички игрални обекти, които впоследствие ще го наследят за да може
 * да се осъществи кастване на обекти при прерисуване на дъската
 */
public class GameScreenLayout{
    private int row;
    private int col;
    private static int pixelSize=12;
    Color color;

    public GameScreenLayout(int row, int col, Color color){
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

    public static int getPixelSize() {
        return pixelSize;
    }
}
