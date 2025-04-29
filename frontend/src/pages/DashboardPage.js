import React, { useEffect, useState } from 'react';
import { getOrdenes } from '../api/ordenes';
import { Box, Grid, Card, CardContent, Typography } from '@mui/material';
import AssignmentIcon from '@mui/icons-material/Assignment';
import DirectionsCarIcon from '@mui/icons-material/DirectionsCar';
import PeopleIcon from '@mui/icons-material/People';
import BuildIcon from '@mui/icons-material/Build';
import OrdenesRecientes from '../components/OrdenesRecientes';
import OrdenesPendientes from '../components/OrdenesPendientes';

// Dummy data for now
const vehiculosCount = 8;
const clientesCount = 12;
const serviciosCount = 5;

const DashboardPage = () => {
  const [ordenes, setOrdenes] = useState([]);

  useEffect(() => {
    getOrdenes().then(res => setOrdenes(res.data));
  }, []);

  // Conteo por estado
  const estados = ['PENDIENTE', 'EN_PROCESO', 'TERMINADO', 'ENTREGADO', 'CANCELADO'];
  const conteoEstados = estados.map(estado => ({
    estado,
    count: ordenes.filter(o => o.estado === estado).length
  }));

  return (
    <Box p={3}>
      <Typography variant="h4" gutterBottom>Panel de Control</Typography>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <AssignmentIcon color="primary" fontSize="large" />
              <Typography variant="h6">Órdenes</Typography>
              <Typography variant="h4">{ordenes.length}</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <DirectionsCarIcon color="primary" fontSize="large" />
              <Typography variant="h6">Vehículos</Typography>
              <Typography variant="h4">{vehiculosCount}</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <PeopleIcon color="primary" fontSize="large" />
              <Typography variant="h6">Clientes</Typography>
              <Typography variant="h4">{clientesCount}</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <BuildIcon color="primary" fontSize="large" />
              <Typography variant="h6">Servicios</Typography>
              <Typography variant="h4">{serviciosCount}</Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
      <Box mt={4}>
        <Typography variant="h6" gutterBottom>Órdenes por Estado</Typography>
        <Grid container spacing={2}>
          {conteoEstados.map(({ estado, count }) => (
            <Grid item xs={12} sm={6} md={2} key={estado}>
              <Card>
                <CardContent>
                  <Typography variant="subtitle1">{estado}</Typography>
                  <Typography variant="h5">{count}</Typography>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Box>

      <OrdenesRecientes />
      <OrdenesPendientes />
    </Box>
  );
};

export default DashboardPage;
