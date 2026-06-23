package hash;

/**
 * Clase genérica que representa un registro con clave entera y dato de tipo T.
 */
public class Register<T> {
    private int key;
    private T data;

    public Register(int key, T data) {
        this.key  = key;
        this.data = data;
    }

    public int getKey()  { return key; }
    public T   getData() { return data; }

    @Override
    public String toString() {
        return "(" + key + ", " + data + ")";
    }
}