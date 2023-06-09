package com.muamerseljubac.controller;

import com.muamerseljubac.entity.dtos.CategoryDTO;
import com.muamerseljubac.entity.dtos.request.CategoryAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.CategoryEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.CategoryDeleteResponseDTO;
import com.muamerseljubac.entity.dtos.response.ErrorResponseDTO;
import com.muamerseljubac.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(@PathVariable int page,
                                                              @RequestParam(value = "sort", required = false) String sort) {
        return new ResponseEntity<>(categoryService.getAllCategories(page, sort), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryAddRequestDTO requestDTO) {
        return new ResponseEntity<>(categoryService.addCategory(requestDTO), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<CategoryDTO> editCategory(@RequestBody CategoryEditRequestDTO requestDTO) {
        return new ResponseEntity<>(categoryService.editCategory(requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDeleteResponseDTO> deleteCategory(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
