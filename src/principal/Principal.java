package principal;

import java.util.ArrayList;

public class Principal {

	public static void main(String args[]) {

		ArrayList<String> instru��es = Arquivo.getInstance().lerArquivo();
		for (String s : instru��es) {
			Interpretador.translateAssembly(s);
		}

	}

}
