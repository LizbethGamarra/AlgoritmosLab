package Ejercicios;
/**
 * Ejercicio 6 – Prueba del sistema de caché de sesiones.
 */
public class TestSessionCache {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("════════════════════════════════════");
        System.out.println("  EJERCICIO 6 – SessionCache");
        System.out.println("════════════════════════════════════\n");

        SessionCache cache = new SessionCache(11);

        // 1. Tres usuarios inician sesión con TTL diferentes
        System.out.println("─── 1) Login de tres usuarios ───");
        cache.login("abc123", "Maria",   "admin",  5000);  // expira en 5s
        cache.login("xyz789", "Carlos",  "editor", 1000);  // expira en 1s  ← expirará pronto
        cache.login("tok456", "Lucia",   "viewer", 8000);  // expira en 8s

        cache.printCache();

        // Esperar 1.5 s para que el token de Carlos expire
        System.out.println("─── Esperando 1.5 s para expirar token de Carlos... ───");
        Thread.sleep(1500);

        // 2. Validar los tres tokens
        System.out.println("\n─── 2) Validación de tokens ───");
        cache.validate("abc123");   // activo
        cache.validate("xyz789");   // expirado
        cache.validate("tok456");   // activo

        // 3. Maria cierra sesión explícitamente
        System.out.println("\n─── 3) Logout de Maria ───");
        cache.logout("abc123");
        cache.printCache();

        // 4. Limpiar expirados y mostrar sesiones activas
        System.out.println("─── 4) cleanExpired() ───");
        cache.cleanExpired();
        System.out.println("Sesiones activas restantes: " + cache.countActive());
        cache.printCache();

        System.out.println("Reflexión:");
        System.out.println("  Una tabla hash valida tokens en O(1) promedio, mientras que");
        System.out.println("  una lista enlazada requeriría O(n) recorrido en cada petición.");
        System.out.println("  Java HashMap ofrece rehashing automático, hashing optimizado");
        System.out.println("  y métodos utilitarios ya probados que nuestra implementación");
        System.out.println("  manual no tiene sin trabajo adicional.");
    }
}