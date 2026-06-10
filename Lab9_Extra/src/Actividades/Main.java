package Actividades;

import javax.swing.*;

/**
 * Application entry point.
 *
 * Asks the user for the B-Tree order (minimum 3) and then opens
 * the graphical visualiser window on the Event Dispatch Thread.
 */
public class Main {

    public static void main(String[] args) {

        // Use the system look-and-feel for a native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { /* fall back to default Metal L&F */ }

        // Ask the user for the tree order before opening the window
        SwingUtilities.invokeLater(() -> {

            int orden = askForOrden();
            if (orden < 0) {
                // User cancelled the dialog
                System.exit(0);
            }

            // Launch the main window
            new BTreeGUI<>(orden);
        });
    }

    
    private static int askForOrden() {

        // Quick-access buttons for common orders
        String[] presets   = { "Orden 3", "Orden 4", "Orden 5", "Orden 6", "Personalizado…" };
        int[]    presetVal = {  3,          4,          5,          6,         -1 };

        int choice = JOptionPane.showOptionDialog(
                null,
                "Selecciona el orden del Árbol B:",
                "Configuración del Árbol B",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                presets,
                presets[1]   // default: Orden 4
        );

        if (choice < 0) return -1;                          // window closed
        if (choice < presets.length - 1) return presetVal[choice]; // preset chosen

        // "Personalizado…" → ask for a number
        while (true) {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Ingresa el orden (mínimo 3):",
                    "Orden personalizado",
                    JOptionPane.PLAIN_MESSAGE);

            if (input == null) return -1;   // cancelled
            try {
                int n = Integer.parseInt(input.trim());
                if (n >= 3) return n;
                JOptionPane.showMessageDialog(null,
                        "El orden mínimo es 3. Intenta de nuevo.",
                        "Valor inválido", JOptionPane.WARNING_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "«" + input + "» no es un número válido.",
                        "Valor inválido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}