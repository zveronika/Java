package hr.fer.oprpp1.hw08.jnotepadpp;

import hr.fer.oprpp1.hw08.jnotepadpp.document.DefaultMultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.document.MultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.local.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.local.LJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.local.LocalizableAction;
import hr.fer.oprpp1.hw08.jnotepadpp.local.LocalizationProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Hello world!
 */
public class JNotepadPP extends JFrame {

    private MultipleDocumentModel multiDocModel;

    private FormLocalizationProvider flp;

    private Action newDocument;
    private Action openDocument;
    private Action saveDocument;
    private Action saveAsDocument;
    private Action closeDocument;
    private Action cutText;
    private Action copyText;
    private Action pasteText;
    private Action statistics;
    private Action exitApp;


    public JNotepadPP() {
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("JNotepad++");
        setLocation(200, 100);
        setSize(1000, 700);

        flp = new FormLocalizationProvider(LocalizationProvider.getInstance(), this);
        flp.addLocalizationListener(() -> {
            if(multiDocModel.getCurrentDocument() != null);
        });

        initGUI();
    }


    private void initGUI() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        DefaultMultipleDocumentModel defaultMultiple = new DefaultMultipleDocumentModel();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(defaultMultiple, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(900,500));
        cp.add(mainPanel, BorderLayout.CENTER);

        this.multiDocModel = defaultMultiple;
        //multiDocModel.addMultipleDocumentListener(null); //TODO

        newDocument = new LocalizableAction("newdocument", flp, KeyStroke.getKeyStroke("control N"));
        openDocument = new LocalizableAction("opendocument", flp, KeyStroke.getKeyStroke("control O"));
        saveDocument = new LocalizableAction("savedocument", flp, KeyStroke.getKeyStroke("control S"));
        saveAsDocument = new LocalizableAction("saveasdocument", flp, KeyStroke.getKeyStroke("control A"));
        closeDocument = new LocalizableAction("closedocument", flp, KeyStroke.getKeyStroke("control W"));
        cutText = new LocalizableAction("cut", flp, KeyStroke.getKeyStroke("control X"));
        copyText = new LocalizableAction("copy", flp, KeyStroke.getKeyStroke("control C"));
        pasteText = new LocalizableAction("paste", flp, KeyStroke.getKeyStroke("control V"));
        statistics = new LocalizableAction("statistics", flp, KeyStroke.getKeyStroke("control T"));
        exitApp = new LocalizableAction("exit", flp, KeyStroke.getKeyStroke("control E"));

        JMenuBar menu = new JMenuBar();
        JMenu file = new LJMenu("file", flp);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JNotepadPP().setVisible(true);
            }
        });
    }

    private static class Icon {

    }
}
