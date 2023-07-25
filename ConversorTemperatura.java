import javax.swing.*;
import java.text.DecimalFormat;

public class ConversorTemperatura {

    private static final String[] unidadesTemperatura = {"Celsius", "Fahrenheit", "Kelvin"};

    public void realizarConversion() {
        JComboBox<String> comboTemperaturaOrigen = new JComboBox<>(unidadesTemperatura);
        JComboBox<String> comboTemperaturaDestino = new JComboBox<>(unidadesTemperatura);
        JTextField cantidadInput = new JTextField();
        Object[] mensajes = {"Temperatura Origen:", comboTemperaturaOrigen, "Temperatura Destino:", comboTemperaturaDestino, "Ingrese la cantidad:", cantidadInput};
        int seleccionSegundaEtapa = JOptionPane.showOptionDialog(
                null, // Usar un JFrame nulo
                mensajes,
                "Conversor de Temperatura",
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
                String unidadOrigen = (String) comboTemperaturaOrigen.getSelectedItem();
                String unidadDestino = (String) comboTemperaturaDestino.getSelectedItem();

                double resultado = convertirTemperatura(unidadOrigen, unidadDestino, cantidad);

                Main.showCustomMessageDialog(
                        null,
                        formatearCantidad(cantidad) + " " + unidadOrigen + " equivalen a " + formatearCantidad(resultado) + " " + unidadDestino,
                        "Conversión de Temperatura",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (NumberFormatException e) {
                mostrarErrorCantidadInvalida();
            }
        }
    }

    private double convertirTemperatura(String unidadOrigen, String unidadDestino, double cantidad) {
        if (unidadOrigen.equals(unidadDestino)) {
            return cantidad;
        } else if (unidadOrigen.equals("Celsius")) {
            if (unidadDestino.equals("Fahrenheit")) {
                return (cantidad * 9 / 5) + 32;
            } else if (unidadDestino.equals("Kelvin")) {
                return cantidad + 273.15;
            }
        } else if (unidadOrigen.equals("Fahrenheit")) {
            if (unidadDestino.equals("Celsius")) {
                return (cantidad - 32) * 5 / 9;
            } else if (unidadDestino.equals("Kelvin")) {
                return (cantidad + 459.67) * 5 / 9;
            }
        } else if (unidadOrigen.equals("Kelvin")) {
            if (unidadDestino.equals("Celsius")) {
                return cantidad - 273.15;
            } else if (unidadDestino.equals("Fahrenheit")) {
                return cantidad * 9 / 5 - 459.67;
            }
        }

        return 0.0;
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
