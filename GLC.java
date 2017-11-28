import java.util.ArrayList;


public class GLC {

	private ArrayList<Estado> naoTerminais;
	private ArrayList<Character> terminais;
	private ArrayList<Producao> producoes;
	private Estado inicial;

	public GLC(ArrayList<Estado> estados, ArrayList<Character> alfabeto,
			ArrayList<Producao> producoes, Estado inicial) {
		this.naoTerminais = estados;
		this.terminais = alfabeto;
		this.producoes = producoes;
		this.inicial = inicial;
		for (Estado e : this.naoTerminais){
			e.setGramatica(this);
		}
		for (Producao p : this.producoes){
			p.setGramatica(this);
		}
	}

	public ArrayList<Estado> getNaoTerminais() {
		return naoTerminais;
	}

	public ArrayList<Character> getTerminais() {
		return terminais;
	}

	public ArrayList<Producao> getProducoes() {
		return producoes;
	}

	public Estado getInicial() {
		return inicial;
	}
	

	public void setInicial(Estado inicialNovo) {
		this.inicial = inicialNovo;
	}

	public void setTerminais(ArrayList<Character> novoAlfabeto) {
		this.terminais = novoAlfabeto;
	}
	
	public Estado getEstadoPorNome(String nomeE){
		for (Estado e : this.naoTerminais){
			if(e.getNome().equalsIgnoreCase(nomeE)){
				return e;
			}
		}
		return null;
	}

	public ArrayList<Producao> getProducoesDoEstado(String nomeE) {
		ArrayList<Producao> prod = new ArrayList<>();
		for (Producao p : this.producoes){
			if (p.getOrigem().getNome().equals(nomeE)){
				prod.add(p);
			}
		}
		return prod;
	}
	
	public void fatorar(){
		for (Producao p : this.getProducoes()){
			for (Estado e : this.getNaoTerminais()){
				if (p.getOrigem() == e){
					ArrayList<Character> first = p.getLeitura();
					for (Producao p2 : this.getProducoes()){
						if (p2.getOrigem() == e){
							ArrayList<Character> first2 = p2.getLeitura();
							for (Character c : first){
								if (first2.contains(c)){
									boolean naoFatorada = true;
								}
							}
						}
					}
				}
			}
		}
	}


}
