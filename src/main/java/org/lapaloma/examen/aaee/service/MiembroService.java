package org.lapaloma.examen.aaee.service;

import java.util.List;
import org.lapaloma.examen.aaee.dao.IMiembroDAO;
import org.lapaloma.examen.aaee.excepcion.MiembroNoEncontradoException;
import org.lapaloma.examen.aaee.vo.Miembro;
import org.springframework.stereotype.Service;

@Service
public class MiembroService {

    private final IMiembroDAO miembroDAO;

    // Inyección por constructor (Recomendado por Spring)
    public MiembroService(IMiembroDAO miembroDAO) {
        this.miembroDAO = miembroDAO;
    }

    /**
     * Obtiene la lista completa de miembros
     * 
     * @return List<Miembro> lista de todos los miembros
     * @throws RuntimeException si la lista está vacía o es nula
     */
    public List<Miembro> obtenerListaMiembros() {
        // Obtenemos los datos reales del DAO
        List<Miembro> lista = miembroDAO.obtenerListaMiembros();
        
        // Validamos si el resultado es nulo o vacío
        if (lista == null || lista.isEmpty()) {
            throw new RuntimeException("No hay miembros disponibles");
        }

        return lista;
    }

    /**
     * Obtiene un miembro por su nombre
     * 
     * @param nombre el nombre del miembro a buscar
     * @return Miembro el miembro encontrado
     * @throws MiembroNoEncontradoException si no se encuentra el miembro
     */
    public Miembro obtenerMiembroPorNombre(String nombre) {
        // Buscamos el miembro real
        Miembro miembro = miembroDAO.obtenerMiembroPorNombre(nombre);
         
        // Validamos si existe
        if (miembro == null) {
            throw new MiembroNoEncontradoException("Miembro con nombre '" + nombre + "' no encontrado");
        }

        return miembro;
    }

    /**
     * Obtiene un miembro por su alias
     * 
     * @param alias el alias del miembro a buscar
     * @return Miembro el miembro encontrado
     * @throws MiembroNoEncontradoException si no se encuentra el miembro
     */
    public Miembro obtenerMiembroPorAlias(String alias) {
        Miembro miembro = miembroDAO.obtenerMiembroPorAlias(alias);

        if (miembro == null) {
            throw new MiembroNoEncontradoException("Miembro con alias '" + alias + "' no encontrado");
        }

        return miembro;
    }
}