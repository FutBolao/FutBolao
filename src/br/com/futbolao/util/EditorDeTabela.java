package br.com.futbolao.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
//import javax.swing.JComboBox;
//import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class EditorDeTabela extends AbstractCellEditor implements TableCellEditor {
 
    protected JPanel p;
    protected JTextField tf;
//    protected JComboBox cf;
 
    /*
     * Construtor da Classe
     */
    public EditorDeTabela() {
        //Primeiro criamos um Painel, assim comparamos que quando a célula é
        //editada, é como se fosse um novo elemento, retornando seu valor.
        this.p = new JPanel(new BorderLayout());
 
        //Aqui adicionamos um campo de texto, ou seja, quando editar a célula,
        //na verdade estará digitando num TextField dentro dela.
        (this.tf = new JTextField()).setBorder(null);
        tf.setDocument(new FormataCampoApenasNumeros(3));
        p.add(tf);
 
        //EXTRA 1: Caso queira validar seu TextField, fique a vontade, abaixo está um
        //exemplo, onde adicionei um KeyListener, chamando um método,
        //validando para aceitar apenas números.
        /*
        tf.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNumeroKeyTyped(evt);
            }
        });
        * */
 
        //EXTRA 2: Caso queira usar um ComboBox, ao invés de um TextField:
        /*
         * this.cf = new JComboBox();
         * cf.addItem("AC");
         * cf.addItem("AL");
         */
    }
 
    /*
     * Este método é responsável por retornar ao TableModel da JTable, o
     * valor presente atual na célula.
     */
    @Override
    public Object getCellEditorValue() {
        return tf.getText(); // Usado com TextField
        // return cf.getSelectedItem().toString() //Usado com ComboBox
    }
 
    /*
     * Este método fica responsável à atribuir o valor já presente, ao textfield
     * na hora da edição.
     * Vamos supor que vá editar a célula que tenha o valor: "texto de exemplo".
     * Você gostaria que ao editar, vc edite o tsxto atual, no exemplo o
     * "texto de exemplo", ou a edição comece em branco ? Eu preferiri editar o
     * valor atual.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        /*
         * Caso use combobox, a linha abaixo não é utilizada, DEVENDO apagá-la.
         * Uma vez que ao clicar o combobox irá se abrir todo automaticamente
         * então não há necessidade de indicar o valor já presente,
         * pois ao mover o mouse, o atual já ficará demarcado.
         * Lembrando que ao apertar ESC, a edição é cancelada, e o valor original
         * mantido.
         */
    	if (value == null) {
    		tf.setText(null); //Caso use combobox, apagar esta linha
    	} else {
    		tf.setText(String.valueOf(value)); //Caso use combobox, apagar esta linha
    	}
        return p;
    }
 
    /*
     * Este método é a chave da Classe... mas como assim a chave ?
     * Ele indica se a célula irá abrir para edição (true) ou não (false). 
     * No meu exemplo, a célula se abrirá para edição, apenas quando houver
     * dois cliques sobre ela.
     */
    @Override
    public boolean isCellEditable(EventObject anEvent) {
        if(anEvent instanceof MouseEvent) {
            if (((MouseEvent) anEvent).getClickCount() == 1) {
                return true;
            }
        }
        return false;
    }
 
    //Como dito antes, este método verifica se o que foi digitado no
    //teclado, esta presente em CARACTERES, caso esteja, o evento é
    //consumado(continuado) e o valor atribuído, caso não, o evento se
    //cancela.
    //No exemplo então, é aceito apenas números.
    /*
    private void tNumeroKeyTyped(java.awt.event.KeyEvent evt) {                                 
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }
    */
}