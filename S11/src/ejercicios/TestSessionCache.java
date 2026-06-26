package ejercicios;

/**
 * Clase de prueba que simula el flujo de uso de SessionCache:
 * 1) tres usuarios inician sesión con tokens y tiempos de expiración distintos,
 * 2) se validan los tokens (uno ya expiró),
 * 3) un usuario cierra sesión explícitamente,
 * 4) se llama a cleanExpired() y se muestra cuántas sesiones quedan activas.
 */
public class TestSessionCache {
    public static void main(String[] args) throws InterruptedException {

        SessionCache cache = new SessionCache(10);

        System.out.println("===== 1) Login de tres usuarios =====");
        cache.login("abc123", "ana", "admin", 5000);   // expira en 5s
        cache.login("xyz789", "bruno", "user", 200);    // expira en 0.2s (para forzar expiración)
        cache.login("qrs456", "carla", "user", 5000);   // expira en 5s

        // Esperamos un poco para que el token "xyz789" expire
        Thread.sleep(300);

        System.out.println("\n===== 2) Validando tokens =====");
        String[] tokens = {"abc123", "xyz789", "qrs456"};
        for (String t : tokens) {
            var sesion = cache.validate(t);
            System.out.println("validate(" + t + ") -> " + (sesion != null ? sesion : "INVALIDO o EXPIRADO"));
        }

        System.out.println("\n===== 3) Logout explícito de 'ana' (abc123) =====");
        cache.logout("abc123");
        System.out.println("validate(abc123) tras logout -> " + cache.validate("abc123"));

        System.out.println("\n===== 4) Limpieza de sesiones expiradas =====");
        cache.cleanExpired();
        System.out.println("Sesiones activas restantes: " + cache.countActive());

        /*
         * Reflexión:
         * - Una tabla hash permite validar un token en tiempo O(1) promedio, porque la
         *   posición se calcula directamente con la función hash, sin recorrer todas
         *   las sesiones existentes. Una lista enlazada de sesiones requeriría, en el
         *   peor caso, recorrer las N sesiones activas para encontrar el token (O(n)).
         *   Con millones de sesiones (caso Gmail, Facebook, banca en línea), esa
         *   diferencia es crítica para responder en milisegundos.
         *
         * - Java HashMap, frente a esta implementación manual, ofrece: redimensionamiento
         *   automático (rehashing) cuando el factor de carga supera un umbral, manejo
         *   interno optimizado de colisiones (incluyendo árboles balanceados para listas
         *   largas en versiones recientes de Java), una función hash y mezcla de bits ya
         *   probada y eficiente, soporte para iteración, concurrencia (con variantes como
         *   ConcurrentHashMap) y años de pruebas y optimizaciones en la JVM. La
         *   implementación manual, en cambio, es educativa pero requeriría agregar todo
         *   eso manualmente para un uso real en producción.
         */
    }
}
