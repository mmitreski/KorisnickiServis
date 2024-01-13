package raf.sk.drugiprojekat.korisnickiservis.security;

import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raf.sk.drugiprojekat.korisnickiservis.security.service.TokenService;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class SecurityAspect {
    @Value("${oauth.jwt.secret}")
    private String jwtSecret;
    private TokenService tokenService;

    public SecurityAspect(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Around("@annotation(raf.sk.drugiprojekat.korisnickiservis.security.CheckSecurity)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        String token = null;
        for(int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if(methodSignature.getParameterNames()[i].equals("authorization")) {
                if(joinPoint.getArgs()[i].toString().startsWith("Bearer"))
                    token = joinPoint.getArgs()[i].toString().split(" ")[1];
            }
        }

        if(token == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Claims claims = tokenService.parseToken(token);
        if(claims == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        CheckSecurity checkSecurity = method.getAnnotation(CheckSecurity.class);
        String valid = claims.get("valid", String.class);
        if(checkSecurity.valid().equals(valid)) {
            return joinPoint.proceed();
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
