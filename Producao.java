import java.util.ArrayList;
import java.util.Collection;


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
		//ArrayList<Character> firstsAux = this.getFirst1();
		//this.firsts = firstsAux;
		return firstsAux;
	}

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
	}
	
	public ArrayList<Character> getFirst1() {
		int index = 0;
		Character firstProducao = this.destino.charAt(index);
		while((int)firstProducao <= 90 && index < this.destino.length()) {
			firstProducao = this.destino.charAt(index);
			if((int) firstProducao != 38){
				ArrayList<Character> firstDoEstado = gramatica.getEstadoPorNome(firstProducao.toString()).getFirst();
				if(firstDoEstado.contains('&')){
					if(this.destino.length() > index){
						ArrayList<Character> aux = this.getFirst(this.destino.charAt(index));
						for (Character c : aux){
							if (!firsts.contains(c)){
								firsts.add(c);
							}
						}
						index++;
					}
				}
				for (Character c1 : firstDoEstado){
					if (!firsts.contains(c1)){
						firsts.add(c1);
					}
				}
				
			} else{
				index++;
			}
				//firsts.addAll(firstDoEstado);
				//System.out.println(gramatica.getEstadoPorNome(firstProducao.toString()).getFirst());
				//return firsts;
		}
		if(index < this.destino.length()){
			int valor = (int)firstProducao;
			if (valor > 90 || valor == 38){
				if (!firsts.contains(firstProducao)){
						firsts.add(firstProducao);
						System.out.println(firstProducao);
					}
				}
		}
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

	
	
	
}
