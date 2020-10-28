package com.company;

public class LogoProgram {

    public static void main(String[] args) {
        Program p = new Program ("Square") ;
        p.addInstruction("REP" , 4 );
        p.addInstruction("FWD",100);
        p.addInstruction("ROT" , 90 );
        p.addInstruction("END", 1 );
        if (p.isCorrect()){
            p.restart() ;
            while (!p.hasFinished()) {
                Instruction instr = p.getNextInstruction();
                System.out.println(instr.info());
            }
        }
    }
}