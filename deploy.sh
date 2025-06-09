#!/bin/bash

set -e

echo "🧹 Step 1: Cleaning and building the project with Maven..."
mvn clean package -DskipTests

echo "🛠 Step 2️ Setting kubectl environment for Minikube..."
eval $(minikube -p minikube docker-env)

echo "🐳 Step 3: Building Docker image..."
docker build -t order-service .

echo "🚀 Step 4: Applying Kubernetes deployment..."
kubectl apply -f ../k8-configs/order-service/

echo "🔄 Step 5: Restarting the Kubernetes deployment to use latest image..."
kubectl rollout restart deployment order-service -n default

echo "⏳ Step 6: Waiting for rollout to complete..."
kubectl rollout status deployment order-service -n default

echo "🌐 Step 7: Port forwarding"
kubectl port-forward service/order-service 8082:8082 -n default