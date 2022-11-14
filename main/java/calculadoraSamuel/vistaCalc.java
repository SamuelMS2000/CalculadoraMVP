package calculadoraSamuel;

import static calculadoraSamuel.Modelo.digitosMax;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author SamuelMS
 */
public class vistaCalc extends javax.swing.JFrame {
        public static void main(String args[]) {
               java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaCalc().setVisible(true); //hace visible la vista
            }
        });
    }
 
    //Variables
    private Modelo modelo;
    boolean resetear = true;
    private String mostrarResultado="";
    private String mostrarOperacion="";
    private double aux;
    private boolean mensajeError,primerNum,numeroPulsado;

    public String mostrarResultado() { //muestra el resultado en la vista
        return mostrarResultado;
    } 
 
    public void insertarNumero(int n) { 
        if (mensajeError) {
            return; //para no poder escribir ya si ha salido error
        } 

        if (primerNum) { 
            mostrarResultado = String.valueOf(n);
            primerNum = false; 
            numeroPulsado=true;  
            return;
        }

        if (mostrarResultado.length() >= digitosMax) {
            return; //controla que no se escriban mas digitos si pasa el límite
        }

        if (mostrarResultado.equals("0")) { //si el número es positivo
            mostrarResultado = String.valueOf(n);
            numeroPulsado=true;
            return;
        }
        if (mostrarResultado.equals("-0")) { //si el número es negativo
            mostrarResultado = "-" + n;
            numeroPulsado=true;
            return;
        }
        mostrarResultado += n;
        
    }

    public void insertarPunto() { //metodo para comprobar donde se pone el .
        if (mensajeError) {
            return;
        }
        if (primerNum) {
            mostrarResultado = "0.";
            primerNum = false;
            return;
        }
        if (mostrarResultado.contains("-")) { //si el numero es negativo
            mostrarResultado = "-0.";
            return;
        }
        if (mostrarResultado.contains(".")) { //si ya tiene, no se pone
            return;
        }
        mostrarResultado += ".";
    }

    public void setOperacion(char op) {
        calculando();
        
        if (mensajeError) { //si ya hay error no se hace
            return;
        }
        try {
            aux = Double.valueOf(mostrarResultado);
            mostrarOperacion = String.valueOf(op);
            primerNum = true;
        } catch (Exception e) {
            mostrarErr();
        }
        //para que no haga la operacion si no se pulsa otro numero
        numeroPulsado=false; 
    }

    public void calculando() {
        if (mensajeError) { //si ya hay error no se hace
            return;
        }
        if (mostrarOperacion.isEmpty()) { //si no hay operacion no se puede
            return;
        }
         if (!numeroPulsado) { //si no se ha pulsado un numero no se puede
            return;
        }
        try {
            char op = mostrarOperacion.charAt(0);
            Double valueIndisplay = Double.valueOf(mostrarResultado);
            Double result = modelo.calcular(op, aux, valueIndisplay);

            mostrarResultado = result.toString();
            mostrarOperacion = "";
            primerNum = true;
        } catch (NumberFormatException | ArithmeticException e) {
            mostrarErr();
        }
    }

    private void mostrarErr() { //metodo para mostrar mensaje de error
        mensajeError = true;
        mostrarResultado = "Err";
        mostrarOperacion = "";
    }

    public vistaCalc() {
        initComponents();
        jTextField1.setEditable(false);    
        jTextField1.setText("");   
        modelo = new Modelo();
        generarKeyBindigs();
    }    
    
    /*
     *A partir de aqui son todo asignación de botones de la vista
    */
    
    //Funcion con Keybindings
    private void registrarKeyBindings(JButton button,
            KeyStroke key, String mapKey) {
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(key, mapKey);
        button.getActionMap().put(mapKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                button.doClick();
            }
        });
    }
    
    //Generar los KeyBindings 
    private void generarKeyBindigs() {
        registrarKeyBindings(jButton0,
                KeyStroke.getKeyStroke("0"), "1");
        registrarKeyBindings(jButton0,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD0, 0), "1");
        registrarKeyBindings(jButton1,
                KeyStroke.getKeyStroke("1"), "1");
        registrarKeyBindings(jButton1,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD1, 0), "1");
        registrarKeyBindings(jButton2,
                KeyStroke.getKeyStroke("2"), "2");
        registrarKeyBindings(jButton2,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD2, 0), "2");
        registrarKeyBindings(jButton3,
                KeyStroke.getKeyStroke("3"), "3");
        registrarKeyBindings(jButton3,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD3, 0), "3");
        registrarKeyBindings(jButton4,
                KeyStroke.getKeyStroke("4"), "4");
        registrarKeyBindings(jButton4,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD4, 0), "4");
        registrarKeyBindings(jButton5,
                KeyStroke.getKeyStroke("5"), "5");
        registrarKeyBindings(jButton5,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD5, 0), "5");
        registrarKeyBindings(jButton6,
                KeyStroke.getKeyStroke("6"), "6");
        registrarKeyBindings(jButton6,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD6, 0), "6");
        registrarKeyBindings(jButton7,
                KeyStroke.getKeyStroke("7"), "7");
        registrarKeyBindings(jButton7,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD7, 0), "7");
        registrarKeyBindings(jButton8,
                KeyStroke.getKeyStroke("8"), "8");
        registrarKeyBindings(jButton8,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD8, 0), "8");
        registrarKeyBindings(jButton9,
                KeyStroke.getKeyStroke("9"), "9");
        registrarKeyBindings(jButton9,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_NUMPAD9, 0), "9");
        registrarKeyBindings(jButtonSumar,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_ADD, 0), "+");
        registrarKeyBindings(jButtonSumar,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_PLUS, 0), "+");
        registrarKeyBindings(jButtonRestar,
                KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "-");
        registrarKeyBindings(jButtonRestar,
                KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "-");
        registrarKeyBindings(jButtonMultipl,
                KeyStroke.getKeyStroke(KeyEvent.VK_PLUS,
                        InputEvent.SHIFT_DOWN_MASK), "*");
        registrarKeyBindings(jButtonMultipl,
                KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "*");
        registrarKeyBindings(jButtonMultipl,
                KeyStroke.getKeyStroke(KeyEvent.VK_ASTERISK, 0), "*");
        registrarKeyBindings(jButtonDividir,
                KeyStroke.getKeyStroke(KeyEvent.VK_7,
                        InputEvent.SHIFT_DOWN_MASK), "/");
        registrarKeyBindings(jButtonDividir,
                KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "/");
        registrarKeyBindings(jButtonMultipl,
                KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "/");
        registrarKeyBindings(jButtonPunto,
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_DECIMAL, 0), ".");
        registrarKeyBindings(jButtonPunto,
                KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0),
                ".");

    }
     public void actualizarVista() { //metodo para mostrar el resultado en texto
        jTextField1.setText(mostrarResultado());
    }
    public void presionarBotonNum(int n) { //metodo para poner numeros
        insertarNumero(n);
        actualizarVista();
    }

    public void presionarOp(char op) { //metodo para poner la operacion
        setOperacion(op);
        actualizarVista();
    }

    public void presionarPunto() { //metodo para poner el punto
        insertarPunto();
        actualizarVista();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton0 = new javax.swing.JButton();
        jButtonDividir = new javax.swing.JButton();
        jButtonMultipl = new javax.swing.JButton();
        jButtonRestar = new javax.swing.JButton();
        jButtonSumar = new javax.swing.JButton();
        jButtonPunto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jTextField1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 211;
        gridBagConstraints.ipady = 48;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 12);
        getContentPane().add(jTextField1, gridBagConstraints);

        jButton1.setText("1");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setIconTextGap(3);
        jButton1.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 6, 6);
        getContentPane().add(jButton1, gridBagConstraints);

        jButton2.setText("2");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setIconTextGap(3);
        jButton2.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        getContentPane().add(jButton2, gridBagConstraints);

        jButton3.setText("3");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setIconTextGap(3);
        jButton3.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        getContentPane().add(jButton3, gridBagConstraints);

        jButton4.setText("4");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.setIconTextGap(3);
        jButton4.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 6, 6);
        getContentPane().add(jButton4, gridBagConstraints);

        jButton5.setText("5");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setIconTextGap(3);
        jButton5.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        getContentPane().add(jButton5, gridBagConstraints);

        jButton6.setText("6");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.setIconTextGap(3);
        jButton6.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        getContentPane().add(jButton6, gridBagConstraints);

        jButton7.setText("7");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton7.setIconTextGap(3);
        jButton7.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 6, 6);
        getContentPane().add(jButton7, gridBagConstraints);

        jButton8.setText("8");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton8.setIconTextGap(3);
        jButton8.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 6, 6);
        getContentPane().add(jButton8, gridBagConstraints);

        jButton9.setText("9");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton9.setIconTextGap(3);
        jButton9.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 6, 6);
        getContentPane().add(jButton9, gridBagConstraints);

        jButton0.setText("0");
        jButton0.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton0.setIconTextGap(3);
        jButton0.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 12, 6);
        getContentPane().add(jButton0, gridBagConstraints);

        jButtonDividir.setText("/");
        jButtonDividir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonDividir.setIconTextGap(3);
        jButtonDividir.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonDividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDividirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 6, 12);
        getContentPane().add(jButtonDividir, gridBagConstraints);

        jButtonMultipl.setText("*");
        jButtonMultipl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonMultipl.setIconTextGap(3);
        jButtonMultipl.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonMultipl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMultiplActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 12);
        getContentPane().add(jButtonMultipl, gridBagConstraints);

        jButtonRestar.setText("-");
        jButtonRestar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonRestar.setIconTextGap(3);
        jButtonRestar.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 12);
        getContentPane().add(jButtonRestar, gridBagConstraints);

        jButtonSumar.setText("+");
        jButtonSumar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonSumar.setIconTextGap(3);
        jButtonSumar.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonSumar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSumarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 12, 12);
        getContentPane().add(jButtonSumar, gridBagConstraints);

        jButtonPunto.setText(".");
        jButtonPunto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPunto.setIconTextGap(3);
        jButtonPunto.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPuntoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 12, 6);
        getContentPane().add(jButtonPunto, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        presionarBotonNum(8);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        presionarBotonNum(9);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButtonDividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDividirActionPerformed
        presionarOp('/');
    }//GEN-LAST:event_jButtonDividirActionPerformed

    private void jButtonMultiplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMultiplActionPerformed
        presionarOp('*');
    }//GEN-LAST:event_jButtonMultiplActionPerformed

    private void jButtonRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestarActionPerformed
       presionarOp('-');
    }//GEN-LAST:event_jButtonRestarActionPerformed

    private void jButtonPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPuntoActionPerformed
        presionarPunto();
    }//GEN-LAST:event_jButtonPuntoActionPerformed

    private void jButtonSumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSumarActionPerformed
        presionarOp('+');
    }//GEN-LAST:event_jButtonSumarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        presionarBotonNum(7);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        presionarBotonNum(4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        presionarBotonNum(5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        presionarBotonNum(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton0ActionPerformed
        presionarBotonNum(0);
    }//GEN-LAST:event_jButton0ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        presionarBotonNum(6);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        presionarBotonNum(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        presionarBotonNum(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton0;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonDividir;
    private javax.swing.JButton jButtonMultipl;
    private javax.swing.JButton jButtonPunto;
    private javax.swing.JButton jButtonRestar;
    private javax.swing.JButton jButtonSumar;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}