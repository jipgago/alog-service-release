package kea.alog.release.config.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kea.alog.release.config.JwtProvider;
import kea.alog.release.web.DTO.TokenPayLoadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        String payload = jwtProvider.getPayload(token.replace("Bearer ", ""));
        TokenPayLoadDto user = jwtProvider.getUserInfo(payload);
        request.setAttribute("user", user);
        return true;
    }
}
