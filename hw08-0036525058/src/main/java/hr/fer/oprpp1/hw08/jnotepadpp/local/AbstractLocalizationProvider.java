package hr.fer.oprpp1.hw08.jnotepadpp.local;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLocalizationProvider implements ILocalizationProvider {

    private List<ILocalizationListener> listenerList;

    public AbstractLocalizationProvider() {
        this.listenerList = new ArrayList<>();
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public String getLanguage() {
        return null;
    }

    @Override
    public void addLocalizationListener(ILocalizationListener l) {
        listenerList.add(l);
    }

    @Override
    public void removeLocalizationListener(ILocalizationListener l) {
        listenerList.remove(l);
    }

    public void fire() {
        for (ILocalizationListener l : listenerList) {
            l.localizationChanged();
        }
    }
}
