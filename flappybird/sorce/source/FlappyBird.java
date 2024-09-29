package flappybird.sorce.source;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class FlappyBird extends JPanel implements ActionListener,KeyListener{
    int boardwidth=360;
    int boardheight=640;
  Image backgroundImage;
  Image birdImage;
  Image toppipImage;
  Image bottompipImage;

  int birdx=boardwidth/8;
  int birdy=boardheight/2;
  int birdwidth=34;
  int birdheight=22;

  class Bird{
    int x=birdx;
    int y=birdy;
    int width=birdwidth;
    int height=birdheight;
    Image img;
     Bird(Image img)
     {
        this.img=img;

     }
  }
  //pipes
  int pipex=boardwidth;
  int pipey=0;
  int pipewidth=64;
  int pipeheight=512;
  class Pipe{
    int x=pipex;
    int y=pipey;
    int width=pipewidth;
    int height=pipeheight;
    Image img;
    boolean passed =false;

    Pipe(Image img)
    {
        this.img=img;
    }


  }
//game logic
  Bird bird;
  int velocityX=-4;
  int velocityY=0;
  int gravity=1;
  double score=0;


  ArrayList<Pipe> pipes;
  Random random=new Random(); 

  Timer gameLoop;
  Timer placePipesTimer;
  boolean gameOver=false;


    FlappyBird()
    {
        setPreferredSize(new Dimension(boardwidth,boardheight));
        //setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);
        //load images
        backgroundImage=new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImage=new ImageIcon(getClass().getResource("./flappybird.png")).getImage();

        bottompipImage=new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        toppipImage=new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        
        bird=new Bird(birdImage);
        pipes=new ArrayList<Pipe>();
        placePipesTimer= new Timer(1500,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                placePipes();
            }
        });
        placePipesTimer.start();
        //game timing
        gameLoop=new Timer(1000/60,this);
        gameLoop.start();


    }
    public void placePipes()
    {
        //result-0-256 range
        //128 
        //0-128-(range)--> gives th upper random pipes//3/4th of pipe height

        int randomPipeY=(int) (pipey - pipeheight/4 - Math.random()*(pipeheight/2));
        int openingSpace= boardheight/4;

        Pipe topPipe=new Pipe(toppipImage);
        topPipe.y=randomPipeY;
        pipes.add(topPipe); 

        Pipe bottomPipe= new Pipe(bottompipImage);
        bottomPipe.y=topPipe.y+ pipeheight + openingSpace;
        pipes.add(bottomPipe);

    }
    public void paintComponent(Graphics g)
       {
        super.paintComponent(g);
        draw(g);

       }
       public void draw(Graphics g)
       {
        g.drawImage(backgroundImage,0,0,boardwidth,boardheight,null);

        //bird
        g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);

        for(int i=0;i<pipes.size();i++)
        {
            Pipe pipe=pipes.get(i);
            g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,34));
        if(gameOver)
        {
            g.drawString("Game Over: " + String.valueOf((int)score),10,35);
        }
        else{
            g.drawString(String.valueOf((int)score),10,35);
        }
       }
       public void move()
       {
        velocityY+=gravity;
        bird.y+=velocityY;
        bird.y=Math.max(bird.y,0);


        //pipes
        for(int i=0;i<pipes.size();i++)
        {
            Pipe pipe=pipes.get(i);
            pipe.x+= velocityX;
            if(!pipe.passed && bird.x >pipe.x +pipe.width)
            {
                pipe.passed=true;
                score+=0.5;

            }

            if(collision(bird,pipe)){ 
                  gameOver=true; 
            }


        }

        if(bird.y > boardheight)
        { 
            gameOver=true;

        }


       }
       public boolean collision(Bird a,Pipe b)
       {
        return a.x < b.x + b.width &&   //a's top left corner doesn't reach b's top right corner
        a.x + a.width > b.x &&   //a's top right corner passes b's top left corner
        a.y < b.y + b.height &&  //a's top left corner doesn't reach b's bottom left corner
        a.y + a.height > b.y;
       }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            placePipesTimer.stop();
            gameLoop.stop();

        }
    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_SPACE){
              velocityY+=-9;
              if(gameOver)
              {
                //restart the game
                velocityY=0;
                bird.y=birdy;
                pipes.clear();
                score=0;
                gameOver=false;
                gameLoop.start();
                placePipesTimer.start();
                

                
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
       
    }
    @Override
    public void keyReleased(KeyEvent e) {
    
        
    }    
}
