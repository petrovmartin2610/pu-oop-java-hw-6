import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Martin Petrov
 * Клас GameScreen, изобразяващ игралната дъска и отделните елементи в нея (препятствия, ябълки, змия)
 */
public class GameScreen extends JFrame implements MouseListener {
    private GameScreenLayout[][] gameScreen;                //обект-масив съхраняващ всички елементи
    int originalSnakeRow;                                   //глобална пр-ва съхраняваща оригиналната генерирана row коорд-та за змията
    int originalSnakeCol;                                   //глобална пр-ва съхраняваща оригиналната генерирана col коорд-та за змията
    int pointsCount;                                        //глобална пр-ва съхраняваща броя натрупани точки до момента
    int numberOfApples;                                     //глобална пр-ва съхраняваща броя генерирани ябълки
    Snake s1;
    Obstacles o1;
    Apples a1;


    public void renderGameScreen() {
        this.gameScreen = new GameScreenLayout[35][35];
        Color color = null;


        //първоначално изобразяване на цялото игрално поле
        int row = 0;
        int col = 0;
        for (row = 1; row < 35; row++) {
            for (col = 1; col < 35; col++) {
                this.gameScreen[row][col] = new GameScreenLayout(row, col, color.GREEN);
            }
        }
        //извикване на съответни методи за изрисуване на отделните обекти в/у вече генерираното поле
        printObstacles();
        printApples();
        printSnake();

        //хар-ки на JFrame
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

    //метод изрисуващ препятствията по игралното поле
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

    //метод изрисуващ ябълките по игралното поле
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

    //метод изрисуващ змията по игралното поле
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

    //метод печатещ съобщение за победител
    public void youAreAWinner() {
        System.out.println("You are a winner!");
    }

    //метод печатещ съобщение за край на играта
    public void gameOver() {
        System.out.println("Game over!");
    }

    //метод взимащ отделните квадратчета от игралното поле
    private Object getBoardPixel(int row, int col) {
        return this.gameScreen[row][col];
    }

    //метод изчисляващ координатите на квадратчетата
    private int tileCoordinates(int pixelCoordinates) {
        return pixelCoordinates / GameScreenLayout.getPixelSize();
    }


    //метод извършващ движение на змията и действия с отделни обекти чрез кликане от потребителя
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
