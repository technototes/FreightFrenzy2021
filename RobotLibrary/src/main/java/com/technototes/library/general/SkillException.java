package com.technototes.library.general;

public class SkillException extends Exception {
    private String skill;
    public SkillException(){
        this("This");
    }
    public SkillException(String s){
        super(s+" seems like a skill issue imo.");
    }
}
