
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameScreen extends JFrame implements MouseListener {
    private Object[][] gameScreen;

    public void renderGameScreen(){
        this.gameScreen = new Object[35][35];
        Color color = null;


        int row=0;
        int col=0;
        for(row=1; row<35; row++){
            for(col=1; col<35; col++){
                this.gameScreen[row][col] = new GameScreenLayout(row,col,color.GREEN);
            }
        }
        printObstacles();
        printApples();

        this.setSize(700, 700);
        this.setVisible(true);
        this.setTitle("Snake game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 1; row < 35; row++) {
            for (int col = 1; col < 35; col++) {
                GameScreenLayout gsl1 = (GameScreenLayout) this.getBoardPixel(row, col);
                gsl1.render(g);
            }
        }
    }

    public void printObstacles(){
        int numberOfGeneratedObstacles = ThreadLocalRandom.current().nextInt(1, 10);

        for(int i=0; i < numberOfGeneratedObstacles; ++i){
            int row = ThreadLocalRandom.current().nextInt(1, 35);
            int col = ThreadLocalRandom.current().nextInt(1, 35);
            GameScreenLayout gsl2 = new Obstacles(row,col,Color.BLACK);
            Obstacles o1 = (Obstacles) gsl2;
            this.gameScreen[row][col]=o1;
            this.repaint();
        }
    }

    public void printApples(){
        int numberOfGeneratedApples = ThreadLocalRandom.current().nextInt(1, 20);

        for(int i=0; i < numberOfGeneratedApples; ++i){
            int row = ThreadLocalRandom.current().nextInt(1, 35);
            int col = ThreadLocalRandom.current().nextInt(1, 35);
            GameScreenLayout gsl3 = new Apples(row,col,Color.RED);
            Apples a1 = (Apples) gsl3;
            this.gameScreen[row][col]=a1;
            this.repaint();
        }
    }

    private Object getBoardPixel(int row, int col) {
        return this.gameScreen[row][col];
    }









    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
