package br.com.vertigo.academy.exercicioJavaFive.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.vertigo.academy.exercicioJavaFive.entities.Rotas;
import br.com.vertigo.academy.exercicioJavaFive.entities.Usuario;

public class Main {

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		String nome, cpf;
		int opcao = 0, opcao2 = 0, destino;

		Usuario usuario = new Usuario("null", "null", null);
		File arq = Usuario.criarArquivo();

		List<Rotas> rotas = new ArrayList<Rotas>();
		List<Rotas> destinoList = new ArrayList<Rotas>();

		rotas.add(new Rotas(1, "Bahia", "12.00", 200.00));
		rotas.add(new Rotas(2, "Minas Gerais", "08.00", 150.00));
		rotas.add(new Rotas(3, "Rio de Janeiro", "19.00", 300.00));
		rotas.add(new Rotas(4, "Canada", "20.00", 1000.00));
		rotas.add(new Rotas(5, "Acre", "00.00", 400.00));
		rotas.add(new Rotas(6, "Ceara", "07.00", 500.00));
		rotas.add(new Rotas(7, "Tocantins", "08.00", 70.00));
		rotas.add(new Rotas(8, "Espirito Santo", "22.00", 300.00));
		rotas.add(new Rotas(9, "Para", "20.00", 1000.00));
		rotas.add(new Rotas(10, "Brasilia", "00.00", 350.00));

		do {
			System.out.println(
					"Selecione a opção desejada: \n 1 - Login; \n 2 - Cadastro; \n 3 - Visualizar rotas sem cadastro \n 9 - Sair;");
			opcao = teclado.nextInt();

			// Login e ações com login
			switch (opcao) {
			case 1:
				System.out.println("Nome: ");
				nome = teclado.next();
				System.out.println("CPF: ");
				cpf = teclado.next();
				
				Usuario login = new Usuario(nome, cpf, arq);
				
				Usuario.escreveDadosDoUsuario(nome, cpf, arq);

				if (login.login(usuario, login)) {
					do {
						System.out.println(
								"\nSelecione a opção desejada: \n 1 - Vizualizar rotas disponiveis; \n 2 - Reservar uma rota; "
										+ "; \n 3 - Visualizar rotas reservadas; \n 4 - Cancelar Passagem; \n 9 - Sair;");
						opcao2 = teclado.nextInt();
						// Vizualizar rotas disponiveis
						switch (opcao2) {
						case 1:
							System.out.println("Rotas disponiveis:");
							Rotas.listaDeRotas(rotas);
							System.out.println("\n");
							break;

						// Reservar uma rota
						case 2:
							do {
								System.out.println("\n Reservar uma rota");
								Rotas.listaDeRotas(rotas);
								System.out.println("");
								System.out.println("Escolha o destino desejado; ");
								destino = teclado.nextInt();
								if(destino < 1 || destino > rotas.size()) {
									System.out.println("Rotas inexistente, selecione novamente!");
								} else {
									destinoList.add(rotas.get(destino - 1));
									
									Rotas.escreveReservasDeRotasDoUsuario(destino - 1, rotas.get(destino - 1).getDestino(), rotas.get(destino - 1).getHorario()
		                                    , rotas.get(destino - 1).getValor(), arq);
								}
							}while(destino < 1 || destino > rotas.size());
							break;

						// Visualizar rotas reservadas
						case 3:
							Usuario.reservaDeRotasPorUsuario(destinoList);
							break;

						// Cancelar passagem
						case 4:
							Usuario.cancelamentoDeRotasPorUsuario(destinoList, teclado, arq);
							break;
						// Saida
						case 9:
							System.out.println("\nUsuario deslogado\n");
							break;

						// Opção invalida
						default:
							System.out.println("\nOp��o invalida, selecione novamente!\n");
							break;
						}
					} while (opcao2 != 9);
				} else {
					System.out.println("Usuario inexistente!");
				}
				break;

			// Cadastro
			case 2:
				System.out.println("\nrealizado de cadastro");
				System.out.println("Nome: ");
				nome = teclado.next();
				System.out.println("CPF: ");
				cpf = teclado.next();

				usuario.setNome(nome);
				usuario.setCPF(cpf);
				
				destinoList = new ArrayList<Rotas>();
				System.out.println("Cadastro realizado, efetue o login\n");
				break;
			// Ações sem cadastro, vizualizar rotas
			case 3:
				Rotas.rotasDeUsuariosSemCadastro(rotas);
				break;
			// saida
			case 9:
				System.out.println("\nObrigado por usar nossa aplicação, volte sempre!");
				break;
			// opcao invalida
			default:
				System.out.println("\nOpção invalida, selecione novamente!");
				break;
			}
		} while (opcao != 9);
		teclado.close();
	}
}