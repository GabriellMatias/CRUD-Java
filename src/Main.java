import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static boolean isString(String input) {
        return input.matches("[a-zA-Z]+");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();

        while (true) {
            System.out.println("|---------------------------------|");
            System.out.println("|      SELECIONE UMA OPCAO        |");
            System.out.println("|---------------------------------|");
            System.out.println("|  01   |     Cadastrar aluno     |");
            System.out.println("|---------------------------------|");
            System.out.println("|  02   |     Listar alunos       |");
            System.out.println("|---------------------------------|");
            System.out.println("|  03   |     Buscar Aluno Por ID |");
            System.out.println("|---------------------------------|");
            System.out.println("|  04   |     Atualizar Aluno     |");
            System.out.println("|---------------------------------|");
            System.out.println("|  05   |     Deletar Aluno       |");
            System.out.println("|---------------------------------|");
            System.out.println("|  06   |     Sair                |");
            System.out.println("|---------------------------------|\n");
            int opcao = scanner.nextInt();

            if (opcao == 1) {

                System.out.println("Digite o nome do aluno:");
                String nome = scanner.next();
                while (!isString(nome)) {
                    System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe uma [STRING]" + ANSI_RESET);
                    nome = scanner.next();
                }


                System.out.println("Digite a idade do aluno:");
                int idade;
                do {
                    while (!scanner.hasNextInt()) {
                        System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe um [NUMERO]."+ ANSI_RESET);
                        scanner.next();
                    }
                    idade = scanner.nextInt();
                } while (idade <= 0);


                System.out.println("Digite o curso do aluno:");
                String curso = scanner.next();
                while (!isString(curso)) {
                    System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe uma [STRING]" + ANSI_RESET);
                    curso = scanner.next();
                }


                Aluno aluno = new Aluno(0, nome, idade, curso);
                alunoDAO.cadastrar(aluno);
                System.out.println(ANSI_GREEN + "Aluno cadastrado com sucesso!\n" + ANSI_RESET);
            } else if (opcao == 2) {
                List<Aluno> alunos = alunoDAO.listar();
                if (alunos.isEmpty()) {
                    System.out.println(ANSI_RED + "Não há alunos cadastrados.\n" + ANSI_RESET);
                } else {
                    System.out.println( ANSI_GREEN + "Listando alunos");
                    for (Aluno aluno : alunos) {
                        System.out.println("|---------------------------------|");
                        System.out.println("|  ID      | " + aluno.getId());
                        System.out.println("|---------------------------------|");
                        System.out.println("|  NOME    | " + aluno.getNome());
                        System.out.println("|---------------------------------|");
                        System.out.println("|  IDADE   | " + aluno.getIdade());
                        System.out.println("|---------------------------------|");
                        System.out.println("|  CURSO   | " + aluno.getCurso());
                        System.out.println("|---------------------------------|\n" + ANSI_RESET);
                    }
                }
            } else if (opcao == 3) {
                System.out.println("Digite o ID do aluno:");
                int id = scanner.nextInt();
                Aluno aluno = alunoDAO.buscarPorId(id);
                if (aluno == null) {
                    System.out.println(ANSI_RED+"Aluno não encontrado."+ANSI_RESET);
                } else {
                    System.out.println(ANSI_GREEN + "Dados do aluno:");
                    System.out.println("|---------------------------------|");
                    System.out.println("|  ID      | " + aluno.getId());
                    System.out.println("|---------------------------------|");
                    System.out.println("|  NOME    | " + aluno.getNome());
                    System.out.println("|---------------------------------|");
                    System.out.println("|  IDADE   | " + aluno.getIdade());
                    System.out.println("|---------------------------------|");
                    System.out.println("|  CURSO   | " + aluno.getCurso());
                    System.out.println("|---------------------------------|\n" + ANSI_RESET);

                }
            } else if (opcao == 4) {
                System.out.println("Digite o ID do aluno:");
                int id = scanner.nextInt();
                Aluno aluno = alunoDAO.buscarPorId(id);
                if (aluno == null) {
                    System.out.println(ANSI_RED+"Aluno não encontrado."+ANSI_RESET);
                } else {
                    System.out.println("Digite o [NOVO] nome do aluno:");
                    String nome = scanner.next();
                    while (!isString(nome)) {
                        System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe uma [STRING]" + ANSI_RESET);
                        nome = scanner.next();
                    }


                    System.out.println("Digite a [NOVA] idade do aluno:");
                    int idade;
                    do {
                        while (!scanner.hasNextInt()) {
                            System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe um [NUMERO]."+ ANSI_RESET);
                            scanner.next();
                        }
                        idade = scanner.nextInt();
                    } while (idade <= 0);


                    System.out.println("Digite o [NOVO] curso do aluno:");
                    String curso = scanner.next();
                    while (!isString(curso)) {
                        System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe uma [STRING]" + ANSI_RESET);
                        curso = scanner.next();
                    }

                    aluno.setNome(nome);
                    aluno.setIdade(idade);
                    aluno.setCurso(curso);
                    alunoDAO.atualizar(aluno);
                    System.out.println(ANSI_GREEN+"Aluno atualizado com sucesso!"+ANSI_RESET);

                }
            } else if (opcao == 5) {
                System.out.println("Digite o ID do aluno:");
                int id = scanner.nextInt();
                alunoDAO.excluir(id);
                System.out.println(ANSI_GREEN+"Aluno excluído com sucesso!"+ANSI_RESET);
            } else if (opcao == 6) {
                break;
            } else {
                System.out.println(ANSI_RED+"Opção inválida!"+ANSI_RESET);
            }
        }

        scanner.close();
    }
}
