import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Interface {

	// Gera uma string para faciliar a visualizacao de uma gramatica
	public static String mostraGramatica(GLC g) {
		String gram = "Gramatica: \n";
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
		JOptionPane.showMessageDialog(null, gram);
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
			
			ArrayList<Character> aRemoverAlfabeto = new ArrayList<>();
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
					aRemoverAlfabeto.add(c);
				}
				menos = JOptionPane.showConfirmDialog(null, "Deseja remover mais um símbolo terminal?");
			}
			alfabeto.removeAll(aRemoverAlfabeto);
			
			//System.out.println(novoAlfabeto);
			//gramatica.setTerminais(novoAlfabeto);
			ArrayList<Producao> aRemoverAlf = new ArrayList<>();
			for (int index = 0; index < producoes.size();index++){
				Producao trans = producoes.get(index);
				boolean contem = false;
				for (Character caux : trans.getDestino().toCharArray()){
					if(aRemoverAlfabeto.contains(caux)){
						contem = true;
					}
				}
				if (contem){
					aRemoverAlf.add(trans);
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
				ArrayList<Estado> ntdestino = trans.getNTDestino();
				ArrayList<Character> tdestino = trans.getTDestino();
				if(!naoTerminais.contains(trans.getOrigem())
						|| !naoTerminais.containsAll(ntdestino) || !alfabeto.containsAll(tdestino)){
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
		
	public static GLC criaGramaticaParse(String entrada){
			ArrayList<Character> auxiliar = new ArrayList<>();
			for (Character c : entrada.toCharArray()){
				if((int)c != 32){
					auxiliar.add(c);
				}
			}
			
			ArrayList<Estado> naoTerminaisAux = new ArrayList<>();
			ArrayList<Character> terminaisAux = new ArrayList<>();
			ArrayList<Producao> producoesAux = new ArrayList<>();
			int i = 0;
			do{
				Character aux = auxiliar.get(i);
				if (i+1 < auxiliar.size() && auxiliar.get(i+1) == '-'){
					if (Principal.getEstadoPorNome(aux.toString(), naoTerminaisAux) == null){
						Estado e = new Estado(aux.toString());
						naoTerminaisAux.add(e);
					}
				} else if(aux == '>' || aux == '|'){
					int r = i;
					Character retorno = auxiliar.get(r);
					do{
						r--;
						retorno = auxiliar.get(r);
					}while(retorno != '-');
					retorno = auxiliar.get(r-1);
					//System.out.println(retorno);
					
					int j = 1;
					String destino = "";
					while(auxiliar.get(i+j) != '|' && auxiliar.get(i+j) != ',' && i+j < auxiliar.size()-1){
						Character aux2 = auxiliar.get(i+j);
						//System.out.println(aux2);
						destino += aux2.toString();
						if((int)aux2 <= 90 && (int) aux2 != 38){
							if (Principal.getEstadoPorNome(aux2.toString(), naoTerminaisAux) == null){
								naoTerminaisAux.add(new Estado(aux2.toString()));
							}
						} else{
							if (!terminaisAux.contains(aux2)){
								terminaisAux.add(aux2);
							}
						}
						j++;
					}
					
					if (i+j == auxiliar.size()-1){
						Character aux2 = auxiliar.get(i+j);
						//System.out.println(aux2);
						destino += aux2.toString();
						if((int)aux2 <= 90 && (int) aux2 != 38){
							if (Principal.getEstadoPorNome(aux2.toString(), naoTerminaisAux) == null){
								naoTerminaisAux.add(new Estado(aux2.toString()));
							}
						} else{
							if (!terminaisAux.contains(aux2)){
								terminaisAux.add(aux2);
							}
						}
					}
					
					Estado origem =  Principal.getEstadoPorNome(retorno.toString(), naoTerminaisAux);
					if (!Principal.possuiProducao1(producoesAux, origem, destino)){
						Producao p = new Producao(origem, destino);
						producoesAux.add(p);
					}
					//System.out.println(destino);
					
					//System.out.println(producoesAux.get(0).getOrigem().getNome()+" -> "+ producoesAux.get(0).getDestino());
					//break;
				}
				i++;
			}while(i < auxiliar.size());
			
			String inicialS = "";
			Estado inicial = null;
			do {
				inicialS = JOptionPane.showInputDialog("Digite o nome do estado inicial: ");
				if (inicialS != null && inicialS != ""){
					inicial = Principal.getEstadoPorNome(inicialS, naoTerminaisAux);
				}
				
			}while(inicial == null || inicialS == "");
			System.out.println(inicial.getNome());
			
			GLC gramatica = new GLC(naoTerminaisAux, terminaisAux, producoesAux, inicial);
			Interface.mostraGramatica(gramatica);
			return gramatica;
		}
}
