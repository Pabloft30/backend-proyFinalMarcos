package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.RegistroVenta;
import com.example.avance2_proyfinal.repository.RegistroVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroVentaService {

    @Autowired
    private RegistroVentaRepository registroVentaRepository;

    public List<RegistroVenta> getAllRegistros() {
        return registroVentaRepository.findAll();
    }

    public RegistroVenta getRegistroVentaById(int id) {
        return registroVentaRepository.findById(id).orElse(null);
    }

    public RegistroVenta addRegistroVenta(RegistroVenta registroVenta) {
        return registroVentaRepository.save(registroVenta);
    }

    public RegistroVenta updateRegistroVenta(int id, RegistroVenta registroVenta) {
        RegistroVenta reg = registroVentaRepository.findById(id).orElse(null);
        if (reg != null) {
            // Asigna los nuevos valores desde registroVenta
            reg.setCliente(registroVenta.getCliente());
            reg.setCantidad(registroVenta.getCantidad());
            reg.setFecha(registroVenta.getFecha()); // Asumiendo que fecha no es un campo modificable, puedes omitir esto
            reg.setMetodo_pago(registroVenta.getMetodo_pago());
            reg.setVendedor(registroVenta.getVendedor());
            reg.setProducto(registroVenta.getProducto());
            reg.setTotal(registroVenta.getTotal());
            return registroVentaRepository.save(reg);
        }
        return null;
    }

    public void deleteRegistroVenta(int id) {
        registroVentaRepository.deleteById(id);
    }

}
