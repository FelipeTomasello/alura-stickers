package com.example.alura.linguagensapi;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinguagenRepository extends MongoRepository<Linguagem, String> {
    
}
