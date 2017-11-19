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

	public GLC getGramatica() {
		return gramatica;
	}

	public void setGramatica(GLC gramatica) {
		this.gramatica = gramatica;
	}
	
	
}
