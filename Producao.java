import java.util.ArrayList;


public class Producao {
	private GLC gramatica;
	
	private Estado origem;
	private String destino;
	
	ArrayList<Character> firsts;
	boolean pegouFirst = false;

	private ArrayList<Character> firstsNT;
	
	public Producao(Estado origem, String destino) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.firsts = new ArrayList<>();
		this.firstsNT = new ArrayList<>();
	}
	public Estado getOrigem() {
		return origem;
	}
	public void setOrigem(Estado origem) {
		this.origem = origem;
	}
	public ArrayList<Character> getLeitura() {
		//ArrayList<Character> firstsAux = this.getFirst(this.destino.charAt(0));
		if (!pegouFirst){
			this.firsts = this.getFirst(0);
			pegouFirst = true;
			return this.firsts;
		} else {
			return this.firsts;
		}
		//this.firsts = firstsAux;
	}
/*
	public ArrayList<Character> getFirst(Character firstProducao) {
		ArrayList<Character> firsts1 = this.firsts;
		//int index = 1;
		do {
			int valor = (int) firstProducao;
			//System.out.println(valor);
			if (valor > 90 || valor == 38){
				if (!firsts.contains(firstProducao)){
					firsts.add(firstProducao);
					System.out.println(firstProducao);
				}
				//return firsts;
			} else {
				ArrayList<Character> firstDoEstado = gramatica.getEstadoPorNome(firstProducao.toString()).getFirst();
				if(firstDoEstado.contains('&')){
					if(this.destino.length() > 1){
						ArrayList<Character> aux = this.getFirst(this.destino.charAt(1));
						for (Character c : aux){
							if (!firsts.contains(c)){
								firsts.add(c);
							}
						}
					}
				}
				for (Character c1 : firstDoEstado){
					if (!firsts.contains(c1)){
						firsts.add(c1);
					}
				}
				//index++;
				//firsts.addAll(firstDoEstado);
				//System.out.println(gramatica.getEstadoPorNome(firstProducao.toString()).getFirst());
				//return firsts;
			}
		}while(!firsts.containsAll(firsts1));
		return firsts;
	}*/
	
	public ArrayList<Character> getFirst(int index) {
		ArrayList<Character> firsts1 = this.firsts;
		//int index = 1;
		if(this.destino.length() > index){
			do {
				Character firstProducao = this.destino.charAt(index);
				int valor = (int) firstProducao;
				//System.out.println(valor);
				if (valor > 90 || valor == 38){
					if (!firsts.contains(firstProducao)){
						firsts.add(firstProducao);
						System.out.println(firstProducao);
					}
					//return firsts;
				} else if (firstProducao != this.origem.getNome().charAt(0)){
					ArrayList<Character> firstDoEstado = gramatica.getEstadoPorNome(firstProducao.toString()).getFirst();
					if(firstDoEstado.contains('&')){
						//System.out.println(index);
						if(this.destino.length() > index){
							ArrayList<Character> aux = this.getFirst(++index);
							for (Character c : aux){
								if (!firsts.contains(c)){
									firsts.add(c);
								}
							}
						}
					}
					
					for (Character c1 : firstDoEstado){
						if (!firsts.contains(c1)){
							firsts.add(c1);
						}
					}
					
					// Se o ultimo for Vn, e possuir epsilon-transicao, adicionar epsilon. Senao, remover
					if (index != this.getDestino().length()-1){
						firsts.remove(new Character('&'));
					}
					
					
					//index++;
					//firsts.addAll(firstDoEstado);
					//System.out.println(gramatica.getEstadoPorNome(firstProducao.toString()).getFirst());
					//return firsts;
				}
			}while(!firsts.containsAll(firsts1));
		}
		return firsts;
	}
/*	
	public ArrayList<Character> getFirst2(int index) {
		ArrayList<Character> firsts1 = this.firsts;
		//int index = 1;
		Character firstProducao = this.destino.charAt(index);
		do {
			int valor = (int) firstProducao;
			//System.out.println(valor);
			if (valor > 90 || valor == 38){
				if (!firsts.contains(firstProducao)){
					firsts.add(firstProducao);
					System.out.println(firstProducao);
				}
				//return firsts;
			} else {
				ArrayList<Character> firstDoEstado = gramatica.getEstadoPorNome(firstProducao.toString()).getFirst();
				if(firstDoEstado.contains('&')){
					System.out.println(index);
					if(this.destino.length() > index){
						ArrayList<Character> aux = this.getFirst2(this.destino.charAt(++index));
						for (Character c : aux){
							if (!firsts.contains(c)){
								firsts.add(c);
							}
						}
					} else {
						break;
					}
				}
				for (Character c1 : firstDoEstado){
					if (!firsts.contains(c1)){
						firsts.add(c1);
					}
				}
				//index++;
				//firsts.addAll(firstDoEstado);
				//System.out.println(gramatica.getEstadoPorNome(firstProducao.toString()).getFirst());
				//return firsts;
			}
		}while(!firsts.containsAll(firsts1));
		return firsts;
	}*/
