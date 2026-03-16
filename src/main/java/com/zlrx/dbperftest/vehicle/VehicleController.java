package com.zlrx.dbperftest.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final List<VehicleRepositoryProxy<?>> repositoryList;

    @GetMapping("/{type}/{id}")
    public ResponseEntity<? extends Vehicle> getVehicle(
            @PathVariable VehicleType type,
            @PathVariable Long id
    ) {
        var repo = repositoryList.stream().filter(
                it -> it.supported(type)
        ).findFirst();

        if (repo.isPresent()) {
            var record = repo.get().findItById(id);
            if (record.isEmpty()) {
                ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(record.get());
        }

        return ResponseEntity.notFound().build();
    }


}
