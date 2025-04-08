package med.voli.api.domain.user;

import jakarta.validation.Valid;
import med.voli.api.domain.user.dto.CreateUserDto;
import med.voli.api.domain.user.dto.ResponseUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserDto createUserDto, UriComponentsBuilder uriBuilder) {
        {
            var encodedPassword = passwordEncoder.encode(createUserDto.password());

            var createdUser = repository.save(new User(createUserDto.username(), encodedPassword));

            var uri = uriBuilder.path("/user/{id}").buildAndExpand(createdUser.getId()).toUri();

            return ResponseEntity.created(uri).body(new ResponseUserDto(createdUser));
        }
    }
}
