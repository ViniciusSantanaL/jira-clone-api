package br.com.iesb.nicetask.application.incoming.auth;

import br.com.iesb.nicetask.application.incoming.auth.commons.converter.SingUpConverter;
import br.com.iesb.nicetask.application.incoming.auth.commons.request.SingUpRequest;
import br.com.iesb.nicetask.application.incoming.auth.commons.response.SingUpResponse;
import br.com.iesb.nicetask.domain.user.service.UserCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sing-up")
public class SingUpResource {

    private final UserCreateService userCreateService;

    @Autowired
    public SingUpResource(UserCreateService userCreateService) {
        this.userCreateService = userCreateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SingUpResponse createUser(@RequestBody @Valid SingUpRequest request) {
        return SingUpConverter.toResponse(userCreateService.create(SingUpConverter.of(request)));
    }
}
