package org.example;


import org.example.Class.DadosDoUsuario;
import org.example.Funcoes.Funcion;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<DadosDoUsuario> db = new ArrayList<>();
        Funcion funcion = new Funcion();
        boolean login = false;

        try {
            while(true) {
                if (!login) {
                    System.out.println("=== Tela de Login ===" +
                            "\n 1 - Criar Usuário" +
                            "\n 2 - Logar na Conta" +
                            "\n 0 - Sair");
                    System.out.print("Digite sua opção: ");
                    int opcao = sc.nextInt();

                    if (opcao == 0) {
                        System.out.println("Saindo...");
                        break;
                    }

                    switch (opcao) {
                        case 1:
                            funcion.criarConta(db,sc);
                            break;
                        case 2:
                            login = funcion.logarConta(db,sc);
                            break;
                        case 0:
                            break;
                        default:
                            throw new IllegalArgumentException("Opção inválida! ");
                    }

                } // Tela e Funções sem Login -

                else if (login) {
                    System.out.println("== Menu com Login ===" +
                            "\n 1 - Buscar endereço " +
                            "\n 2 - Listar usuários ativos " +
                            "\n 3 - Atualizar conta " +
                            "\n 4 - Atualizar CEP" +
                            "\n 5 - Deletar conta" +
                            "\n 0 - Deslogar" );
                    System.out.print("Digite sua opcao: ");
                    int opcao = sc.nextInt();

                    if (opcao == 0) {
                        System.out.println("Voltando a tela sem login...");
                        login = false;
                        break;
                    }

                    switch (opcao) {
                        case 1:
                            funcion.mostrarEnd(db,sc);
                            break;
                        case 2:
                            funcion.listarUsuarios(db);
                            break;
                        case 3:
                            login = funcion.atualizarSenha(db,sc);
                            break;
                        case 4:
                            funcion.atualizarCep(db,sc);
                            break;
                        case 5:
                            login = funcion.deletarConta(db,sc);
                            break;
                        case 0:
                            break;
                        default:
                            throw new IllegalArgumentException("Opção inválida! ");
                    }

                } // Tela e Funções com Login -
            }

        } catch (IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        }



    }
}