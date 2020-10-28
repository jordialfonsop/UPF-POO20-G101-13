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
        loopIteration = 0;
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
        return instructions.get(currentLine);
    }

    public boolean addInstruction(String c, double p){
        Instruction i = new Instruction(c,p);
        instructions.add(i);
        return i.isCorrect();
    }

    public boolean hasFinished(){
        return (currentLine >= instructions.size()-1);
    }

    public void restart(){
        currentLine = 0;
        loopIteration = 0;
        Instruction i = instructions.get(currentLine);
        int size = instructions.size();
        LinkedList<Instruction> temp = new LinkedList<Instruction>();
        while (currentLine < size-1) {
            if (i.isRepInstruction()) {
                loopIteration = (int) i.getParam();
                while (loopIteration > 0) {
                    i = getNextInstruction();
                    while ((!i.isRepInstruction())) {
                        temp.add(i);
                        i = getNextInstruction();

                    }
                    loopIteration--;
                    if (loopIteration > 0) {
                        goToStartLoop();
                    }
                }

            }else{
                temp.add(i);
            }


        }
        currentLine = -1;
        loopIteration = 0;
        instructions = temp;
    }

    public boolean isCorrect(){
        while (!hasFinished()){
            if(!instructions.get(currentLine).isCorrect()){
                printErrors();
                return false;
            }
            getNextInstruction();
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