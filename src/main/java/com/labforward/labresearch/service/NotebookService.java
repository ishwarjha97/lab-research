package com.labforward.labresearch.service;

import com.labforward.labresearch.model.NoteBook;
import com.labforward.labresearch.model.NoteBookRequest;
import com.labforward.labresearch.model.NotebookResponse;

import java.util.List;

public interface NotebookService {

    NoteBook addEntriesToNoteBook(NoteBookRequest noteBookRequest);

    NotebookResponse findFrequencyAndSimilar(String notebookEntry);

    Long findFrequency(String notebookEntry);

    List<String> findSimilar(String notebookEntry);

    NoteBook viewNoteBook();

}
