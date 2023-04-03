package hr.fer.oprpp1.hw08.jnotepadpp.local;

public class LocalizationProviderBridge extends AbstractLocalizationProvider {

    private boolean connected;

    private String language;

    private ILocalizationProvider decorator;

    private ILocalizationListener listener = new ILocalizationListener() {
        @Override
        public void localizationChanged() {
            language = decorator.getLanguage();
            fire();
        }
    };

    public LocalizationProviderBridge(ILocalizationProvider l) {
        this.decorator = l;
        this.language = decorator.getLanguage();
        this.connected = false;
    }

    public void disconnect() {
        if (!connected) return;

        decorator.removeLocalizationListener(listener);
        connected = false;
    }

    public void connect() {
        if (connected) return;

        decorator.addLocalizationListener(listener);
        connected = true;

        if (!decorator.getLanguage().equals(language)) {
            language = decorator.getLanguage();
            fire();
        }
    }

    @Override
    public String getString(String key) {
        return decorator.getString(key);
    }

    @Override
    public String getLanguage() {
        return language;
    }
}
