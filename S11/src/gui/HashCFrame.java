package gui;

import hash.HashC;
import hash.Register;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * GUI para la Tabla Hash Cerrada (sondeo lineal).
 * Muestra la tabla como JTable con colores por estado.
 */
public class HashCFrame extends JFrame {

    private HashC<String> hashC;

    // Controles
    private JTextField tfKey, tfData, tfSearchKey, tfDeleteKey;
    private JLabel     lblMsg;
    private JTable     table;
    private DefaultTableModel tableModel;

    // Colores de estado
    private static final Color C_EMPTY    = new Color(230, 230, 230);
    private static final Color C_OCCUPIED = new Color(144, 238, 144);
    private static final Color C_DELETED  = new Color(255, 180, 180);
    private static final Color C_BG       = new Color(30,  30,  40);
    private static final Color C_PANEL    = new Color(45,  45,  60);
    private static final Color C_TEXT     = new Color(220, 220, 220);
    private static final Color C_ACCENT   = new Color(80, 160, 255);

    public HashCFrame() {
        hashC = new HashC<>(13);
        buildUI();
    }

    private void buildUI() {
        setTitle("Hash Cerrado – Sondeo Lineal");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(820, 640);
        setLocationRelativeTo(null);
        getContentPane().setBackground(C_BG);
        setLayout(new BorderLayout(8, 8));

        add(buildTopPanel(),    BorderLayout.NORTH);
        add(buildTablePanel(),  BorderLayout.CENTER);
        add(buildStatusBar(),   BorderLayout.SOUTH);

        refreshTable();
        setVisible(true);
    }

    // ── Panel superior con controles ─────────────────────────────────────────
    private JPanel buildTopPanel() {
        JPanel outer = new JPanel(new GridLayout(2, 1, 6, 6));
        outer.setBackground(C_BG);
        outer.setBorder(new EmptyBorder(10, 10, 4, 10));

        // Fila 1: Insertar
        JPanel rowInsert = styledPanel();
        rowInsert.add(label("Clave:"));
        tfKey  = textField(6);  rowInsert.add(tfKey);
        rowInsert.add(label("Dato:"));
        tfData = textField(10); rowInsert.add(tfData);
        JButton btnInsert = button("Insertar", C_ACCENT);
        btnInsert.addActionListener(this::onInsert);
        rowInsert.add(btnInsert);

        // Separador de tamaño
        rowInsert.add(Box.createHorizontalStrut(20));
        rowInsert.add(label("Tamaño tabla:"));
        JTextField tfSize = textField(4); tfSize.setText("13");
        rowInsert.add(tfSize);
        JButton btnResize = button("Reiniciar", new Color(180, 120, 50));
        btnResize.addActionListener(e -> {
            try {
                int s = Integer.parseInt(tfSize.getText().trim());
                hashC = new HashC<>(s);
                refreshTable();
                msg("Tabla reiniciada con tamaño " + s, false);
            } catch (NumberFormatException ex) { msg("Tamaño inválido", true); }
        });
        rowInsert.add(btnResize);
        outer.add(rowInsert);

        // Fila 2: Buscar y Eliminar
        JPanel rowOps = styledPanel();
        rowOps.add(label("Buscar clave:"));
        tfSearchKey = textField(6); rowOps.add(tfSearchKey);
        JButton btnSearch = button("Buscar", new Color(100, 180, 100));
        btnSearch.addActionListener(this::onSearch);
        rowOps.add(btnSearch);

        rowOps.add(Box.createHorizontalStrut(20));
        rowOps.add(label("Eliminar clave:"));
        tfDeleteKey = textField(6); rowOps.add(tfDeleteKey);
        JButton btnDelete = button("Eliminar", new Color(200, 80, 80));
        btnDelete.addActionListener(this::onDelete);
        rowOps.add(btnDelete);
        outer.add(rowOps);

        return outer;
    }

    // ── JTable que muestra la tabla hash ────────────────────────────────────
    private JScrollPane buildTablePanel() {
        String[] cols = {"Índice", "Estado", "Valor"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Monospaced", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setBackground(C_PANEL);
        table.getTableHeader().setForeground(C_TEXT);
        table.setBackground(C_BG);
        table.setForeground(C_TEXT);
        table.setGridColor(new Color(70, 70, 90));

        // Renderer con colores por estado
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable t, Object val, boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                String st = (String) t.getModel().getValueAt(row, 1);
                if ("OCCUPIED".equals(st))      setBackground(C_OCCUPIED);
                else if ("DELETED".equals(st))  setBackground(C_DELETED);
                else                            setBackground(C_EMPTY);
                setForeground(Color.BLACK);
                setHorizontalAlignment(CENTER);
                return this;
            }
        };
        for (int c = 0; c < 3; c++) table.getColumnModel().getColumn(c).setCellRenderer(renderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        sp.getViewport().setBackground(C_BG);
        return sp;
    }

    private JLabel buildStatusBar() {
        lblMsg = new JLabel(" ");
        lblMsg.setForeground(C_TEXT);
        lblMsg.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblMsg.setBorder(new EmptyBorder(4, 12, 6, 12));
        return lblMsg;
    }

    // ── Acciones ────────────────────────────────────────────────────────────
    private void onInsert(ActionEvent e) {
        try {
            int    key  = Integer.parseInt(tfKey.getText().trim());
            String data = tfData.getText().trim();
            if (data.isEmpty()) { msg("El dato no puede estar vacío", true); return; }
            hashC.insert(new Register<>(key, data));
            refreshTable();
            msg("Insertado: (" + key + ", " + data + ")", false);
            tfKey.setText(""); tfData.setText("");
        } catch (NumberFormatException ex) { msg("Clave debe ser un entero", true); }
    }

    private void onSearch(ActionEvent e) {
        try {
            int              key = Integer.parseInt(tfSearchKey.getText().trim());
            Register<String> r   = hashC.search(key);
            if (r != null) msg("Encontrado: " + r, false);
            else           msg("Clave " + key + " no encontrada", true);
        } catch (NumberFormatException ex) { msg("Clave debe ser un entero", true); }
    }

    private void onDelete(ActionEvent e) {
        try {
            int     key = Integer.parseInt(tfDeleteKey.getText().trim());
            boolean ok  = hashC.delete(key);
            refreshTable();
            msg(ok ? "Eliminado (lógico) clave " + key : "Clave " + key + " no encontrada", !ok);
        } catch (NumberFormatException ex) { msg("Clave debe ser un entero", true); }
    }

    // ── Helpers UI ───────────────────────────────────────────────────────────
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < hashC.getSize(); i++) {
            tableModel.addRow(new Object[]{i, hashC.getStatusLabel(i), hashC.getCellValue(i)});
        }
    }

    private void msg(String text, boolean error) {
        lblMsg.setForeground(error ? new Color(255, 100, 100) : new Color(100, 230, 100));
        lblMsg.setText(text);
    }

    private JPanel styledPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        p.setBackground(C_PANEL);
        p.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
        return p;
    }

    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(C_TEXT);
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
        return l;
    }

    private JTextField textField(int cols) {
        JTextField tf = new JTextField(cols);
        tf.setBackground(new Color(60, 60, 75));
        tf.setForeground(C_TEXT);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(BorderFactory.createLineBorder(C_ACCENT));
        return tf;
    }

    private JButton button(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(4, 12, 4, 12));
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HashCFrame::new);
    }
}