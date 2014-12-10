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
        //Primeiro criamos um Painel, assim comparamos que quando a c�lula �
        //editada, � como se fosse um novo elemento, retornando seu valor.
        this.p = new JPanel(new BorderLayout());
 
        //Aqui adicionamos um campo de texto, ou seja, quando editar a c�lula,
        //na verdade estar� digitando num TextField dentro dela.
        (this.tf = new JTextField()).setBorder(null);
        tf.setDocument(new FormataCampoApenasNumeros(3));
        p.add(tf);
 
        //EXTRA 1: Caso queira validar seu TextField, fique a vontade, abaixo est� um
        //exemplo, onde adicionei um KeyListener, chamando um m�todo,
        //validando para aceitar apenas n�meros.
        /*
        tf.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNumeroKeyTyped(evt);
            }
        });
        * */
 
        //EXTRA 2: Caso queira usar um ComboBox, ao inv�s de um TextField:
        /*
         * this.cf = new JComboBox();
         * cf.addItem("AC");
         * cf.addItem("AL");
         */
    }
 
    /*
     * Este m�todo � respons�vel por retornar ao TableModel da JTable, o
     * valor presente atual na c�lula.
     */
    @Override
    public Object getCellEditorValue() {
        return tf.getText(); // Usado com TextField
        // return cf.getSelectedItem().toString() //Usado com ComboBox
    }
 
    /*
     * Este m�todo fica respons�vel � atribuir o valor j� presente, ao textfield
     * na hora da edi��o.
     * Vamos supor que v� editar a c�lula que tenha o valor: "texto de exemplo".
     * Voc� gostaria que ao editar, vc edite o tsxto atual, no exemplo o
     * "texto de exemplo", ou a edi��o comece em branco ? Eu preferiri editar o
     * valor atual.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        /*
         * Caso use combobox, a linha abaixo n�o � utilizada, DEVENDO apag�-la.
         * Uma vez que ao clicar o combobox ir� se abrir todo automaticamente
         * ent�o n�o h� necessidade de indicar o valor j� presente,
         * pois ao mover o mouse, o atual j� ficar� demarcado.
         * Lembrando que ao apertar ESC, a edi��o � cancelada, e o valor original
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
     * Este m�todo � a chave da Classe... mas como assim a chave ?
     * Ele indica se a c�lula ir� abrir para edi��o (true) ou n�o (false). 
     * No meu exemplo, a c�lula se abrir� para edi��o, apenas quando houver
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
 
    //Como dito antes, este m�todo verifica se o que foi digitado no
    //teclado, esta presente em CARACTERES, caso esteja, o evento �
    //consumado(continuado) e o valor atribu�do, caso n�o, o evento se
    //cancela.
    //No exemplo ent�o, � aceito apenas n�meros.
    /*
    private void tNumeroKeyTyped(java.awt.event.KeyEvent evt) {                                 
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }
    */
}