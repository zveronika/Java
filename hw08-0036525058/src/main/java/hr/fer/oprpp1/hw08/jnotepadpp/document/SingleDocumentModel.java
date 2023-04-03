package hr.fer.oprpp1.hw08.jnotepadpp.document;

import javax.swing.*;
import java.nio.file.Path;

public interface SingleDocumentModel {
    JTextArea getTextComponent();

    Path getFilePath();

    /**
     *
     * @param path can not be null
     */
    void setFilePath(Path path);

    boolean isModified();

    void setModified(boolean modified);

    void addSingleDocumentListener(SingleDocumentListener l);

    void removeSingleDocumentListener(SingleDocumentListener l);
}
