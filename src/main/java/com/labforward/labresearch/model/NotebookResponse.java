package com.labforward.labresearch.model;

import lombok.Data;

import java.util.List;

@Data
public class NotebookResponse {

    private Long frequency;
    private List<String> similarWords;

}
