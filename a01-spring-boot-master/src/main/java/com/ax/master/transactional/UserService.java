package com.ax.master.transactional;

import com.ax.master.jpa.User;
import com.ax.master.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    @Transactional(rollbackFor = Exception.class)
    public void addUser1(User user) {
        final User save = userRepository.save(user);
        System.out.println("addUser1 save = " + save);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addUser2(User user) {
        final User save = userRepository.save(user);
        System.out.println("addUser2 save = " + save);
        throw new RuntimeException();
    }

}
