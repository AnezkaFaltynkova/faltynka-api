package com.faltynka.faltynkaapi.resources;

import com.faltynka.faltynkaapi.model.Token;
import com.faltynka.faltynkaapi.model.User;
import com.faltynka.faltynkaapi.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/user")
public class UserResource {

        @Autowired
        private UserService userService;

        @RequestMapping(value = "/register", method = RequestMethod.POST)
        public User registerUser(@RequestBody User user) {
            return userService.save(user);
        }

        @RequestMapping(value = "/login", method = RequestMethod.POST)
        public Token login(@RequestBody User login) throws ServletException {

            String jwtToken = "";

            if (login.getEmail() == null || login.getPassword() == null) {
                throw new ServletException("Please fill in username and password");
            }

            String email = login.getEmail();
            String password = login.getPassword();

            User user = userService.findByEmail(email);

            if (user == null) {
                throw new ServletException("User email not found.");
            }

            String pwd = user.getPassword();

            if (!password.equals(pwd)) {
                throw new ServletException("Invalid login. Please check your name and password.");
            }

            jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "nsrengGBVUbjub11BJBMNKFBC").compact();

            return new Token(jwtToken);
        }

}
