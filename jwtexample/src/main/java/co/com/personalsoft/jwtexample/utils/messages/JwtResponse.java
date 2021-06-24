package co.com.personalsoft.jwtexample.utils.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private static final String TOKEN_TYPE = "Bearer";
    private String refreshToken;
    private String id;
    private String name;
    private String username;
    private String email;
    private List<String> roles;
}
