package org.jaalon.jcontact

import org.jaalon.jcontact.model.Person
import org.jaalon.jcontact.repository.InMemoryPersonRepository
import org.jaalon.jcontact.repository.InMemoryRepository
import org.jaalon.jcontact.repository.NotFoundException
import org.jaalon.jcontact.repository.PersonRepository
import org.jaalon.jcontact.repository.Repository
import spock.lang.Specification

class PersonSpec extends Specification {
    Repository repository

    def setup() {
        PersonRepository personRepository = new InMemoryPersonRepository();
        repository = new InMemoryRepository(personRepository: personRepository)
    }

    def "Create a person"() {
        when: "A contact is created"
        Person person = new Person(
                lastname: "Bouquet",
                firstname: "Frédéric",
                dateOfBirth: "2014-02-08",
                address: "somewhere in France"
        )

        PersonRepository personRepository = repository.personRepository
        String personId = personRepository.store(person)

        then: "Its fields are retrieved"
        Person retrievedPerson = personRepository.retrieve(personId)
        retrievedPerson.firstname == "Frédéric"
        retrievedPerson.lastname == "Bouquet"
        retrievedPerson.dateOfBirth == "2014-02-08"
        retrievedPerson.address == "somewhere in France"

        when: "A contact is updated"
        retrievedPerson.address = "somewhere else"

        personId = personRepository.update(retrievedPerson)

        then: "Update is stored"
        personRepository.retrieve(personId).address == "somewhere else"

        when: "A contact is removed"
        Person removedPerson = personRepository.remove(personId)

        then: "it's returned by remove method"
        removedPerson.firstname == "Frédéric"
        removedPerson.lastname == "Bouquet"
        removedPerson.dateOfBirth == "2014-02-08"
        removedPerson.address == "somewhere else"

        when: "An access is tryed"
        personRepository.retrieve(personId)

        then: "It's not found"
        NotFoundException e = thrown(NotFoundException)
        e.message == "Person $personId not found"
    }
}
