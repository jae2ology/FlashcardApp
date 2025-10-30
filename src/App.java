// main app file

// study app should work like quizlet flashcard section. users can create flashcards,
// put anything on the back (description), front should be a word or idea.
// computer will randomly output the list of flashcards that the user creates
// should have a "stop" adding flashcards button to stop creating flashcards
// after that, the user can start studying, clicking though the flashcards as
// "known" or "unknown"
// app will output the grade (known/total)
// the app will have a restart or start over option.

import javax.swing.*; // mainly using javax
import java.awt.*;

public class App {
    public static void main(String[] args){
        JFrame frame = new JFrame("Flashcard App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label1 = new JLabel("Welcome to the Flashcard App!");
        label1.setPreferredSize(new Dimension(200, 50));

        JLabel label2 = new JLabel("Get started by adding some flashcards!");
        label1.setPreferredSize(new Dimension(200, 50));

        // user adds flashcards:
        // TODO: loop until button is pressed to continue to add flashcards

        frame.add(label1); // add everything together
        frame.add(label2);

        frame.pack(); // reframe every time, make sure everything is added before this
        frame.setLocationRelativeTo(null);

        frame.setSize(500, 750);
        frame.setBackground(Color.decode("#FFFCF2"));

        frame.setVisible(true);

    }
}
