package hash;

/**
 * Clase que representa un registro genérico con una clave entera
 * y un dato de tipo T.
 */
public class Register<T> {

    private int key;
    private T data;

    public Register(int key, T data) {
        this.key = key;
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + data + ")";
    }
}
