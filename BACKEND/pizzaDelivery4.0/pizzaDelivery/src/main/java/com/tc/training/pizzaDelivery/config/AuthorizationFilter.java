package com.tc.training.pizzaDelivery.config;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.FirebaseToken;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class AuthorizationFilter extends BasicAuthenticationFilter {
//
//    public AuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                 FilterChain filterChain) throws ServletException, IOException {
//
//        String header = request.getHeader(SecurityConstants.HEADER_STRING);
//
//        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//    }
//
//    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(SecurityConstants.HEADER_STRING);
//
//        if (token != null) {
//            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
//
//            try {
//                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
//                String uid = decodedToken.getUid();
//
//                if (uid != null) {
//                    return new UsernamePasswordAuthenticationToken(uid, null, new ArrayList<>());
//                }
//            } catch (FirebaseAuthException e) {
//
//            }
//        }
//
//        return null;
//    }
//
//}

public class AuthorizationFilter{

}