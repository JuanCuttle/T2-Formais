import java.util.ArrayList;


public class Principal {
	
	static ArrayList<GLC> gramaticasCriadas = new ArrayList<>();
	public static void main(String[] par){
		Estado S = new Estado("S");
		Estado A = new Estado("A");
		Estado B = new Estado("B");
		//Estado C = new Estado("C");
		
		ArrayList<Character> terminais = new ArrayList<>();
		terminais.add('a');
		terminais.add('b');
		terminais.add('c');
		
		ArrayList<Estado> naoTerminais = new ArrayList<>();
		naoTerminais.add(S);
		naoTerminais.add(A);
		naoTerminais.add(B);
		//naoTerminais.add(C);
		
		ArrayList<Producao> producoes = new ArrayList<>();
		//producoes.add(new Producao(S, "ABcA"));
		producoes.add(new Producao(S, "AcAb"));
		//producoes.add(new Producao(S, "AAbA"));
		producoes.add(new Producao(A, "a"));
		producoes.add(new Producao(A, "&"));
		producoes.add(new Producao(B, "b"));
		//producoes.add(new Producao(B, "&"));
		
		GLC g = new GLC(naoTerminais, terminais, producoes, S);
		
		
		String string = "S -> AcAB, A-> a | &, B -> b";
		String string2 = "S -> AC | CeB | Ba, A -> aA | BC, B -> bB | AB | &, C -> cC | &";
		
		//System.out.println(Interface.editaGramaticaParse(string));
		
		//System.out.println(S.getFirst());
		
		//System.out.println(A.getFollow());


/*		for (Estado e : naoTerminaisAux){
			System.out.println(e.getNome());
		}*/
		
/*		for (Character car : terminaisAux){
			System.out.println(car);
		}*/
		
/*		for (Producao p : producoesAux){
			System.out.println(p.getOrigem().getNome()+" -> "+p.getDestino());
		}*/
		
		//GLC g1 = Interface.criarGramatica();
		//Interface.editarGramatica(g);
		
		//Interface.mostraGramatica(g);
		
/*		for (Character c : "abcABCXYZ".toCharArray()){
			System.out.println((int)c);
		}*/
		
		
	}
	
	public static boolean possuiProducao1(ArrayList<Producao> producoesAux,
			Estado estadoPorNome, String destino) {
		for (Producao p : producoesAux){
			if (p.getOrigem() == estadoPorNome && p.getDestino().equals(destino)){
				return true;
			}
		}
		return false;
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

	public static ArrayList<Character> firstNT(Estado e) {
		return e.getFirstNT();
	}
	
	public static ArrayList<Character> followNT(Estado e) {
		return e.getFollowNT();
	}
	
/*	public static ArrayList<Character> getFirst(int index, String palavra, ArrayList<Character> firsts, GLC gramatica) {
		ArrayList<Character> firsts1 = firsts;
		//int index = 1;
		if(palavra.length() > index){
			do {
				Character firstProducao = palavra.charAt(index);
				int valor = (int) firstProducao;
				//System.out.println(valor);
				if (valor > 90 || valor == 38){
					if (!firsts.contains(firstProducao)){
						firsts.add(firstProducao);
						//System.out.println(firstProducao);
					}
					return firsts;
				} else {
					ArrayList<Character> firstDoEstado = gramatica.getEstadoPorNome(firstProducao.toString()).getFirst();
					if(firstDoEstado.contains('&')){
						//System.out.println(index);
						if(palavra.length() > index){
							ArrayList<Character> aux = Principal.getFirst(++index, palavra, firsts, gramatica);
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
					if (index != palavra.length()-1){
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
	}*/
}
