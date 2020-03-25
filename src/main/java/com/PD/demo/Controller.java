package com.PD.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final CategoryRepository categoryRepository;

    public Controller(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/start")
    public List<Category> start() {
        return categoryRepository.findAll();
    }
}
