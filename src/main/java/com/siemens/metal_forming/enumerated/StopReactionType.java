package com.siemens.metal_forming.enumerated;


public enum StopReactionType {
    DO_NOTHING((short)1),
    TOP_POSITION((short)2),
    IMMEDIATE((short)3);

    private final short code;

    StopReactionType(short code){
        this.code = code;
    }

    public short toCode(){
        return code;
    }
}
