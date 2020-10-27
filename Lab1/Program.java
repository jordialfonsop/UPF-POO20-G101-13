package com.company;

import java.util.LinkedList;

public class Program {
    private int currentLine;
    private LinkedList<Instruction> instructions;
    private int loopIteration;
    private String programName;

    public Program(String name){
        programName = name;
        currentLine = 0;
        loopIteration = 1;
        instructions = new LinkedList<Instruction>();
    }
    public String getName(){
        return programName;
    }
    private void goToStartLoop(){
        currentLine = 0;
        Instruction i = instructions.get(currentLine);
        while (!i.isRepInstruction()){
            i=getNextInstruction();
        }
    }
    public Instruction getNextInstruction(){
        currentLine++;
        loopIteration++;
        return null;
    } //NO ACABAT
    public boolean addInstruction(String c, double p){
        Instruction i = new Instruction(c,p);
        instructions.add(i);
        return i.isCorrect();
    }
    public boolean hasFinished(){
        return ((instructions.get(currentLine).getCode().equals("END") && (instructions.size() == loopIteration)));
    }
    public void restart(){
        currentLine = 0;
        loopIteration = 1;
    }
    public boolean isCorrect(){
        while (!hasFinished()){
            if(!instructions.get(currentLine).isCorrect()){
                printErrors();
                return false;
            }
            getNextInstruction();
        }
        if (!instructions.getLast().getCode().equals("END")){
            System.out.println("Last instruction is not END");
            return false;
        }
        return true;
    }
    public void printErrors(){
        switch(instructions.get(currentLine).errorCode()){
            case 1:
                System.out.println("FWD instruction parameter in line " + currentLine + " is out of boundaries" );
            case 2:
                System.out.println("PEN instruction parameter in line " + currentLine + " is neither 0 or 1" );
            case 3:
                System.out.println("ROT instruction parameter in line " + currentLine + " is out of boundaries" );
            case 4:
                System.out.println("REP instruction parameter in line " + currentLine + " is out of boundaries" );
        }
    }
}