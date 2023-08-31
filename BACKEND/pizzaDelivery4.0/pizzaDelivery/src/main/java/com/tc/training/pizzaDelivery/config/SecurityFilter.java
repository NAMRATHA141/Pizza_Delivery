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
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
//@Configuration
public class SecurityFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String idToken = request.getHeader("Authorization");

        String requestedUrl = request.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

        if (isPublic(requestedUrl, requestMethod)) {
            chain.doFilter(request, response);
        } else if (idToken != null && idToken.startsWith("Bearer ")) {
            String firebaseToken = idToken.split(" ")[1]; // Remove "Bearer " prefix
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
                String firebaseUserId = decodedToken.getUid();

                Optional<User> userOptional = userRepository.findByFirebaseId(firebaseUserId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    UserThreadLocal.setUserId(user.getId());
                    Role userRole = user.getRole();

                    // Get the URLs accessible for the user's role
                    List<ApiEntity> accessibleApis = userService.getAccessibleApis(userRole);

                    // Check if the requested URL is accessible for the user's role
                    if (isUrlAccessible(requestedUrl, requestMethod, accessibleApis)) {
                        chain.doFilter(request, response);
                    } else {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        return; // Exit the filter chain for unauthorized access
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid token");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return; // Exit the filter chain for invalid token
            }
        }
        else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("Unauthorized access");
        }
        UserThreadLocal.removeUserId();
    }

    private boolean isUrlAccessible(String url, RequestMethod method, List<ApiEntity> accessibleApis) {
        return accessibleApis.stream().anyMatch(api -> api.getUrl().equals(url) && api.getMethod().equals(method));
    }

    private boolean isPublic(String url, RequestMethod method) {
        List<ApiEntity> publicApis = new ArrayList<>();
        publicApis.add(new ApiEntity(123456001L, "/api/coupons", RequestMethod.GET));
        publicApis.add(new ApiEntity(123456018L, "/api/users/signup", RequestMethod.POST));
        publicApis.add(new ApiEntity(123456009L, "/api/menu/by-location", RequestMethod.GET));
        publicApis.add(new ApiEntity(123456010L, "/api/menu/by-location/products", RequestMethod.GET));
        publicApis.add(new ApiEntity(123456011L, "/api/menu/by-location/crusts", RequestMethod.GET));
        publicApis.add(new ApiEntity(123456012L, "/api/menu/by-location/sizes", RequestMethod.GET));
        publicApis.add(new ApiEntity(123456013L, "/api/menu/by-location/toppings", RequestMethod.GET));

        return publicApis.stream().anyMatch(api -> api.getUrl().equals(url) && api.getMethod().equals(method));
    }

}
