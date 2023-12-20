package com.example.Pethood.CoreLayer.Application.Services.Authentifier;

import com.example.Pethood.CoreLayer.Application.Repository.UserRepository;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;
    @Override
    public Utilisateur loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
