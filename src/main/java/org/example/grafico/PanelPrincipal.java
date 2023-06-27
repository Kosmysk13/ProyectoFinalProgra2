package org.example.grafico;

import org.example.logica.Recorridos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Crea el panel que contendra al panel comprador y panel expendedor en su interior, se encarga de juntar ambos
 * paneles para mostrarse en uno solo para la Ventana
 */
public class PanelPrincipal extends JPanel implements ActionListener{
    private PanelAsientos pa;
    private PanelHorarios ph;
    private PanelRecorridos pr;
    private JComboBox origen,destino,pisoBus;
    private JButton busqueda,ConfirmarPago,ElegirHorario,asientos[][][];
    private JRadioButton[][] horarios;
    private ButtonGroup horariosG;
    private JPanel PanelRecorridos;
    private String[] ciudadOrigen,ciudadDestino,pisosB;
    private String auxrec;
    private boolean[][][] ocupado;
    private int hora=12,auxCualHorario,auxPiso=1,auxElegirH=0;
    /**
     * Metodo constructor que crea instancias del panel comprador y panel expendedor
     * Ademas define los limites del panel
     */
    public PanelPrincipal(){
        this.setLayout(null);
        this.setBackground(new Color(188,227,255));
        this.setVisible(true);

        pa = new PanelAsientos();
        this.add(pa);
        ph = new PanelHorarios();
        this.add(ph);
        pr = new PanelRecorridos();
        this.add(pr);

        ciudadOrigen = new String[] {String.valueOf(Recorridos.CHILLAN), String.valueOf(Recorridos.CONCEPCION),String.valueOf(Recorridos.LOS_ANGELES)};
        ciudadDestino = new String[] {String.valueOf(Recorridos.CONCEPCION),String.valueOf(Recorridos.CHILLAN),String.valueOf(Recorridos.LOS_ANGELES)};
        pisosB = new String[] {"PRIMER   PISO","SEGUNDO   PISO"};
        asientos = new JButton[6][6][53];
        ocupado = new boolean[6][6][53];

        origen = new JComboBox(ciudadOrigen);
        origen.addActionListener(this);
        origen.setBounds(120,70,400,50);
        origen.setFocusable(false);
        this.add(origen);
        destino = new JComboBox(ciudadDestino);
        destino.addActionListener(this);
        destino.setBounds(570,70,400,50);
        destino.setFocusable(false);
        this.add(destino);
        busqueda = new JButton("   BUSCAR");
        busqueda.setBounds(1020,70,120,50);
        busqueda.setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/lupa.png"));
        busqueda.addActionListener(this);
        busqueda.setFocusable(false);
        this.add(busqueda);

        pisoBus = new JComboBox(pisosB);
        pisoBus.addActionListener(this);
        pisoBus.setBounds(500,274,700,75);
        pisoBus.setFocusable(false);
        this.add(pisoBus);

        ConfirmarPago = new JButton("Confirmar Pago");
        ConfirmarPago.setBounds(1000,750,200,50);
        ConfirmarPago.setFocusable(false);
        ConfirmarPago.addActionListener(this);
        this.add(ConfirmarPago);

        ElegirHorario = new JButton("Elegir Horario");
        ElegirHorario.setBounds(50,750,300,50);
        ElegirHorario.setFocusable(false);
        ElegirHorario.addActionListener(this);
        this.add(ElegirHorario);

        PanelRecorridos = new JPanel();
        PanelRecorridos.setBounds(50,25, 1150, 150);
        PanelRecorridos.setBackground(new Color(99, 132, 180));
        PanelRecorridos.setVisible(true);
        this.add(PanelRecorridos);

        horarios = new JRadioButton[6][6];
        horariosG = new ButtonGroup();

        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++){
                horarios[i][j] = new JRadioButton("SALIDA: "+(hora+j)+":00 - LLEGADA: "+(hora+j+2)+":00");
                horarios[i][j].setBounds(100,285+(75*j),250,50);
                horarios[i][j].setBackground(new Color(188,227,255));
                horarios[i][j].setFocusable(false);
                horarios[i][j].addActionListener(this);
                horariosG.add(horarios[i][j]);
                this.add(horarios[i][j]);
                horarios[i][j].setVisible(false);
                repaint();
            }
        }

        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++){
                for (int k=0;k<53;k++){
                    if (k<5){
                        asientos[i][j][k] = new JButton();                                            //Asiento Premium
                        asientos[i][j][k].setBounds(598+(118*k),410,103,50);
                        asientos[i][j][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoPLibre.png"));
                        asientos[i][j][k].setBackground(Color.white);
                        asientos[i][j][k].addActionListener(this);
                        asientos[i][j][k].setFocusable(false);
                        this.add(asientos[i][j][k]);
                        asientos[i][j][k].setVisible(false);
                    }else if (k<11){
                        asientos[i][j][k] = new JButton();                                          //Asiento Cama
                        asientos[i][j][k].setBounds(598+((k-5)*96),570,81,50);
                        asientos[i][j][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoCLibre.png"));
                        asientos[i][j][k].setBackground(Color.white);
                        asientos[i][j][k].addActionListener(this);
                        asientos[i][j][k].setFocusable(false);
                        this.add(asientos[i][j][k]);
                        asientos[i][j][k].setVisible(false);                        //k+j+2
                    }else if (k<17){
                        asientos[i][j][k] = new JButton();                                          //Asiento Cama
                        asientos[i][j][k].setBounds(598+((k-11)*96),630,81,50);
                        asientos[i][j][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoCLibre.png"));
                        asientos[i][j][k].setBackground(Color.white);
                        asientos[i][j][k].addActionListener(this);
                        asientos[i][j][k].setFocusable(false);
                        this.add(asientos[i][j][k]);
                        asientos[i][j][k].setVisible(false);                        //k+j+2
                    }else if (k<26){
                        asientos[i][j][k] = new JButton();
                        asientos[i][j][k].setBounds(598+((k-17)*65),410,50,50);
                        asientos[i][j][k].setBackground(Color.white);
                        asientos[i][j][k].addActionListener(this);
                        asientos[i][j][k].setFocusable(false);
                        asientos[i][j][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoSCLibre.png"));
                        this.add(asientos[i][j][k]);
                        asientos[i][j][k].setVisible(false);                        //k+j+2
                    }else if (k<35){
                        asientos[i][j][k] = new JButton();
                        asientos[i][j][k].setBounds(598+((k-26)*65),470,50,50);
                        asientos[i][j][k].setBackground(Color.white);
                        asientos[i][j][k].addActionListener(this);
                        asientos[i][j][k].setFocusable(false);
                        asientos[i][j][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoSCLibre.png"));
                        this.add(asientos[i][j][k]);
                        asientos[i][j][k].setVisible(false);                        //k+j+2
                    }else if (k<44){
                        asientos[i][j][k] = new JButton();
                        asientos[i][j][k].setBounds(598+((k-35)*65),570,50,50);
                        asientos[i][j][k].setBackground(Color.white);
                        asientos[i][j][k].addActionListener(this);
                        asientos[i][j][k].setFocusable(false);
                        asientos[i][j][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoSCLibre.png"));
                        this.add(asientos[i][j][k]);
                        asientos[i][j][k].setVisible(false);                        //k+j+2
                    }else{
                        asientos[i][j][k] = new JButton();
                        asientos[i][j][k].setBounds(598+((k-44)*65),630,50,50);
                        asientos[i][j][k].setBackground(Color.white);
                        asientos[i][j][k].addActionListener(this);
                        asientos[i][j][k].setFocusable(false);
                        asientos[i][j][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoSCLibre.png"));
                        this.add(asientos[i][j][k]);
                        asientos[i][j][k].setVisible(false);                        //k+j+2
                    }
                }
            }
        }
    }

    /**
     * Metodo que se encarga de la parte visual del panel
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint (Graphics g){
        super.paint(g);
        pa.paint(g);
        ph.paint(g);
        pr.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==origen){
            if (auxrec!=null){
                destino.addItem(auxrec);
            }
            auxrec = origen.getSelectedItem().toString();
            destino.removeItem(origen.getSelectedItem());
        }
        if (e.getSource()==busqueda){
            for (int i=0;i<6;i++){
                horarios[0][i].setVisible(true);
                repaint();
            }
        }
        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++){
                if (e.getSource()==horarios[i][j]){
                    auxCualHorario=j;
                    if (asientos[0]!=null){
                        for (int k=0;k<36;k=k+4){
                            for (int l=0;l<2;l++){
                                //this.remove(asientos[i][j][k]);
                                //this.remove(asientos[i][j][k]);
                                ocupado[0][0][k] =true;
                                ocupado[0][0][k]=true;
                            }
                        }
                    }
                    auxElegirH=1;
                }
            }
        }
        if (e.getSource()==ElegirHorario){
            if (auxElegirH==1){
                //for (int k=0;k<6;k++){
                    //for (int j=0;j<6;j++){
                        for (int k=0;k<17;k++){
                            asientos[0][auxCualHorario][k].setVisible(true);

                            asientos[0][auxCualHorario][k].setVisible(true);                        //k+j+2

                            ocupado[0][auxCualHorario][k]=false;
                            ocupado[0][auxCualHorario][k]=false;
                        }
                    //}
                //}
                auxElegirH=2;
            }
            repaint();
        }
        if (e.getSource()==pisoBus){
            if ((pisoBus.getSelectedItem()=="PRIMER   PISO")&&(auxPiso==2)&&(auxElegirH==2)){
                for (int i=0;i<6;i++){
                    for (int j=0;j<2;j++){
                        for (int k=0;k<53;k++){
                            //asientos[i][j][k].setVisible(false);
                            //this.remove(asientos[k][j][i]);           //k+j+2
                            ocupado[0][0][k]=true;
                            ocupado[0][0][k]=true;
                        }
                    }
                }
                for (int k=0;k<6;k++){
                    for (int i=0;i<17;i++){
                        asientos[i][auxCualHorario][k].setVisible(true);
                        ocupado[0][0][k]=false;
                    }
                }
                auxPiso=1;
            }else if ((pisoBus.getSelectedItem()=="SEGUNDO   PISO")&&(auxPiso==1)&&(auxElegirH==2)){
                if (asientos[0]!=null){
                    for (int i=0;i<6;i++){
                        for (int j=0;j<6;j++){
                            for (int k=17;k<53;k++){
                                //this.remove(asientos[k][j][i]);
                                //asientos[k][j][i].setVisible(false);
                                //this.remove(asientos[k][j][i]);                               //k+j+2

                                ocupado[0][0][k]=true;
                                ocupado[0][0][k]=true;
                            }
                        }
                    }
                }
                for (int i=0;i<6;i++){
                    for (int k=17;k<53;k++){
                        asientos[i][auxCualHorario][k].setVisible(true);
                        ocupado[0][0][k]=false;
                    }
                }
                auxPiso=2;
            }
            repaint();
        }
        for (int i=0;i<6;i++){
            for (int k=0;k<53;k++){
                if (auxPiso==2){
                    if (e.getSource()==asientos[i]){
                        if (ocupado[0][0][k]==false){
                            asientos[i][auxCualHorario][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoSCOcupado.png"));
                            ocupado[0][auxCualHorario][k]=true;
                        }else{
                            asientos[i][auxCualHorario][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoSCLibre.png"));
                            ocupado[0][auxCualHorario][k]=false;
                        }
                    }
                }else{
                    if (e.getSource()==asientos[i]){
                        if (i%8==0){
                            if (ocupado[0][0][k]==false){
                                asientos[i][auxCualHorario][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoPOcupado.png"));
                                ocupado[0][auxCualHorario][k]=true;
                            }else{
                                asientos[i][auxCualHorario][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoPLibre.png"));
                                ocupado[0][auxCualHorario][k]=false;
                            }
                        }else{
                            if (ocupado[0][0][k]==false){
                                asientos[i][auxCualHorario][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoCOcupado.png"));
                                ocupado[0][auxCualHorario][k]=true;
                            }else{
                                asientos[i][auxCualHorario][k].setIcon(new ImageIcon("src/main/java/org/example/elementosPanel/asientoCLibre.png"));
                                ocupado[0][auxCualHorario][k]=false;
                            }
                        }
                    }
                }
            }
        }
        if (e.getSource()==ConfirmarPago){
            for (int i=0;i<6;i++){
                for (int j=0;j<6;j++){
                    for (int k=0;k<53;k++){
                        if (ocupado[i][auxCualHorario][k]==true){
                            asientos[i][auxCualHorario][k].removeActionListener(this);
                        }
                    }
                }
            }
        }
    }
}