/*	private void getFirstAux(ArrayList<Character> firsts, Character firstProducao) {
			int valor = (int) firstProducao;
			//System.out.println(valor);
			if (valor > 90 || valor == 38){
				if (!firsts1.contains(firstProducao)){
					firsts1.add(firstProducao);
					System.out.println(firstProducao);
				}
				return firsts1;
			} else {
				ArrayList<Character> firstDoEstado = gramatica.getEstadoPorNome(firstProducao.toString()).getFirst();
				if(firstDoEstado.contains('&')){
					if(this.destino.length() > 1){
						ArrayList<Character> aux = this.getFirst(this.destino.charAt(1));
						for (Character c : aux){
							if (!firsts1.contains(c)){
								firsts1.add(c);
							}
						}
					}
				}
				//this.firsts.addAll(firsts1);
				//System.out.println(gramatica.getEstadoPorNome(firstProducao.toString()).getFirst());
				return firsts1;
			}
	}*/
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public GLC getGramatica() {
		return gramatica;
	}
	public void setGramatica(GLC gramatica) {
		this.gramatica = gramatica;
	}
	public ArrayList<Estado> getNTDestino() {
		ArrayList<Estado> ntDestino = new ArrayList<>();
		for(Character c : this.destino.toCharArray()){
			if((int)c <= 90 && (int) c != 38){
				//System.out.println(c);
				if (!ntDestino.contains(c)){
					ntDestino.add(Principal.getEstadoPorNome(c.toString(), this.gramatica.getNaoTerminais()));
				}
			}
		}
		//System.out.println(ntDestino);
		return ntDestino;
	}
	
	public ArrayList<Character> getTDestino() {
		ArrayList<Character> tDestino = new ArrayList<>();
		for(Character c : this.destino.toCharArray()){
			if((int)c > 90){
				//System.out.println(c);
				if (!tDestino.contains(c)){
					tDestino.add(c);
				}
			}
		}
		return tDestino;
	}
	
	public ArrayList<Character> getDestinoArray() {
		ArrayList<Character> array = new ArrayList<>();
		for (Character c : this.destino.toCharArray()){
			array.add(c);
		}
		return array;
	}
	public ArrayList<Character> getFirstNT(int index) {
		ArrayList<Character> firsts1 = this.firstsNT;
		//int index = 1;
		if(this.destino.length() > index){
			do {
				Character firstProducao = this.destino.charAt(index);
				//System.out.println(firstProducao);
				int valor = (int) firstProducao;
				//System.out.println(valor);
				if (valor > 90 || valor == 38){
					return firstsNT;
				} else if (firstProducao != this.origem.getNome().charAt(0)){
					if (!firstsNT.contains(firstProducao)){
						firstsNT.add(firstProducao);
						//System.out.println(firstProducao);
					}
					ArrayList<Character> firstDoEstado = gramatica.getEstadoPorNome(firstProducao.toString()).getFirst();
					if(firstDoEstado.contains('&')){
						//System.out.println(index);
						if(this.destino.length() > index){
							ArrayList<Character> aux = this.getFirstNT(++index);
							for (Character c : aux){
								if (!firstsNT.contains(c)){
									firstsNT.add(c);
								}
							}
						}
					}
/*					
					for (Character c1 : firstDoEstado){
						if (!firstsNT.contains(c1)){
							firstsNT.add(c1);
						}
					}*/
					
/*					// Se o ultimo for Vn, e possuir epsilon-transicao, adicionar epsilon. Senao, remover
					if (index != this.getDestino().length()-1){
						firstsNT.remove(new Character('&'));
					}*/
					
					
					//index++;
					//firsts.addAll(firstDoEstado);
					//System.out.println(gramatica.getEstadoPorNome(firstProducao.toString()).getFirst());
					//return firsts;
				}
			}while(!firstsNT.containsAll(firsts1));
		}
		return firstsNT;
	}

	
	
	
}
