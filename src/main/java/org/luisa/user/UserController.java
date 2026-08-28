package org.factoriaf5.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.factoriaf5.group.GroupEntity;
import org.factoriaf5.profile.ProfileEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<UserEntity> index() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getUserProfile(@PathVariable("id") Long id) {

        UserEntity user = repository.findById(id).orElseThrow();
        ProfileEntity profile = user.getProfile();
        Set<GroupEntity> groups = user.getGroups();

        Map<String, ? super Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("email", profile.getEmail());
        data.put("country", profile.getCountry().getName());
        data.put("groups", groups);
        
        return data;
    }

}
