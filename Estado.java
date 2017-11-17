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

	public Character getFirst() {
		ArrayList<Character> firsts = new ArrayList<>();
		for (Producao p : this.gramatica.getProducoesDoEstado(this.getNome())){
			firsts.add(p.getLeitura());
		}
		for (Character c : firsts){
			if (c == '&'){
				return c;
			}
		}
		return firsts.get(0);
	}

	public GLC getGramatica() {
		return gramatica;
	}

	public void setGramatica(GLC gramatica) {
		this.gramatica = gramatica;
	}
	
	
}
