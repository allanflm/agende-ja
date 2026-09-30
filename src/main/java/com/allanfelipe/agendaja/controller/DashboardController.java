package com.allanfelipe.agendaja.controller;

import java.time.YearMonth;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allanfelipe.agendaja.dto.DashboardResponseDTO;
import com.allanfelipe.agendaja.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // mes no formato yyyy-MM; se omitido, usa o mês atual
    @GetMapping
    public DashboardResponseDTO resumo(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth mes) {
        return dashboardService.resumoDoMes(mes != null ? mes : YearMonth.now());
    }
}
