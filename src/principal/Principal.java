package principal;

import java.util.ArrayList;

public class Principal {

	public static void main(String args[]) {

		ArrayList<String> instru��es = Arquivo.lerArquivo();
		for (String s : instru��es) {
			System.out.println(Interpretador.translateAssembly(s));

		}

	}

}