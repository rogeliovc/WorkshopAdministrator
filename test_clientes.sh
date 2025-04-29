#!/bin/bash

API_URL="http://localhost:8080/api/clientes"

echo "=== Crear cliente ==="
CREATE_RESPONSE=$(curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Juan Perez","telefono":"555-1234","email":"juan@example.com"}')
echo "Respuesta: $CREATE_RESPONSE"
CLIENTE_ID=$(echo $CREATE_RESPONSE | grep -oP '"id"\s*:\s*\K\d+')

echo ""
echo "=== Listar clientes ==="
curl -s $API_URL | jq

echo ""
echo "=== Obtener cliente por ID ($CLIENTE_ID) ==="
curl -s $API_URL/$CLIENTE_ID | jq

echo ""
echo "=== Actualizar cliente ==="
curl -s -X PUT $API_URL/$CLIENTE_ID \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Juan Actualizado","telefono":"555-5678","email":"juan2@example.com"}' | jq

echo ""
echo "=== Eliminar cliente ==="
curl -s -X DELETE $API_URL/$CLIENTE_ID

echo ""
echo "=== Listar clientes (después de eliminar) ==="
curl -s $API_URL | jq
