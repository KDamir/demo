package kz.test.demo;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;


@RestController
public class MainController {


    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public MainController(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @GetMapping("/hello")
    @Transactional
    public ResponseEntity<String> method() {

        Session session = entityManager.unwrap(Session.class);

        User newUser = new User("name", "pass");

        session.setFlushMode(FlushModeType.AUTO);

        session.persist(newUser);
        assert newUser.getId() == session.createQuery("select u.name from User u").uniqueResult();

//        User save = userRepository.save(newUser);

        Iterable<User> all = userRepository.findAll();

        return ResponseEntity.ok().body(all.toString());

    }

}
