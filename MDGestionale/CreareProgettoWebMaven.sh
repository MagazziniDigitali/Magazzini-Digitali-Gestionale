#!/bin/bash

mvn archetype:generate \
    -DgroupId=it.opendigital.teca.gestionale.services \
    -DartifactId=TecaGestionaleServices \
    -DarchetypeArtifactId=maven-archetype-webapp

cd TecaGestionaleServices

mvn eclipse:eclipse -Dwtpversion=2.0
