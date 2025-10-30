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
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args){

        // add super large font to text
        Font bigFont = new Font("Comic Sans MS", Font.BOLD, 20);
        UIManager.put("Label.font", bigFont);
        UIManager.put("Button.font", bigFont);
        UIManager.put("TextField.font", bigFont);


        JFrame frame = new JFrame("Flashcard App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Welcome to the Flashcard App!");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2 = new JLabel("Get started by adding some flashcards!");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // user adds flashcards:
        // TODO: loop until button is pressed to continue to add flashcards
        JPanel flashcardPanel = new JPanel();
        flashcardPanel.setLayout(new BoxLayout(flashcardPanel, BoxLayout.Y_AXIS));

        JButton add = new JButton("Add Flashcard");
        add.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton stop = new JButton("Stop Adding Flashcards");
        stop.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Flashcard> flashcards = new ArrayList<>();
        int[] flashCount = {1};

        add.addActionListener(e -> {
            Flashcard card = new Flashcard(flashCount[0]++);
            flashcards.add(card);
            flashcardPanel.add(card);
            flashcardPanel.revalidate();
            flashcardPanel.repaint();
        }); // when the button is pressed, there will be a new card every time

        // when done:
        Collections.shuffle(flashcards); // make a random list of flashcards

        stop.addActionListener(e -> {
            flashcardPanel.setVisible(false);
            add.setVisible(false);
            stop.setVisible(false);
            label1.setVisible(false);
            label2.setVisible(false); // make everything go away

            JPanel study = new JPanel(new BorderLayout());
            JLabel front = new JLabel();
            JLabel back = new JLabel();
            back.setVisible(false);

            JButton showBack = new JButton("Show Answer");
            JButton knownB = new JButton("Known");
            JButton unknown = new JButton("Unknown");

            JPanel buttons = new JPanel();
            buttons.add(showBack);
            buttons.add(knownB);
            buttons.add(unknown);

            JLabel label3 = new JLabel();

            // next card logic:

            int[] index = {0};
            int[] known = {0};

            Runnable showNextCard  = () -> {
                if (index[0] < flashcards.size()){
                    Flashcard curr = flashcards.get(index[0]);
                    front.setText("Term: " + curr.getFront());
                    back.setText("Definition: " + curr.getBack());
                    back.setVisible(false);
                    label3.setText("Card " + index[0] + " of " + flashcards.size());
                }

                else {
                    // end of study session
                    frame.getContentPane().removeAll();
                    JLabel result = new JLabel("You got " + known[0] + " out of " + flashcards.size() + " correct!");
                    JButton restart = new JButton("Start Over");
                    restart.addActionListener(r -> frame.dispose()); // restart
                    frame.add(result);
                    frame.add(restart);
                    frame.revalidate();
                    frame.repaint();
                }
            };

            showBack.addActionListener(b -> {
                back.setVisible(true);
            });

            knownB.addActionListener(c -> {
                known[0]++;
                index[0]++;
                showNextCard.run(); // show the next card if known
            });

            unknown.addActionListener(x -> {
                index[0]++;
                showNextCard.run(); // show next card if unknown
            });

            showNextCard.run();

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BorderLayout());
            bottomPanel.add(buttons, BorderLayout.CENTER);
            bottomPanel.add(label3, BorderLayout.SOUTH);

            study.add(front, BorderLayout.NORTH);
            study.add(back, BorderLayout.CENTER);
            study.add(bottomPanel, BorderLayout.SOUTH);

            frame.add(study);
        });


        frame.add(label1); // add everything together
        frame.add(label2);
        frame.add(flashcardPanel);
        frame.add(add);
        frame.add(stop);


        frame.pack(); // reframe every time, make sure everything is added before this

        frame.setBackground(Color.decode("#FFFCF2"));

        frame.setVisible(true);

    }
}
