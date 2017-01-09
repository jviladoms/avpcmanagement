#!/usr/bin/env bash

./gradlew clean
./gradlew build
docker-compose up -d