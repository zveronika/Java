package hr.fer.oprpp1.hw08.jnotepadpp.document;

public interface MultipleDocumentListener {
    /**
     * previousModel or currentModel can be null but not both. Here the term
     * “current” document means the one that the user is currently interacting with; you can also think about it as
     * the “active” document.
     *
     * @param previousModel
     * @param currentModel
     */
    void currentDocumentChanged(SingleDocumentModel previousModel,
                                SingleDocumentModel currentModel);

    void documentAdded(SingleDocumentModel model);

    void documentRemoved(SingleDocumentModel model);

}
