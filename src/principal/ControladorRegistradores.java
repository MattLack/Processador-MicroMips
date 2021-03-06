package principal;

import java.util.Arrays;

public class ControladorRegistradores {

	private Registrador[] registradores = new Registrador[32];
	private Registrador[] Aux = new Registrador[32];
	private Registrador hi = new Registrador("hi", "00000000000000000000000000000000");
	private Registrador lo = new Registrador("lo", "00000000000000000000000000000000");
	private int TObyte;

	private Util util = new Util();
	private ControladorMemoria memoria;

	public ControladorRegistradores() {
		inicializarRegistradores();
		memoria = ControladorMemoria.getIntance();
	}

	public void inicializarRegistradores() {
		for (int i = 0; i < registradores.length; i++) {
			registradores[i] = new Registrador("$" + i, "00000000000000000000000000000000");
		}

	}

	public void verificaENDERECO(String endereco) {
		int end = Integer.parseInt(endereco);
		end = end - MemoriaDados.getInstance().getPosicINICIO();
		int posicao = end / 4;
		int casa = posicao * 4;
		// casa vai dizer quem que byte deve ser inserido
		this.TObyte = end - casa;
	}

	public int branchAddr(String v) {

		String valor = util.twoComplment(v);
		int k = Integer.parseInt(valor);

		k = k * 4;

		return k;
	}

	public Registrador[] getRegistradores() {
		return this.registradores;
	}

	public void add(String rd, String rs, String rt) {

		String conteudoRegs = registradores[util.TODecimal(rs)].getConteudo();
		conteudoRegs = util.twoComplment(conteudoRegs);
		int conteudoComplementoS = Integer.parseInt(conteudoRegs);

		String conteudoRegT = registradores[util.TODecimal(rt)].getConteudo();
		conteudoRegT = util.twoComplment(conteudoRegT);
		int conteudoComplementoT = Integer.parseInt(conteudoRegT);

		registradores[util.TODecimal(rd)]
				.setConteudo(util.completacomZero(Integer.toBinaryString(conteudoComplementoS + conteudoComplementoT)));

		System.out.println(toString());

	}

	public void addi(String rt, String rs, String imm) {

		String conteudoRegs = registradores[util.TODecimal(rs)].getConteudo();
		conteudoRegs = util.twoComplment(conteudoRegs);
		int conteudoComplementoS = Integer.parseInt(conteudoRegs);

		String imediato = util.twoComplment(imm);
		int imediatoComplemento = Integer.parseInt(imediato);

		registradores[util.TODecimal(rt)]
				.setConteudo(util.completacomZero(Integer.toBinaryString(conteudoComplementoS + imediatoComplemento)));

		System.out.println(toString());

	}

	public void addu(String rd, String rs, String rt) {

		String conteudoRegs = registradores[util.TODecimal(rs)].getConteudo();
		System.out.println("Rs:"+Integer.parseInt(conteudoRegs));
		int conteudoComplementoS = util.TODecimal(conteudoRegs);

		
		String conteudoRegT = registradores[util.TODecimal(rt)].getConteudo();
		System.out.println("RT:"+(conteudoRegT));
		int conteudoComplementoT = util.TODecimal(conteudoRegT);		
		
		System.out.println((conteudoComplementoS + conteudoComplementoT));

		registradores[util.TODecimal(rd)]
				.setConteudo(util.completacomZero(Integer.toBinaryString(conteudoComplementoS + conteudoComplementoT)));

		System.out.println(toString());

	}

	public void addiu(String rt, String rs, String imm) {

		String conteudoRegs = registradores[util.TODecimal(rs)].getConteudo();
		conteudoRegs = util.twoComplment(conteudoRegs);
		int conteudoComplementoS = Integer.parseInt(conteudoRegs);

		String imediato = util.twoComplment(imm);
		int imediatoComplemento = Integer.parseInt(imediato);

		registradores[util.TODecimal(rt)]
				.setConteudo(util.completacomZero(Integer.toBinaryString(conteudoComplementoS + imediatoComplemento)));

		System.out.println(toString());

	}

