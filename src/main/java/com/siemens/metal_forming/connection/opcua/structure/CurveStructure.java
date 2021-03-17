package com.siemens.metal_forming.connection.opcua.structure;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import java.util.List;

@Getter @NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurveStructure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=3;s=DT_\"PrUni_typeTorqueSpeedCharacteristicValues\"");
    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("ns=3;s=TE_\"PrUni_typeTorqueSpeedCharacteristicValues\"");
    List<Float> torque;
    List<Float> speed;

    public CurveStructure(Float[] torque, Float[] speed){
        this.torque = List.of(torque);
        this.speed = List.of(speed);
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

    public static class Codec extends GenericDataTypeCodec<CurveStructure> {
        @Override
        public Class<CurveStructure> getType() {
            return CurveStructure.class;
        }

        @Override
        public CurveStructure decode(
                SerializationContext context,
                UaDecoder decoder) throws UaSerializationException {

            Float[] torqueArray = decoder.readFloatArray("torque");
            Float[] speedArray = decoder.readFloatArray("speed");

            return new CurveStructure(torqueArray,speedArray);
        }

        @Override
        public void encode(
                SerializationContext context,
                UaEncoder encoder, CurveStructure value) throws UaSerializationException {

            encoder.writeFloatArray("torque", value.torque.toArray(Float[]::new));
            encoder.writeFloatArray("speed", value.speed.toArray(Float[]::new));
        }
    }
}
