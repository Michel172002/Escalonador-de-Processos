import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String diretorio = "/home/michel/Documentos/Faculdade/4semestre/SO/Escalonador-de-Processos/src/";
        verificacaoDeArquivos(diretorio);

        ler(diretorio);
    }

    static public void verificacaoDeArquivos(String diretorio){
        File entrada = new File(diretorio, "entrada");
        File saida = new File(diretorio, "saida");

        if(entrada.exists()){
            System.out.println("Arquivo Entrada já existe!");
        }else {
            try{
                boolean statusArqEntrada = entrada.createNewFile();
                System.out.println("Entrada criada: " + statusArqEntrada);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(saida.exists()){
            System.out.println("Arquivo Saida já existe!");
        }else {
            try{
                boolean statusArqSaida = saida.createNewFile();
                System.out.println("Saida criada: " + statusArqSaida);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    static public void ler(String diretorio){
        File entrada = new File(diretorio, "entrada");

        try {
            FileReader fileReader = new FileReader(entrada);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = "";
            String numProcessosString = "";
            String algoritmo = "";

            ArrayList<String> processos = new ArrayList<>();
            int i = 0;
            while ((linha = bufferedReader.readLine()) != null) {
                if(linha != null && !linha.isEmpty()) {
                    if(i == 0){
                        numProcessosString = linha;
                        i++;
                    }else if(i == 1) {
                        algoritmo = linha;
                        i++;
                    }else {
                        processos.add(linha);
                    }
                }else {
                    System.out.println("Arquivo Vazio!");
                    break;
                }
            }

            int numProcessos = Integer.parseInt(numProcessosString);
            if(numProcessos != processos.size()){
                System.out.println("Numero de Processos informado divirgente da quantidade de processos!");
                System.out.println("Quantidades de processos informados:" + numProcessos);
                System.out.println("Quantidade real de processos:" + processos.size());
                numProcessos = processos.size();
                System.out.println("Numero de processos atualizado!");
                System.out.println("---------------------------------------------------------------------");
            }

            ArrayList<Processos> arrayProcessos = new ArrayList<>();
            for(String s : processos){
                String[] S = s.split(";");
                int idProcesso = Integer.parseInt(S[0]);
                int tempoChegada = Integer.parseInt(S[1]);
                int tempoCPU = Integer.parseInt(S[2]);
                Processos p = new Processos(idProcesso, tempoChegada, tempoCPU);
                arrayProcessos.add(p);
            }

            algoritmo = algoritmo.toUpperCase().trim();
            String algoritmo2[] = algoritmo.split(";");
            switch (algoritmo2[0]){
                case "RR":
                    Algoritmos RR = new RR();
                    RR.executar(numProcessos, arrayProcessos);
                    break;

                case "FCFS":
                    Algoritmos FCFS = new FCFS();
                    FCFS.executar(numProcessos, arrayProcessos);
                    break;

                case "SJF":
                    Algoritmos SJF = new SJF();
                    SJF.executar(numProcessos, arrayProcessos);
                    break;
            }
            fileReader.close();
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
