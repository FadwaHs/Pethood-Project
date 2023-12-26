package com.example.Pethood.Adapterlayer.Authentifier;


import com.example.Pethood.CoreLayer.Application.Services.Authentifier.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Auth")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication)
    {
        return authentication;
    }


    @PostMapping("/login")
    // this  end point :---Authentifier user ---génerate token ---access Token --And Refrech Token
    public Map<String, String> login(String email, String password)
    {
        Authentication authentication  = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // to get roles
        String scope  = authentication.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
        Instant instant = Instant.now();

        // Generate Token
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .subject(email)
                .claim("scope", scope) // to Define user roles or user authorities
                .build();

        // Encoder le jwt token
        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(
                        JwsHeader.with(MacAlgorithm.HS512).build(),
                        jwtClaimsSet
                );

        String jwt  = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

        // Generate refresh token
        jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(30, ChronoUnit.DAYS)) // Set a longer expiration time for refresh token
                .subject(email)
                .claim("scope", scope)
                .claim("refresh_token", true) // Add a custom claim to identify it as a refresh token
                .build();

        jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), jwtClaimsSet);

        String refreshToken = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();


        // Return both access token and refresh token in the response
        return Map.of("access-token", jwt , "refresh-token", refreshToken);

    }

    @PostMapping("/refreshToken")
    public Map<String, String> refreshToken(String refreshToken) {

        Jwt jwt = jwtDecoder.decode(refreshToken);
        Instant instant = Instant.now();

        Map<String, Object> claims = jwt.getClaims();

        // Check if the token contains the "refresh_token" claim and if it is not expired
        Boolean isRefreshToken = (Boolean) claims.get("refresh_token");

        if (isRefreshToken != null && !jwt.getExpiresAt().isBefore(instant)) {

            // Get the username from the token
            String email = jwt.getSubject();
            // Get the scope ( roles or Authority )
            UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(email);
            String scope = userDetails.getAuthorities().stream()
                    .map(a->a.getAuthority()).collect(Collectors.joining(" "));

            // Generate a new access token
            JwtClaimsSet newJwtClaimsSet = JwtClaimsSet.builder()
                    .issuedAt(instant)
                    .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                    .subject(email)
                    .claim("scope", scope)
                    .build();

            JwtEncoderParameters jwtEncoderParameters =
                    JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), newJwtClaimsSet);

            String accessToken = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

            // Return the new access token in the response
            return Map.of("access-token", accessToken);
        } else {
            // Invalid or expired refresh token
            throw new RuntimeException("Invalid or expired refresh token");
        }
    }




}
