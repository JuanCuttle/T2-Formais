import java.util.ArrayList;


public class Principal {
	
	static ArrayList<GLC> gramaticasCriadas = new ArrayList<>();
	public static void main(String[] par){
		Estado S = new Estado("S");
		Estado A = new Estado("A");
		Estado B = new Estado("B");
		
		ArrayList<Character> terminais = new ArrayList<>();
		terminais.add('a');
		terminais.add('b');
		terminais.add('c');
		
		ArrayList<Estado> naoTerminais = new ArrayList<>();
		naoTerminais.add(S);
		naoTerminais.add(A);
		naoTerminais.add(B);
		
		ArrayList<Producao> producoes = new ArrayList<>();
		//producoes.add(new Producao(S, "ABcA"));
		producoes.add(new Producao(S, "AAcA"));
		producoes.add(new Producao(A, "a"));
		producoes.add(new Producao(A, "&"));
		producoes.add(new Producao(B, "b"));
		//producoes.add(new Producao(B, "&"));
		
		GLC g = new GLC(naoTerminais, terminais, producoes, S);
		//ArrayList<Character> first = new ArrayList<>();
		
		//System.out.println(S.getFirst());
		
		System.out.println(A.getFollow());
		
		//GLC g1 = Interface.criarGramatica();
		//Interface.editarGramatica(g);
		
		Interface.mostraGramatica(g);
		
/*		for (Character c : "abcABCXYZ".toCharArray()){
			System.out.println((int)c);
		}*/
	}
	
	public static ArrayList<Character> first(Estado estado){
		return estado.getFirst();
	}
	
	public static ArrayList<Character> follow(Estado estado){
		return estado.getFollow();
	}
	
	public static Estado getEstadoPorNome(String nomeE, ArrayList<Estado> estados){
		for (Estado e : estados){
			if(e.getNome().equalsIgnoreCase(nomeE)){
				return e;
			}
		}
		return null;
	}
	
	public static boolean letraPertenceAoAlfabeto(char c, ArrayList<Character> letras){
		for (char l : letras){
			if(c == l){
				return true;
			}
		}
		return false;
	}
	
	public static boolean possuiProducao(ArrayList<Producao> producoes, Estado ladoEsquerdo, Estado ladoDireito) {
		for (Producao t : producoes){
			if (t.getOrigem().getNome().equals(ladoEsquerdo.getNome()) && t.getDestino().equals(ladoDireito.getNome())){
				return true;
			}
		}
		return false;
	}
	
	public static Producao getProducao(ArrayList<Producao> producoes, Estado ladoEsquerdo, Estado ladoDireito) {
		for (Producao t : producoes){
			if (t.getOrigem().getNome().equals(ladoEsquerdo.getNome()) && t.getDestino().equals(ladoDireito.getNome())){
				return t;
			}
		}
		return null;
	}
}
