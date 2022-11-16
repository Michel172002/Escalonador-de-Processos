import java.util.ArrayList;

public class RR implements Algoritmos{
    @Override
    public void executar(int numProcessos, ArrayList<Processos> processos, int quantum) {

        ArrayList<Processos> filaDePronto = new ArrayList<>();
        ArrayList<Processos> filaConcluidos = new ArrayList<>();
        int tempoExecucao = 0;
        ArrayList<Integer> duracaoAux = new ArrayList<Integer>();

        for(int i=0; i <= 15; i++){
            for (int j = 0; j < processos.size(); j++) {
                if (i == processos.get(j).getTempoChegada()) {
                    filaDePronto.add(processos.get(j));
                }
            }
            if(!filaDePronto.isEmpty()) {
                System.out.println("Tempo " + i + ": Processo id: " + filaDePronto.get(0).getIdProcesso());

                if(tempoExecucao == quantum || tempoExecucao == filaDePronto.get(0).getTempoCPU()) {
                    filaDePronto.get(0).setTempoCPU(filaDePronto.get(0).getTempoCPU() - quantum);

                    if (filaDePronto.get(0).getTempoCPU() > 0) {
                        filaDePronto.add(filaDePronto.get(0));
                    }

                    if(!filaConcluidos.contains(filaDePronto.get(0))){
                        filaConcluidos.add(filaDePronto.get(0));
                    }

                    filaDePronto.remove(0);
                    tempoExecucao = 0;
                }
                for (int j = 0; j < filaDePronto.size(); j++) {
                    if (j == 0) {
                        filaDePronto.get(j).setTempoSistema(filaDePronto.get(j).getTempoSistema() + 1);
                        System.out.println("Processo " + filaDePronto.get(j).getIdProcesso() + "Em execução :"
                        + filaDePronto.get(j).getTempoSistema());
                    }else {
                        filaDePronto.get(j).setTempoEspera(filaDePronto.get(j).getTempoEspera() + 1);
                        System.out.println("Processo " + filaDePronto.get(j).getIdProcesso() + "Em espera :"
                                + filaDePronto.get(j).getTempoEspera());
                    }
                }
                tempoExecucao++;
            }else {
                System.out.println("arrayRR empty!");
            }
        }
        for (Processos p : filaConcluidos) {
            p.setTempoDeConclusao(p.getTempoEspera() + p.getTempoSistema());
            p.setTempoCPU(p.getTempoSistema());
        }
//        criarTabela(filaConcluidos);
    }

    public void criarTabela(ArrayList<Processos> processos){
        System.out.println("       |-----------------------TEMPO DE--------------------|");
        System.out.println("Tarefa | Ingresso | Duração | Conclusão | Sistema | Espera |");
        System.out.println("-------+----------+---------+-----------+---------+--------+");
        for (int j=0; j < processos.size(); j++){
            System.out.print("  T" + processos.get(j).getIdProcesso() + "   | ");
            System.out.print("    " + processos.get(j).getTempoChegada() + "    | ");
            System.out.print("   " + processos.get(j).getTempoCPU() + "    | ");
            System.out.print("    " + processos.get(j).getTempoDeConclusao() + "     | ");
            System.out.print("   " + processos.get(j).getTempoSistema() + "    | ");
            System.out.println("   " + processos.get(j).getTempoEspera() + "  | ");
        }
        System.out.println("-------+----------+---------+-----------+---------+--------+");
    }


}
