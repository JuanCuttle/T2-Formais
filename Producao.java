import java.util.ArrayList;


public class Producao {
	private GLC gramatica;
	
	private Estado origem;
	private String destino;
	
	ArrayList<Character> firsts;
	
	public Producao(Estado origem, String destino) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.firsts = new ArrayList<>();
	}
	public Estado getOrigem() {
		return origem;
	}
	public void setOrigem(Estado origem) {
		this.origem = origem;
	}
	public ArrayList<Character> getLeitura() {
		ArrayList<Character> firstsAux = this.getFirst(this.destino.charAt(0));
		//this.firsts = firstsAux;
		return firstsAux;
	}

	public ArrayList<Character> getFirst(Character firstProducao) {
		ArrayList<Character> firsts1 = this.firsts;
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
				//firsts.addAll(firstDoEstado);
				//System.out.println(gramatica.getEstadoPorNome(firstProducao.toString()).getFirst());
				//return firsts;
			}
		}while(!firsts.containsAll(firsts1));
		return firsts;
	}
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
	
	
	
}
