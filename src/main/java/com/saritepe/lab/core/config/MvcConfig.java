package com.saritepe.lab.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        Path reportUploadDir = Paths.get("./report-images");

        String reportUploadPath = reportUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/report-images/**").addResourceLocations("file:/" + reportUploadPath + "/");
    }
}
