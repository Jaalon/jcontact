package org.jaalon.jcontact.repository

import org.jaalon.jcontact.model.Person

class InMemoryPersonRepository implements PersonRepository {
    Map<String, Person> persons = [:]
    int id = 0

    @Override
    String store(Person person) {
        String personId = "$id"
        persons.put(personId, person)
        id ++;
        return personId
    }

    @Override
    Person retrieve(String personId) {
        Person person = persons.get(personId)
        if (person == null) {
            throw new NotFoundException("Person $personId not found")
        }
        return person
    }

    @Override
    String update(Person person) {
        persons[person.id] = person
        return person.id
    }

    @Override
    Person remove(String personId) {
        return persons.remove(personId)
    }
}
