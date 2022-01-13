package br.com.vertigo.academy.exercicioJavaFive.entities;

import java.util.List;
import java.util.Scanner;

public class Usuario {
	private String nome;
	private String CPF;

	public Usuario(String nome, String CPF) {
		this.nome = nome;
		this.CPF = CPF;
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

	public static void cancelamentoDeRotasPorUsuario(List<Rotas> destinoList, Scanner teclado) {
		int cancelar;
		System.out.println("\n\nCancelamento de passagem");
		int cont = 0;
		System.out.printf("|%2s|%-15s|%8s|%7s|", "ID", "Destino", "Horario", "Valor");
		System.out.println("");
		for (Rotas rotas2 : destinoList) {
			cont++;
			System.out.printf("|%-2d|%-15s|%8s|%7.2f|", cont, rotas2.getDestino(), rotas2.getHorario(),
					rotas2.getValor());
			System.out.println("");
		}
		System.out.println("\n");

		System.out.println("Informe a passagem a ser cancelada: ");
		cancelar = teclado.nextInt();
		destinoList.remove(cancelar - 1);
	}
}
