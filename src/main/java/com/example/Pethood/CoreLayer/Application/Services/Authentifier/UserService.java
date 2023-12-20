package com.example.Pethood.CoreLayer.Application.Services.Authentifier;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;

public interface UserService {

    Utilisateur loadUserByEmail(String email);
}
