package principal;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorMemoria {
	
	private HashMap<Integer,String> memoriaInstrucoes;
	
	private int enderecoAtual;
	private int enderecoFinal;

	public ControladorMemoria(){
		memoriaInstrucoes = new HashMap<Integer,String>();
		 
		enderecoAtual =4194304;
	}
	
	public void addInstrucao(String instrucao){
		//memoriaInstrucoes.put(, instrucao); 
	}
	public void povoaMemoria(){
		int end = 4194300;
		
		ArrayList<String> instrucoes = Arquivo.getInstance().lerArquivo();
		for (String s : instrucoes) {
			end = end +4;
			memoriaInstrucoes.put(end,s); 
		}
		enderecoFinal = end;
		
	}

	public int getEnderecoAtual() {
		return enderecoAtual;
	}

	public void setEnderecoAtual(int enderecoAtual) {
		this.enderecoAtual = enderecoAtual;
	}
	public void executar(){
		while(enderecoAtual < enderecoFinal){
			Interpretador.translateAssembly(memoriaInstrucoes.get(enderecoAtual));
			enderecoAtual = enderecoAtual+4;
		}
	}
	
	
	
}
