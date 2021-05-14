package com.labforward.labresearch.service;

import com.labforward.labresearch.exception.EmptyNotebookException;
import com.labforward.labresearch.model.NoteBook;
import com.labforward.labresearch.model.NoteBookRequest;
import com.labforward.labresearch.model.NotebookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NotebookServiceImpl implements NotebookService {

    Map<String, Long> noteBookEntriesWordCount = new HashMap<>();
    NoteBook noteBook = new NoteBook();

    @Override
    public NoteBook addEntriesToNoteBook(NoteBookRequest noteBookRequest) {

        try {

            /* Clear notebook words if new note book is entered */
            if (noteBookRequest.getIsNewBook()) {
                noteBookEntriesWordCount.clear();
                noteBook.getWords().clear();
            }

            List<String> noteBookEntries = Arrays.asList(noteBookRequest.getEntries().split(" "));
            noteBook.getWords().addAll(noteBookEntries);

            /* Adding word and their frequency count to the map*/
            noteBookEntriesWordCount = noteBookEntries
                    .stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            return noteBook;

        } catch (Exception e) {
            log.error("Exception while adding entries to notebook", e);
            throw e;
        }
    }

    @Override
    public NotebookResponse findFrequencyAndSimilar(String word) {

        try {

            if (noteBook.getWords().isEmpty()) {
                log.error("Notebook is empty for request :" + word);
                throw new EmptyNotebookException("Notebook is empty , please enter notebook entries first");
            }
            NotebookResponse notebookResponse = new NotebookResponse();
            notebookResponse.setFrequency(findFrequency(word));
            notebookResponse.setSimilarWords(findSimilar(word));
            return notebookResponse;

        } catch (Exception e) {
            log.error("Exception while finding frequency and similar words", e);
            throw e;
        }

    }

    @Override
    public Long findFrequency(String word) {
        return noteBookEntriesWordCount.getOrDefault(word, 0L);
    }

    @Override
    public List<String> findSimilar(String request) {

        List<String> similarWords = new ArrayList<>();

        for (String entry : noteBookEntriesWordCount.keySet()) {

            /* if its not the same word and is similar then add to similar words */
            if (!entry.equals(request) && isSimilar(entry, request)) {
                similarWords.add(entry);
            }
        }
        return similarWords;
    }

    @Override
    public NoteBook viewNoteBook() {

        if (noteBook.getWords().isEmpty()) {
            log.error("Notebook is empty for view request");
        }
        return noteBook;
    }

    private Boolean isSimilar(String word1, String word2) {

        int len1 = word1.length(), len2 = word2.length();

        /*if the difference between two words length is greater than 1 return false*/
        if (Math.abs(len1 - len2) > 1)
            return false;

        int levenshteinDist = 0, index1 = 0, index2 = 0;

        while (index1 < len1 && index2 < len2) {

            if (word1.charAt(index1) != word2.charAt(index2)) {

                /* if the distance is already one then the word are not similar */
                if (levenshteinDist == 1)
                    return false;

                /* increment the indexes based on lengths */
                if (len1 > len2) {
                    index1++;
                } else if (len1 < len2) {
                    index2++;
                } else {
                    index1++;
                    index2++;
                }

                levenshteinDist++;
            } else {
                index1++;
                index2++;
            }
        }

        /* Handling the extra character in any word */
        if (index1 < len1 || index2 < len2) {
            levenshteinDist++;
        }

        return levenshteinDist == 1;
    }
}
