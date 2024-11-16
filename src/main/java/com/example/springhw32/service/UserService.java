package com.example.springhw32.service;

import com.example.springhw32.dto.UserDto;
import com.example.springhw32.entity.User;
import com.example.springhw32.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto join(UserDto userDto) {

        if (userDto.getUsername().isBlank()) {
            throw new NoSuchElementException("아이디를 입력해 주세요.");
        }
        if (userDto.getPassword().isBlank()) {
            throw new NoSuchElementException("비밀번호를 입력해 주세요.");
        }
        if (userDto.getNickname().isBlank()) {
            throw new NoSuchElementException("닉네임을 입력해 주세요.");
        }

        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("아이디가 이미 존재합니다.");
        }
        if (userRepository.existsByNickname(userDto.getNickname())) {
            throw new RuntimeException("닉네임이 이미 존재합니다.");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setNickname(userDto.getNickname());
        userRepository.save(user);

        userDto.setUserId(user.getId());
        return userDto;
    }

    public String login(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user != null && user.getPassword().equals(userDto.getPassword())) {
            return user.getNickname() + " 님, 환영합니다!!";
        }
        return "아이디 및 비밀번호가 일치하지 않습니다.";
    }

}
