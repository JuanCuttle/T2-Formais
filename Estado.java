import java.util.ArrayList;



public class Estado {
	private GLC gramatica;
	
	private String nome;
	//private ArrayList<Estado> estadosInternos = new ArrayList<>();
	
	public Estado (String nome){
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

/*	public ArrayList<Estado> getEstadosInternos() {
		return estadosInternos;
	}

	public void setEstadosInternos(ArrayList<Estado> estadosInternos) {
		this.estadosInternos = estadosInternos;
	}*/

	public ArrayList<Character> getFirst() {
		ArrayList<Character> firsts = new ArrayList<>();
		for (Producao p : this.gramatica.getProducoesDoEstado(this.getNome())){
			//System.out.println(p.getDestino());
			//for (Character c : p.getLeitura()){
			for (int i = 0; i < p.getLeitura().size(); i++){
				Character c = p.getLeitura().get(i);
				//System.out.println(c);
				if (!firsts.contains(c)){
					firsts.addAll(p.getLeitura());
				}
			}
		}
		return firsts;
	}
	
	public ArrayList<Character> getFirstNT(){
		ArrayList<Character> firsts = new ArrayList<>();
		for (Producao p : this.gramatica.getProducoesDoEstado(this.getNome())){
			//System.out.println(p.getDestino());
			//for (Character c : p.getLeitura()){
			ArrayList<Character> firstNT = p.getFirstNT(0);
			for (int i = 0; i < firstNT.size(); i++){
				Character c = firstNT.get(i);
				//System.out.println(c);
				if (!firsts.contains(c)){
					firsts.addAll(firstNT);
				}
			}
		}
		return firsts;
	}
	
	public ArrayList<Character> getFollow(){
		ArrayList<Character> follow = new ArrayList<>();
		Character c = this.getNome().charAt(0);
		
		if (this.getGramatica().getInicial() == this){
			follow.add(new Character('$'));
		}
		/*for (int i = 0; i < this.getGramatica().getProducoes().size(); i++){
			Producao p = this.getGramatica().getProducoes().get(i);*/
		for(Producao p : this.getGramatica().getProducoes()){
			ArrayList<Character> destinoArr = p.getDestinoArray();
			
			for (int i = 0; i  < destinoArr.size(); i++){
				Character aux = destinoArr.get(i);
			//for(Character aux : p.getDestinoArray()){
				// Se o estado eh produzido nesta producao, verificar o que vem depois dele
				if(aux == c){
					boolean continua = false;
					int deslocamento = 0;
					do {
						continua = false;
						//int posicaoAtual = p.getDestinoArray().indexOf(aux)+deslocamento;
						int posicaoAtual = i+deslocamento;
						
						// Se o caracter posterior ao Vn na producao existe, eh Vt e nao foi adicionado ainda, adicionar
						if(posicaoAtual+1 < p.getDestino().length()) {
							Character proximo  = p.getDestinoArray().get(posicaoAtual+1);
							//System.out.println(proximo);
							if ((int) proximo > 90 && !follow.contains(proximo)){
								follow.add(proximo);
								follow.remove(new Character('&'));
								//break;
								continua = true;
								deslocamento++;
								
								break;
								
							} else if((int) proximo <= 90){
								// Se o caracter posterior ao Vn na producao existe, e eh Vn, adicionar seus first ao follow
								ArrayList<Character> firstsProximo = this.getGramatica().getEstadoPorNome(proximo.toString()).getFirst();
								for (Character fp : firstsProximo){
									if(!follow.contains(fp)){
										follow.add(fp);
									}
								}
								// Se o caracter posterior ao Vn na producao pode ser '&', pegar o proximo ainda
								if (follow.contains('&')){
									continua = true;
									deslocamento++;
									
									
									//follow.remove(new Character('&'));
								} else{
									//follow.remove(new Character('&'));
									//break;
								}
							}
						} else if (posicaoAtual+1 == p.getDestino().length()){
							ArrayList<Character> followProximo = this.getGramatica().getEstadoPorNome(p.getOrigem().getNome()).getFollow();
							for (Character fp2 : followProximo){
								if(!follow.contains(fp2)){
									follow.add(fp2);
								}
							}
						}
					} while(continua == true);
				}
			}
			// Faz sentido ter epsilon no follow?
			follow.remove(new Character('&'));
		}
		
		
		return follow;
	}
	
	public ArrayList<Character> getFollowNT(){
		
		
		ArrayList<Character> followNT = new ArrayList<>();
		Character c = this.getNome().charAt(0);
		
/*		if (this.getGramatica().getInicial() == this){
			followNT.add(new Character('$'));
		}*/
		for(Producao p : this.getGramatica().getProducoes()){
			ArrayList<Character> destinoArr = p.getDestinoArray();
			
			boolean continua = false;
			for (int i = 0; i  < destinoArr.size(); i++){
				Character aux = destinoArr.get(i);
			//for(Character aux : p.getDestinoArray()){
				//ArrayList<Character> auxiliar = (ArrayList<Character>) p.getDestinoArray().clone();
				// Se o estado eh produzido nesta producao, verificar o que vem depois dele
				if(aux == c){
					continua = false;
					int deslocamento = 0;
					do {
						continua = false;
						//int posicaoAtual = destinoArr.indexOf(aux)+deslocamento;
						int posicaoAtual = i+deslocamento;
						System.out.println(posicaoAtual);
						
						// Se o caracter posterior ao Vn na producao existe, eh Vt e nao foi adicionado ainda, adicionar
						if(posicaoAtual+1 < p.getDestino().length()) {
							Character proximo  = p.getDestinoArray().get(posicaoAtual+1);
							//System.out.println(proximo);
							if ((int) proximo > 90){
/*							//if ((int) proximo > 90 && !followNT.contains(proximo)){
								//follow.add(proximo);
								//follow.remove(new Character('&'));
								auxiliar.remove(0);
								//System.out.println(auxiliar);
								
								if (auxiliar.contains(this.getNome().toCharArray())) {
									continua = true;
									deslocamento++;
								} else {
									break;
								}*/
								//break;
								continua = true;
								deslocamento++;
								break;
							} else if((int) proximo <= 90 && (int) proximo != 38){
								if (!followNT.contains(proximo)){
									followNT.add(proximo);
									//System.out.println(firstProducao);
								}
								// Se o caracter posterior ao Vn na producao existe, e eh Vn, adicionar seus first ao follow
								ArrayList<Character> firstsProximo = this.getGramatica().getEstadoPorNome(proximo.toString()).getFirstNT();
								for (Character fp : firstsProximo){
									if(!followNT.contains(fp)){
										followNT.add(fp);
									}
								}
								
								//System.out.println(proximo);
								ArrayList<Character> firstsTProximo = this.getGramatica().getEstadoPorNome(proximo.toString()).getFirst();
								
								
								//auxiliar.remove(0);
								//System.out.println(auxiliar);
								// Se o caracter posterior ao Vn na producao pode ser '&', pegar o proximo ainda
								//if (firstsTProximo.contains('&') || auxiliar.contains(this.getNome().toCharArray())){
								if (firstsTProximo.contains('&')){
									continua = true;
									deslocamento++;
									
									
									//follow.remove(new Character('&'));
								} else{
									//break;
								}
							}
						} else if (posicaoAtual+1 == p.getDestino().length()){
							ArrayList<Character> followProximo = this.getGramatica().getEstadoPorNome(p.getOrigem().getNome()).getFollowNT();
							for (Character fp2 : followProximo){
								if(!followNT.contains(fp2)){
									followNT.add(fp2);
								}
							}
						}
					} while(continua == true);
				}
			}
			// Faz sentido ter epsilon no follow?
			followNT.remove(new Character('&'));
		}
		
		
		return followNT;
	}

	public GLC getGramatica() {
		return gramatica;
	}

	public void setGramatica(GLC gramatica) {
		this.gramatica = gramatica;
	}
	
	
}
