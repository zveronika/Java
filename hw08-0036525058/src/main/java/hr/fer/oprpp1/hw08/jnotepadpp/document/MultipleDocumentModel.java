package hr.fer.oprpp1.hw08.jnotepadpp.document;

import javax.swing.*;
import java.nio.file.Path;

public interface MultipleDocumentModel extends Iterable<SingleDocumentModel> {
    JComponent getVisualComponent();

    SingleDocumentModel createNewDocument();

    SingleDocumentModel getCurrentDocument();

    /**
     * @param path must not be null
     * @return
     */
    SingleDocumentModel loadDocument(Path path);

    /**
     * newPath can be null; if null, document should be saved using path associated from
     * document, otherwise, new path should be used and after saving is completed, document’s path should be
     * updated to newPath.
     *
     * @param model
     * @param newPath
     */
    void saveDocument(SingleDocumentModel model, Path newPath);

    /**
     * removes specified document (does not check modification status or ask any questions)
     *
     * @param model
     */
    void closeDocument(SingleDocumentModel model);

    void addMultipleDocumentListener(MultipleDocumentListener l);

    void removeMultipleDocumentListener(MultipleDocumentListener l);

    int getNumberOfDocuments();

    SingleDocumentModel getDocument(int index);

    /**
     * @param path given path must not be null
     * @return
     */
    SingleDocumentModel findForPath(Path path);

    /**
     * @param doc
     * @return -1 if not present
     */
    int getIndexOfDocument(SingleDocumentModel doc);
}
