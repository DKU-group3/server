package com.example.taggo.importer.util;

import com.example.taggo.importer.request.PlaceImportRequest;
import com.example.taggo.importer.service.DataImportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("import")
@RequiredArgsConstructor
public class JsonDataLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final DataImportService dataImportService;

    @Override
    public void run(String... args) throws Exception {
        File file = new File("src/main/resources/data/sinsa_restaurants_reviews_final_tags_cleaned.json");
        List<PlaceImportRequest> places = Arrays.asList(
                objectMapper.readValue(file, PlaceImportRequest[].class)
        );
        dataImportService.importData(places);
    }
}
