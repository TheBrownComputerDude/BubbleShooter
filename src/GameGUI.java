import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameGUI extends JPanel
{
    private BubbleGame game;
    private JFrame frame;
    private int width;
    private int height;
    private double mouseX;
    private double mouseY=20;
    private boolean fired;

    private int x1;
    private int y1;
    private int x2 = 0;
    private int y2 = 0;

    public GameGUI()
    {
        this.frame = new JFrame();
        int buffer = Shared.DIAMETER * 3;
        this.width = Shared.COLS*Shared.DIAMETER + Shared.DIAMETER;
        this.height = Shared.ROWS*Shared.DIAMETER + Shared.DIAMETER;

        this.x1 = this.width/2;
        this.y1 = this.height+Shared.RADIUS;
        this.fired = false;

        this.frame.setSize(this.width,this.height + buffer);
        this.frame.setTitle("Bubble Shooter Game");
        this.frame.setResizable(false);
        this.frame.getMousePosition();
        this.game = new BubbleGame(Shared.ROWS,Shared.COLS,6,3);
        Timer t = new Timer(100, e -> {
            if(fired)
            {
                Bubble b = game.getNextBubble();
                int x = (int)(b.getX() + b.getChangeX());
                int y = (int)(b.getY() - b.getChangeY());
                if(x <= 0 || x + Shared.DIAMETER >= this.width) b.setChangeX(b.getChangeX() * -1);
                b.setX(x);
                b.setY(y);
                if(game.checkCollision())
                {
                    fired = false;
                    game.addBubble(b);
                    game.generateNextBubble();
                }
            }
            else if(mouseY > 0)
            {
                try
                {
                    mouseX = frame.getMousePosition().x - width / 2 - 10;
                    mouseY = height - frame.getMousePosition().y + 55;
                    double theta = Math.atan(mouseX / mouseY);
                    x2 = (int) (100 * Math.sin(theta));
                    y2 = (int) (100 * Math.cos(theta));
                }catch(Exception v){}
            }
            this.repaint();
        });
        t.setRepeats(true);
        t.start();
        this.frame.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                double theta = Math.atan(mouseX / mouseY);
                int changeX = (int) (Shared.SPEED * Math.sin(theta));
                int changeY = (int) (Shared.SPEED * Math.cos(theta));
                game.getNextBubble().setChangeX(changeX);
                game.getNextBubble().setChangeY(changeY);
                fired=true;
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
        this.frame.add(this);

        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.clearRect(0,0,this.width,this.height + Shared.DIAMETER*3);

        for(int i = 0; i < game.getRows(); i++)
        {
            for(int j = 0; j < game.getCols(); j++)
            {
                Bubble b = game.getBubble(i,j);
                g.setColor(Shared.colorMap.get(b.getColor()));
                g.fillOval(j*Shared.DIAMETER + (i % 2 == 0 ? 0 : Shared.RADIUS),i*Shared.DIAMETER,Shared.DIAMETER,Shared.DIAMETER);
            }
        }

        Bubble b = game.getNextBubble();
        g.setColor(Color.BLACK);
        if(!fired) g.drawLine(x1,y1,x1+x2,y1-y2);
        g.setColor(Shared.colorMap.get(b.getColor()));
        g.fillOval((int)b.getX(), (int)b.getY() , Shared.DIAMETER, Shared.DIAMETER);
    }
    public static void main(String[] args)
    {
        new GameGUI();
    }
}
