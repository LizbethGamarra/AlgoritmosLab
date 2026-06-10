package Actividades;


public class NodeLayout<E extends Comparable<E>> {

    /** The tree node being represented. */
    public final BNode<E> node;

    /** Top-left X coordinate of the rendered rectangle (in canvas pixels). */
    public final int x;

    /** Top-left Y coordinate of the rendered rectangle (in canvas pixels). */
    public final int y;

    /** Total pixel width of the rectangle (sum of all cell widths). */
    public final int width;

    /** Height of the rectangle. */
    public final int height;

    public NodeLayout(BNode<E> node, int x, int y, int width, int height) {
        this.node   = node;
        this.x      = x;
        this.y      = y;
        this.width  = width;
        this.height = height;
    }

    /** Centre X of the top edge — used as origin for parent→child lines. */
    public int topCenterX()    { return x + width / 2; }
    public int topCenterY()    { return y; }

    /** Centre X of the bottom edge — used as target for parent→child lines. */
    public int bottomCenterX() { return x + width / 2; }
    public int bottomCenterY() { return y + height; }
}
