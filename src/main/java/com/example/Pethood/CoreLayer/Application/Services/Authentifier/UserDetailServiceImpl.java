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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur appuser =  userService.loadUserByEmail(email);
        if(appuser == null) throw new UsernameNotFoundException("not found");

        String[] roles = appuser.getRoleList().stream().map(u->u.getRoleName()).toArray(String[]::new);
        UserDetails userDetails  = org.springframework.security.core.userdetails.User
                                  .withUsername(appuser.getEmail())
                                  .password(appuser.getPassword())
                                  .authorities(roles).build();


        return userDetails;
    }
}



