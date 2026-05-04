package org.example.Funcoes;


import com.google.gson.Gson;
import org.example.Class.DadosDoUsuario;

// Server
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

// API
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class Funcion {

    public void criarConta(ArrayList<DadosDoUsuario> bd, Scanner sc){
        sc.nextLine(); // Limpando o Buffer
        try {
            System.out.println("Digite o nome de usuário: ");
            String nome = sc.nextLine();

            if (verificação(bd, nome)) {
                throw new IllegalArgumentException("Usuario em uso!");
            }
            else {
                System.out.println("Digite sua Senha: ");
                String senha = sc.nextLine();
                System.out.println("Confitme sua Senha: ");
                String confirmarSenha = sc.nextLine();

                if (senha.equals(confirmarSenha)) {

                    System.out.println("Digite seu cep: ");
                    String cep = sc.nextLine();

                    if (cep == null) {
                        throw new NullPointerException("Argumento vazil!");
                    }
                    else {
                        // Set no Banco de Dados
                        DadosDoUsuario dadosDoUsuario = new DadosDoUsuario();

                        DadosDoUsuario endereco = buscarEndereco(cep);

                        dadosDoUsuario.setCep(cep);
                        dadosDoUsuario.setNome(nome);
                        dadosDoUsuario.setSenha(senha);

                        dadosDoUsuario.setlocalidade(endereco.getlocalidade());
                        dadosDoUsuario.setEstado(endereco.getEstado());
                        dadosDoUsuario.setRua(endereco.getRua());

                        bd.add(dadosDoUsuario);
                        System.out.println("Usuário criado! Faça seu Login! ");

                    }
                }
                else {
                    System.out.println("Senhas incorretas!");
                }
            }

        }
        catch (Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
    }

    public boolean logarConta(ArrayList<DadosDoUsuario> bd, Scanner sc) {
        sc.nextLine(); // Limpando o Buffer

       try {
           System.out.println("Digite o nome de Usuario: ");
           String nome = sc.nextLine();

           int i = posicaoDoUsuario(bd,nome);

           if (i == -1) {
               throw new IllegalArgumentException("Usuário incorreto!");
           }

           System.out.println("Digite sua senha: ");
           String senha = sc.nextLine();

           if (bd.get(i).getSenha().equals(senha)) {
               System.out.println("Login feito com sucesso!");
               return true;
           }
           else {
               throw new IllegalArgumentException("Senha incorretas!");
           }



       }catch (Exception e){
           System.out.println("ERRO: "+e.getMessage());
           return false;
       }
    }



    public void mostrarEnd(ArrayList<DadosDoUsuario> bd, Scanner sc) {
        sc.nextLine(); // Limpando o Buffer

        try {
            System.out.println("Digite o nome de Usuario: ");
            String nome = sc.nextLine();
            int i = posicaoDoUsuario(bd,nome);

            if (i == -1) {
                throw new IllegalArgumentException("Usuário incorreto!");
            }
            else {
                System.out.println("==  Informações  ==");
                System.out.println("Cep: " + bd.get(i).getCep() + " | localidade: " + bd.get(i).getlocalidade() + " | Estado: " + bd.get(i).getEstado() + " | Rua: " +  bd.get(i).getRua());
            }
        } catch (Exception e) {
            System.out.println("ERRO: "+ e.getMessage());
        }
    }

    public void listarUsuarios(ArrayList<DadosDoUsuario> bd) {
        System.out.println("== Usuarios ==");
        for (int i = 0; i < bd.size(); i++) {
            System.out.println("Nome: " + bd.get(i).getNome());
            System.out.println("localidade: " +  bd.get(i).getlocalidade());
            System.out.println("----------------------------------");
        }
    }

    public Boolean atualizarSenha(ArrayList<DadosDoUsuario>  bd, Scanner sc) {
        sc.nextLine();

        try {
            System.out.println("Digite seu nome de usuario");
            String nome = sc.nextLine();

            int i = posicaoDoUsuario(bd,nome);

            if (i == -1 ) {
                throw new IllegalArgumentException("Usuário incorreto!");
            }

            else {
                System.out.println("Digite sua senha: ");
                String senha = sc.nextLine();

                if (bd.get(i).getSenha().equals(senha)) {
                    System.out.println("Digite sua nova senha: ");
                    String novaSenha = sc.nextLine();

                    bd.get(i).setSenha(novaSenha);
                    System.out.println("Senha atualizada! Faça login novamente!");
                    return false;
                }
                else {
                    throw new IllegalArgumentException("Senha incorreta!");
                }
            }

        } catch (Exception e) {
            System.out.println("ERRO: "+e.getMessage());
        }
        return true;
    }

    public void atualizarCep(ArrayList<DadosDoUsuario> bd, Scanner sc) {
        sc.nextLine(); // Limpando o Buffer

        try {

            System.out.println("Digite seu usuário: ");
            String nome = sc.nextLine();

            int i = posicaoDoUsuario(bd,nome);

            if (i == -1 ) {
                throw new IllegalArgumentException("Usuário incorreto!");
            }
            else {
                System.out.println("Cep atual: " +  bd.get(i).getCep());
                System.out.println("Digite seu novo cep :");
                String novoCep = sc.nextLine();

                if (novoCep == null || novoCep.length() != 8) {
                    throw new NullPointerException("Argumento Inválido!");
                }
                else {
                    DadosDoUsuario endereco = buscarEndereco(novoCep);

                    bd.get(i).setCep(novoCep);
                    bd.get(i).setlocalidade(endereco.getlocalidade());
                    bd.get(i).setEstado(endereco.getEstado());
                    bd.get(i).setRua(endereco.getRua());

                    System.out.println("Novo CEP definido! ");
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO: "+e.getMessage());

        }
    }

    public Boolean deletarConta(ArrayList<DadosDoUsuario> bd, Scanner sc) {
        sc.nextLine(); // Limpando o Buffer
        try {

            System.out.println("Digite seu nome de Usuario: ");
            String nome = sc.nextLine();

            int i = posicaoDoUsuario(bd,nome);

            if (i == -1 ) {
                throw new IllegalArgumentException("Usuario incorreto!");
            }

            System.out.println("Digite sua senha" );
            String senha = sc.nextLine();

            if (bd.get(i).getSenha().equals(senha)) {
                System.out.println("Confitme com SIM");
                String confirmacao = sc.nextLine();

                if (confirmacao.equals("SIM")) {
                    bd.remove(i);
                    System.out.println("Usuário removido! ");
                    return false;
                }
                else {
                    throw new IllegalArgumentException("Confirmação Incorreta!");
                }
            }
            else {
                throw new IllegalArgumentException("Senha incorreta!");
            }
        }catch (Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return true;
    }

    private DadosDoUsuario buscarEndereco (String cep) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/"+ cep + "/json/"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        return gson.fromJson(response.body(), DadosDoUsuario.class);



    }
    private boolean verificação(ArrayList<DadosDoUsuario> bd, String nome) {
        for (int i = 0; i < bd.size() ; i ++) {
            if (bd.get(i).getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
    private int posicaoDoUsuario(ArrayList<DadosDoUsuario> bd, String nome) {
        for  (int i = 0; i < bd.size() ; i ++) {
            if (bd.get(i).getNome().equals(nome)) {
                return i;
            }
        }
        return -1;
    }
}
