package com.tc.training.pizzaDelivery.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.tc.training.pizzaDelivery.enums.Role;
import com.tc.training.pizzaDelivery.model.ApiEntity;
import com.tc.training.pizzaDelivery.model.User;
import com.tc.training.pizzaDelivery.repository.UserRepository;
import com.tc.training.pizzaDelivery.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Order(1)
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String idToken = request.getHeader("Authorization"); // Assuming the token is sent in the Authorization header

        if (idToken != null && idToken.startsWith("Bearer ")) {
            String firebaseToken = idToken.split(" ")[1]; // Remove "Bearer " prefix
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
                String firebaseUserId = decodedToken.getUid();

                Optional<User> userOptional = userRepository.findByFirebaseId(firebaseUserId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    UserThreadLocal.setUserId(user.getId().toString());
                    Role userRole = user.getRole();

                    // Get the URLs accessible for the user's role
                    List<ApiEntity> accessibleApis = userService.getAccessibleApis(userRole);

                    // Check if the requested URL is accessible for the user's role
                    String requestedUrl = request.getRequestURI();
                    RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
                    if (isUrlAccessible(requestedUrl, requestMethod, accessibleApis)) {
                        chain.doFilter(request, response);
                    } else {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid token");
            }
        }

        chain.doFilter(request, response);

        UserThreadLocal.removeUserId();
    }
    private boolean isUrlAccessible(String url, RequestMethod method, List<ApiEntity> accessibleApis) {
        return accessibleApis.stream().anyMatch(api -> api.getUrl().equals(url) && api.getMethod().equals(method));
    }
}
