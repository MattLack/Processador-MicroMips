package principal;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	private static Scanner teclado;

	public static void main(String args[]) {

		
		teclado = new Scanner(System.in);

		System.out.println("Digite 1 para executar modo processador: ");
		System.out.println("Digite 2 para executar modo tradutor: ");

		int k = teclado.nextInt();

		// Execu��o como processador (uso das memorias de instru��o e dados)
		if (k == 1)
			ControladorMemoria.getIntance().executar();

		// Execu��o como fila de instru��o (ignora a mem�ria de instru��o)
		else if (k == 2) {
			ArrayList<String> instru��es = Arquivo.getInstance().lerArquivo();
			for (String s : instru��es) {
				Interpretador.translateAssembly(s);
			}
		}
	
	}
}
