package com.siemens.metal_forming.connection.opcua.structure;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@Getter @NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ToolDataStructure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=3;s=DT_\"PrUni_typeDieDataGeneral\"");
    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("ns=3;s=TE_\"PrUni_typeDieDataGeneral\"");
    Integer toolNumber;
    String toolName;


    public ToolDataStructure(int toolNumber, String toolName){
        this.toolNumber = toolNumber;
        this.toolName = toolName;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        // XML IS NOT SUPPORTED
        return ExpandedNodeId.NULL_VALUE;
    }

    public static class Codec extends GenericDataTypeCodec<ToolDataStructure> {
        @Override
        public Class<ToolDataStructure> getType() {
            return ToolDataStructure.class;
        }

        @Override
        public ToolDataStructure decode(
                SerializationContext context,
                UaDecoder decoder) throws UaSerializationException {

            //The order of those is important!!
            Integer dieNumber = decoder.readInt32("dieNumber");
            String dieName = decoder.readString("dieName");

            return new ToolDataStructure(dieNumber, dieName);
        }

        @Override
        public void encode(
                SerializationContext context,
                UaEncoder encoder, ToolDataStructure value) throws UaSerializationException {

            //The order of those is important!!
            encoder.writeInt32("dieNumber", value.toolNumber);
            encoder.writeString("dieName", value.toolName);
        }
    }
}
