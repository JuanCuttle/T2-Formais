import java.util.ArrayList;


public class Principal {
	public static void main(String[] par){
		Estado S = new Estado("S");
		Estado A = new Estado("A");
		
		ArrayList<Character> terminais = new ArrayList<>();
		terminais.add('a');
		terminais.add('b');
		
		ArrayList<Estado> naoTerminais = new ArrayList<>();
		naoTerminais.add(S);
		naoTerminais.add(A);
		
		ArrayList<Producao> producoes = new ArrayList<>();
		producoes.add(new Producao(S, "AbcA"));
		producoes.add(new Producao(A, "a"));
		producoes.add(new Producao(A, "&"));
		
		GLC g = new GLC(naoTerminais, terminais, producoes, S);
		
		System.out.println(producoes.get(0).getLeitura());
		
		Interface.mostraGramatica(g);
		
/*		for (Character c : "abcABCXYZ".toCharArray()){
			System.out.println((int)c);
		}*/
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
}