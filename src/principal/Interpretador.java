package principal;

import java.math.BigInteger;

public class Interpretador {

	public static ControladorRegistradores Controler = new ControladorRegistradores();
	
	public Interpretador() {

	}
	
	
	public static String toDecimal(String nom){
				
		String data = Integer.toString(Integer.parseInt(nom, 2), 10);		
		return data;
	}
	
	
	private static String toBin(String parametro){
		
		parametro = parametro.toLowerCase();
		
		String resultado = "";
		for(int i = 0; i< parametro.length();i++){
			switch(parametro.substring(i,i+1)){
				case "0": resultado += "0000";break;
				case "1": resultado += "0001";break;
				case "2": resultado += "0010";break;
				case "3": resultado += "0011";break;
				case "4": resultado += "0100";break;
				case "5": resultado += "0101";break;
				case "6": resultado += "0110";break;
				case "7": resultado += "0111";break;
				case "8": resultado += "1000";break;
				case "9": resultado += "1001";break;
				case "a": resultado += "1010";break;
				case "b": resultado += "1011";break;
				case "c": resultado += "1100";break;
				case "d": resultado += "1101";break;
				case "e": resultado += "1110";break;
				case "f": resultado += "1111";break;
			}
		}
		return resultado;
	}
	
	
	
	
	public static String twoComplment(String parametro){
		if(parametro.charAt(0) == '0'){
			return toDecimal(parametro.substring(1, parametro.length()));
		}
		else
		{
			int position = parametro.substring(1,parametro.length()).lastIndexOf("1");
			if(position == -1){
				
				return toDecimal(parametro.substring(1,parametro.length()));
			}
			else{
				char[] vetor = parametro.substring(1,parametro.length()).toCharArray();
				
				for(int i = 0;i < position;i++){
					
					if(vetor[i] == '1'){
						vetor[i] = '0';
					}
					else
						vetor[i] = '1';
				}
				return "-" + toDecimal(String.valueOf(vetor));
				
			}
			
		}
		
	}
	

