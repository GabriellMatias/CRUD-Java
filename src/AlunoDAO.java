import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private List<Aluno> alunos;
    private int ultimoId;

    public AlunoDAO() {
        alunos = new ArrayList<>();
        ultimoId = 0;
    }

    public void cadastrar(Aluno aluno) {
        ultimoId++;
        aluno.setId(ultimoId);
        alunos.add(aluno);
    }

    public List<Aluno> listar() {
        return alunos;
    }

    public Aluno buscarPorId(int id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }
        return null;
    }

    public void atualizar(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == aluno.getId()) {
                alunos.set(i, aluno);
                return;
            }
        }
    }

    public void excluir(int id) {
        alunos.removeIf(aluno -> aluno.getId() == id);
    }
}
