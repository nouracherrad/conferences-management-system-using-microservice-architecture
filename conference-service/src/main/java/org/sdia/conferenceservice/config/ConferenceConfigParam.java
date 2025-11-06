package org.sdia.conferenceservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "conference.params")
public record ConferenceConfigParam (int x , int y){
}
