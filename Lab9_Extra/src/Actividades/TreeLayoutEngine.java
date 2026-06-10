package Actividades;

import java.util.*;

/**
 * Pure layout engine: traverses the BTree (via getRoot()) and computes a
 * NodeLayout for every node using a two-pass algorithm:
 *
 *  Pass 1 – measure the "subtree width" of each node bottom-up.
 *  Pass 2 – assign absolute (x, y) coordinates top-down.
 *
 * No Swing imports — this class is purely geometric.
 */
public class TreeLayoutEngine<E extends Comparable<E>> {

    // ── Visual constants
    public static final int CELL_W      = 42;   // width of one key cell (px)
    public static final int CELL_H      = 30;   // height of one node row (px)
    public static final int H_GAP       = 24;   // minimum horizontal gap between sibling subtrees
    public static final int V_GAP       = 60;   // vertical gap between tree levels
    public static final int MARGIN      = 20;   // left/top canvas margin

    //  Results 

    /** All computed layouts, keyed by node id so the painter can look them up fast. */
    private final Map<Integer, NodeLayout<E>> layoutMap = new LinkedHashMap<>();

    /** Total canvas size needed (set after layout). */
    private int canvasWidth;
    private int canvasHeight;

    // Public API 

    public Map<Integer, NodeLayout<E>> getLayoutMap()  { return layoutMap; }
    public int getCanvasWidth()                        { return canvasWidth; }
    public int getCanvasHeight()                       { return canvasHeight; }

    /**
     * Runs the full layout computation for the given tree.
     * Call this every time the tree changes before repainting.
     */
    public void compute(BTree<E> tree) {
        layoutMap.clear();

        BNode<E> root = tree.getRoot();
        if (root == null) {
            canvasWidth  = 400;
            canvasHeight = 200;
            return;
        }

        // Pass 1: measure subtree widths
        Map<Integer, Integer> subtreeWidths = new HashMap<>();
        measureSubtreeWidth(root, subtreeWidths);

        // Pass 2: assign positions, root centred at MARGIN + subtreeWidth/2
        int rootX = MARGIN + subtreeWidths.get(root.idNode) / 2
                    - nodeWidth(root) / 2;
        assignPositions(root, rootX, MARGIN, subtreeWidths);

        // Compute canvas bounds
        int maxX = 0, maxY = 0;
        for (NodeLayout<E> nl : layoutMap.values()) {
            maxX = Math.max(maxX, nl.x + nl.width  + MARGIN);
            maxY = Math.max(maxY, nl.y + nl.height + MARGIN);
        }
        canvasWidth  = Math.max(maxX, 400);
        canvasHeight = Math.max(maxY, 200);
    }

    // Private helpers 

    /** Width in pixels of a single node rectangle. */
    private int nodeWidth(BNode<E> node) {
        return Math.max(node.count, 1) * CELL_W;
    }

    /**
     * Bottom-up pass: computes the pixel width that the subtree rooted at
     * {@code node} needs so that none of its descendants overlap.
     */
    private int measureSubtreeWidth(BNode<E> node,
                                    Map<Integer, Integer> subtreeWidths) {
        if (node == null) return 0;

        // Collect widths of all non-null children
        int childrenTotalWidth = 0;
        int childCount = 0;
        for (int i = 0; i <= node.count; i++) {
            BNode<E> child = node.childs.get(i);
            if (child != null) {
                childrenTotalWidth += measureSubtreeWidth(child, subtreeWidths);
                childCount++;
            }
        }

        int myWidth = nodeWidth(node);
        int needed;
        if (childCount == 0) {
            needed = myWidth;
        } else {
            // children spread out with H_GAP between them
            int childrenSpan = childrenTotalWidth + H_GAP * (childCount - 1);
            needed = Math.max(myWidth, childrenSpan);
        }

        subtreeWidths.put(node.idNode, needed);
        return needed;
    }

 
    private void assignPositions(BNode<E> node, int leftEdge, int y,
                                 Map<Integer, Integer> subtreeWidths) {
        if (node == null) return;

        int subtreeW = subtreeWidths.get(node.idNode);
        int nodeW    = nodeWidth(node);

        // Centre the node within its allocated horizontal band
        int nodeX = leftEdge + (subtreeW - nodeW) / 2;

        layoutMap.put(node.idNode,
                new NodeLayout<>(node, nodeX, y, nodeW, CELL_H));

        // Distribute children left-to-right within the same horizontal band
        int childY   = y + CELL_H + V_GAP;
        int cursorX  = leftEdge;

        for (int i = 0; i <= node.count; i++) {
            BNode<E> child = node.childs.get(i);
            if (child != null) {
                int childSubtreeW = subtreeWidths.get(child.idNode);
                assignPositions(child, cursorX, childY, subtreeWidths);
                cursorX += childSubtreeW + H_GAP;
            }
        }
    }
}
