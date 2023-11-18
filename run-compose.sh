#!/bin/bash

# Para os serviços em execução
docker-compose stop

# Remove os contêineres para os serviços definidos no Compose
docker-compose rm -f

# Puxa as imagens mais recentes definidas no Compose
docker-compose --verbose pull

# Inicia os serviços em background
docker-compose --verbose up -d