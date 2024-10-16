package edu.gsu.cs4350.Repositories;

public interface WebsiteRepository extends JpaRepository<Website, String> {
    Website findByUrl(String url);
}