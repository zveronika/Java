package hr.fer.oprpp1.hw08.jnotepadpp.local;

import javax.swing.*;

public class LJMenu extends JMenu {

    private static final long serialVersionUID = 1L;

    private String key;

    private ILocalizationProvider lp;

    public LJMenu(String key, ILocalizationProvider lp){
        this.key = key;
        this.lp = lp;
        this.lp.addLocalizationListener(() -> {
            this.setText(lp.getString(key));
        });
    }
}
