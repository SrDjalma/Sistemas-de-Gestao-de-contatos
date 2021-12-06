/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import model.Contato;
import model.banco;
import view.PrincipalView;

/**
 *
 * @author Natan de Paula
 * @author Eriani Moreira
 */
public class Principal {
    public int chave;
    public static void main(String[] args) {
      banco auxBanco =new banco();
      auxBanco.conectaBanco();
    
       //auxBanco.criarTabela();
       
      //auxBanco.inserebanco(contato);
      //auxBanco.selectBanco();
      PrincipalView tela = new PrincipalView();
      tela.setVisible(true);
      
    }
}
