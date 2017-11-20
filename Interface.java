import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Interface {

	// Gera uma string para faciliar a visualizacao de uma gramatica
	public static String mostraGramatica(GLC g) {
		String gram = "";
		for (Estado e : g.getNaoTerminais()) {
			if (g.getInicial() == e){
				gram = gram + "*";
				System.out.print("*");
			}
			gram = gram + e.getNome() + " -> ";
			System.out.print(e.getNome() + " -> ");
			for (Producao p : g.getProducoes()) {
				if (p.getOrigem() == e) {
					// Para cada transicao do estado, imprimir ela e " | "
					gram = gram + p.getDestino() + " | ";
					System.out.print(p.getDestino() + " | ");
				}
			}
			// Terminaram as transicoes, imprime nova linha e reinicia o
			// processo no
			// proximo estado
			gram = gram + "\n";
			System.out.println("");
		}
		// JOptionPane.showMessageDialog(null, gram);
		gram = gram + "\n";
		System.out.println("");
		return gram;
	}
	
	// Permite que o usuario interaja com o modelo, para construir uma gramatica nova
		public static GLC criarGramatica(){
			GLC g = null;
			ArrayList<Estado> naoTerminais = new ArrayList<>();
			ArrayList<Producao> producoes = new ArrayList<>();
			
			ArrayList<Character> alfabeto = new ArrayList<>();
			alfabeto.add('&');
			//int i = -1;
			int mais = JOptionPane.showConfirmDialog(null, "Deseja adicionar um símbolo (terminal) ao alfabeto?");
			while(mais == 0) {
				//i++;
				String caracter = JOptionPane.showInputDialog("Digite o caracter (simbolo único, minusculo ou digito):");
				char c = caracter.charAt(0);
				if (!alfabeto.contains(c)){
					alfabeto.add(c);
				}
				mais = JOptionPane.showConfirmDialog(null, "Deseja adicionar mais um símbolo terminal?");
			}
			
			String nomeEstado = JOptionPane.showInputDialog("Digite o nome do simbolo inicial:");
			Estado inicial = new Estado(nomeEstado);
			naoTerminais.add(inicial);
	/*		int inicialFinal = JOptionPane.showConfirmDialog(null, "O simbolo inicial é final?");
			if (inicialFinal == 0){
				finais.add(inicial);
			}*/
			int confirmE = JOptionPane.showConfirmDialog(null, "Deseja criar um simbolo não-terminal?");
			while (confirmE == 0){
				String nome = JOptionPane.showInputDialog("Digite o nome do simbolo não-terminal novo:");
				Estado novo = new Estado(nome);
				naoTerminais.add(novo);
				
				confirmE = JOptionPane.showConfirmDialog(null, "Deseja criar mais um simbolo nao-terminal?");
			}
			
			int confirmP = JOptionPane.showConfirmDialog(null, "Deseja criar uma produção?");
			while (confirmP == 0){
				String nomeI = JOptionPane.showInputDialog("Digite o nome do estado no lado esquerdo da produção nova:");
				
				Estado ladoEsquerdo = Principal.getEstadoPorNome(nomeI, naoTerminais);
				if (ladoEsquerdo != null){
					String ladoDireito = JOptionPane.showInputDialog("Digite o destino da produção (conjunto de simbolos terminais e nao-terminais):");
					boolean prodValida = false;
					for (Character caracter : ladoDireito.toCharArray()){
						if(naoTerminais.contains(caracter) || alfabeto.contains(caracter)){
							prodValida = true;
						}
					}
					if(prodValida){
						Producao novaP = new Producao(ladoEsquerdo, ladoDireito);
						producoes.add(novaP);
					}
					
				}
				String gram = Interface.mostraGramatica(new GLC(naoTerminais, alfabeto, producoes, inicial));
				confirmP = JOptionPane.showConfirmDialog(null, "Deseja criar mais uma produção?\n"+"Gramatica atual:\n"+gram);
			}
/*			// Adicionar os proprios estados na composicao de cada estado
			for (Estado nt : naoTerminais){
				nt.getEstadosInternos().add(nt);
			}*/
			
			g = new GLC(naoTerminais, alfabeto, producoes, inicial);
			//g.setPosicaoTerminais(i);
			System.out.println("Gramatica gerada: ");
			Interface.mostraGramatica(g);
			return g;
		}
		
		// Permite ao usuario interagir com o modelo, de forma a editar uma gramatica previamente criada
		public static void editarGramatica(GLC gramatica) {
			ArrayList<Estado> naoTerminais = gramatica.getNaoTerminais();
			ArrayList<Producao> producoes = gramatica.getProducoes();
			
			ArrayList<Character> alfabeto = gramatica.getTerminais();
			alfabeto.add('&');
			//int i = gramatica.getPosicaoTerminais();
	/*		ArrayList<Character> novoAlfabeto = new ArrayList<>();
			for (int a = 0; a < alfabeto.size(); a++){
					novoAlfabeto.add(alfabeto[a]);
			}*/
			int mais = JOptionPane.showConfirmDialog(null, "Deseja adicionar um símbolo (terminal) ao alfabeto?");
			while(mais == 0) {
				String caracter = JOptionPane.showInputDialog("Digite o caracter (simbolo único, minusculo ou digito):");
				char c = caracter.charAt(0);
				boolean jahExiste = false;
				for (char letras : alfabeto){
					if (letras == c){
						jahExiste = true;
					}
				}
				if (!jahExiste){
					//i++;
					alfabeto.add(c);
					//novoAlfabeto.add(c);
				}
				mais = JOptionPane.showConfirmDialog(null, "Deseja adicionar mais um símbolo terminal?");
			}
			
			int menos = JOptionPane.showConfirmDialog(null, "Deseja remover um símbolo (terminal) do alfabeto?");
			while(menos == 0) {
				String caracter = JOptionPane.showInputDialog("Digite o caracter (simbolo único, minusculo ou digito):");
				char c = caracter.charAt(0);
				boolean jahExiste = false;
				for (char letras : alfabeto){
					if (letras == c){
						jahExiste = true;
					}
				}
				if (jahExiste){
					alfabeto.remove(c);
				}
				menos = JOptionPane.showConfirmDialog(null, "Deseja remover mais um símbolo terminal?");
			}
			//System.out.println(novoAlfabeto);
			//gramatica.setTerminais(novoAlfabeto);
			ArrayList<Producao> aRemoverAlfabeto = new ArrayList<>();
			for (int index = 0; index < producoes.size();index++){
				Producao trans = producoes.get(index);
				boolean contem = false;
				for (Character caux : trans.getDestino().toCharArray()){
					if(alfabeto.contains(caux)){
						contem = true;
					}
				}
				if (contem){
					aRemoverAlfabeto.add(trans);
				}
			}
			producoes.removeAll(aRemoverAlfabeto);
			//gramatica.setPosicaoTerminais(i);
			
			
			String nomeEstado = JOptionPane.showInputDialog("Digite o nome do simbolo inicial:");
			Estado inicialNovo = Principal.getEstadoPorNome(nomeEstado, naoTerminais); 
			if (inicialNovo != null){
				gramatica.setInicial(inicialNovo);
			} else {
				Estado inicial = new Estado(nomeEstado);
				naoTerminais.add(inicial);
				gramatica.setInicial(inicialNovo);
			}
	/*		int inicialFinal = JOptionPane.showConfirmDialog(null, "O simbolo inicial é final?");
			if (inicialFinal == 0){
				finais.add(inicial);
			}*/
			int confirmE = JOptionPane.showConfirmDialog(null, "Deseja criar um simbolo não-terminal?");
			while (confirmE == 0){
				String nome = JOptionPane.showInputDialog("Digite o nome do simbolo não-terminal novo:");
				Estado estadoNovo = Principal.getEstadoPorNome(nome, naoTerminais);
				if (estadoNovo == null){
					Estado novo = new Estado(nome);
					naoTerminais.add(novo);
				} else {
					JOptionPane.showMessageDialog(null, "Este estado já existe!");
				}
				
				confirmE = JOptionPane.showConfirmDialog(null, "Deseja criar mais um simbolo não-terminal?");
			}
			
			int confirmE2 = JOptionPane.showConfirmDialog(null, "Deseja remover um simbolo não-terminal?");
			while (confirmE2 == 0){
				String nome = JOptionPane.showInputDialog("Digite o nome do simbolo não-terminal a remover:");
				Estado estadoNovo = Principal.getEstadoPorNome(nome, naoTerminais);
				if (estadoNovo != null){
					naoTerminais.remove(estadoNovo);
				} else {
					JOptionPane.showMessageDialog(null, "Este simbolo não existe!");
				}
				
				confirmE2 = JOptionPane.showConfirmDialog(null, "Deseja remover mais um simbolo não-terminal?");
			}
			
			ArrayList<Producao> aRemoverEstados = new ArrayList<>();
			for (int index = 0; index < producoes.size();index++){
				Producao trans = producoes.get(index);
				if(!naoTerminais.contains(trans.getOrigem())
						|| naoTerminais.containsAll(trans.getNTDestino())){
					aRemoverEstados.add(trans);
				}
			}
			producoes.removeAll(aRemoverEstados);
			
			
			String gram = Interface.mostraGramatica(new GLC(naoTerminais, alfabeto, producoes, inicialNovo));
			int confirmP = JOptionPane.showConfirmDialog(null, "Deseja criar uma produção?\n"+"Gramatica atual:\n"+gram);
			while (confirmP == 0){
				String nomeI = JOptionPane.showInputDialog("Digite o nome do estado no lado esquerdo da produção nova:");
				
				Estado ladoEsquerdo = Principal.getEstadoPorNome(nomeI, naoTerminais);
				if (ladoEsquerdo != null){
						String ladoDireito = JOptionPane.showInputDialog("Digite o nome do estado no lado direito da produção nova (se não houver, deixe em branco):");
						if (!ladoDireito.equalsIgnoreCase("")){
							boolean prodValida = false;
							for (Character caracter : ladoDireito.toCharArray()){
								if(naoTerminais.contains(caracter) || alfabeto.contains(caracter)){
									prodValida = true;
								}
							}
							if(prodValida){
								Producao novaP = new Producao(ladoEsquerdo, ladoDireito);
								producoes.add(novaP);
							}
						
					}
				}
				gram = Interface.mostraGramatica(new GLC(naoTerminais, alfabeto, producoes, inicialNovo));
				confirmP = JOptionPane.showConfirmDialog(null, "Deseja criar mais uma produção?\n"+"Gramatica atual:\n"+gram);
			}
			
			int confirmP2 = JOptionPane.showConfirmDialog(null, "Deseja remover uma produção?\n"+"Gramatica atual:\n"+gram);
			while (confirmP2 == 0){
				String nomeI = JOptionPane.showInputDialog("Digite o nome do estado no lado esquerdo da produção a remover:");
				
				Estado ladoEsquerdo = Principal.getEstadoPorNome(nomeI, naoTerminais);
				if (ladoEsquerdo != null){
					String ladoDireito = JOptionPane.showInputDialog("Digite o nome do estado no lado direito da produção nova (se não houver, deixe em branco):");
					if (!ladoDireito.equalsIgnoreCase("")){
						boolean prodValida = false;
						for (Character caracter : ladoDireito.toCharArray()){
							if(naoTerminais.contains(caracter) || alfabeto.contains(caracter)){
								prodValida = true;
							}
						}
						if(prodValida){
							Estado ld = Principal.getEstadoPorNome(ladoDireito, naoTerminais);
							if(Principal.possuiProducao(producoes, ladoEsquerdo, ld)){
								producoes.remove(Principal.getProducao(producoes, ladoEsquerdo, ld));
							}
						}
					
					}
				}
				gram = Interface.mostraGramatica(new GLC(naoTerminais, alfabeto, producoes, inicialNovo));
				confirmP2 = JOptionPane.showConfirmDialog(null, "Deseja remover mais uma produção?\n"+"Gramatica atual:\n"+gram);
			}
/*			// Adicionar os proprios estados na composicao de cada estado
			for (Estado nt : naoTerminais){
				nt.getEstadosInternos().add(nt);
			}	*/	
		}
}
