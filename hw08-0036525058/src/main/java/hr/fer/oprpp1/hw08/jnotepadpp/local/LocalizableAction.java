package hr.fer.oprpp1.hw08.jnotepadpp.local;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LocalizableAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    private String key;

    private ILocalizationProvider lp;

    public LocalizableAction(String key, ILocalizationProvider lp){
        this.key = key;
        this.lp = lp;
        putValue(Action.NAME, lp.getString(key));
        this.lp.addLocalizationListener(() -> {
            this.putValue(Action.NAME, lp.getString(key));
        });
    }

    public LocalizableAction(String key, ILocalizationProvider lp, KeyStroke ks){
        this.key = key;
        this.lp = lp;
        putValue(Action.NAME, lp.getString(key));
        putValue(Action.ACCELERATOR_KEY, ks);
        this.lp.addLocalizationListener(() -> {
            this.putValue(Action.NAME, lp.getString(key));
            this.putValue(Action.ACCELERATOR_KEY, ks);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
