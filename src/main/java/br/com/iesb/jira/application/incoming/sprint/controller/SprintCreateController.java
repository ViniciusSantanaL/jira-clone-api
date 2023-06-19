package br.com.iesb.jira.application.incoming.sprint.controller;

import br.com.iesb.jira.application.incoming.sprint.commons.converter.SprintConverter;
import br.com.iesb.jira.application.incoming.sprint.commons.request.SprintRequest;
import br.com.iesb.jira.application.incoming.sprint.commons.response.SprintResponse;
import br.com.iesb.jira.application.incoming.sprint.controller.api.SprintCreateApi;
import br.com.iesb.jira.domain.sprint.service.SprintCreateService;
import br.com.iesb.jira.domain.sprint.service.SprintCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/sprints")
@RestController
public class SprintCreateController implements SprintCreateApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(SprintCreateController.class);
    private final SprintCreateService sprintCreateService;

    public SprintCreateController(SprintCreateService sprintCreateService) {
        this.sprintCreateService = sprintCreateService;
    }

    @Override
    public SprintResponse createSprint(final SprintRequest request) {
        LOGGER.info("start create sprint, request: {}", request);
        return SprintConverter.toResponse(sprintCreateService.createSprint(SprintConverter.of(request)));
    }
}
