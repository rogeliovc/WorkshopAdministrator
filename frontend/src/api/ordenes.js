import axios from 'axios';

const API_URL = '/api/ordenes';

export const getOrdenes = () => axios.get(API_URL);
export const getOrdenById = (id) => axios.get(`${API_URL}/${id}`);
export const createOrden = (orden) => axios.post(API_URL, orden);
export const updateOrden = (id, orden) => axios.put(`${API_URL}/${id}`, orden);
export const deleteOrden = (id) => axios.delete(`${API_URL}/${id}`);
