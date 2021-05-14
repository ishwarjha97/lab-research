package com.labforward.labresearch.controller;

import com.labforward.labresearch.model.NoteBook;
import com.labforward.labresearch.model.NoteBookRequest;
import com.labforward.labresearch.model.NotebookResponse;
import com.labforward.labresearch.service.NotebookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notebook/api")
@Slf4j
public class NotebookController {

    @Autowired
    NotebookService notebookService;


    @PostMapping(value = "/entry", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NoteBook> postEntryToNotebook(@Valid @RequestBody NoteBookRequest noteBookRequest) {

        log.info("NotebookController::entryToNotebook , Request : " + noteBookRequest);
        return new ResponseEntity<>
                (notebookService.addEntriesToNoteBook(noteBookRequest), HttpStatus.OK);
    }

    @GetMapping("/frequency-and-similar/{requestedWord}")
    ResponseEntity<NotebookResponse> getFrequencyAndSimilarWords(@PathVariable String requestedWord) {

        log.info("NotebookController::frequencyAndSimilarWords, Request : " + requestedWord);
        return new ResponseEntity<>
                (notebookService.findFrequencyAndSimilar(requestedWord), HttpStatus.OK);
    }

    @GetMapping("/view")
    ResponseEntity<NoteBook> viewNotebook() {

        log.info("NotebookController::viewNotebook");
        return new ResponseEntity<>
                (notebookService.viewNoteBook(), HttpStatus.OK);
    }
}
