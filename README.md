# POC Décisions

On test les décisions, avec:

### Person.java
```
java/org/kie/kogito/examples/demo/Person.java
```
L'objet Person, avec un nom, un age et deux booléens.


### PersonValidationService.drl
```
resources/org/kie/kogito/examples/PersonValidationService.drl
```
Fichier dans lequel on retrouve les règles de décision.


### persons.bpmn2
```
resources/org/kie/kogito/examples/persons.bpmn2
```
Le BPMN.
En recevant une personne, il utilise le drl pour savoir si la personne est adulte et si elle a dépassé l'âge de la retraite.


## Tester

Pour tester, on lance avec cette commande.
```
mvn clean compile spring-boot:run
```

Après ça, http://localhost:8080 est actif, et on retrouve la liste des personnes qu'on va ajouter dans http://localhost:8080/persons

On peux ajouter des personnes depuis un autre terminal avec ces commandes:

Enfant:
```sh
curl -X POST http://localhost:8080/persons -H 'content-type: application/json' -H 'accept: application/json' -d '{"person": {"name":"Alex", "age": 15}}'
```
Adulte:
```sh
curl -X POST http://localhost:8080/persons -H 'content-type: application/json' -H 'accept: application/json' -d '{"person": {"name":"Eulalie", "age": 21}}'
```
Adulte après retraite:
```sh
curl -X POST http://localhost:8080/persons -H 'content-type: application/json' -H 'accept: application/json' -d '{"person": {"name":"Yves", "age": 99}}'
```


