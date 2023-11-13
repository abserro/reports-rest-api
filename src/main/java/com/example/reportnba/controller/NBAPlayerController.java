package com.example.reportnba.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "NBAReport")
@RestController
@RequestMapping(path = "/v1/")
public class NBAPlayerController {
}
