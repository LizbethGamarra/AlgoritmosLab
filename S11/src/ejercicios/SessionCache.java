package ejercicios;

import hash.LinkedList;

/**
 * Ejercicio 6: Caso real - sistema de autenticación de usuarios (caché de sesiones).
 * Implementa una tabla hash con encadenamiento (usando la LinkedList propia del
 * paquete hash) donde la clave es el token de sesión y el valor es la información
 * del usuario.
 */
public class SessionCache {

    private LinkedList<Session>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public SessionCache(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String token) {
        return Math.abs(token.hashCode()) % size;
    }

    /** Registra una nueva sesión con tiempo de vida en milisegundos. */
    public void login(String token, String username, String role, long ttlMs) {
        long expiresAt = System.currentTimeMillis() + ttlMs;
        Session s = new Session(token, username, role, expiresAt);
        int idx = hash(token);
        table[idx].addLast(s);
        System.out.println("Login: " + username + " (token=" + token + ") en índice " + idx);
    }

    /** Retorna la sesión si el token existe y no ha expirado; null en caso contrario. */
    public Session validate(String token) {
        int idx = hash(token);
        LinkedList<Session>.Node nodo = table[idx].getHead();
        long now = System.currentTimeMillis();
        while (nodo != null) {
            if (nodo.getData().getToken().equals(token)) {
                if (nodo.getData().isExpired(now)) {
                    return null; // token existe pero expiró
                }
                return nodo.getData();
            }
            nodo = nodo.getNext();
        }
        return null; // no existe
    }

    /** Elimina la sesión del caché (cierre de sesión explícito). */
    public void logout(String token) {
        int idx = hash(token);
        LinkedList<Session>.Node nodo = table[idx].getHead();
        while (nodo != null) {
            if (nodo.getData().getToken().equals(token)) {
                table[idx].remove(nodo);
                System.out.println("Logout del token " + token);
                return;
            }
            nodo = nodo.getNext();
        }
    }

    /** Recorre toda la tabla y elimina las sesiones expiradas. */
    public void cleanExpired() {
        long now = System.currentTimeMillis();
        int eliminadas = 0;
        for (int i = 0; i < size; i++) {
            LinkedList<Session>.Node nodo = table[i].getHead();
            while (nodo != null) {
                LinkedList<Session>.Node siguiente = nodo.getNext();
                if (nodo.getData().isExpired(now)) {
                    table[i].remove(nodo);
                    eliminadas++;
                }
                nodo = siguiente;
            }
        }
        System.out.println("cleanExpired(): " + eliminadas + " sesión(es) expirada(s) eliminada(s).");
    }

    /** Cuenta cuántas sesiones activas (no expiradas) quedan en el caché. */
    public int countActive() {
        long now = System.currentTimeMillis();
        int activas = 0;
        for (int i = 0; i < size; i++) {
            LinkedList<Session>.Node nodo = table[i].getHead();
            while (nodo != null) {
                if (!nodo.getData().isExpired(now)) {
                    activas++;
                }
                nodo = nodo.getNext();
            }
        }
        return activas;
    }
}
