import java.util.ArrayList;



public class Estado {
	private GLC gramatica;
	
	private String nome;
	private ArrayList<Estado> estadosInternos = new ArrayList<>();
	
	public Estado (String nome){
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Estado> getEstadosInternos() {
		return estadosInternos;
	}

	public void setEstadosInternos(ArrayList<Estado> estadosInternos) {
		this.estadosInternos = estadosInternos;
	}

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
	
	public ArrayList<Character> getFollow(){
		ArrayList<Character> follow = new ArrayList<>();
		Character c = this.getNome().charAt(0);
		
		for(Producao p : this.getGramatica().getProducoes()){
			
			for(Character aux : p.getDestinoArray()){
				// Se o estado eh produzido nesta producao, verificar o que vem depois dele
				if(aux == c){
					boolean continua = false;
					int deslocamento = 0;
					do {
						continua = false;
						int posicaoAtual = p.getDestinoArray().indexOf(aux)+deslocamento;
						// Se o caracter posterior ao Vn na producao existe, eh Vt e nao foi adicionado ainda, adicionar
						if(posicaoAtual+1 < p.getDestino().length()) {
							Character proximo  = p.getDestinoArray().get(posicaoAtual+1);
							//System.out.println(proximo);
							if ((int) proximo > 90 && !follow.contains(proximo)){
								follow.add(proximo);
								//follow.remove(new Character('&'));
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
									break;
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
		}
		
		
		return follow;
	}

	public GLC getGramatica() {
		return gramatica;
	}

	public void setGramatica(GLC gramatica) {
		this.gramatica = gramatica;
	}
	
	
}
