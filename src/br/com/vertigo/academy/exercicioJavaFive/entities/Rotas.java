package br.com.vertigo.academy.exercicioJavaFive.entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Rotas {
	private int id;
	private String destino;
	private String horario;
	private double valor;

	public Rotas(int id, String destino, String horario, double valor) {
		this.id = id;
		this.destino = destino;
		this.horario = horario;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public static void escreveReservasDeRotasDoUsuario( int id, String destino, String horario, double valor, File arq) {
		try {
			FileWriter escrever = new FileWriter(arq, true);
			escrever.write("\nROTA RESERVADA: \nId:" + (id + 1) + " | Destino: "+ destino + "| Horario: "+ horario + " | Valor: " + valor + "\n" );
			escrever.close();
		} catch (IOException e) {
			System.out.println("Arquivo não encontrado");
		}
	}

	public static void listaDeRotas(List<Rotas> rotas) {
		System.out.printf("|%2s|%-15s|%8s|%7s|", "ID", "Destino", "Horario", "Valor");
		System.out.println("");
		for (Rotas rotas2 : rotas) {
			System.out.printf("|%-2d|%-15s|%8s|%7.2f|", rotas2.getId(), rotas2.getDestino(), rotas2.getHorario(),
					rotas2.getValor());
			System.out.println("");
		}
	}

	public static void rotasDeUsuariosSemCadastro(List<Rotas> rotas) {
		System.out.println("Vizualização de rotas sem cadastro:");
		System.out.printf("|%-15s|%8s|%7s|", "Destino", "Horario", "Valor");
		System.out.println("");
		for (Rotas rotas2 : rotas) {

			System.out.printf("|%-15s|%8s|%7.2f|", rotas2.getDestino(), rotas2.getHorario(), rotas2.getValor());
			System.out.println("");
		}
		System.out.println("\n");
	}

	public static void escreveCancelamentoDeRotasDoUsuario(int id, String destino, String horario, double valor, File arq) {
		try {
			FileWriter escrever = new FileWriter(arq, true);
			escrever.write("\nROTA CANCELADA: \nId:" + id + " | Destino: " + destino + "| Horario: " + horario + " | Valor: " + valor + "\n");
			escrever.close();
		} catch (IOException e) {
			System.out.println("Arquivo não encontrado");
		}
	}

}
