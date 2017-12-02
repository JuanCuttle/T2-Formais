import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JMenuItem;


public class AtorUsuario extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtorUsuario frame = new AtorUsuario();
					frame.setVisible(true);
					//Interface.mostraGramatica(Principal.gramaticasCriadas.get(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AtorUsuario() {
		setTitle("Trabalho II");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trabalho 2");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(127, 70, 159, 44);
		getContentPane().add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGramaticas = new JMenu("Gramaticas");
		menuBar.add(mnGramaticas);
		
		JButton btnCriarGramtica = new JButton("Criar Gram\u00E1tica");
		mnGramaticas.add(btnCriarGramtica);
		
		JButton btnEditarGramtica = new JButton("Editar Gram\u00E1tica");
		btnEditarGramtica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String confirmP;
				int i = 0;
				String gramaticas = "";
				for (GLC g : Principal.gramaticasCriadas){
					gramaticas += "\nGramatica "+i+":\n";
					gramaticas += Interface.mostraGramatica(g);
					i++;
				}
				confirmP = JOptionPane.showInputDialog(null, "Deseja editar qual gramatica?\n"+gramaticas);
				if (!confirmP.equals("")){
					int escolhida = Integer.parseInt(confirmP);
					Interface.editarGramatica(Principal.gramaticasCriadas.get(escolhida));
					
				}
			}
		});
		mnGramaticas.add(btnEditarGramtica);
		
		JMenu mnOperaes = new JMenu("Opera\u00E7\u00F5es");
		menuBar.add(mnOperaes);
		
		JButton btnFirstestado = new JButton("First (Estado)");
		btnFirstestado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String confirmP;
				int i = 0;
				String gramaticas = "";
				for (GLC g : Principal.gramaticasCriadas){
					gramaticas += "\nGramatica "+i+":\n";
					gramaticas += Interface.mostraGramatica(g);
					i++;
				}
				confirmP = JOptionPane.showInputDialog(null, "Selecione a gramatica:\n"+gramaticas);
				if (!confirmP.equals("")){
					int escolhida = Integer.parseInt(confirmP);
					GLC gEscolhida = Principal.gramaticasCriadas.get(escolhida);
					String nomeEstado = JOptionPane.showInputDialog("Digite o nome do estado a mostrar o first():");
					ArrayList<Character> first = Principal.first(Principal.getEstadoPorNome(nomeEstado, gEscolhida.getNaoTerminais()));
					JOptionPane.showMessageDialog(null, first);
					
				}
			}
		});
		mnOperaes.add(btnFirstestado);
		
		JButton btnFollowestado = new JButton("Follow (Estado)");
		btnFollowestado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String confirmP;
				int i = 0;
				String gramaticas = "";
				for (GLC g : Principal.gramaticasCriadas){
					gramaticas += "\nGramatica "+i+":\n";
					gramaticas += Interface.mostraGramatica(g);
					i++;
				}
				confirmP = JOptionPane.showInputDialog(null, "Selecione a gramatica:\n"+gramaticas);
				if (!confirmP.equals("")){
					int escolhida = Integer.parseInt(confirmP);
					GLC gEscolhida = Principal.gramaticasCriadas.get(escolhida);
					String nomeEstado = JOptionPane.showInputDialog("Digite o nome do estado a mostrar o follow():");
					ArrayList<Character> follow = Principal.follow(Principal.getEstadoPorNome(nomeEstado, gEscolhida.getNaoTerminais()));
					JOptionPane.showMessageDialog(null, follow);
					
				}
			}
		});
		mnOperaes.add(btnFollowestado);
		
		JButton btnFirstntestado = new JButton("FirstNT (Estado)");
		btnFirstntestado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String confirmP;
				int i = 0;
				String gramaticas = "";
				for (GLC g : Principal.gramaticasCriadas){
					gramaticas += "\nGramatica "+i+":\n";
					gramaticas += Interface.mostraGramatica(g);
					i++;
				}
				confirmP = JOptionPane.showInputDialog(null, "Selecione a gramatica:\n"+gramaticas);
				if (!confirmP.equals("")){
					int escolhida = Integer.parseInt(confirmP);
					GLC gEscolhida = Principal.gramaticasCriadas.get(escolhida);
					String nomeEstado = JOptionPane.showInputDialog("Digite o nome do estado a mostrar o first():");
					ArrayList<Character> firstNT = Principal.firstNT(Principal.getEstadoPorNome(nomeEstado, gEscolhida.getNaoTerminais()));
					JOptionPane.showMessageDialog(null, firstNT);
					
				}
			}
		});
		mnOperaes.add(btnFirstntestado);
		
		JButton btnFollowntestado = new JButton("FollowNT (Estado)");
		btnFollowntestado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String confirmP;
				int i = 0;
				String gramaticas = "";
				for (GLC g : Principal.gramaticasCriadas){
					gramaticas += "\nGramatica "+i+":\n";
					gramaticas += Interface.mostraGramatica(g);
					i++;
				}
				confirmP = JOptionPane.showInputDialog(null, "Selecione a gramatica:\n"+gramaticas);
				if (!confirmP.equals("")){
					int escolhida = Integer.parseInt(confirmP);
					GLC gEscolhida = Principal.gramaticasCriadas.get(escolhida);
					String nomeEstado = JOptionPane.showInputDialog("Digite o nome do estado a mostrar o follow():");
					ArrayList<Character> followNT = Principal.followNT(Principal.getEstadoPorNome(nomeEstado, gEscolhida.getNaoTerminais()));
					JOptionPane.showMessageDialog(null, followNT);
					
				}
			}
		});
		mnOperaes.add(btnFollowntestado);
		btnEditarGramtica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		btnCriarGramtica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Principal.gramaticasCriadas.add(Interface.criarGramatica());
				
				String entrada = JOptionPane.showInputDialog("Digite a gramatica (colocar virgulas entre os nao-terminais, exemplo: S-aA | bB, A->a): ");
				Principal.gramaticasCriadas.add(Interface.criaGramaticaParse(entrada));
			}
		});
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
