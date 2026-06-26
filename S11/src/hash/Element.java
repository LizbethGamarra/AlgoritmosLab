package hash;

/**
 * Celda de la tabla hash cerrada.
 * Guarda un Register<T> y un estado representado como entero:
 *   0 = EMPTY
 *   1 = OCCUPIED
 *   2 = DELETED
 */
public class Element<T> {

    public static final int EMPTY = 0;
    public static final int OCCUPIED = 1;
    public static final int DELETED = 2;

    private Register<T> register;
    private int status;

    public Element() {
        this.register = null;
        this.status = EMPTY;
    }

    public Register<T> getRegister() {
        return register;
    }

    public void setRegister(Register<T> register) {
        this.register = register;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /** Texto descriptivo del estado, útil para imprimir la tabla. */
    public String statusName() {
        switch (status) {
            case EMPTY:    return "EMPTY";
            case OCCUPIED: return "OCCUPIED";
            case DELETED:  return "DELETED";
            default:       return "?";
        }
    }
}
