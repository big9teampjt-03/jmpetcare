package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.model.Pet;
import hello.repository.PetRepository;

@Service
@Transactional
public class PetService {

	@Autowired
	private PetRepository pRepository;
	
	
	
	public void insert(Pet pet) {
		pRepository.save(pet);
	}



	public Pet findById(Long petid) {
		Pet pet = pRepository.findById(petid).get();
		return pet;
	}

}