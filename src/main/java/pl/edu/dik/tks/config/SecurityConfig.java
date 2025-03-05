package pl.edu.dik.tks.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@RequiredArgsConstructor
//@EnableWebSecurity
@Configuration
public class SecurityConfig {

//    @Value("${rsa.private-key}")
//    private RSAPrivateKey privateKey;
//
//    @Value("${rsa.public-key}")
//    private RSAPublicKey publicKey;
//
//    public RSAPrivateKey getPrivateKey() {
//        return privateKey;
//    }
//
//    public RSAPublicKey getPublicKey() {
//        return publicKey;
//    }
}
