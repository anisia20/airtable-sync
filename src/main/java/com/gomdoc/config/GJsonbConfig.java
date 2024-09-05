package com.gomdoc.config;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;

public class GJsonbConfig {
	
	// Replaces the CDI producer for JsonbConfig built into Quarkus
    @Dependent
    public JsonbConfig jsonConfig() {
        JsonbConfig config = new JsonbConfig(); // Custom `JsonbConfig`

        config.withPropertyNamingStrategy(PropertyNamingStrategy.IDENTITY)
        .withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL)
        .withStrictIJSON(true)
        .withFormatting(true)
        .withNullValues(true);
        
        return config;
    }
    
    @Singleton
    public Jsonb createJsonb() {
    	return JsonbBuilder.create(jsonConfig());
    }

}
