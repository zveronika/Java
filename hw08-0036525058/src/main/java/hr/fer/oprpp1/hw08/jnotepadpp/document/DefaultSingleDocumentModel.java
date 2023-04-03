package hr.fer.oprpp1.hw08.jnotepadpp.document;

import javax.swing.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DefaultSingleDocumentModel implements SingleDocumentModel{

    private JTextArea textArea;

    private Path filePath;

    private boolean isModified;

    private List<SingleDocumentListener> listenerList;

    public DefaultSingleDocumentModel() {
        this.textArea = new JTextArea();
        //TODO
        this.filePath = null;
        this.listenerList = new ArrayList<>();
        this.isModified = false;
    }


    @Override
    public JTextArea getTextComponent() {
        return this.textArea;
    }

    @Override
    public Path getFilePath() {
        return this.filePath;
    }

    @Override
    public void setFilePath(Path path) {
        this.filePath = path;
        for(SingleDocumentListener l : listenerList)
            l.documentFilePathUpdated(this);
    }

    @Override
    public boolean isModified() {
        return this.isModified;
    }

    @Override
    public void setModified(boolean modified) {
        this.isModified = modified;
        for(SingleDocumentListener l : listenerList)
            l.documentModifyStatusUpdated(this);
    }

    @Override
    public void addSingleDocumentListener(SingleDocumentListener l) {
        listenerList.add(l);
    }

    @Override
    public void removeSingleDocumentListener(SingleDocumentListener l) {
        listenerList.remove(l);
    }
}
