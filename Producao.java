
public class Producao {
	private GLC gramatica;
	
	private Estado origem;
	private String destino;
	public Producao(Estado origem, String destino) {
		super();
		this.origem = origem;
		this.destino = destino;
	}
	public Estado getOrigem() {
		return origem;
	}
	public void setOrigem(Estado origem) {
		this.origem = origem;
	}
	public Character getLeitura() {
		return this.getFirst(this.destino.charAt(0));
	}

	public Character getFirst(Character firstProducao) {
		int valor = (int) firstProducao;
		System.out.println(valor);
		if (valor > 90 || valor == 38){
			return firstProducao;
		} else {
			return gramatica.getEstadoPorNome(firstProducao.toString()).getFirst();
		}
	}
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
