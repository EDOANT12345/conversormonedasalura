import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        boolean continuar;

        do {
            String[] opcionesPrimeraEtapa = {"Conversor de Monedas", "Conversor de Temperatura"};
            int seleccionPrimeraEtapa = showCustomOptionDialog(
                    null,
                    "Seleccione una opción",
                    "Menú Principal",
                    opcionesPrimeraEtapa,
                    opcionesPrimeraEtapa[0]
            );

            if (seleccionPrimeraEtapa == 0) {
                ConversorDivisas conversorDivisas = new ConversorDivisas();
                conversorDivisas.realizarConversion();
            } else if (seleccionPrimeraEtapa == 1) {
                ConversorTemperatura conversorTemperatura = new ConversorTemperatura();
                conversorTemperatura.realizarConversion();
            } else {
                // El usuario cerró el cuadro de diálogo o hizo clic en cancelar
                break;
            }

            // Mostrar cuadro de diálogo de continuar
            int opcionContinuar = showCustomOptionDialog(
                    null,
                    "¿Desea continuar?",
                    "Continuar",
                    new String[]{"SI", "NO"},
                    "SI"
            );

            continuar = opcionContinuar == 0;
        } while (continuar);

        // Mostrar mensaje de programa terminado
        showCustomMessageDialog(
                null,
                "Programa terminado",
                "Fin del Programa",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static int showCustomOptionDialog(JFrame parentComponent, Object message, String title, Object[] options, Object initialValue) {
        JPanel panel = createGreenPanel();
        JLabel label = createLabel(message.toString());
        panel.add(label);

        return JOptionPane.showOptionDialog(
                parentComponent,
                panel,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                initialValue
        );
    }

    public static void showCustomMessageDialog(JFrame parentComponent, Object message, String title, int messageType) {
        JPanel panel = createGreenPanel();
        JLabel label = createLabel(message.toString());
        panel.add(label);

        JOptionPane.showMessageDialog(
                parentComponent,
                panel,
                title,
                messageType
        );
    }

    private static JPanel createGreenPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        return panel;
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        return label;
    }
}


