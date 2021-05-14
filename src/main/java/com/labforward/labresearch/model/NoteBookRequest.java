package com.labforward.labresearch.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NoteBookRequest {

    @NotBlank
    String entries;
    @NotNull
    Boolean isNewBook;

}
