package netty.dev;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.*;

/**
 * @author Yale.Wei
 * @date 2018/12/13 15:47
 */
public final class MarshallingCodecFactory {

    public static MyMarshallingDecoder buildMarshallingDecoder(){
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory,configuration);
        return new MyMarshallingDecoder(provider,1024);
    }

    public static MyMarshallingEncoder buildMarshallingEncoder(){
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(factory,configuration);
        return new MyMarshallingEncoder(provider);
    }

}
