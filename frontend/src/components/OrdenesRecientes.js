import React, { useEffect, useState } from 'react';
import { getOrdenes } from '../api/ordenes';
import { Table, TableBody, TableCell, TableHead, TableRow, Paper, Typography, Box } from '@mui/material';

const OrdenesRecientes = () => {
  const [ordenes, setOrdenes] = useState([]);

  useEffect(() => {
    getOrdenes().then(res => {
      // Ordenar por fechaIngreso descendente y tomar las 5 más recientes
      const ordenadas = res.data.sort((a, b) => (b.fechaIngreso > a.fechaIngreso ? 1 : -1)).slice(0, 5);
      setOrdenes(ordenadas);
    });
  }, []);

  return (
    <Box mt={4}>
      <Typography variant="h6" gutterBottom>Órdenes Recientes</Typography>
      <Paper>
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Fecha Ingreso</TableCell>
              <TableCell>Estado</TableCell>
              <TableCell>Costo Total</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {ordenes.map((orden) => (
              <TableRow key={orden.id}>
                <TableCell>{orden.id}</TableCell>
                <TableCell>{orden.fechaIngreso}</TableCell>
                <TableCell>{orden.estado}</TableCell>
                <TableCell>{orden.costoTotal}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
    </Box>
  );
};

export default OrdenesRecientes;
