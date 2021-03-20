package com.deepak.user.service;

import com.deepak.user.VO.Department;
import com.deepak.user.VO.UserVO;
import com.deepak.user.entity.User;
import com.deepak.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User save(User user) {
        log.info("Inside save method of UserService");
        return this.userRepository.save(user);
    }

    public UserVO getUserDepartment(Long userId) {
        log.info("Inside getUserDepartment method of UserRepository");
        User user = this.userRepository.findById(userId).orElse(new User());
        log.info("user : " + user.toString());
        Department department = null;
        try {
            department =
                    restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                            Department.class);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return new UserVO(user,department);
    }
}
