package Actividades;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
public class BTreeGUI<E extends Comparable<E>> extends JFrame {

    // Tree and layout objects 
    private final BTree<Integer>           tree;
    private final TreeLayoutEngine<Integer> layoutEngine;
    private final BTreePanel<Integer>       treePanel;

    //  Control widgets 
    private final JTextField insertField  = new JTextField(6);
    private final JTextField removeField  = new JTextField(6);
    private final JTextField searchField  = new JTextField(6);

    private final JButton insertBtn = new JButton("Insertar");
    private final JButton removeBtn = new JButton("Eliminar");
    private final JButton searchBtn = new JButton("Buscar");

    private final JLabel statusLabel = new JLabel(" ");   // bottom status bar

    //  Constructor

    public BTreeGUI(int orden) {
        super("Visualizador de Árbol B  –  Orden " + orden);

        tree         = new BTree<>(orden);
        layoutEngine = new TreeLayoutEngine<>();
        treePanel    = new BTreePanel<>(tree, layoutEngine);

        buildUI();
        wireActions();

        // Initial empty render
        treePanel.refresh();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ── UI construction ────────────────────────────────────────────────────────

    private void buildUI() {
        setLayout(new BorderLayout(0, 0));

        // ── Title bar ─────────────────────────────────────────────────────────
        JLabel title = new JLabel("Árbol B — Visualizador Interactivo", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setOpaque(true);
        title.setBackground(new Color(40, 80, 160));
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // ── Scrollable tree canvas ────────────────────────────────────────────
        JScrollPane scrollPane = new JScrollPane(treePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new LineBorder(new Color(180, 200, 230), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // ── South panel: controls + status ───────────────────────────────────
        JPanel south = new JPanel(new BorderLayout());
        south.add(buildControlPanel(), BorderLayout.CENTER);
        south.add(buildStatusBar(),    BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);
    }

    /** Builds the three-section control panel (insert / remove / search). */
    private JPanel buildControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        panel.setBackground(new Color(240, 244, 255));
        panel.setBorder(new CompoundBorder(
                new MatteBorder(1, 0, 0, 0, new Color(180, 200, 230)),
                new EmptyBorder(8, 16, 8, 16)));

        // Style shared by all action buttons
        styleButton(insertBtn, new Color(60, 130, 200), Color.WHITE);
        styleButton(removeBtn, new Color(200, 60, 60),  Color.WHITE);
        styleButton(searchBtn, new Color(60, 160, 90),  Color.WHITE);

        // ── Insert section ────────────────────────────────────────────────────
        JPanel insPanel = sectionPanel("Inserción",
                new Color(60, 130, 200));
        insPanel.add(insertField);
        insPanel.add(insertBtn);
        panel.add(insPanel);

        // ── Remove section ────────────────────────────────────────────────────
        JPanel remPanel = sectionPanel("Eliminación",
                new Color(200, 60, 60));
        remPanel.add(removeField);
        remPanel.add(removeBtn);
        panel.add(remPanel);

        // ── Search section ────────────────────────────────────────────────────
        JPanel srchPanel = sectionPanel("Búsqueda",
                new Color(60, 160, 90));
        srchPanel.add(searchField);
        srchPanel.add(searchBtn);
        panel.add(srchPanel);

        return panel;
    }

    /** Creates a labelled section panel with a coloured top border. */
    private JPanel sectionPanel(String title, Color accentColor) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        p.setOpaque(false);
        p.setBorder(new TitledBorder(
                new LineBorder(accentColor, 2, true),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                accentColor));
        return p;
    }

    /** Applies consistent styling to action buttons. */
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(5, 14, 5, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /** Builds the thin status bar at the very bottom. */
    private JPanel buildStatusBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(230, 235, 245));
        bar.setBorder(new EmptyBorder(3, 10, 3, 10));
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        statusLabel.setForeground(new Color(60, 60, 100));
        bar.add(statusLabel, BorderLayout.WEST);
        return bar;
    }

    // ── Action wiring ──────────────────────────────────────────────────────────

    private void wireActions() {

        // Insert on button click or Enter in the text field
        ActionListener insertAction = e -> doInsert();
        insertBtn.addActionListener(insertAction);
        insertField.addActionListener(insertAction);

        // Remove on button click or Enter
        ActionListener removeAction = e -> doRemove();
        removeBtn.addActionListener(removeAction);
        removeField.addActionListener(removeAction);

        // Search on button click or Enter
        ActionListener searchAction = e -> doSearch();
        searchBtn.addActionListener(searchAction);
        searchField.addActionListener(searchAction);
    }

    // ── Business handlers ──────────────────────────────────────────────────────

    private void doInsert() {
        Integer val = parseField(insertField, "Inserción");
        if (val == null) return;

        tree.insert(val);
        treePanel.clearHighlight();
        treePanel.refresh();

        setStatus("Insertado: " + val
                + "   |   Total de claves: " + tree.countKeys()
                + "   |   Altura: " + tree.height());
        insertField.setText("");
        insertField.requestFocus();
    }

    private void doRemove() {
        Integer val = parseField(removeField, "Eliminación");
        if (val == null) return;

        // Check existence before removing so we can give a meaningful message
        boolean exists = tree.search(val);
        if (!exists) {
            setStatus("Clave " + val + " no encontrada en el árbol.");
            JOptionPane.showMessageDialog(this,
                    "La clave  " + val + "  no existe en el árbol.",
                    "Clave no encontrada",
                    JOptionPane.WARNING_MESSAGE);
            removeField.setText("");
            return;
        }

        tree.remove(val);
        treePanel.clearHighlight();
        treePanel.refresh();

        setStatus("Eliminado: " + val
                + "   |   Total de claves: " + tree.countKeys()
                + "   |   Altura: " + tree.height());
        removeField.setText("");
        removeField.requestFocus();
    }

    private void doSearch() {
        Integer val = parseField(searchField, "Búsqueda");
        if (val == null) return;

        BNode<Integer> found = tree.searchNode(val);

        if (found != null) {
            treePanel.highlightNode(found.idNode);
            setStatus("Clave " + val + " encontrada en Nodo " + found.idNode + ".");
        } else {
            treePanel.clearHighlight();
            setStatus("Clave " + val + " NO encontrada.");
            JOptionPane.showMessageDialog(this,
                    "La clave  " + val + "  no se encontró en el árbol.",
                    "Clave no encontrada",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        searchField.setText("");
        searchField.requestFocus();
    }

    // ── Helpers ────────────────────────────────────────────────────────────────

    /**
     * Reads an integer from a text field, shows a dialog on bad input.
     * Returns null if the field is empty or contains non-numeric text.
     */
    private Integer parseField(JTextField field, String context) {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            setStatus(context + ": ingresa un número.");
            return null;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            setStatus(context + ": valor inválido «" + text + "».");
            JOptionPane.showMessageDialog(this,
                    "«" + text + "» no es un número entero válido.",
                    "Entrada inválida",
                    JOptionPane.ERROR_MESSAGE);
            field.selectAll();
            return null;
        }
    }

    private void setStatus(String msg) {
        statusLabel.setText(msg);
    }
}