	public void sub(String rd, String rs, String rt) {

		String conteudoRegs = registradores[util.TODecimal(rs)].getConteudo();
		conteudoRegs = util.twoComplment(conteudoRegs);
		int conteudoComplementoS = Integer.parseInt(conteudoRegs);

		String conteudoRegT = registradores[util.TODecimal(rt)].getConteudo();
		conteudoRegT = util.twoComplment(conteudoRegT);
		int conteudoComplementoT = Integer.parseInt(conteudoRegT);

		registradores[util.TODecimal(rd)]
				.setConteudo(util.completacomZero(Integer.toBinaryString(conteudoComplementoS - conteudoComplementoT)));

		System.out.println(toString());

	}

	public void subu(String rd, String rs, String rt) {

		String conteudoRegs = registradores[util.TODecimal(rs)].getConteudo();
		int conteudoComplementoS = Integer.parseInt(conteudoRegs);

		String conteudoRegT = registradores[util.TODecimal(rt)].getConteudo();
		int conteudoComplementoT = Integer.parseInt(conteudoRegT);

		registradores[util.TODecimal(rd)]
				.setConteudo(util.completacomZero(Integer.toBinaryString(conteudoComplementoS - conteudoComplementoT)));

		System.out.println(toString());

	}

	public void slt(String rd, String rs, String rt) {

		String conteudoRegs = registradores[util.TODecimal(rs)].getConteudo();
		conteudoRegs = util.twoComplment(conteudoRegs);
		int conteudoComplementoS = Integer.parseInt(conteudoRegs);

		String conteudoRegT = registradores[util.TODecimal(rt)].getConteudo();
		conteudoRegT = util.twoComplment(conteudoRegT);
		int conteudoComplementoT = Integer.parseInt(conteudoRegT);

		if (conteudoComplementoS > conteudoComplementoT)
			registradores[util.TODecimal(rd)].setConteudo(util.completacomZero("1"));
		else
			registradores[util.TODecimal(rd)].setConteudo(util.completacomZero("0"));

		System.out.println(toString());

	}

	public void slti(String rt, String rs, String imm) {

		String conteudoRegs = registradores[util.TODecimal(rs)].getConteudo();
		conteudoRegs = util.twoComplment(conteudoRegs);
		int conteudoComplementoS = Integer.parseInt(conteudoRegs);

		String imediato = util.twoComplment(imm);
		int imediatoComplemento = Integer.parseInt(imediato);

		if (conteudoComplementoS > imediatoComplemento)
			registradores[util.TODecimal(rt)].setConteudo(util.completacomZero("1"));
		else
			registradores[util.TODecimal(rt)].setConteudo(util.completacomZero("0"));

		System.out.println(toString());

	}

	public void mfhi(String rd) {

		String conteudoHi = hi.getConteudo();
		registradores[util.TODecimal(rd)].setConteudo(conteudoHi);
		hi.setConteudo("00000000000000000000000000000000");

		System.out.println(toString());

	}

	public void mflo(String rd) {

		String conteudoLo = lo.getConteudo();
		registradores[util.TODecimal(rd)].setConteudo(conteudoLo);
		lo.setConteudo("00000000000000000000000000000000");

		System.out.println(toString());

	}

