package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.dto.AuthRequest;
import ru.example.java.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
//@RequestMapping()
public class LoginController {

    private final AuthenticationManager authManager;

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthRequest authReq){

        if (authReq.getUsername() == null || authReq.getUsername().length() == 0 ||
                authReq.getPassword()     == null || authReq.getPassword().length()     == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword());
        final Authentication auth = this.authManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/auth")
    public ResponseEntity<?> auth(final HttpServletRequest req
    ) {
        try {
            req.logout();
        } catch (Exception exc) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
