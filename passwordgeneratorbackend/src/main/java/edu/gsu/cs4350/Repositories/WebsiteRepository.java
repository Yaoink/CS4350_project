package edu.gsu.cs4350.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.gsu.cs4350.Models.Website;

public interface WebsiteRepository extends JpaRepository<Website, String> {
    Website findByUrl(String url);
}