package med.voli.api.domain.auth;

import jakarta.validation.Valid;
import med.voli.api.domain.auth.dto.AuthData;
import med.voli.api.domain.auth.dto.ResponseJwtTokenDto;
import med.voli.api.domain.auth.token.TokenService;
import med.voli.api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthData data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        System.out.println("authToken: " + authToken);
        var authentication = authenticationManager.authenticate(authToken);

        var jwtToken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new ResponseJwtTokenDto(jwtToken));
    }

}