	public void and(String rd, String rs, String rt) {
		int rtB = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());
		int rsB = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());

		String r = Integer.toBinaryString(rtB & rsB);

		registradores[util.TODecimal(rd)].setConteudo(util.completacomZero(r));
		System.out.println(toString());

	}

	public void andi(String rt, String rs, String imm) {
		int rsB = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());
		int immB = util.TODecimal(imm);

		String r = Integer.toBinaryString(rsB & immB);

		registradores[util.TODecimal(rt)].setConteudo(util.completacomZero(r));
		System.out.println(toString());

	}

	public void or(String rd, String rs, String rt) {
		
		System.out.println(util.twoComplment(registradores[util.TODecimal(rt)].getConteudo()));
		
		int rtB = Integer.parseInt(util.twoComplment(registradores[util.TODecimal(rt)].getConteudo()));
		int rsB = Integer.parseInt(util.twoComplment(registradores[util.TODecimal(rs)].getConteudo()));

		String r = Integer.toBinaryString(rtB | rsB);

		registradores[util.TODecimal(rd)].setConteudo(util.completacomZero(r));
		System.out.println(toString());

	}

	public void ori(String rt, String rs, String imm) {
		int rsB = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());
		int immB = util.TODecimal(imm);

		String r = Integer.toBinaryString(rsB | immB);

		registradores[util.TODecimal(rt)].setConteudo(util.completacomZero(r));
		System.out.println(toString());

	}

	public void nor(String rd, String rs, String rt) {
		int rtB = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());
		int rsB = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());

		String r = Integer.toBinaryString(~(rtB | rsB));

		registradores[util.TODecimal(rd)].setConteudo(util.completacomZero(r));
		System.out.println(toString());

	}

	public void mult(String rs, String rt) {

		String rsB = util.twoComplment(registradores[util.TODecimal(rs)].getConteudo());
		String rtB = util.twoComplment(registradores[util.TODecimal(rt)].getConteudo());

		long a = Long.parseLong(rsB);
		long b = Long.parseLong(rtB);

		long r = a * b;

		String rS = util.completacomZero64(Long.toBinaryString(r));

		this.hi.setConteudo(rS.substring(0, 32));
		this.lo.setConteudo(rS.substring(32, 63));

		System.out.println("hi: " + hi.getConteudo() + "\nlo: " + lo.getConteudo());

	}

	public void multu(String rs, String rt) {

		long rsB = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());
		long rtB = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());

		long r = rsB * rtB;

		String rS = util.completacomZero64(Long.toBinaryString(r));

		this.hi.setConteudo(rS.substring(0, 32));
		this.lo.setConteudo(rS.substring(32, 63));

		System.out.println("hi: " + hi.getConteudo() + "\nlo: " + lo.getConteudo());

	}

	public void div(String rs, String rt) {

		String rsB = util.twoComplment(registradores[util.TODecimal(rs)].getConteudo());
		String rtB = util.twoComplment(registradores[util.TODecimal(rt)].getConteudo());

		int a = Integer.parseInt(rsB);
		int b = Integer.parseInt(rtB);

		if (b > 0) {
			int r = a / b;
			int resto = a % b;

			String rS = util.completacomZero(Integer.toBinaryString(r));
			String restoS = util.completacomZero(Integer.toBinaryString(resto));

			this.hi.setConteudo(restoS);
			this.lo.setConteudo(rS);
		} else {
			this.hi.setConteudo(util.completacomZero("0"));
			this.lo.setConteudo(util.completacomZero("0"));
		}

		System.out.println("hi: " + hi.getConteudo() + "\nlo: " + lo.getConteudo());

	}

	public void divu(String rs, String rt) {
		int rsB = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());
		int rtB = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());

		if (rtB > 0) {
			int r = rsB / rtB;
			int resto = rsB % rtB;

			String rS = util.completacomZero(Integer.toBinaryString(r));
			String restoS = util.completacomZero(Integer.toBinaryString(resto));

			this.hi.setConteudo(restoS);
			this.lo.setConteudo(rS);
		} else {
			this.hi.setConteudo(util.completacomZero("0"));
			this.lo.setConteudo(util.completacomZero("0"));
		}

		System.out.println("hi: " + hi.getConteudo() + "\nlo: " + lo.getConteudo());
	}

	public void xor(String rd, String rs, String rt) {
		int rtB = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());
		int rsB = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());

		String r = Integer.toBinaryString(rtB ^ rsB);

		registradores[util.TODecimal(rd)].setConteudo(util.completacomZero(r));
		System.out.println(toString());

	}

	public void xori(String rt, String rs, String imm) {
		int rsB = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());
		int immB = util.TODecimal(imm);

		String r = Integer.toBinaryString(rsB ^ immB);

		registradores[util.TODecimal(rt)].setConteudo(util.completacomZero(r));
		System.out.println(toString());

	}

	public void bltz(String rs, String imm) {
		String conteudo = registradores[util.TODecimal(rs)].getConteudo();
		String conteudoDD = util.twoComplment(conteudo);
		int conteudoD = Integer.parseInt(conteudoDD);
		if (conteudoD < 0) {
			int end = memoria.getEnderecoAtual();
			end = end + branchAddr(imm);
			memoria.setEnderecoAtual(end);
		}
	}

	public void beq(String rs, String rt, String imm) {
		String conteudo = registradores[util.TODecimal(rs)].getConteudo();
		String conteudoA = registradores[util.TODecimal(rt)].getConteudo();

		if (conteudo.equals(conteudoA)) {
			int end = memoria.getEnderecoAtual();
			end = end + branchAddr(imm);
			memoria.setEnderecoAtual(end);
		}

	}

	public void bne(String rs, String rt, String imm) {
		String conteudo = registradores[util.TODecimal(rs)].getConteudo();

		String conteudoA = registradores[util.TODecimal(rt)].getConteudo();

		if (!conteudo.equals(conteudoA)) {
			int end = memoria.getEnderecoAtual();
			end = end + branchAddr(imm);
			memoria.setEnderecoAtual(end);
		}
	}

	public void j(String imm) {

		String s = jumpAddr(imm);
		int conteudoD = Integer.parseInt(util.twoComplment(s));
		conteudoD = conteudoD - 4;
		memoria.setEnderecoAtual(conteudoD);
	}

	public void jr(String rs) {
		String conteudo = registradores[util.TODecimal(rs)].getConteudo();
		int conteudoD = Integer.parseInt(util.twoComplment(conteudo));
		conteudoD = conteudoD - 4;
		memoria.setEnderecoAtual(conteudoD);
	}

	public String jumpAddr(String v) {

		int nextEnd = memoria.getEnderecoAtual();

		String nexEnd = util.completacomZero(Integer.toBinaryString(nextEnd));
		String k = nexEnd.substring(0, 4);

		String t = k.concat(v).concat("00");

		return t;
	}

	public void jal(String imm) {

		int endereco = ControladorMemoria.getIntance().getEnderecoAtual();
		endereco = endereco + 4;

		String s = jumpAddr(imm);
		int conteudoD = Integer.parseInt(util.twoComplment(s));
		conteudoD = conteudoD - 4;

		memoria.setEnderecoAtual(conteudoD);

		String r = util.completacomZero(Integer.toBinaryString(endereco));

		registradores[31].setConteudo(r);

	}

	public void lb(String rt, String imm, String rs) {

		String contIMM = util.twoComplment(imm);
		String conteudoRs = registradores[util.TODecimal(rs)].getConteudo();
		int conteudoRS = Integer.parseInt(util.twoComplment(conteudoRs));
		int conteudoIMM = Integer.parseInt(contIMM);
		
		int soma = conteudoIMM + conteudoRS;
		String r = Integer.toString(soma);

		verificaENDERECO(r);
		String data = MemoriaDados.getInstance().LerMemoria(r);

		String corteDBINa = data.substring(0, 8);
		String corteDBINb = data.substring(8, 16);
		String corteDBINc = data.substring(16, 24);
		String corteDBINd = data.substring(24);

		int valorCorte;
		String BINvalor;

		String dataINSERT;

		switch (this.TObyte) {
		case 0:
			valorCorte = Integer.parseInt(util.twoComplment(corteDBINd));
			BINvalor = Integer.toBinaryString(valorCorte);
			BINvalor = util.completacomZero(BINvalor);
			registradores[util.TODecimal(rt)].setConteudo(BINvalor);
			break;
		case 1:
			valorCorte = Integer.parseInt(util.twoComplment(corteDBINc));
			BINvalor = Integer.toBinaryString(valorCorte);
			BINvalor = util.completacomZero(BINvalor);
			registradores[util.TODecimal(rt)].setConteudo(BINvalor);
			break;
		case 2:
			valorCorte = Integer.parseInt(util.twoComplment(corteDBINb));
			BINvalor = Integer.toBinaryString(valorCorte);
			BINvalor = util.completacomZero(BINvalor);
			registradores[util.TODecimal(rt)].setConteudo(BINvalor);
			break;
		case 3:
			valorCorte = Integer.parseInt(util.twoComplment(corteDBINa));
			BINvalor = Integer.toBinaryString(valorCorte);
			BINvalor = util.completacomZero(BINvalor);
			registradores[util.TODecimal(rt)].setConteudo(BINvalor);
			break;
		}

	}

	public void lbu(String rt, String imm, String rs) {

		String contIMM = util.twoComplment(imm);
		String conteudoRs = registradores[util.TODecimal(rs)].getConteudo();
		int conteudoRS = Integer.parseInt(util.twoComplment(conteudoRs));
		int conteudoIMM = Integer.parseInt(contIMM);
		
		int soma = conteudoIMM + conteudoRS;
		String r = Integer.toString(soma);

		verificaENDERECO(r);
		String data = MemoriaDados.getInstance().LerMemoria(r);

		String corteDBINa = data.substring(0, 8);
		String corteDBINb = data.substring(8, 16);
		String corteDBINc = data.substring(16, 24);
		String corteDBINd = data.substring(24);

		int valorCorte;
		String BINvalor;

		String dataINSERT;

		switch (this.TObyte) {
		case 0:
			valorCorte = util.TODecimal(corteDBINd);
			BINvalor = Integer.toBinaryString(valorCorte);
			BINvalor = util.completacomZero(BINvalor);
			registradores[util.TODecimal(rt)].setConteudo(BINvalor);
			break;
		case 1:
			valorCorte = util.TODecimal(corteDBINc);
			BINvalor = Integer.toBinaryString(valorCorte);
			BINvalor = util.completacomZero(BINvalor);
			registradores[util.TODecimal(rt)].setConteudo(BINvalor);
			break;
		case 2:
			valorCorte = util.TODecimal(corteDBINb);
			BINvalor = Integer.toBinaryString(valorCorte);
			BINvalor = util.completacomZero(BINvalor);
			registradores[util.TODecimal(rt)].setConteudo(BINvalor);
			break;
		case 3:
			valorCorte = util.TODecimal(corteDBINa);
			BINvalor = Integer.toBinaryString(valorCorte);
			BINvalor = util.completacomZero(BINvalor);
			registradores[util.TODecimal(rt)].setConteudo(BINvalor);
			break;
		}
	}

	public void lui(String rt, String imm) {

		String zeros = "0000000000000000";

		String r = imm.concat(zeros);
		System.out.println(r);
		registradores[util.TODecimal(rt)].setConteudo(r);

	}

	public void lw(String rt, String imm, String rs) {

		String conteudoRt = registradores[util.TODecimal(rt)].getConteudo();

		String contIMM = util.twoComplment(imm);
		String conteudoRs = registradores[util.TODecimal(rs)].getConteudo();
		int conteudoRS = Integer.parseInt(util.twoComplment(conteudoRs));
		int conteudoIMM = Integer.parseInt(contIMM);

		int soma = conteudoIMM + conteudoRS;
		String r = Integer.toString(soma);

		verificaENDERECO(r);
		String dadoLIDO = MemoriaDados.getInstance().LerMemoria(r);
		
		String corte1 = dadoLIDO.substring(0, 24);
		String corte2 = dadoLIDO.substring(0, 16);
		String corte3 = dadoLIDO.substring(0, 8);
		
		int valorC1 = Integer.parseInt(util.twoComplment(corte1));
		int valorC2 = Integer.parseInt(util.twoComplment(corte2));
		int valorC3 = Integer.parseInt(util.twoComplment(corte3));
		
		String dataINSERT;

		switch (this.TObyte) {
		case 0:
			registradores[util.TODecimal(rt)].setConteudo(dadoLIDO);
			break;
		case 1:
			dataINSERT = util.completacomZero(Integer.toBinaryString(valorC1));
			registradores[util.TODecimal(rt)].setConteudo(dataINSERT);
			break;
		case 2:
			dataINSERT = util.completacomZero(Integer.toBinaryString(valorC2));
			registradores[util.TODecimal(rt)].setConteudo(dataINSERT);
			break;
		case 3:
			dataINSERT = util.completacomZero(Integer.toBinaryString(valorC3));
			registradores[util.TODecimal(rt)].setConteudo(dataINSERT);
			break;
		}

	}

	public void sb(String rt, String imm, String rs) {

		String contIMM = util.twoComplment(imm);
		String conteudoRs = registradores[util.TODecimal(rs)].getConteudo();
		int conteudoRS = Integer.parseInt(util.twoComplment(conteudoRs));
		int conteudoIMM = Integer.parseInt(contIMM);

		int soma = conteudoIMM + conteudoRS;
		String r = Integer.toString(soma);

		String data = registradores[util.TODecimal(rt)].getConteudo();
		int valor = Integer.parseInt(util.twoComplment(data));

		MemoriaDados.getInstance().InserirSB(r, valor);

	}

	public void sw(String rt, String imm, String rs) {

		String contIMM = util.twoComplment(imm);
		String conteudoRs = registradores[util.TODecimal(rs)].getConteudo();
		int conteudoRS = Integer.parseInt(util.twoComplment(conteudoRs));
		int conteudoIMM = Integer.parseInt(contIMM);

		int soma = conteudoIMM + conteudoRS;
		String r = Integer.toString(soma);

		String data = registradores[util.TODecimal(rt)].getConteudo();
		int valor = Integer.parseInt(util.twoComplment(data));
		
		MemoriaDados.getInstance().InserirSW(r, valor);

	}

	public void sll(String rd, String rt, String sh) {
		int shiftAmount = Integer.valueOf(util.toDecimalString(sh));

		int conteudoRT = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());

		int resultado = conteudoRT << shiftAmount;

		registradores[util.TODecimal(rd)].setConteudo(util.completacomZero(Integer.toBinaryString(resultado)));
	}

	public void sllv(String rd, String rt, String rs) {
		int shiftAmount = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());

		int conteudoRT = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());

		int resultado = conteudoRT << shiftAmount;

		registradores[util.TODecimal(rd)].setConteudo(util.completacomZero(Integer.toBinaryString(resultado)));
	}

	public void sra(String rd, String rt, String sh) {
		int shiftAmount = Integer.valueOf(util.toDecimalString(sh));

		int bitSinal = Integer.valueOf(registradores[util.TODecimal(rt)].getConteudo().substring(0, 1));

		int contador = 0;
		String resultado = registradores[util.TODecimal(rt)].getConteudo();

		while (contador < shiftAmount) {
			resultado = resultado.substring(0, 31);
			resultado = bitSinal + resultado;
			contador++;
		}

		registradores[util.TODecimal(rd)].setConteudo(resultado);
	}

	public void srav(String rd, String rt, String rs) {
		int shiftAmount = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());

		int bitSinal = Integer.valueOf(registradores[util.TODecimal(rt)].getConteudo().substring(0, 1));

		int contador = 0;
		String resultado = registradores[util.TODecimal(rt)].getConteudo();

		while (contador < shiftAmount) {
			resultado = resultado.substring(0, 31);
			resultado = bitSinal + resultado;
			contador++;
		}

		registradores[util.TODecimal(rd)].setConteudo(resultado);
	}

	public void srl(String rd, String rt, String sh) {
		int shiftAmount = Integer.valueOf(util.toDecimalString(sh));

		int conteudoRT = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());

		int resultado = conteudoRT >>> shiftAmount;

		registradores[util.TODecimal(rd)].setConteudo(util.completacomZero(Integer.toBinaryString(resultado)));
	}

	public void srlv(String rd, String rt, String rs) {
		int shiftAmount = util.TODecimal(registradores[util.TODecimal(rs)].getConteudo());
		int conteudoRT = util.TODecimal(registradores[util.TODecimal(rt)].getConteudo());
		int resultado = conteudoRT >>> shiftAmount;
		registradores[util.TODecimal(rd)].setConteudo(util.completacomZero(Integer.toBinaryString(resultado)));
	}

	public String toString() {
		String conteudo;

		for (int i = 0; i < registradores.length; i++) {

			conteudo = util.twoComplment(registradores[i].getConteudo());

			Aux[i] = new Registrador("$" + i, conteudo);

		}

		return Arrays.toString(Aux);
	}

	public String toStringHILO() {

		String conteudoHI, conteudoLO;

		conteudoHI = util.twoComplment(hi.getConteudo());
		conteudoLO = util.twoComplment(lo.getConteudo());

		Registrador Hi1 = new Registrador("hi", conteudoHI);
		Registrador Lo1 = new Registrador("lo", conteudoLO);

		return (Hi1.toString() + ";" + "\r\n" + Lo1.toString() + ";");
	}

}
