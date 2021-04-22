package com.siemens.metal_forming.enumerated;


public enum StopReactionType {
    DO_NOTHING((short)0),
    TOP_POSITION((short)1),
    IMMEDIATE((short)2);

    private final short code;

    StopReactionType(short code){
        this.code = code;
    }

    public short toCode(){
        return code;
    }
}
