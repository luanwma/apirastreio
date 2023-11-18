#!/bin/bash

docker system prune -af

docker build -t tracking-api .

docker tag tracking-api:latest jeffreyutfpr/tracking-api:latest

docker push jeffreyutfpr/tracking-api:latest