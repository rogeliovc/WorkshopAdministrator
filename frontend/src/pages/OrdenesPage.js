import React, { useEffect, useState } from 'react';
import { getOrdenes, createOrden, updateOrden, deleteOrden } from '../api/ordenes';
import {
  Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, Typography,
  Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, IconButton
} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

const OrdenesPage = () => {
  const [ordenes, setOrdenes] = useState([]);
  const [open, setOpen] = useState(false);
  const [editOrden, setEditOrden] = useState(null);

  const fetchOrdenes = async () => {
    const res = await getOrdenes();
    setOrdenes(res.data);
  };

  useEffect(() => {
    fetchOrdenes();
  }, []);

  const handleOpen = (orden = null) => {
    setEditOrden(orden);
    setOpen(true);
  };
  const handleClose = () => {
    setEditOrden(null);
    setOpen(false);
  };

  // Simplified form for demo purposes
  const handleSave = async () => {
    if (editOrden && editOrden.id) {
      await updateOrden(editOrden.id, editOrden);
    } else {
      await createOrden(editOrden || {});
    }
    fetchOrdenes();
    handleClose();
  };

  const handleDelete = async (id) => {
    await deleteOrden(id);
    fetchOrdenes();
  };

  return (
    <Box p={2}>
      <Typography variant="h4" gutterBottom>Órdenes de Trabajo</Typography>
      <Button variant="contained" startIcon={<AddIcon />} onClick={() => handleOpen({})} sx={{ mb: 2 }}>
        Nueva Orden
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Fecha Ingreso</TableCell>
              <TableCell>Estado</TableCell>
              <TableCell>Costo Total</TableCell>
              <TableCell>Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {ordenes.map((orden) => (
              <TableRow key={orden.id}>
                <TableCell>{orden.id}</TableCell>
                <TableCell>{orden.fechaIngreso}</TableCell>
                <TableCell>{orden.estado}</TableCell>
                <TableCell>{orden.costoTotal}</TableCell>
                <TableCell>
                  <IconButton onClick={() => handleOpen(orden)}><EditIcon /></IconButton>
                  <IconButton color="error" onClick={() => handleDelete(orden.id)}><DeleteIcon /></IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>{editOrden && editOrden.id ? 'Editar Orden' : 'Nueva Orden'}</DialogTitle>
        <DialogContent>
          {/* Aquí iría el formulario completo, por ahora solo editable el estado y notas */}
          <Box my={1}>
            <Typography variant="subtitle1">Estado</Typography>
            <input type="text" value={editOrden?.estado || ''} onChange={e => setEditOrden({ ...editOrden, estado: e.target.value })} />
          </Box>
          <Box my={1}>
            <Typography variant="subtitle1">Notas</Typography>
            <input type="text" value={editOrden?.notas || ''} onChange={e => setEditOrden({ ...editOrden, notas: e.target.value })} />
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancelar</Button>
          <Button variant="contained" onClick={handleSave}>Guardar</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default OrdenesPage;
