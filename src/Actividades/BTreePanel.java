package Actividades;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * JPanel that renders a BTree visually.
 *
 * Responsibilities:
 *  - Accepts a reference to the BTree and a TreeLayoutEngine.
 *  - On every repaint it reads the already-computed layout and paints:
 *      • Edges (parent → child lines)
 *      • Node rectangles with key cells
 *      • Node id labels above each rectangle
 *      • A highlighted node (for search results)
 *
 * No tree-modification logic lives here.
 */
public class BTreePanel<E extends Comparable<E>> extends JPanel {

    // Colour palette 
    private static final Color COLOR_NODE_BG       = new Color(220, 235, 255); // light blue
    private static final Color COLOR_NODE_BORDER    = new Color(60, 100, 180);  // dark blue
    private static final Color COLOR_KEY_TEXT       = new Color(20,  20,  60);
    private static final Color COLOR_ID_TEXT        = new Color(100, 100, 100);
    private static final Color COLOR_EDGE           = new Color(80, 120, 180);
    private static final Color COLOR_HIGHLIGHT_BG   = new Color(255, 230, 120); // yellow
    private static final Color COLOR_HIGHLIGHT_BORDER = new Color(200, 60, 60); // red
    private static final Color COLOR_EMPTY_TEXT     = new Color(150, 150, 150);

    private static final int   STROKE_NORMAL        = 1;
    private static final int   STROKE_HIGHLIGHT     = 3;
    private static final Font  FONT_KEY  = new Font("SansSerif", Font.BOLD,  13);
    private static final Font  FONT_ID   = new Font("SansSerif", Font.PLAIN, 10);

    //  State 
    private final BTree<E>             tree;
    private final TreeLayoutEngine<E>  layoutEngine;

    /** Id of the node to highlight; -1 means no highlight. */
    private int highlightedNodeId = -1;

    //  Constructor

    public BTreePanel(BTree<E> tree, TreeLayoutEngine<E> layoutEngine) {
        this.tree         = tree;
        this.layoutEngine = layoutEngine;
        setBackground(Color.WHITE);
    }

    //  Public API 

    /**
     * Triggers a full layout recomputation and repaints the panel.
     * Call this after every insert / remove.
     */
    public void refresh() {
        layoutEngine.compute(tree);
        // Resize the preferred area so JScrollPane can scroll when the tree is large
        Dimension pref = new Dimension(
                Math.max(layoutEngine.getCanvasWidth(),  600),
                Math.max(layoutEngine.getCanvasHeight(), 400));
        setPreferredSize(pref);
        revalidate();
        repaint();
    }

   
    public void highlightNode(int nodeId) {
        this.highlightedNodeId = nodeId;
        repaint();
    }

    
    public void clearHighlight() {
        highlightNode(-1);
    }

    //  Painting

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Map<Integer, NodeLayout<E>> layouts = layoutEngine.getLayoutMap();

        if (layouts.isEmpty()) {
            paintEmptyMessage(g2);
            return;
        }

        // 1. Draw edges first (so they appear behind nodes)
        for (NodeLayout<E> nl : layouts.values()) {
            paintEdges(g2, nl, layouts);
        }

        // 2. Draw nodes on top
        for (NodeLayout<E> nl : layouts.values()) {
            paintNode(g2, nl);
        }
    }

    //  Drawing helpers 

    private void paintEmptyMessage(Graphics2D g2) {
        g2.setColor(COLOR_EMPTY_TEXT);
        g2.setFont(new Font("SansSerif", Font.ITALIC, 16));
        String msg = "El árbol está vacío. Inserta claves para empezar.";
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth()  - fm.stringWidth(msg)) / 2;
        int y = (getHeight() + fm.getAscent())       / 2;
        g2.drawString(msg, x, y);
    }

   
    private void paintEdges(Graphics2D g2,
                            NodeLayout<E> parent,
                            Map<Integer, NodeLayout<E>> layouts) {
        g2.setColor(COLOR_EDGE);
        g2.setStroke(new BasicStroke(STROKE_NORMAL));

        BNode<E> node = parent.node;
        for (int i = 0; i <= node.count; i++) {
            BNode<E> child = node.childs.get(i);
            if (child == null) continue;

            NodeLayout<E> childLayout = layouts.get(child.idNode);
            if (childLayout == null) continue;

            // Line from bottom-centre of parent cell to top-centre of child
            int x1 = cellCenterX(parent, i);
            int y1 = parent.y + parent.height;
            int x2 = childLayout.topCenterX();
            int y2 = childLayout.topCenterY();

            // Draw a smooth bezier-like line using a quadratic curve
            int ctrlY = (y1 + y2) / 2;
            g2.draw(new java.awt.geom.QuadCurve2D.Float(x1, y1, x1, ctrlY, x2, y2));
        }
    }

    
    private int cellCenterX(NodeLayout<E> nl, int childIndex) {
        // Each child pointer sits between key[i-1] and key[i].
        // Child 0 is to the left of key[0]; child k is to the right of key[k-1].
        int cellW = TreeLayoutEngine.CELL_W;
        return nl.x + childIndex * cellW + cellW / 2;
    }

    
    private void paintNode(Graphics2D g2, NodeLayout<E> nl) {
        BNode<E> node = nl.node;
        boolean highlighted = (node.idNode == highlightedNodeId);

        int x = nl.x;
        int y = nl.y;
        int w = nl.width;
        int h = nl.height;
        int cellW = TreeLayoutEngine.CELL_W;

        //  Node id label (above the rectangle) 
        g2.setFont(FONT_ID);
        g2.setColor(COLOR_ID_TEXT);
        String idLabel = "Nodo " + node.idNode;
        FontMetrics idFm = g2.getFontMetrics();
        int labelX = x + (w - idFm.stringWidth(idLabel)) / 2;
        g2.drawString(idLabel, labelX, y - 3);

        //  Node rectangle background 
        Color bg     = highlighted ? COLOR_HIGHLIGHT_BG     : COLOR_NODE_BG;
        Color border = highlighted ? COLOR_HIGHLIGHT_BORDER : COLOR_NODE_BORDER;

        g2.setColor(bg);
        g2.fillRoundRect(x, y, w, h, 6, 6);

        //  Cell dividers and key text 
        g2.setFont(FONT_KEY);
        FontMetrics keyFm = g2.getFontMetrics();

        for (int i = 0; i < node.count; i++) {
            int cellX = x + i * cellW;

            // Divider between cells (not before the first)
            if (i > 0) {
                g2.setColor(border);
                g2.drawLine(cellX, y + 2, cellX, y + h - 2);
            }

            // Key text centred in cell
            String keyStr = String.valueOf(node.keys.get(i));
            int textX = cellX + (cellW - keyFm.stringWidth(keyStr)) / 2;
            int textY = y + (h + keyFm.getAscent() - keyFm.getDescent()) / 2 - 1;
            g2.setColor(COLOR_KEY_TEXT);
            g2.drawString(keyStr, textX, textY);
        }

        // Outer border 
        g2.setColor(border);
        int strokeW = highlighted ? STROKE_HIGHLIGHT : STROKE_NORMAL;
        g2.setStroke(new BasicStroke(strokeW));
        g2.drawRoundRect(x, y, w, h, 6, 6);

        // Extra glow ring for highlighted nodes
        if (highlighted) {
            g2.setStroke(new BasicStroke(1.5f));
            g2.setColor(new Color(200, 60, 60, 80));
            g2.drawRoundRect(x - 3, y - 3, w + 6, h + 6, 10, 10);
        }
    }
}