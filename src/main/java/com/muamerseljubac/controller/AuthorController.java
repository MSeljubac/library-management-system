package com.muamerseljubac.controller;

import com.muamerseljubac.entity.dtos.AuthorDTO;
import com.muamerseljubac.entity.dtos.request.AuthorAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.AuthorEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.AuthorDeleteResponseDTO;
import com.muamerseljubac.entity.dtos.response.ErrorResponseDTO;
import com.muamerseljubac.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(@PathVariable("page") int page,
                                                         @RequestParam(value = "sort", required = false) String sort) {
        return new ResponseEntity<>(authorService.getAllAuthors(page, sort), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorAddRequestDTO requestDTO) {
        return new ResponseEntity<>(authorService.addAuthor(requestDTO), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<AuthorDTO> editAuthor(@RequestBody AuthorEditRequestDTO requestDTO) {
        return new ResponseEntity<>(authorService.editAuthor(requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDeleteResponseDTO> deleteAuthor(@PathVariable("id") UUID id) {
        return new ResponseEntity<AuthorDeleteResponseDTO>(authorService.deleteAuthor(id), HttpStatus.OK);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
