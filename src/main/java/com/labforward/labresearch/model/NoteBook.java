package com.labforward.labresearch.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoteBook {

    List<String> words;

    public NoteBook() {
        this.words = new ArrayList<>();
    }
}
