import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Vui extends JDialog{
    //Attributs
    private JPanel JpMain;
    private JLabel jlMenu;
    private JButton addButton;
    private JButton removeButton;
    private JButton listButton;
    private JButton catButton;
    private JButton quitButton;
    private JPanel dynArea;
    private JPanel addArea;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton cancelButton;
    private JButton validateButton;
    private JTextPane textPane1;


    //Constructeur
    public Vui(JDialog parent){
        super(parent);

        ArrayList<Livre> cdaLibrary = new ArrayList<Livre>();
        Requete.loadDB(cdaLibrary);
        String cat="        へ     ♡  ╱|、\n     ૮ - ՛ )    (`- 7\n      / ⁻ ៸|      |、⁻〵\n   乀(ˍ,ل ل      じしˍ,)ノ";
        String header="==================================================Secret Library==================================================";
        setTitle("Secret Library");
        //Assigner le container
        setContentPane(JpMain);
        //Assigner la dimension de la fenêtre
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));
        //choisir si c'est un modal
        setModal(false);
        setVisible(true);

        addArea.setVisible(false);

        addButton.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addArea.setVisible(true);
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addArea.setVisible(false);
            }
        });
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textField1.getText();
                String description = textPane1.getText();
                String datePublication = textField3.getText();
                ArrayList<String> genre = new ArrayList<>();
                Livre newBook = new Livre(title, description, datePublication, genre);
                newBook.setGenreFromString(textField4.getText());

                System.out.println("Book created: " + newBook);
                cdaLibrary.add(newBook);
                Requete.addLivreDB(newBook,cdaLibrary);
                textField1.setText("");
                textPane1.setText("");
                textField3.setText("");
                textField4.setText("");
            }
        });
    }
}