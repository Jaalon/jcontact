package org.jaalon.jcontact.repository

import org.jaalon.jcontact.model.Person

interface PersonRepository {
    /**
     * Stores a person
     * @param person the person to store
     * @return the person
     */
    String store(Person person)

    /**
     * Retrieve a person
     * @param personId the person Id
     * @return the person
     * @throws NotFoundException : when the person is not found
     */
    Person retrieve(String personId)

    /**
     * Updates a person
     * @param person the updated version
     * @return the updated person id
     */
    String update(Person person)

    /**
     * Removes a person
     * @param personId the personId
     * @return the removed object
     */
    Person remove(String personId)
}
