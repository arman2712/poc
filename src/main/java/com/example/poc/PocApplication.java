package com.example.poc;

import com.example.poc.model.UserData;
import com.example.poc.repository.PocRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PocApplication {

  private final PocRepository pocRepository;

  public PocApplication(PocRepository pocRepository) {
    this.pocRepository = pocRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(PocApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  void ready() {
    pocRepository.save(new UserData("John", "Doe"));
    pocRepository.save(new UserData("Armando", "Rivas"));
    pocRepository.save(new UserData("John", "Smith"));
    pocRepository.save(new UserData("Foo", "Bar"));
    pocRepository.save(new UserData("John", "Jones"));
  }
}
