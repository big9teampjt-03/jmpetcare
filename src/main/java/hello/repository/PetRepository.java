package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

	Pet findByPetname(String petname);
	
}