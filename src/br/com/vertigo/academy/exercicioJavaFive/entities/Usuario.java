package br.com.vertigo.academy.exercicioJavaFive.entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Usuario {
	public static Scanner ler = new Scanner(System.in);
	private String nome;
	private String CPF;
	private File arq;

	public Usuario(String nome, String CPF, File arq) {
		this.nome = nome;
		this.CPF = CPF;
		this.setArq(arq);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public File getArq() {
		return arq;
	}

	public void setArq(File arq) {
		this.arq = arq;
	}

	public static File criarArquivo() {
		File arq = new File("Dados.txt");
		try {
			arq.createNewFile();
		} catch (IOException e) {
			System.out.println("Arquivo não encontrado");
		}
		return arq;
	}

	public static void escreveDadosDoUsuario(String nome, String cpf, File arq) {
		try {
			FileWriter escrever = new FileWriter(arq, true);
			escrever.write("\n\n************************************* " + "\nNome: " + nome + "; CPF: " + cpf + "\n");
			escrever.close();
		} catch (IOException e) {
			System.out.println("Arquivo não encontrado");
		}
	}

	public boolean login(Usuario usuario1, Usuario usuario2) {
		if (usuario1.getNome().equals(usuario2.getNome()) && usuario1.getCPF().contentEquals(usuario2.getCPF())) {
			return true;
		}
		return false;
	}

	public static void reservaDeRotasPorUsuario(List<Rotas> destinoList) {
		System.out.println("\nRotas reservadas:");
		System.out.printf("|%-15s|%8s|%7s|", "Destino", "Horario", "Valor");
		System.out.println("");
		
		for (Rotas rotas2 : destinoList) {
			System.out.printf("|%-15s|%8s|%7.2f|", rotas2.getDestino(), rotas2.getHorario(), rotas2.getValor());
			System.out.println("");
		}
		System.out.println("\n");
	}

	public static void cancelamentoDeRotasPorUsuario(List<Rotas> destinoList, Scanner teclado, File arq) {
		int cancelar;
		System.out.println("\n\nCancelamento de passagem");
		System.out.printf("|%2s|%-15s|%8s|%7s|", "ID", "Destino", "Horario", "Valor");
		System.out.println("");
		
		do {
			int cont = 0;
			for (Rotas rotas2 : destinoList) {
				cont++;
				System.out.printf("|%-2d|%-15s|%8s|%7.2f|", cont, rotas2.getDestino(), rotas2.getHorario(),
						rotas2.getValor());
				System.out.println("");
			}
			System.out.println("\n\nInforme a passagem a ser cancelada: ");
			cancelar = teclado.nextInt();
			if(cancelar < 0 || cancelar > destinoList.size()) {
				System.out.println("\nOpção inexistente, selecione novamente!\n");
			}
			
		}while(cancelar < 0 || cancelar > destinoList.size());

		Rotas.escreveCancelamentoDeRotasDoUsuario( destinoList.get(cancelar - 1).getId(), destinoList.get(cancelar - 1).getDestino(),
				destinoList.get(cancelar - 1).getHorario(), destinoList.get(cancelar - 1).getValor(), arq);
		destinoList.remove(cancelar - 1);

	}
}