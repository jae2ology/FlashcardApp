// flashcard class
import javax.swing.*;
import java.awt.*;

public class FlashcardPanel extends JPanel{
    private JTextField front;
    private JTextField back;

    public FlashcardPanel(int number){
        setLayout(new FlowLayout());
        setBackground(Color.decode("#F08CAE")); // pink

        JLabel label1 = new JLabel("Flashcard " + number);
        front = new JTextField( 5); // front = word
        back = new JTextField(15); // back = description

        label1.setSize(50, 150);
        add(label1);
        add(front);
        add(back);
    }

    public String getFront(){
        return front.getText();
    }

    public String getBack() {
        return back.getText();
    }
}
