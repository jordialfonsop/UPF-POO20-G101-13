package com.company;

public class Instruction {
    private String code;
    private double param;

    public Instruction(String c, double p){
        code = c;
        param = p;
    }
    public String getCode(){
        return code;
    }
    public double getParam(){
        return param;
    }
    public boolean isRepInstruction(){
        return (code.equals("REP")) || (code.equals("END"));
    }
    public int errorCode(){
        switch(code){
            case "FWD":
                if ((param < -1000) || (param > 1000)){
                    return 1;
                }
            case "PEN":
                if ((param != 1) && (param != 0)){
                    return 2;
                }
            case "ROT":
                if ((param <= -360) || (param >= 360)){
                    return 3;
                }
            case "REP":
                if ((param <= 0) || (param >= 1000)){
                    return 4;
                }
        }
        return 0;
    }
    public boolean isCorrect(){
        if(isRepInstruction()){
            return errorCode() == 0;
        }else{
            return false;
        }
    }
    public String info(){
        return code + " " + param;
    }

}