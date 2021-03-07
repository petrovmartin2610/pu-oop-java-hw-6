import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameScreen extends JFrame implements MouseListener {
    private GameScreenLayout[][] gameScreen;
    int originalSnakeRow;
    int originalSnakeCol;
    int pointsCount;
    int numberOfApples;
    Snake s1;
    Obstacles o1;
    Apples a1;


    public void renderGameScreen() {
        this.gameScreen = new GameScreenLayout[35][35];
        Color color = null;


        int row = 0;
        int col = 0;
        for (row = 1; row < 35; row++) {
            for (col = 1; col < 35; col++) {
                this.gameScreen[row][col] = new GameScreenLayout(row, col, color.GREEN);
            }
        }
        printObstacles();
        printApples();
        printSnake();

        this.setSize(700, 700);
        this.setVisible(true);
        this.setTitle("Snake game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);

        JPanel buttonContainer = new JPanel();
        JButton start = new JButton("Start");
        JButton pause = new JButton("Pause");
        JButton restart = new JButton("Restart");
        buttonContainer.add(start);
        buttonContainer.add(pause);
        buttonContainer.add(restart);
        buttonContainer.setLayout(null);
        buttonContainer.setBounds(500, 100, 50, 50);
        buttonContainer.setSize(250, 250);
        buttonContainer.setVisible(true);
        buttonContainer.repaint(500, 100, 50, 50);
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

    public void printObstacles() {
        int numberOfGeneratedObstacles = ThreadLocalRandom.current().nextInt(1, 10);

        for (int i = 0; i < numberOfGeneratedObstacles; ++i) {
            int row = ThreadLocalRandom.current().nextInt(1, 35);
            int col = ThreadLocalRandom.current().nextInt(1, 35);
            GameScreenLayout gsl2 = new Obstacles(row, col, Color.BLACK);
            o1 = (Obstacles) gsl2;
            this.gameScreen[row][col] = o1;
            this.repaint();
        }
    }

    public void printApples() {
        int numberOfGeneratedApples = ThreadLocalRandom.current().nextInt(1, 20);

        for (int i = 0; i < numberOfGeneratedApples; ++i) {
            int row = ThreadLocalRandom.current().nextInt(1, 35);
            int col = ThreadLocalRandom.current().nextInt(1, 35);
            GameScreenLayout gsl3 = new Apples(row, col, Color.RED);
            a1 = (Apples) gsl3;
            this.gameScreen[row][col] = a1;
            this.repaint();
        }
        numberOfApples = numberOfGeneratedApples;
    }

    public void printSnake() {
        int row = ThreadLocalRandom.current().nextInt(1, 35);
        int col = ThreadLocalRandom.current().nextInt(1, 35);
        GameScreenLayout gsl4 = new Snake(row, col, Color.YELLOW);
        s1 = (Snake) gsl4;
        this.gameScreen[row][col] = s1;
        this.repaint();
        originalSnakeRow = row;
        originalSnakeCol = col;
    }

    public void youAreAWinner() {
        System.out.println("You are a winner!");
    }

    public void gameOver() {
        System.out.println("Game over!");
    }

    private Object getBoardPixel(int row, int col) {
        return this.gameScreen[row][col];
    }

    private int tileCoordinates(int pixelCoordinates) {
        return pixelCoordinates / GameScreenLayout.getPixelSize();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println(this.tileCoordinates(e.getX()));
        System.out.println(this.tileCoordinates(e.getY()));

        int row = this.tileCoordinates(e.getX());
        int col = this.tileCoordinates(e.getY());


        GameScreenLayout gsl3 = new GameScreenLayout(row, col, Color.GREEN);
        Snake s3 = new Snake(row, col, Color.YELLOW);
        if (row != originalSnakeRow) {
            this.gameScreen[row - 1][col] = gsl3;

        }
        if (col != originalSnakeCol) {
            this.gameScreen[row][col - 1] = gsl3;

        }
        this.gameScreen[row][col] = s3;

        if (s3.row == o1.row & s3.col == o1.col){
            gameOver();
            System.exit(0);
        }
        if (s3.row == a1.row & s3.col == a1.col) {
            pointsCount++;
            numberOfApples--;
            System.out.println("Points: " + pointsCount);
            System.out.println("Apples: " + numberOfApples);
            if (numberOfApples == 0) {
                printApples();
            }
        }
        if (pointsCount > 300) {
            youAreAWinner();
        }
        this.repaint();
        /**код с private статични row и col и техни гетъри, поради някаква причина съсипва изобразяването на
         игралното поле, отделните обекти се изобразяват бели
         /------------------------------------------------------------------------------------------------/
         GameScreenLayout gsl3 = new GameScreenLayout(row,col,Color.GREEN);
         this.gameScreen[originalSnakeRow][originalSnakeCol]=gsl3;
         Snake s3 = new Snake (row,col,Color.YELLOW);
         this.gameScreen[row][col]=s3;
         originalSnakeRow=s3.getRow();
         originalSnakeCol=s3.getCol();
         this.repaint();
         if(Snake.getRow() == Obstacles.getRow() && Snake.getCol() == Obstacles.getCol()){
         gameOver();
         System.exit(0);
         }
         if(Snake.getRow() == Apples.getRow() && Snake.getCol() == Apples.getCol()){
         pointsCount++;
         numberOfApples--;
         System.out.println(pointsCount);
         System.out.println(numberOfApples);
         if(numberOfApples==0){
         printApples();
         }
         }
         if(pointsCount>300){
         youAreAWinner();
         }
         /--------------------------------------------------------------------------------------------/
         */


    }
        @Override
        public void mousePressed (MouseEvent e){

        }

        @Override
        public void mouseReleased (MouseEvent e){

        }

        @Override
        public void mouseEntered (MouseEvent e){

        }

        @Override
        public void mouseExited (MouseEvent e){

        }


}
