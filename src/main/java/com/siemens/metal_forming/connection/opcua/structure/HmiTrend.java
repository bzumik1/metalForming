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
public class HmiTrend implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=3;s=DT_\"PrServo_typeRuntimeDriveDiagnosticsProcessValuesTrends\".\"hmiTrend\"");
    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("ns=3;s=TE_\"PrServo_typeRuntimeDriveDiagnosticsProcessValuesTrends\".\"hmiTrend\"");
    List<Float> torque;
    List<Float> speed;

    public HmiTrend(Float[] torque, Float[] speed){
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

    public static class Codec extends GenericDataTypeCodec<HmiTrend> {
        @Override
        public Class<HmiTrend> getType() {
            return HmiTrend.class;
        }

        @Override
        public HmiTrend decode(
                SerializationContext context,
                UaDecoder decoder) throws UaSerializationException {

            Float[] torqueArray = decoder.readFloatArray("motorTorque");
            Float[] speedArray = decoder.readFloatArray("motorSpeed");

            return new HmiTrend(torqueArray,speedArray);
        }

        @Override
        public void encode(
                SerializationContext context,
                UaEncoder encoder, HmiTrend value) throws UaSerializationException {

            encoder.writeFloatArray("motorTorque", value.torque.toArray(Float[]::new));
            encoder.writeFloatArray("motorTorque", value.speed.toArray(Float[]::new));
        }
    }
}
