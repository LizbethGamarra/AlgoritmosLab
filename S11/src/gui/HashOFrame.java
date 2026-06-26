package gui;

import hash.HashO;
import hash.Register;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * GUI para la Tabla Hash Abierta (encadenamiento con arreglos).
 */
public class HashOFrame extends JFrame {

    private HashO<String> hashO;

    private JTextField       tfKey, tfData, tfSearchKey, tfDeleteKey;
    private JLabel           lblMsg;
    private JTable           table;
    private DefaultTableModel tableModel;

    private static final Color C_BG     = new Color(30,  30,  40);
    private static final Color C_PANEL  = new Color(45,  45,  60);
    private static final Color C_TEXT   = new Color(220, 220, 220);
    private static final Color C_ACCENT = new Color(80, 200, 160);
    private static final Color C_CHAIN  = new Color(200, 230, 255);
    private static final Color C_EMPTY  = new Color(200, 200, 200);

    public HashOFrame() {
        hashO = new HashO<>(7);
        buildUI();
    }

    private void buildUI() {
        setTitle("Hash Abierto – Encadenamiento");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(820, 560);
        setLocationRelativeTo(null);
        getContentPane().setBackground(C_BG);
        setLayout(new BorderLayout(8, 8));

        add(buildTopPanel(),   BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildStatusBar(),  BorderLayout.SOUTH);

        refreshTable();
        setVisible(true);
    }

    private JPanel buildTopPanel() {
        JPanel outer = new JPanel(new GridLayout(2, 1, 6, 6));
        outer.setBackground(C_BG);
        outer.setBorder(new EmptyBorder(10, 10, 4, 10));

        // Fila 1: Insertar
        JPanel rowIns = styledPanel();
        rowIns.add(label("Clave:"));
        tfKey  = textField(6);  rowIns.add(tfKey);
        rowIns.add(label("Dato:"));
        tfData = textField(10); rowIns.add(tfData);
        JButton btnIns = button("Insertar", C_ACCENT);
        btnIns.addActionListener(this::onInsert);
        rowIns.add(btnIns);

        rowIns.add(Box.createHorizontalStrut(20));
        rowIns.add(label("Tamaño:"));
        JTextField tfSize = textField(4); tfSize.setText("7");
        rowIns.add(tfSize);
        JButton btnReset = button("Reiniciar", new Color(180, 120, 50));
        btnReset.addActionListener(e -> {
            try {
                int s = Integer.parseInt(tfSize.getText().trim());
                hashO = new HashO<>(s);
                refreshTable();
                msg("Tabla reiniciada con tamaño " + s, false);
            } catch (NumberFormatException ex) { msg("Tamaño inválido", true); }
        });
        rowIns.add(btnReset);
        outer.add(rowIns);

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
        JButton btnDel = button("Eliminar", new Color(200, 80, 80));
        btnDel.addActionListener(this::onDelete);
        rowOps.add(btnDel);
        outer.add(rowOps);

        return outer;
    }

    private JScrollPane buildTablePanel() {
        String[] cols = {"Índice", "Cadena de elementos (colisiones)"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(32);
        table.setFont(new Font("Monospaced", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setBackground(C_PANEL);
        table.getTableHeader().setForeground(C_TEXT);
        table.setBackground(C_BG);
        table.setForeground(C_TEXT);
        table.setGridColor(new Color(70, 70, 90));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable t, Object val, boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                String chain = (String) t.getModel().getValueAt(row, 1);
                boolean empty = "(vacío)".equals(chain);
                setBackground(empty ? C_EMPTY : C_CHAIN);
                setForeground(Color.BLACK);
                setHorizontalAlignment(col == 0 ? CENTER : LEFT);
                return this;
            }
        };
        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
        table.getColumnModel().getColumn(1).setCellRenderer(renderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);

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

    private void onInsert(ActionEvent e) {
        try {
            int    key  = Integer.parseInt(tfKey.getText().trim());
            String data = tfData.getText().trim();
            if (data.isEmpty()) { msg("El dato no puede estar vacío", true); return; }
            hashO.insert(new Register<>(key, data));
            refreshTable();
            msg("Insertado: (" + key + ", " + data + ")  → índice " + (Math.abs(key) % hashO.getSize()), false);
            tfKey.setText(""); tfData.setText("");
        } catch (NumberFormatException ex) { msg("Clave debe ser un entero", true); }
    }

    private void onSearch(ActionEvent e) {
        try {
            int              key = Integer.parseInt(tfSearchKey.getText().trim());
            Register<String> r   = hashO.search(key);
            msg(r != null ? "Encontrado: " + r : "Clave " + key + " no encontrada", r == null);
        } catch (NumberFormatException ex) { msg("Clave debe ser un entero", true); }
    }

    private void onDelete(ActionEvent e) {
        try {
            int     key = Integer.parseInt(tfDeleteKey.getText().trim());
            boolean ok  = hashO.delete(key);
            refreshTable();
            msg(ok ? "Eliminado clave " + key : "Clave " + key + " no encontrada", !ok);
        } catch (NumberFormatException ex) { msg("Clave debe ser un entero", true); }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < hashO.getSize(); i++) {
            tableModel.addRow(new Object[]{i, hashO.getChainString(i)});
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
        SwingUtilities.invokeLater(HashOFrame::new);
    }
}