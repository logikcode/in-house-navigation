package com.navigation.mobilestationservice.config;

import com.navigation.mobilestationservice.util.ReadJsonFileToObject;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@OpenAPIDefinition
@Configuration
public class SpringDocConfiguration {

   private ReadJsonFileToObject readJsonFileToObject = new ReadJsonFileToObject();
    @Bean()
    public OpenAPI mobileOpenApiConfig(){

        Content content = new Content();
        io.swagger.v3.oas.models.media.MediaType mediaType = new io.swagger.v3.oas.models.media.MediaType();

        mediaType.addExamples("Default", new Example().value(readJsonFileToObject.read().get("Bad Request").toString()));
        content.addMediaType(MediaType.APPLICATION_JSON.toString(), mediaType);

        ApiResponse badRequestAPI = new ApiResponse().content(content);


        Content forInternalServerError = new Content();
        io.swagger.v3.oas.models.media.MediaType mediaTypeInternalServer = new io.swagger.v3.oas.models.media.MediaType();

        mediaTypeInternalServer.addExamples("Default", new Example().value(readJsonFileToObject.read().get("Internal Server").toString()));
        forInternalServerError.addMediaType(MediaType.APPLICATION_JSON.toString(), mediaTypeInternalServer);

        ApiResponse internalServerResponse = new ApiResponse().content(forInternalServerError);


        Content successApiContent = new Content();
        io.swagger.v3.oas.models.media.MediaType successApiMediaType = new io.swagger.v3.oas.models.media.MediaType();

        successApiMediaType.addExamples("Default", new Example().value(readJsonFileToObject.read().get("Success").toString()));
        successApiContent.addMediaType(MediaType.APPLICATION_JSON.toString(), successApiMediaType);

        ApiResponse successApiResponse = new ApiResponse().content(successApiContent);

        Components components = new Components();
        components.addResponses("Success", successApiResponse);
        components.addResponses("Bad Request", badRequestAPI);
        components.addResponses("Internal Server", internalServerResponse);

        Info info = new Info();
        info.title("Base Station: In-House-Navigation");
        info.version("1.0.0");
        info.description("Docs for In-House-Navigation");

        OpenAPI openAPI = new OpenAPI().info(info);
        openAPI.components(components);
        return openAPI;
    }
}
