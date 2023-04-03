package hr.fer.oprpp1.hw08.jnotepadpp.local;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationProvider extends AbstractLocalizationProvider {
    private String language;
    private ResourceBundle bundle;

    private static final LocalizationProvider localizationProvider = new LocalizationProvider();

    private LocalizationProvider() {
        language = "en";
        Locale locale = Locale.forLanguageTag(language);
        bundle = ResourceBundle.getBundle("hr.fer.oprpp1.hw08.jnotepadpp.i18n.prijevod", locale);
    }

    public static LocalizationProvider getInstance() {
        return localizationProvider;
    }

    @Override
    public String getString(String key) {
        return bundle.getString(key);
    }

    public void setLanguage(String language) {
        bundle = ResourceBundle.getBundle(
                "hr.fer.zemris.java.hw11.jnotepadpp.i18n.prijevod",
                Locale.forLanguageTag(language));
        this.language = language;
        fire();
    }

    @Override
    public String getLanguage() {
        return language;
    }
}
