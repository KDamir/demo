package kz.test.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class MainController {


    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    @Transactional
    public ResponseEntity<String> method() {

        User newUser = new User("name", "pass");


        User save = userRepository.save(newUser);

        Iterable<User> all = userRepository.findAll();

        return ResponseEntity.ok().body(all.toString());

    }

}
