package br.com.iesb.jira.application.incoming.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/task")
@RestController
public class TaskCreateController {

    @GetMapping
    public String createTask() {
        return "Teste";
    }
}