	public static  String translateAssembly(String result) {
		
		result = toBin(result.substring(2,result.length()));

		// ---------- separa��o do opCode ---------//

		String opCode = result.substring(0, 6);

		// ----------------------------------------//

		// -------- estrutura��o do tipo r -------//

		String rs, rt, rd, sh, fn,imm;

		// ---------------------------------------//

	
		switch (opCode) {

		case "000000":

			// ADD ADDU AND ADD ADDU AND DIV DIVU JR MFHI MFLO MULT
			// MULTU NOR OR SLL SLLV SLT SRA SRAV SRL SRLV SUB SUBU
			// SYSCALL XOR

			fn = result.substring(26, 32);
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			rd = result.substring(16, 21);
			sh = result.substring(21, 26);

			switch (fn) {

			case "100000":
				// ADD
				System.out.println("\nInstru��o ADD:");
				Controler.add(rd, rs, rt);
				return ("Add " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );

			case "100001":
				// ADDU
				System.out.println("\nInstru��o ADDU:");
				Controler.addu(rd, rs, rt);
				return("Addu " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );


			case "100100":
				// AND
				System.out.println("\nInstru��o AND:");
				Controler.and(rd, rs, rt);
				return ("And " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );
		

			case "001000":
				// JR
				System.out.println("\nInstru��o JR:");

				return ("Jr " +"$"+toDecimal(rs));
				

			case "010000":
				// MFHI
				System.out.println("\nInstru��o MFHI:");
				Controler.mfhi(rd);
				return ("Mfhi " +"$"+toDecimal(rd));
			

			case "010010":
				// MFLO
				System.out.println("\nInstru��o MFLO:");
				Controler.mflo(rd);
				return ("Mflo " +"$"+ toDecimal(rd) );
			

			case "011000":
				// MULT
				System.out.println("\nInstru��o MULT:");
				Controler.mult(rs, rt);
				return ("Mult " +"$"+toDecimal(rs)+ ", $" +toDecimal(rt));
				

			case "011001":
				// MULTU
				System.out.println("\nInstru��o MULTU:");

				return ("Multu " +"$"+toDecimal(rs)+ ", $"+ toDecimal(rt) );
				

			case "100111":
				// NOR
				System.out.println("\nInstru��o NOR:");
				Controler.nor(rd, rs, rt);
				return ("Nor " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );
				

			case "100101":
				// OR
				System.out.println("\nInstru��o OR:");

				return ("Or " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );
				

			case "000000":
				// SLL
				System.out.println("\nInstru��o SLL:");

				return ("Sll " +"$"+toDecimal(rd)+ ", $" +toDecimal(rt)+", $"+ toDecimal(sh) );
			

			case "000100":
				// SLLV
				System.out.println("\nInstru��o SLLV:");

				return ("Sllv " +"$"+toDecimal(rd)+ ", $" +toDecimal(rt)+", $"+ toDecimal(rs) );
				

			case "101010":
				// SLT
				System.out.println("\nInstru��o SLT:");
				Controler.slt(rd, rs, rt);
				return ("Slt " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );
				

			case "000011":
				// SRA
				System.out.println("\nInstru��o SRA:");

				return ("Sra " +"$"+toDecimal(rd)+ ", $" +toDecimal(rt)+", $"+ toDecimal(sh) );
		

			case "000111":
				// SRAV
				System.out.println("\nInstru��o SRAV:");

				return ("Srav " +"$"+toDecimal(rd)+ ", $" +toDecimal(rt)+", $"+ toDecimal(rs) );
				

			case "000010":
				// SRL
				System.out.println("\nInstru��o SRL:");

				return ("Srl " +"$"+toDecimal(rd)+ ", $" +toDecimal(rt)+", $"+ toDecimal(sh) );
				
			case "000110":
				// SRLV
				System.out.println("\nInstru��o SRLV:");

				return ("Srlv " +"$"+toDecimal(rd)+ ", $" +toDecimal(rt)+", $"+ toDecimal(rs) );
				

			case "100010":
				// SUB
				System.out.println("\nInstru��o SUB:");
				Controler.sub(rd, rs, rt);
				return ("Sub " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );
				

			case "100011":
				// SUBU
				System.out.println("\nInstru��o SUBU:");
				Controler.subu(rd, rs, rt);
				return ("Subu " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );
				

			case "001100":
				// SYSCALL
				System.out.println("\nInstru��o SYSCALL:");

				return ("Syscall");
				

			case "100110":
				// XOR
				System.out.println("\nInstru��o XOR:");

				return ("Xor " +"$"+toDecimal(rd)+ ", $" +toDecimal(rs)+", $"+ toDecimal(rt) );
				

			case "011011":
				// DIVU
				System.out.println("\nInstru��o DIVU:");

				return ("Divu "+ "$"+toDecimal(rs)+", $"+ toDecimal(rt) );
				

			case "011010":
				// DIV
				System.out.println("\nInstru��o DIV:");
				Controler.div(rs, rt);
				return("Div " +"$"+toDecimal(rs)+ ", $" +toDecimal(rt) );

			}

			break;

		case "000001":
			// BLTZ
			
			rs = result.substring(6, 11);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o BLTZ:");

			return("Bltz " + "$" + toDecimal(rs) + "," + twoComplment(imm));
			
			

		case "000010":
			// J
			
			imm= result.substring(6,32);
			
			System.out.println("\nInstru��o J:");

			return("J " + "$" + toDecimal(imm));



		case "000011":

			// JAL
			
			imm= result.substring(6,32);
			
			System.out.println("\nInstru��o JAL:");

			return ("Jal " + "$" + toDecimal(imm));

			

		case "000100":

			// BEQ
			
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o BEQ:");

			return("Beq " + "$" + toDecimal(rs) + ", $" + toDecimal(rt) + "," + twoComplment(imm));


		case "000101":

			// BNE
			
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o BNE:");

			return ("Bne " + "$" + toDecimal(rs) + ", $" + toDecimal(rt) + "," + twoComplment(imm));



		case "001000":

			// ADDI
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o ADDI:");
			Controler.addi(rt, rs, imm);
			return("Addi " + "$" + toDecimal(rt) + ", $" + toDecimal(rs) + "," + toDecimal(imm));

		

		case "001001":

			// ADDIU
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o ADDIU:");
			Controler.addiu(rt, rs, imm);
			return ("Addiu " + "$" + toDecimal(rt) + ", $" + toDecimal(rs) + "," + toDecimal(imm));


		case "001010":

			// SLTI
			
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o SLTI:");
			Controler.slti(rt, rs, imm);
			return ("Slti " + "$" + toDecimal(rt) + ", $" + toDecimal(rs) + "," + toDecimal(imm));

		

		case "001100":

			// ANDI
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o ANDI:");

			return ("Andi " + "$" + toDecimal(rt) + ", $" + toDecimal(rs) + "," + toDecimal(imm));

			

		case "001101":

			// ORI
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o ORI:");

			return ("Ori " + "$" + toDecimal(rt) + ", $" + toDecimal(rs) + "," + toDecimal(imm));

			

		case "001110":

			// XORI
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o XORI:");

			return ("Xori " + "$" + toDecimal(rt) + ", $" + toDecimal(rs) + "," + toDecimal(imm));

			

		case "001111":

			// LUI
			
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o LUI:");

			return ("Lui " + "$" + toDecimal(rt) + "," + toDecimal(imm));

		

		case "100000":

			// LB
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o LB:");

			return ("Lb " + "$" + toDecimal(rt) + "," + toDecimal(imm) + "($"+ toDecimal(rs)+ ")");


		case "100011":

			// LW
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o LW:");

			return ("Lw  $" + toDecimal(rt) + "," + toDecimal(imm) + "($" +toDecimal(rs) +")");


		case "100100":

			// LBU
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o LBU:");

			return("lbu  $" + toDecimal(rt) + "," + toDecimal(imm) + "($" +toDecimal(rs) +")");


		case "101000":

			// SB
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o SB:");

			return ("Sb  $" + toDecimal(rt) + "," + toDecimal(imm) + "($" +toDecimal(rs) +")");
			

		case "101011":

			// SW
			rs = result.substring(6, 11);
			rt = result.substring(11, 16);
			imm= result.substring(16,32);
			
			System.out.println("\nInstru��o SW:");

			return ("Sw  $" + toDecimal(rt) + "," + toDecimal(imm) + "($" +toDecimal(rs) +")");

		}
		return "Instru��o n�o reconhecida";


	}

}
