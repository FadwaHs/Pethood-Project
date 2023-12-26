package com.example.Pethood.CoreLayer.Application.Services.Authentifier;


import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private UserService userService;

    // This method is part of the UserDetailsService interface and is used to load a user by username (in this case, by email).
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur appuser =  userService.loadUserByEmail(email);
        if(appuser == null) throw new UsernameNotFoundException("not found");
        // Extract roles from the user and create a UserDetails object with the necessary information.
        String[] roles = appuser.getRoleList().stream().map(u->u.getRoleName()).toArray(String[]::new);
        UserDetails userDetails  = org.springframework.security.core.userdetails.User
                                  .withUsername(appuser.getEmail())
                                  .password(appuser.getPassword())
                                  .authorities(roles).build();


        return userDetails;
    }
}



