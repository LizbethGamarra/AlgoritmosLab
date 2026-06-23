package Ejercicios;

/**
 * Representa una sesión activa en el caché.
 */
public class Session {
    public String token;
    public String username;
    public String role;
    public long   expiresAt;   // timestamp Unix en ms

    public Session(String token, String username, String role, long expiresAt) {
        this.token     = token;
        this.username  = username;
        this.role      = role;
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiresAt;
    }

    @Override
    public String toString() {
        return "[" + username + " | " + role + " | expira=" + expiresAt
               + (isExpired() ? " (EXPIRADA)" : " (ACTIVA)") + "]";
    }
}