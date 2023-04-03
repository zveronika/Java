package hr.fer.oprpp1.hw08.jnotepadpp.document;

import javax.swing.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultMultipleDocumentModel extends JTabbedPane implements MultipleDocumentModel {

    private JComponent component;
    private List<SingleDocumentModel> documentList;
    private SingleDocumentModel current;

    private List<MultipleDocumentListener> listenerList;

    public DefaultMultipleDocumentModel() {
        //TODO
        this.documentList = new ArrayList<>();
        this.current = null;
    }

    @Override
    public JComponent getVisualComponent() {
        return this.component;
    }

    @Override
    public SingleDocumentModel createNewDocument() {
        return new DefaultSingleDocumentModel();
    }

    @Override
    public SingleDocumentModel getCurrentDocument() {
        return this.current;
    }

    @Override
    public SingleDocumentModel loadDocument(Path path) {
        return null;
    }

    @Override
    public void saveDocument(SingleDocumentModel model, Path newPath) {

    }

    @Override
    public void closeDocument(SingleDocumentModel model) {

    }

    @Override
    public void addMultipleDocumentListener(MultipleDocumentListener l) {
        listenerList.add(l);
    }

    @Override
    public void removeMultipleDocumentListener(MultipleDocumentListener l) {
        listenerList.remove(l);
    }

    @Override
    public int getNumberOfDocuments() {
        return documentList.size();
    }

    @Override
    public SingleDocumentModel getDocument(int index) {
        if (index < 0 || index > getNumberOfDocuments())
            throw new IllegalArgumentException("Index of document is out of bounds.");
        return documentList.get(index);
    }

    @Override
    public SingleDocumentModel findForPath(Path path) {
        //TODO
        return null;
    }

    @Override
    public int getIndexOfDocument(SingleDocumentModel doc) {
        int i = 0;
        for (SingleDocumentModel d : documentList) {
            if (d.equals(doc))
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public Iterator<SingleDocumentModel> iterator() {
        return documentList.iterator();
    }
}
