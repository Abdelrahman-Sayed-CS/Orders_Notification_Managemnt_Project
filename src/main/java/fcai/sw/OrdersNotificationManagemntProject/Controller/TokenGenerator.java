package fcai.sw.OrdersNotificationManagemntProject.Controller;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class TokenGenerator {
    private static final String SECRET_KEY = "eyJhbGciOiJIUzUxMiJ9eyJzdWIiOiJhaG1lZDEyMiIsImV4cCI6MTcwNDc5MTM1MX0TmoVnsrWKJNHKA130YA8cToKjKMk17287pMCLwqviP9fqjDSejdHflD3sslH1GMIiLEl8843Nsm5fo6g";

    TokenGenerator(){}

    public String generateToken(String username) {
        // 10 days
        long EXPIRATION_TIME = 864_000_000;
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY).build()
                    .parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (Exception e) {
            // Handle the exception appropriately (log it or return null)
            e.printStackTrace();
            return null;
        }
    }
}