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
        if ((code.equals("FWD")) || (code.equals("PEN")) || (code.equals("ROT")) || (code.equals("REP")) || (code.equals("END"))){
            switch (code) {
                case "FWD":
                    if ((param < -1000) || (param > 1000)) {
                        return 1;
                    }
                    break;
                case "PEN":
                    if ((param != 1) && (param != 0)) {
                        return 2;
                    }
                    break;
                case "ROT":
                    if ((param <= -360) || (param >= 360)) {
                        return 3;
                    }
                    break;
                case "REP":
                    if ((param <= 0) || (param >= 1000)) {
                        return 4;
                    }
                    break;
            }
            return 0;
        }else{
            return -1;
        }
    }
    public boolean isCorrect(){
        return errorCode() == 0;
    }
    public String info(){
        return code + " " + param;
    }
}
