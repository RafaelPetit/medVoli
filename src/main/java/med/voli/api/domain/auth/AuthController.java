package med.voli.api.domain.auth;

import jakarta.validation.Valid;
import med.voli.api.domain.auth.dto.AuthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthData data) {
        var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
