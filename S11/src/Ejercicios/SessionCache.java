package Ejercicios;
/**
 * Ejercicio 6 – Caché de sesiones usando tabla hash con encadenamiento.
 * Usa arreglos nativos. La clave es el token (String).
 */
public class SessionCache {

    // Nodo interno para la cadena de cada celda
    private static class Node {
        String  token;
        Session session;
        Node    next;

        Node(String token, Session session) {
            this.token   = token;
            this.session = session;
            this.next    = null;
        }
    }

    private Node[] table;
    private int    size;

    public SessionCache(int size) {
        this.size = size;
        table     = new Node[size];   // null = cadena vacía
    }

    /** Hash sobre el token usando hashCode() nativo de String. */
    private int hash(String token) {
        return Math.abs(token.hashCode()) % size;
    }

    /**
     * Registra una nueva sesión.
     * ttlMs = tiempo de vida en milisegundos.
     */
    public void login(String token, String username, String role, long ttlMs) {
        int     idx  = hash(token);
        long    exp  = System.currentTimeMillis() + ttlMs;
        Session sess = new Session(token, username, role, exp);

        // Buscar si ya existe el token → actualizar
        Node cur = table[idx];
        while (cur != null) {
            if (cur.token.equals(token)) {
                cur.session = sess;
                System.out.println("[Cache] Sesión actualizada para " + username);
                return;
            }
            cur = cur.next;
        }

        // Insertar al inicio de la cadena
        Node newNode = new Node(token, sess);
        newNode.next = table[idx];
        table[idx]   = newNode;
        System.out.println("[Cache] Login: " + username + " → índice " + idx);
    }

    /**
     * Valida el token: retorna la sesión si existe y no ha expirado, null si no.
     */
    public Session validate(String token) {
        int  idx = hash(token);
        Node cur = table[idx];
        while (cur != null) {
            if (cur.token.equals(token)) {
                if (cur.session.isExpired()) {
                    System.out.println("[Cache] Token EXPIRADO: " + token);
                    return null;
                }
                System.out.println("[Cache] Token válido: " + cur.session);
                return cur.session;
            }
            cur = cur.next;
        }
        System.out.println("[Cache] Token no encontrado: " + token);
        return null;
    }

    /** Cierra la sesión eliminando el token del caché. */
    public void logout(String token) {
        int  idx  = hash(token);
        Node cur  = table[idx];
        Node prev = null;
        while (cur != null) {
            if (cur.token.equals(token)) {
                if (prev == null) table[idx] = cur.next;
                else              prev.next  = cur.next;
                System.out.println("[Cache] Logout: " + cur.session.username);
                return;
            }
            prev = cur;
            cur  = cur.next;
        }
        System.out.println("[Cache] Token no encontrado para logout: " + token);
    }

    /** Recorre toda la tabla y elimina sesiones expiradas. */
    public void cleanExpired() {
        int removed = 0;
        for (int i = 0; i < size; i++) {
            Node cur  = table[i];
            Node prev = null;
            while (cur != null) {
                if (cur.session.isExpired()) {
                    System.out.println("[Cache] Limpiando sesión expirada: "
                                       + cur.session.username);
                    if (prev == null) table[i] = cur.next;
                    else              prev.next = cur.next;
                    removed++;
                    cur = (prev == null) ? table[i] : prev.next;
                } else {
                    prev = cur;
                    cur  = cur.next;
                }
            }
        }
        System.out.println("[Cache] Sesiones expiradas eliminadas: " + removed);
    }

    /** Cuenta cuántas sesiones activas (no expiradas) quedan en la tabla. */
    public int countActive() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            Node cur = table[i];
            while (cur != null) {
                if (!cur.session.isExpired()) count++;
                cur = cur.next;
            }
        }
        return count;
    }

    /** Imprime el estado del caché. */
    public void printCache() {
        System.out.println("=== SessionCache (tamaño=" + size + ") ===");
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.printf("  [%2d] ", i);
                Node cur = table[i];
                while (cur != null) {
                    System.out.print(cur.session + (cur.next != null ? " → " : ""));
                    cur = cur.next;
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}