package org.deeplearning4j.ui.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.shade.serde.jackson.ndarray.NDArrayDeSerializer;
import org.nd4j.shade.serde.jackson.ndarray.NDArraySerializer;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author Adam Gibson
 */
@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
    @Override
    public ObjectMapper getContext(Class<?> type) {
        final ObjectMapper result = new ObjectMapper();
        result.registerModule(module());
        return result;
    }

    public static SimpleModule module() {
        SimpleModule module = new SimpleModule("nd4j");
        module.addDeserializer(INDArray.class, new NDArrayDeSerializer());
        module.addSerializer(INDArray.class, new NDArraySerializer());
        return module;
    }
}
