import javax.swing.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ConversorDivisas {

    private static final Map<String, Double> tasasDeCambio = new HashMap<>();

    static {
        // Tasas de cambio entre monedas
        tasasDeCambio.put("Peso Chileno", 1.0);
        tasasDeCambio.put("Dólar", 0.00090);
        tasasDeCambio.put("Euro", 0.00076);
        tasasDeCambio.put("Libra Esterlina", 0.00066);
        tasasDeCambio.put("Yen Japonés", 0.099);
        tasasDeCambio.put("Won Sur-Coreano", 1.03);
    }

    public void realizarConversion() {
        JComboBox<String> comboMonedasOrigen = new JComboBox<>(tasasDeCambio.keySet().toArray(new String[0]));
        JComboBox<String> comboMonedasDestino = new JComboBox<>(tasasDeCambio.keySet().toArray(new String[0]));
        JTextField cantidadInput = new JTextField();
        Object[] mensajes = {"Moneda Origen:", comboMonedasOrigen, "Moneda Destino:", comboMonedasDestino, "Ingrese la cantidad:", cantidadInput};
        int seleccionSegundaEtapa = JOptionPane.showOptionDialog(
                null, // Usar un JFrame nulo
                mensajes,
                "Conversor de Monedas",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"OK", "Cancelar"},
                "OK"
        );

        if (seleccionSegundaEtapa == JOptionPane.OK_OPTION) {
            String cantidadStr = cantidadInput.getText();
            try {
                double cantidad = Double.parseDouble(cantidadStr);
                String monedaOrigen = (String) comboMonedasOrigen.getSelectedItem();
                String monedaDestino = (String) comboMonedasDestino.getSelectedItem();

                double resultado = convertirMoneda(monedaOrigen, monedaDestino, cantidad);

                Main.showCustomMessageDialog(
                        null,
                        formatearCantidad(cantidad) + " " + monedaOrigen + " equivalen a " + formatearCantidad(resultado) + " " + monedaDestino,
                        "Conversión de Monedas",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (NumberFormatException e) {
                mostrarErrorCantidadInvalida();
            }
        }
    }

    private double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) {
        double tasaOrigen = tasasDeCambio.get(monedaOrigen);
        double tasaDestino = tasasDeCambio.get(monedaDestino);
        return cantidad * tasaDestino / tasaOrigen;
    }

    private void mostrarErrorCantidadInvalida() {
        Main.showCustomMessageDialog(
                null,
                "Ingrese una cantidad válida (solo números con punto decimal)",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private String formatearCantidad(double cantidad) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(cantidad);
    }
}
