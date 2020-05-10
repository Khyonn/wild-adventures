package com.wildadventures.msgateway;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SleuthConfig {
    public Sampler defautlSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
