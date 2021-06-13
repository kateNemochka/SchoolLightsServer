package com.katenemochka.schoollights.service.impl;

import com.katenemochka.schoollights.dao.RoleRepository;
import com.katenemochka.schoollights.dao.RoomRepository;
import com.katenemochka.schoollights.dao.UserRepository;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.User;
import com.katenemochka.schoollights.dto.UserRequest;
import com.katenemochka.schoollights.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoomRepository marathonRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roomRepository = marathonRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No user with id="+id)));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createOrUpdateUser(User user) {
        user.setRole(roleRepository.findByName(user.getRole().getName()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());
    }

    @Override
    public List<User> getAllByRole(String role) {
        List<User> users = userRepository.findAllByRole(roleRepository.findByName(role));
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            log.error("Could not fin user by " + email + "email");
//            throw new EntityNotFoundException("Could")
        }
        return user;
    }


}
