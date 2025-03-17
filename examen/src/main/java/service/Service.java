package service;

import domain.AdoptionCentre;
import domain.Animal;
import domain.Type;
import repository.AdoptionCentreRepository;
import repository.AnimalRepository;

import java.util.ArrayList;
import java.util.List;


public class Service {
    private final AdoptionCentreRepository adoptionCentreRepository;
    private final AnimalRepository animalRepository;

    public Service() {
        this.adoptionCentreRepository = new AdoptionCentreRepository();
        this.animalRepository = new AnimalRepository();
    }

    public AdoptionCentreRepository getAdoptionCentreRepository() {
        return adoptionCentreRepository;
    }
    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }

    public Iterable<Animal> getAnimalByCentreId(int centreId) {
        return animalRepository.findByCentreId(centreId);
    }
    public Iterable<Animal> getAnimalByTypeAndCentreId(Type type, int centreId) {
        return animalRepository.findAnimalByTypeAndCentreId(type,centreId);
    }

    public Iterable<AdoptionCentre> getCentreByLocation(String location) {
        return adoptionCentreRepository.findCentreByLocation(location);
    }

    public Float getOccupancy(Integer centreId) {
        Iterable<Animal> animals = getAnimalByCentreId(centreId);
        int nrAnimale = 0;
        for (Animal animal : animals) {
            nrAnimale++;
        }
        AdoptionCentre centre = adoptionCentreRepository.findOne(centreId);
        int capacity = centre.getCapacity();
        if (capacity == 0) {
            return 0f;
        }
        return (float) nrAnimale / capacity * 100;
    }


}
