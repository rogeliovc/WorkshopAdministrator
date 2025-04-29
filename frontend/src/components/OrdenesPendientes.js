import React, { useEffect, useState } from 'react';
import { getOrdenes } from '../api/ordenes';
import { Table, TableBody, TableCell, TableHead, TableRow, Paper, Typography, Box } from '@mui/material';

const OrdenesPendientes = () => {
  const [ordenes, setOrdenes] = useState([]);

  useEffect(() => {
    getOrdenes().then(res => {
      // Filtrar órdenes en estado PENDIENTE o EN_PROCESO
      const pendientes = res.data.filter(o => o.estado === 'PENDIENTE' || o.estado === 'EN_PROCESO');
      setOrdenes(pendientes);
    });
  }, []);

  return (
    <Box mt={4}>
      <Typography variant="h6" gutterBottom>Órdenes Pendientes / En Proceso</Typography>
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

export default OrdenesPendientes;
