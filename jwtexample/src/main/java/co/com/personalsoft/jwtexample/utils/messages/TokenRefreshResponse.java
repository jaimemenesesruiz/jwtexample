package co.com.personalsoft.jwtexample.utils.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenRefreshResponse {
  private String accessToken;
  private String refreshToken;
  private static final String TOKEN_TYPE = "Bearer";
}
