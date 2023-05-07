package br.com.iesb.jira.application.incoming.card;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/card")
@RestController
public class CardCreateController {

    @GetMapping
    public String test() {
        return "Teste";
    }
}
