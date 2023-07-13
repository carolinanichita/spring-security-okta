package javapuffs.security;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping("api/courses")
    public ResponseEntity<List<String>> getBootcampCourses() {
        List<String> courses = new ArrayList<>(
                List.of("JSFS",
                        "JFS",
                        "DNFS"));
        return ResponseEntity.ok(courses);
    }

    @GetMapping("api/developers/labs")
    public String home(@AuthenticationPrincipal OidcUser user) {
        return "Welcome, "+ user.getFullName() + "!";
    }

    @GetMapping("api/developers/grades")
    public String userGrades(@AuthenticationPrincipal OidcUser user) {
        return "Welcome, "+ user.getFullName() + "!";
    }

    @GetMapping("api/developers/{email}")
    public String getUserByEmail(@PathVariable String email, @AuthenticationPrincipal OidcUser user) {
        if (email.equals(user.getEmail())) {
            return user.getFullName();
        }
        return "Not authorized";
    }


}
