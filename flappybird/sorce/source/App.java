package flappybird.sorce.source;
import javax.swing.*;

public class App {
    public static void main(String[]args){
        int boardwidth=360;
        int boardheight=640;

        JFrame frame=new JFrame("Flappy Bird");
        //frame.setVisible(true);
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);//places the screen at the centre
        frame.setResizable(false);//cant resize the screen by the user
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird=new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);



}
}