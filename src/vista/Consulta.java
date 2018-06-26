/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.InscripcionesDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Inscripciones;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {
    public JLabel lblCarnet, lblNombres, lblApellidos,lblUniversidad,lblEdad, lblExistencia;
    public JTextField carnet,nombres,apellidos, edad;
    public JComboBox universidad;
    
    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    public JButton buscar,eliminar, insertar, limpiar, actualizar;
    
    private static final int ANCHOC = 130, ALTOC = 30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("CortoPOO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCarnet);
        container.add(lblNombres);
        container.add(lblApellidos);
        container.add(lblUniversidad);
        container.add(lblEdad);
        container.add(lblExistencia);
        container.add(carnet);
        container.add(nombres);
        container.add(apellidos);
        container.add(edad);
        container.add(universidad);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(800,800);
        eventos();
    }
    
    public final void agregarLabels(){
        lblCarnet = new JLabel("Carnet");
        lblNombres = new JLabel("Nombres");
        lblApellidos = new JLabel("Apellidos");
        lblUniversidad = new JLabel ("Universidad");
        lblEdad = new JLabel("Edad");
        lblExistencia = new JLabel("Estado");
        
        //modificar ubicacion
        lblCarnet.setBounds(10,10,ANCHOC,ALTOC);
        lblNombres.setBounds(10,60,ANCHOC,ALTOC);
        lblApellidos.setBounds(350,60,ANCHOC,ALTOC);
        lblUniversidad.setBounds(10,160,ANCHOC,ALTOC);
        lblEdad.setBounds(10,110,ANCHOC,ALTOC);
        lblExistencia.setBounds(10,240,ANCHOC,ALTOC);
    }
    
    public final void formulario(){
        carnet = new JTextField();
        nombres = new JTextField();
        apellidos = new JTextField();
        edad = new JTextField();
        universidad = new JComboBox();
        
        si = new JRadioButton("si",true);
        no = new JRadioButton("no");
        resultados = new JTable();
        
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table = new JPanel();
        //agregar elementos al combox marca
        universidad.addItem("UCA");
        universidad.addItem("UES");
        universidad.addItem("Matias");
        universidad.addItem("ESEN");
        //agregando los radioa un grupo
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);

        carnet.setBounds(100,10,ANCHOC,ALTOC);
        nombres.setBounds(100,60,ANCHOC,ALTOC);
        apellidos.setBounds(450,60,ANCHOC,ALTOC);
        edad.setBounds(110,110,ANCHOC,ALTOC);
        universidad.setBounds(110,160,ANCHOC,ALTOC);
        si.setBounds(140,240,50,ALTOC);
        no.setBounds(210,240,50,ALTOC);
         
        buscar.setBounds(300,10,ANCHOC,ALTOC);
        insertar.setBounds(10,310,ANCHOC,ALTOC);
        actualizar.setBounds(150,310,ANCHOC,ALTOC);
        eliminar.setBounds(300,310,ANCHOC,ALTOC);
        limpiar.setBounds(450,310,ANCHOC,ALTOC);
        resultados = new JTable();
        table.setBounds(10,350,500,300);
        table.add(new JScrollPane(resultados));
    }
    public void llenarTabla(){
        //Aca le colocamos el tipo de dato que tendra nuestras columnas
        //si el un dato booleano aparecera un check en el JTable
        tm = new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Carnet");
        tm.addColumn("Nombres");
        tm.addColumn("Apellidos");
        tm.addColumn("Edad");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");
        
        InscripcionesDao fd = new InscripcionesDao();
        ArrayList<Inscripciones> inscripciones = fd.readAll();
        
        //Agregamos el resultado a nuestro JTable
        //se agregan de tipo Object
        for (Inscripciones fi: inscripciones){
           //System.out.println(fi.toString());
            tm.addRow(new Object[]{fi.getCarnet(),fi.getNombres(),fi.getApellidos(),fi.getEdad(),fi.getUniversidad(),fi.getExistencia()});
            
        }
        //le agregamos el modelo a nuestra tabla
        resultados.setModel(tm);
    }
    
    public void eventos(){
        //insertar
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesDao fd = new InscripcionesDao();
                Inscripciones f = new Inscripciones(carnet.getText(),Integer.parseInt(edad.getText()),nombres.getText(),apellidos.getText(),universidad.getSelectedItem().toString(),true);
                //int edad, String nombres, String apellidos, String universidad
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Inscripcion registrada con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de hacer la inscripcion");
                }
            }
            
        });
        
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesDao fd = new InscripcionesDao();
                Inscripciones f = new Inscripciones(carnet.getText(),Integer.parseInt(edad.getText()),nombres.getText(),apellidos.getText(),universidad.getSelectedItem().toString(),
                true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null, "Inscripcion modificada con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de modificar la inscripcion");
                }
            }
            
        });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesDao fd = new InscripcionesDao();
                if (fd.delete(carnet.getText())){
                    JOptionPane.showMessageDialog(null,"Inscripcion eliminada con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de hacer la inscripcion");
                }
            }
        });
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesDao fd = new InscripcionesDao();
                Inscripciones f = fd.read(carnet.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null, "Inscripcion buscada no se encontro");
                    
                }else{
                   carnet.setText(f.getCarnet());
                   nombres.setText(f.getNombres());
                   apellidos.setText(f.getApellidos());
                   edad.setText(Integer.toString(f.getEdad()));
                   universidad.setSelectedItem(f.getUniversidad());

                   
                   if (f.getExistencia()){
                       si.setSelected(true);
                   }else{
                       no.setSelected(true);
                   }
                }
            }
            
        });
        
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    public void limpiarCampos(){
        carnet.setText("");
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        universidad.setSelectedItem("UCA");
    }
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }  
        });
    }
}
